# Functional Programming, What is left? (e-olymp 1964)

The same problem solved in two languages. Java as the imperative one and Haskell as the functional one.

Problem: https://www.eolymp.com/en/problems/1964

The numbers from 1 to n are written in a row. Then these three steps are repeated in this order until only one number is left:

1. remove the numbers in even positions
2. remove the numbers in odd positions
3. remove the numbers in positions divisible by 3

After every step the remaining numbers are numbered again from 1. The program prints the last number.

For example, n = 10:

```
1 2 3 4 5 6 7 8 9 10
1 3 5 7 9 // even positions removed
3 7 // odd positions removed
3 7 // nothing removed
3 // even positions removed
```

## Structure

```
Functional-Programming/
├── README.md
├── .gitignore
├── Java/
│   └── Main.java // imperative, array processing
└── Haskell/
    └── Main.hs // functional, list processing
```

## Requirements

- Java 17 or newer.
- GHC (the Haskell compiler).

You can check what is installed with `java -version`, `javac -version` and `ghc --version`. These work the same in the macOS Terminal and in the Windows Command Prompt or PowerShell.

If java is not installed, download the latest JDK from https://www.oracle.com/java/technologies/downloads/ and run the installer for your system. On macOS it can also be installed with Homebrew: `brew install --cask oracle-jdk`.

If GHC is not installed, the easiest way on both systems is GHCup: https://www.haskell.org/ghcup/. The page shows one command to paste, for macOS into the Terminal and for Windows into PowerShell. On macOS it can also be installed with Homebrew: `brew install ghc`.

After installing, open a new terminal and check the versions again.

## How to run

Both programs wait for n. Type the number and press Enter, and the answer is printed.

### Java

From the root of the repository:

```
cd Java
javac Main.java
java Main
```

Then type `10` and press Enter. This is the same on macOS and Windows.

### Haskell

From the root of the repository, on macOS:

```
cd Haskell
ghc Main.hs
./Main
```

On Windows, `ghc` makes `Main.exe` instead, so the last line is `Main.exe` (in PowerShell `.\Main.exe`).

Then type `10` and press Enter.

It can also be run without compiling, with `runghc Main.hs`. This works on both systems but is slower.

Both should print `3`.

## How it works

### Java (imperative)

The numbers 1..n are put in an `int` array. A `while` loop runs until one number is left, and the variable `step` (0, 1, 2) says which rule is used now. Every step is one `for` loop over the array. The position of `numbers[i]` is `i + 1`, and if the number should stay it is copied to the front of the same array (`numbers[newSize] = numbers[i]`). After the loop, `size` becomes `newSize`, so the array is reused and only its front part is the current row.

### Haskell (functional)

There are no variables or loops, only functions, lists and recursion.

- `rule` gives the check for each step as a lambda, for example step 0 is `\pos -> even pos`.
- `withPositions` pairs every number with its position using `zip [1..] xs`.
- `keep` removes the pairs whose position matches the rule, with `filter`.
- `onlyNumbers` takes the numbers back out of the pairs, with `map`.
- `removeBy` is one whole step, it puts these three together.
- `play` is the recursion. If the list has one number (`[x]`), that is the answer. If not, it calls itself on the shorter list with the next step, `(step + 1) mod 3`.

## Results

Scores on e-olymp:

| Language | Score | Slowest test | Memory |
|---|---|---|---|
| Java (openjdk 1.25) | 100 / 100 | 2157 ms | 419.2 MB |
| Haskell (ghc 8.8) | 97 / 100 | over 5000 ms (time limit exceeded) | 4.5 MB |

The Haskell version passes every test except test 29, where it goes over the 5 second limit. Making a new list on every step is much slower than reusing one array. On the other hand it uses very little memory, because the lists are lazy and are not all kept in memory at once. The Java array takes about 400 MB for n = 10^8, which is close to the 512 MB limit.