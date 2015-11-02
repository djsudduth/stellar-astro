package au.edu.swin.astro.lib;
import java.math.*;
import java.io.*;

public class Polytrope {
	
	public static void main(String[] args) {
		
		// Default runtime arguments
		BigDecimal H = new BigDecimal("0.01");
		int tIncrements = 700;
		double n = 3.0D;
		int skipFlag = 0;
		String sOutputPath = "";
		
		// User entered values
		if (args.length > 0) {
		    try {
		        tIncrements = Integer.parseInt(args[0]);  // Total number of increments
		        double hh = Double.parseDouble(args[1]);
		        H = new BigDecimal(hh);
		        n = Double.parseDouble(args[2]);
		        if (args.length > 3) {
		        	sOutputPath = args[3];
		        }
		        if (args.length > 4) {
		        	skipFlag = Integer.parseInt(args[4]);
		        }

		    } catch (Exception e) {
		    	System.out.println("Parameters are total increments, increment value, polytropic index, path to output file");
		        System.err.println(e.toString());
		        System.exit(1);
		    }
		}
		
		// Setup the ODE starting values of Y', Y and t
		Parameters startParams = new Parameters(BigDecimal.ZERO, new BigDecimal("1.0000000000"), new BigDecimal("0.0000000001"));
		
		// Select the equation to use for this test
		LaneEmdenEquation leEq = new LaneEmdenEquation(n);
				
		// for n=3 6.89685
		RungeKutta secondOrderOde = new RungeKutta(tIncrements, H, startParams);
		secondOrderOde.runIteration(leEq);
		
		
		// Create output data to screen and/or file
		try {
			int skipInterval = tIncrements/1500;
			PrintWriter pw = null;
			
			// Open print to file if indicated
			if (sOutputPath.length() > 0) {
				pw = new PrintWriter(sOutputPath, "UTF-8");
				pw.println("YIntersection=" + secondOrderOde.getYIntersection() + "," + "Polytropic Index="+ n);
			}
				
			// Print out all values. Skipping values is indicated with flag to reduce number of points
			for (int i = 0; i < tIncrements; i++) {
				if (skipFlag > 0) {
					if ((sOutputPath.length() > 0) && (i%skipInterval == 0)) {
						pw.println((secondOrderOde.getValues(i).t) + "," + (secondOrderOde.getValues(i).y) + "," + (secondOrderOde.getValues(i).yprime));
						System.out.println((secondOrderOde.getValues(i).t) + " " + (secondOrderOde.getValues(i).y) + " " + (secondOrderOde.getValues(i).yprime));
					}
				}
				// Print out all values with no skipping
				else {
					if (sOutputPath.length() > 0) {
						pw.println((secondOrderOde.getValues(i).t) + "," + (secondOrderOde.getValues(i).y) + "," + (secondOrderOde.getValues(i).yprime));
					}
					System.out.println((secondOrderOde.getValues(i).t) + " " + (secondOrderOde.getValues(i).y) + " " + (secondOrderOde.getValues(i).yprime));
				}

			}		
			if (sOutputPath.length() > 0) {pw.close();}
			
			System.out.println("Y Intersection is: " + secondOrderOde.getYIntersection() + "YPrime Intersection is: " + secondOrderOde.getYPrimeIntersection());

		}
		catch(Exception e) {  
			System.out.println(e.getMessage());
		}
		
		
			
			
		/*
		 * 
		 * 		// Select the equation to use for this test
		//Equation eulerEq = new Equation();
		
		// Create a Euler test 
		//Euler eulerOde = new Euler(INCREMENTS, H, startParams);
		 * 		// Create a Euler test 
		//DParameters startDParams = new DParameters(0.00000000000D, 1.0000000000D, 0.0000000001D);
		//Euler secondOrderOde = new Euler(INCREMENTS, H, startParams);
		//secondOrderOde.runIteration(leEulerEq);

		//eulerOde.runIteration(eulerEq);
		 BigDecimal h = new BigDecimal("0.01");
		 BigDecimal y = new BigDecimal("3");
		 BigDecimal c = new BigDecimal("10");
		 BigDecimal d = new BigDecimal("400");
		 
		 BigDecimal ytemp =y.pow(5);
		 BigDecimal yptemp = d.divide(c).multiply(new BigDecimal("-2.0"));
		 BigDecimal htemp = yptemp.subtract(ytemp).multiply(h);
		//	pNew.yprime = p.yprime.add(htemp);
 

		// BigDecimal total = BigDecimal.ZERO;
		// BigDecimal zz = d.multiply(h);
		// zz = zz.add(y);
		// BigDecimal pp = zz;
		 System.out.println(h temp);
		 */

	}

}
