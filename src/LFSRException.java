/* LFSRExeception:
 * ---------------
 * Simple exception tossed by our LFSR generator. */

public class LFSRException extends Exception {
   public LFSRException (String s) {
      super (s);
   }
}
