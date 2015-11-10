package au.edu.swin.astro.lib;
import java.math.*;

// This is the encapsulation of the Lane-Emden 2nd order ODE
//   Specific needs for the equation should be passed into the constructor of this class such as
//   roots, power-laws, constants, etc. to be used in the equation
//   
public class LaneEmdenEquation extends Equation {
	
	private double n;  // Polytropic index in Lane-Emden equation
	
	// Constructor for an Equation class 
	//
	public LaneEmdenEquation (double polytropicIndex) {
		
		// Set the Lane-Emden polytropic index on creation of the calculation class
		n = polytropicIndex;
	}
	
	
	
	// Use this method to define the 2nd order ODE needed in the numerical calculation
	//    This is the Lane-Emden Equation which will be called by the Euler, Runge-Kutta, etc calculation classes
	//    Note that theta = Y and xi = t for convenience
	//
	public Parameters getResult(BigDecimal h, Parameters p) {
		
		Parameters pNew = new Parameters();
		double y;
		
		// Calculate the new y position based the current slope Y = Y + h*(Y')
		pNew.k = p.yprime.multiply(h, MathContext.DECIMAL128);
		pNew.y = pNew.k.add(p.y, MathContext.DECIMAL128);
		
		//Y'' = Y' + h*(-2(Y'/t) - Y^n)
		// Note that BigDecimal cannot do fractional exponents - if y is very small Math.pow will 
		//    error with NaN
		y = p.y.doubleValue();
		if (y < 0.000000000000001D) {
			y = 0.0D;
		}
		// Lane-Emden equation calculation
		BigDecimal ytemp = new BigDecimal(Math.pow(y, n), MathContext.DECIMAL128);  
		BigDecimal yptemp = p.yprime.divide(p.t, MathContext.DECIMAL128).multiply(new BigDecimal("-2.0"), MathContext.DECIMAL128);
		pNew.m = yptemp.subtract(ytemp, MathContext.DECIMAL128).multiply(h, MathContext.DECIMAL128);
		pNew.yprime = p.yprime.add(pNew.m, MathContext.DECIMAL128);
		
		// Move to the next increment
		pNew.t = p.t.add(h, MathContext.DECIMAL128);

		return(pNew);		
	}
	
	
	// Use this method to define the 2nd order ODE needed if using doubles
	//

	public DParameters getDResult(double h, DParameters p) {
		
		DParameters pNew = new DParameters();
		
		// Calculate the new y position based the current slope Y = Y + h*(Y')
		pNew.y = p.y + h*p.yprime;
		
		//Y'' = Y' + h*(-2(Y'/t) - Y^n)
		pNew.yprime = p.yprime + h*(-2.0D*(p.yprime/p.t) - Math.pow(p.y, n));
	
		pNew.t = p.t + h;
			
		return(pNew);
	}
	
	
	// Notes for Lane-Emden calculation using doubles
	// 		pNew.y = roundToSignificantFigures(p.y + h*p.yprime, 12);
	//		pNew.yprime = roundToSignificantFigures(p.yprime + h*(-2.0D*(p.yprime/p.t) - Math.pow(p.y, n)), 12);
	//		pNew.t = roundToSignificantFigures(p.t + h, 12);
	
}
