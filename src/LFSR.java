/* LFSR:
 * -----
 * Linear-feedback shift register pseudorandom number generator.  To use:
 *
 * 1. Instantiate a generator given a number of bits, bits to tap (in the
 *    form of a bitmask), and an optional seed (defaults to 1).
 *
 *   LFSR l1 = new LFSR (8, 0x8E);     // 8-bit, taps: {2,3,4,7},     seed: 1
 *   LFSR l2 = new LFSR (8, 0xAF, 81); // 8-bit, taps: {1,2,3,4,5,7}, seed: 81
 *
 * 2. Generate numbers using LFSR.next() or LFSR.next(n) to perform next()
 *    n times.
 *
 *   int curr = l.current(); // Get current value in LSFR l.
 *   int val1 = l.next ();   // Performs bit shifting, returns current().
 *   int val2 = l.next (7);  // Performs l1.next() 7 times, returns current().
 */

public class LFSR {
   // Variables provided by constructor.
   public final int bits, mask, seed, bitsMask;

   // Current value, increased when LFSR is incremented.
   private int current, next;

   // Constructor with default seed (1).
   public LFSR (int bits, int mask) throws LFSRException {
      this (bits, mask, 1);
   }

   // Constructor with custom seed.
   public LFSR (int bits, int mask, int seed) throws LFSRException {
      // Set final variables.
      this.bits     = bits;
      this.bitsMask = (0x01 << bits) - 1;
      this.mask     = mask & bitsMask;
      this.seed     = seed & bitsMask;

      // Make sure this is a valid LFSR.
      if (this.seed == 0)
         throw new LFSRException ("Seed of 0 will always produce 0");
      if (this.mask == 0)
         throw new LFSRException ("Mask of 0 will break generator");

      // Set up current and next values.
      this.current = this.seed;
      this.next    = next (this.seed, this.bits, this.bitsMask, this.mask);
   }

   public int current () {
      return this.current;
   }

   public int next () {
      this.current = this.next;
      this.next = next (this.current, this.bits, this.bitsMask, this.mask);
      return this.current;
   }

   public int next (int n) {
      for (int i = 0; i < n; i++)
         next ();
      return this.current;
   }

   static public int next (int in, int bits, int mask) {
      return next (in, bits, (0x01 << bits) - 1, mask);
   }

   static public int next (int in, int bits, int bitsMask, int mask) {
      return (in << 1) & bitsMask | lsb (in, bits, bitsMask, mask);
   }

   static public int lsb (int value, int bits, int bitsMask, int mask) {
      int bit = 0;
      for (int i = 0x01; i < bitsMask; i <<= 1)
         if ((mask & i) != 0 && (value & i) != 0)
            bit ^= 0x01;
      return bit;
   }
}
