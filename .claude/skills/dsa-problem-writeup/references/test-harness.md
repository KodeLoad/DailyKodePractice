# Compile-and-run pattern

Satisfies the gate in SKILL.md. Put the harness in the scratchpad directory, never
in the repo — it is throwaway and must not be committed.

## Why reflection

Solution classes here are declared `class Solution` (package-private) inside
`package GeeksForGeeks.<Dir>;`. A harness in the default package cannot name the
type or call the method directly, and adding a `main` to `Solution.java` pollutes
a file that gets published in the README. Reflection avoids both.

## Harness

```java
import java.util.*;
import java.lang.reflect.*;

public class Main {
  public static void main(String[] args) throws Exception {
    Class<?> c = Class.forName("GeeksForGeeks.YourSocialNetwork.Solution");
    Constructor<?> ctor = c.getDeclaredConstructor(); ctor.setAccessible(true);
    Object s = ctor.newInstance();
    Method m = c.getDeclaredMethod("socialNetwork", int[].class); m.setAccessible(true);

    int[][] tests = { {1, 2}, {1, 1} };           // official API samples first
    for (int[] t : tests) {
      System.out.println(Arrays.toString(t) + " -> " + m.invoke(s, t));
    }
  }
}
```

Compile from the **repo root** so the package path resolves:

```bash
SP=<scratchpad>
javac -d $SP/out GeeksForGeeks/<Dir>/Solution.java $SP/Main.java && java -cp $SP/out Main
```

Signature notes: `int[][]` is `int[][].class`; a `List<Integer>` parameter is
`List.class`. Wrap array args in `new Object[]{arr}` when the method takes a
single array, so varargs does not spread it.

## Verifying prose claims, not just samples

Official samples confirm correctness. They rarely confirm what you are about to
*claim*. Assert the claims directly:

- **A variant you introduced in the prose** — run both implementations over the
  same inputs and compare outputs for equality, including order. Do not eyeball.
- **An ordering claim** — assert the invariant over every output row rather than
  reading one example.
- **A counting claim** ("a chain gives n(n−1)/2 rows") — compute both sides.
- **Randomized inputs** — generate a few hundred valid inputs under the problem's
  constraints and check invariants. This is what separates a verified claim from
  a plausible one.

Generate inputs that respect the constraints, or failures will be meaningless:

```java
Random rnd = new Random(7);                      // fixed seed: reproducible
int[] a = new int[2 + rnd.nextInt(60)];
for (int i = 0; i < a.length; i++) {
  a[i] = 1 + rnd.nextInt(i + 1);                 // honors "friend < user"
}
```

Report the count — "404 inputs, 0 failures" is evidence; "I verified it" is not.
