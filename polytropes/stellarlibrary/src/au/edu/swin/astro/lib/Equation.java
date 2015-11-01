package au.edu.swin.astro.lib;
import java.math.BigDecimal;


// This class is an abstract class for first and second order ODE equations
//   Equations are encapsulated in their own classes but use this as a base class for
//   generalization. Inherited by the Euler, Runge-Kutta, and other numerical method classes
//
public abstract class Equation {
		
	abstract Parameters getResult(BigDecimal h, Parameters p);
	abstract DParameters getDResult(double h, DParameters p);


	// This parent function is for any inherited equation classes that use doubles instead of 
	//   BigIntegers. Allows any inherited class to round doubles.
	//
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
