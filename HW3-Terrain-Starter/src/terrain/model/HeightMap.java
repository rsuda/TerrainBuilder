// COURSE: CSCI1620
 // TERM: Fall 2018  	
 //
 // NAME: Tyler LaBreck and Robin Suda
 // RESOURCES: I did not use any resources for this lab.
 package terrain.model;  	
 /**
  * This class represents a basic square height map of terrain. 
  * It supports programmatic generation of random terrain based
  * on four pre-determined corner values.  	
  * 
  * @author tlabreck and rsuda	  	
  *  	
  */
 public class HeightMap 	  	
 {
     /**
      * An array used to store HeightMap values.
      */
     private double[][] newMap;
     /**
      * An integer used to store dimensions for the array location.
      */
     private int dim;
     /**
      * A new HeightShifter object.
      */
     private HeightShifter getShiftyInHere;
     /**
      * Creates a new empty HeightMap object that can be configured for terrain generation.
      * @param dimension Specifies the number of rows and columns to be stored in this HeightMap.
      * Since this is a square, only one dimension needs to be specified.
      * @param shifter A valid, pre-configured HeightShifter object that will be used for 
      * computing offsets during the terrain generation algorithm.
      */
     public HeightMap(int dimension, HeightShifter shifter)	
     {
         dim = dimension;
         newMap = new double[dimension][dimension];
         getShiftyInHere = shifter;	
     }
     /**
      * This method sets the fixed a-priori height of the top-left corner for this HeightMap.
      * @param height The height value to use for the corner.
      */
     public void setTopLeft(double height)	
     {
         newMap[0][0] = height;	
     }
     /**
      * This method sets the fixed a-priori height of the top-right corner for this HeightMap.
      * @param height The height value to use for the corner.
      */
     public void setTopRight(double height)	
     {
         newMap[0][dim - 1] = height;	
     } 	
     
     /**
      * This method sets the fixed a-priori height of the bottom-left corner for this HeightMap.
      * @param height The height value to use for the corner.
      */
     public void setBottomLeft(double height)	
     {
         newMap[dim - 1][0] = height;
     }
     /**
      * This method sets the fixed a-priori height of the bottom-right corner for this HeightMap.
      * @param height The height value to use for the corner.
      */
     public void setBottomRight(double height)	
     {
         newMap[dim - 1][dim - 1] = height;	
     }
     /**
      * Provided that all details in this HeightMap are set and amenable to terrain generation,
      * this method will fill in the height map data for this HeightMap according to the 
      * diamond-square algorithm using the predetermined HeightShifter for this HeightMap.
      * The HeightShifter should use an initial shift amplifier of 10 for the first set level 
      * of computations, with subsequently smaller values at each level. See the supplemental 
      * assignment instructions for further details about this algorithm.
      * @return true when a terrain has been successfully generated; false when a terrain cannot 
      * be generated because one of the initial corners has no value yet or the dimensions of 
      * the HeightMap does not support the diamond-square algorithm (i.e., dimension is not a 
      * value in the set(2^n + 1) where n may be any positive integer).
      */
     public boolean generateTerrain()	
     {
         double avg = newMap[0][0] + newMap[0][dim - 1] + newMap[dim - 1][0] + newMap[dim - 1][dim - 1];
         avg = avg / 4;
         int center = newMap.length;
         center = center / 2;
         newMap[center][center] = getShiftyInHere.randShift(avg, 10);
         avg = newMap[0][0] + newMap[0][dim - 1] + newMap[center][center];
         avg = avg / 3;
         newMap[0][center] = getShiftyInHere.randShift(avg, 10);
         avg = newMap[dim - 1][0] + newMap[dim - 1][dim - 1] + newMap[center][center];
         avg = avg / 3;
         newMap[dim - 1][center] = getShiftyInHere.randShift(avg,  10);
         avg = newMap[0][dim - 1] + newMap[0][0] + newMap[center][center];
         avg = avg / 3;
         newMap[center][0] = getShiftyInHere.randShift(avg, 10);
         avg = newMap[0][dim - 1] + newMap[dim - 1][dim - 1] + newMap[center][center];
         avg = avg / 3;
         newMap[center][dim - 1] = getShiftyInHere.randShift(avg, 10);
         return true;  	
     }  	
     /**
      * A helper method for generateTerrain that assists with recursion.  
      * @param level The initial shift amplifier starting at 10 for the first set level
      * and subsequently getting smaller at each following level.
      * @param array The passed in array to fill.
      * @return true when a terrain has been successfully generated; false when a terrain cannot 
      * be generated because one of the initial corners has no value yet or the dimensions of 
      * the HeightMap does not support the diamond-square algorithm (i.e., dimension is not a 
      * value in the set (2^n + 1) where n may be any positive integer).  	
      */  	
     /**
     public boolean generateTerrainHelper(int level, double[][] array)  	
     {
         if (level < 1)	
         {
             return true;	
         }
         else	
         {
             return false;	
         }
         return true;  	
     }
         */ 	  	
       	
     /**
      * Retrieves the global maximum height from this HeightMap.
      * @return The global maximum.  	
      */
     public double getMaxHeight()  	
     {
         double max = newMap[0][0];
         for (int i = 0; i < newMap.length; i++)	
         {
             for (int j = 0; j < newMap.length; j++)
             {
                 if (newMap[i][j] > max)
                 {
                     max = newMap[i][j];
                 }
             }	
         }
         return max;  	
     }  	
     /**
      * Retrieves the global minimum height from this HeightMap.
      * @return The global minimum.  	
      */
     public double getMinHeight()  	
     {
         double min = newMap[0][0];
         for (int i = 0; i < newMap.length; i++)	
         {
             for (int j = 0; j < newMap.length; j++)
             {
                 if (newMap[i][j] < min)
                 {
                     min = newMap[i][j];
                 }
             }	
         }
         return min;  	
     }  	
     /**
      * Retrieves the number of columns in this HeightMap.
      * @return The width.  	
      */
     public int getWidth()  	
     {
         return dim;  	
     }  	
     /**
      * Retrieves the number of rows in this HeightMap.
      * @return The height.  	
      */
     public int getHeight()  	
     {
         return dim;  	
     }  	
     /**
      * Retrieves an individual height value at a specified position within this HeightMap.
      * @param row The row index in the HeightMap.
      * @param col The column index in the HeightMap.
      * @return The current height at the specified position.
      */
     public double getHeightAt(int row, int col)  	
     {
         return newMap[row][col];  	
     }  	
     /**
      * Retrieves a String representation of this HeightMap suitable for use in Dr. Ricks' 3dterrain 
      * visualizer: https://ricks.io/apps/terrain.html. 
      * To comply with the requirements for this visualizer, you need to prepare a string that 
      * looks like this: 
      * width=COL&depth=ROW&heights=A B C D E ...
      * where COL would be the number of columns in this HeightMap and ROW would be the number 
      * of rows in this HeightMap. The values A B C D E (and so on) would be a series of double 
      * values rounded to one decimal place for the individual heights. Row data will be contiguous   	
      * starting from the first row. Subsequent rows in the String will be separated by only one 
      * space (not a newline). Width/Depth/Heights fields are separated by an ampersand character. 
      * For example, if the HeightMap contains these values: 
      * 100.0 220.284 243.494 273.550 300.0
      * 39.992 64.186 192.569 152.502 293.373
      * -39.627 58.693 174.018 240.051 162.35
      * 34.058 120.286 130.522 164.239 79.425
      * 0.0 48.392 142.391 143.816 100.0
      * The returned string would be: width=5&depth=5&heights=100.0 220.3 243.5 273.6 300.0 40.0 64.2 
      * 192.6 152.5 293.4 -39.6 58.7 174.0 240.1 162.4 34.1 120.3 130.5 164.2 79.4 0.0 48.4 142.4 143.8 100.0
      * @return The properly formatted String.  	
      */
     public String toString()  	
     {
         return "width=" + getWidth() + "&" + "depth=" + getHeight() + "&" + "heights=" + getGrid2();  	
     }  	
     /**
      * Retrieves a two-dimensional grid of values for the height map. Values on the same row are 
      * separated by a single space, rows are separated by a single newline. Each individual 
      * value should appear formatted right aligned in a six character wide field with 1 decimal place.
      * @return the formatted grid of values.  	
      */
     public String getGrid()  	
     {
         String temp;
         String grid = "";
         for (int i = 0; i < newMap.length; i++)	
         {
             for (int j = 0; j < newMap.length; j++)
             {
                 temp = String.format("%.1f", newMap[i][j]);
                 grid += temp + " ";
             }
             grid += "\n";	
         }
         return grid;  	
     }  	
     /**
      * Retrieves a two-dimensional grid of values for the height map. Values are 
      * separated by a single space. Each individual value should appear formatted 
      * right aligned in a six character wide field with 1 decimal place.
      * @return the formatted grid of values.  	
      */
     public String getGrid2()  	
     {
         String temp;
         String grid = "";
         for (int i = 0; i < newMap.length; i++)	
         {
             for (int j = 0; j < newMap.length; j++)
             {
                 temp = String.format("%.1f", newMap[i][j]);
                 grid += temp + " ";
             }
         }        
 		return grid;  	
     }  	
 }