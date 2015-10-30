package au.edu.swin.astro.lib;
import java.math.*;


public class LaneEmdenEquation extends Equation {
	
	private int n;
	
	// Constructor for an Equation class - this is the Lane-Emden Equation
	//
	public LaneEmdenEquation (int polytropicIndex) {
		
		// Adjust the Lane-Emden polytropic index on creation of the calculation class
		n = polytropicIndex;
	}
	
	
	
	// Use this class to define the ODE equation needed in the numerical calculation
	// Used by the Euler, Runge-Kutta, etc classes
	public Parameters getResult(BigDecimal h, Parameters p) {
		
		Parameters pNew = new Parameters();
		pNew.y = p.yprime.multiply(h);
		pNew.y = pNew.y.add(p.y);
		//pNew.y = roundToSignificantFigures(p.y + h*p.yprime, 12);
		
		//Y'' = Y' + h*(-2(Y'/t) - Y^n)
		
		pNew.yprime = roundToSignificantFigures(p.yprime + h*(-2.0D*(p.yprime/p.t) - Math.pow(p.y, n)), 12);
		
		pNew.t = p.t.add(h);
		//pNew.t = roundToSignificantFigures(p.t + h, 12);
			
		return(pNew);
	}
	
}
