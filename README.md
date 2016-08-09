# LFSR:

Java linear-feedback shift register pseudorandom number generator.

To use:

1. Instantiate a generator given a number of bits, bits to tap (in the form of a bitmask), and an optional seed (default is 1).

```
LFSR l1 = new LFSR (8, 0x8E);     // 8-bit, taps: {2,3,4,7},     seed: 1
LFSR l2 = new LFSR (8, 0xAF, 81); // 8-bit, taps: {1,2,3,4,5,7}, seed: 81
```

2. Generate numbers using LFSR.next() or LFSR.next(n) to perform next() n times.

```
int curr = l.current(); // Get current value in LSFR l.
int val1 = l.next ();   // Performs bit shifting, returns current().
int val2 = l.next (7);  // Performs l1.next() 7 times, returns current().
```
