package au.edu.swin.astro.lib;
import java.math.BigDecimal;;

public abstract class Equation {
	
	// Use this class to define the ODE equation needed in the numerical calculation
	// Used by the Euler, Runge-Kutta, etc classes
	
	abstract Parameters getResult(BigDecimal h, Parameters p);
	/*	
		Parameters pNew = new Parameters();
		
		pNew.y = roundToSignificantFigures(p.y + h*p.yprime, 9);
		pNew.t = roundToSignificantFigures(p.t + h, 9);
		
		// Example 1 - first order ODE;
		//Y' = (2 - e^(-4*t)-2*Y)  actual solution is  Y =(1 + 0.5*e^(-4*t)-0.5*e^(-2*t))
		pNew.yprime = (2.0D - Math.exp(-4.0D * (pNew.t)) - (2.0D*pNew.y));
		// End Example 1
			
		return(pNew);
	*/


	
	public static double roundToSignificantFigures(double num, int n) {
	    if(num == 0) {
	        return 0;
	    }

	    final double d = Math.ceil(Math.log10(num < 0 ? -num: num));
	    final int power = n - (int) d;

	    final double magnitude = Math.pow(10, power);
	    final long shifted = Math.round(num*magnitude);
	    return shifted/magnitude;
	}

}
