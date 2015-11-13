package au.edu.swin.astro.lib;
import java.math.*;
import java.io.*;

/*
####################################################################################
## Polytrope.java
##
## This module is main class that calls the RK4 methods for the Lane-Emden equation.
## Note that the y-axis values are labeled with y and x-axis labeled with t for
## generalized ODEs.
## Copyright (C) 2015  Don Sudduth: 9764909@student.swin.edu.au
##
####################################################################################
*/

public class Polytrope {
	
	public static void main(String[] args) {
		
		// Default runtime arguments
		BigDecimal h = new BigDecimal("0.01");
		String sOutputPath = "";
		double n = 3.0D;
		int tIncrements = 700;
		int totalArrayOutput = 0;
		
		// User entered values
		if (args.length > 0) {
		    try {
		    	// User input number of increments
		        tIncrements = Integer.parseInt(args[0]);
		        // User input increment size
		        h = new BigDecimal(Double.parseDouble(args[1]));		        
		        // User input polytropic index
		        n = Double.parseDouble(args[2]);
		        // User optional output path to save data
		        if (args.length > 3) {
		        	sOutputPath = args[3];
		        }
		        // User optional setting to reduce data output to near this-value (rounding errors may not be exact)
		        if (args.length > 4) {
		        	totalArrayOutput = Integer.parseInt(args[4]);
		        }

		    } catch (Exception e) {
		    	System.out.println("Parameters are total increments, increment value, polytropic index, path to output file");
		        System.err.println(e.toString());
		        System.exit(1);
		    }
		}
		
		// Start the calculations
		System.out.println("Begin data calculation process: Iterations="+tIncrements+" Step Size="+args[1]+" Polytropic Index="+n);
			
		// Setup the ODE starting values of Y', Y and t in the Parameters data array
		Parameters startParams = new Parameters(BigDecimal.ZERO, new BigDecimal("1.0000000000"), new BigDecimal("0.0000000001"));
		
		// Select the equation to use for this test
		LaneEmdenEquation leEq = new LaneEmdenEquation(n);
				
		// Call the RK4 method
		RungeKutta secondOrderOde = new RungeKutta(tIncrements, h, startParams);
		secondOrderOde.runIteration(leEq);
		
		
		//--------------------------------------------------//
		
		
		// Send output data to screen and/or to file
		try {
			boolean bFileOutput = false;
			int skipInterval = 0;
			PrintWriter pw = null;
			
			// Set the output data skip if set
			if (totalArrayOutput > 0) skipInterval = tIncrements/totalArrayOutput;
			
			// Set the write to file flag
			if (sOutputPath.length() > 0) bFileOutput = true;
			
			// Open print to file if indicated
			if (bFileOutput) {
				pw = new PrintWriter(sOutputPath, "UTF-8");
				pw.println("YIntersection=" + secondOrderOde.getYIntersection() + ", " + "YPrime Intersection=" + secondOrderOde.getYPrimeIntersection() + ", " + "Polytropic Index="+ n);
				pw.println("Increment t, Y values, dY/dt values");
			}
				
			// Print out all values. Skipping values is indicated with skipInterval to reduce number of output points
			for (int i = 0; i < tIncrements; i++) {
				if (totalArrayOutput > 0) {
					if ((bFileOutput) && (i%skipInterval == 0)) {
						pw.println((secondOrderOde.getValues(i).t) + "," + (secondOrderOde.getValues(i).y) + "," + (secondOrderOde.getValues(i).yprime));
						System.out.println((secondOrderOde.getValues(i).t) + " " + (secondOrderOde.getValues(i).y) + " " + (secondOrderOde.getValues(i).yprime));
					}
				}
				// Print out all values with no skipping
				else {
					if (bFileOutput) {
						pw.println((secondOrderOde.getValues(i).t) + "," + (secondOrderOde.getValues(i).y) + "," + (secondOrderOde.getValues(i).yprime));
					}
					System.out.println((secondOrderOde.getValues(i).t) + " " + (secondOrderOde.getValues(i).y) + " " + (secondOrderOde.getValues(i).yprime));
				}

			}		
			if (bFileOutput) pw.close();
			
			System.out.println("Y Intersection is: " + secondOrderOde.getYIntersection() + " YPrime Intersection is: " + secondOrderOde.getYPrimeIntersection());

		}
		catch(Exception e) {  
			System.out.println(e.getMessage());
		}
		
	}

}
