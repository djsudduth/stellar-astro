package au.edu.swin.astro.lib;
import java.math.*;


// Each ODE can be defined in inherited class from the Equation class
//   Specific needs for the equation should be passed into the constructor of this class such as
//   roots, power-laws, constants, etc. to be used in the equation
//   
public class LaneEmdenEquation extends Equation {
	
	private int n;  //Polytropic index
	
	
	
	// Constructor for an Equation class 
	//
	public LaneEmdenEquation (int polytropicIndex) {
		
		// Adjust the Lane-Emden polytropic index on creation of the calculation class
		n = polytropicIndex;
	}
	
	
	
	// Use this method to define the ODE equation needed in the numerical calculation
	//    This is the Lane-Emden Equation which will be called by the Euler, Runge-Kutta, etc calculation classes
	//
	public Parameters getResult(BigDecimal h, Parameters p) {
		
		Parameters pNew = new Parameters();
		
		// Calculate the new y position based the current slope Y = Y + h*(Y')
		pNew.k = p.yprime.multiply(h, MathContext.DECIMAL128);
		pNew.y = pNew.k.add(p.y, MathContext.DECIMAL128);
		//pNew.y = roundToSignificantFigures(p.y + h*p.yprime, 12);
		
		//Y'' = Y' + h*(-2(Y'/t) - Y^n)
		BigDecimal ytemp = p.y.pow(n, MathContext.DECIMAL128);
		BigDecimal yptemp = p.yprime.divide(p.t, MathContext.DECIMAL128).multiply(new BigDecimal("-2.0"), MathContext.DECIMAL128);
		pNew.m = yptemp.subtract(ytemp, MathContext.DECIMAL128).multiply(h, MathContext.DECIMAL128);
		pNew.yprime = p.yprime.add(pNew.m, MathContext.DECIMAL128);
		
		
		//pNew.yprime = roundToSignificantFigures(p.yprime + h*(-2.0D*(p.yprime/p.t) - Math.pow(p.y, n)), 12);
	
		pNew.t = p.t.add(h, MathContext.DECIMAL128);
		//pNew.t = roundToSignificantFigures(p.t + h, 12);
			
		return(pNew);
	}
	
	
	public DParameters getDResult(double h, DParameters p) {
		
		DParameters pNew = new DParameters();
		
		// Calculate the new y position based the current slope Y = Y + h*(Y')
		pNew.y = p.y + h*p.yprime;
		
		//Y'' = Y' + h*(-2(Y'/t) - Y^n)
		pNew.yprime = p.yprime + h*(-2.0D*(p.yprime/p.t) - Math.pow(p.y, n));
	
		pNew.t = p.t + h;
			
		return(pNew);
	}
	
}
