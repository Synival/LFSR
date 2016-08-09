/* LFSR Tester:
 * ------------
 * This will print all possible values from all 8-bit LFSR generators with a
 * maximum period of 254. */

public class Application {
   public static void main (String[] args) {
      // Masks we're going to use for LFSR generators.
      // Each of these have periods of 254 (maximum for 8-bit numbers).
      int[] masks = new int[] {
         0x8e, 0x95, 0x96, 0xa6, 0xaf, 0xb1, 0xb2, 0xb4,
         0xb8, 0xc3, 0xc6, 0xd4, 0xe1, 0xe7, 0xf3, 0xfa
      };

      // Instantiate LFSR number generators.
      LFSR[] lfsr = new LFSR[masks.length];
      try {
         for (int i = 0; i < masks.length; i++)
            lfsr[i] = new LFSR (8, masks[i]);
      }
      catch (LFSRException e) {
      }

      // Print a top line that shows our masks.
      System.out.print ("      ");
      for (int i = 0; i < masks.length; i++)
         System.out.printf (" %02X ", masks[i]);
      System.out.println ("\n" +
   "---------------------------------------------------------------------");

      // Print all results from our LFSR.
      for (int j = 0; j <= 254; j++) {
         System.out.printf ("%3d | ", j + 1);
         for (int i = 0; i < lfsr.length; i++) {
            int val = lfsr[i].next ();
            System.out.printf ("%3d ", val);
         }
         System.out.print ("\n");
      }
      System.out.print ("\n");
   }
}
