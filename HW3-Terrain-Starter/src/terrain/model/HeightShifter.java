// COURSE: CSCI1620
// TERM: Fall 2018  	
//
// NAME: Tyler LaBreck and Robin Suda
// RESOURCES: I did not use any resources for this lab.
 package terrain.model;  	
 /**
  * This class provides an advanced random number generator for the purposes of 
  * computing height displacements in a HeightMap.
  * @author tlabreck and rsuda	  	
  *  	
  */
 public class HeightShifter 	  	
 {
     /**
      * Represents the Random value between 0 and 1 that will be shifted.
      */
     private double shiftVal;
 
     /**
      * A floating point value to used to scale random numbers.
      */
     private double peak;
 
     /**
      * Configures a new HeightShifter using a predefined random number generator 
      * and a specified peakiness value.
      * @param generator An externally defined random number generator that will 
      * be used by this HeightShifter.
      * @param peakiness A floating point value to used to scale random numbers.
      */
     public HeightShifter(java.util.Random generator, double peakiness)	
     {
         shiftVal = generator.nextDouble();
         peak = peakiness;
     }
 
     /**
      * This method will shift a given value by a randomly determined amount. Shift 
      * computations should be made using the formula: (L ^ P) * (1 - 2 * R)
      * where L is the level of the computation, P is the peakiness for this HeightShifter, 
      * and R is a randomly generated double value between 0 and 1.
      * @param origVal The value to be randomly shifted up or down.
      * @param level The level of this shift computation.
      * @return The result of shifting origVal per the computation described above.
      */
     public double randShift(double origVal, int level)	
     {
         double orig;
         orig = origVal;
         orig += (Math.pow(level, peak)) * (1 - 2 * shiftVal);
         return orig;	
     }	  	
 }