package au.edu.swin.astro.lib;
import java.math.*;
import java.io.*;

public class Polytrope {
	
	static int INCREMENTS = 39;
	static BigDecimal H = new BigDecimal("0.085");
	public Parameters resultP;

	
	public static void main(String[] args) {
		
		// Setup the ODE starting values of Y', Y and t
		Parameters startParams = new Parameters(BigDecimal.ZERO, new BigDecimal("1.0000000000"), new BigDecimal("0.0000000001"));
		
		// Select the equation to use for this test
		//Equation eulerEq = new Equation();
		
		// Create a Euler test 
		//Euler eulerOde = new Euler(INCREMENTS, H, startParams);
		//eulerOde.runIteration(eulerEq);
		
		
		// Select the equation to use for this test
		LaneEmdenEquation leEulerEq = new LaneEmdenEquation(5);
		
		// Create a Euler test 
		//DParameters startDParams = new DParameters(0.00000000000D, 1.0000000000D, 0.0000000001D);
		//Euler euler2Ode = new Euler(INCREMENTS, H, startParams);
		//euler2Ode.runIteration(leEulerEq);
		RungeKutta rk2Ode = new RungeKutta(INCREMENTS, H, startParams);
		rk2Ode.runIteration(leEulerEq);
		/*
		
		try {
			PrintWriter pw = new PrintWriter("/Apps/lem.csv", "UTF-8");
				
			for (int i = 0; i < INCREMENTS; i++) {
				pw.println((euler2Ode.getValues(i).t) + " " + (euler2Ode.getValues(i).y));
				System.out.println((euler2Ode.getValues(i).t) + " " + (euler2Ode.getValues(i).y));
			
			}		
			pw.close();
			}
			catch(Exception e) {  
				System.out.println(e.getMessage());
			}
			
			*/
		/*
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
