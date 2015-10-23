package au.edu.swin.astro.lib;

public class Equation {
	
	public Parameters resultValues[];
	int tIncrements;
	double h;
	
	// Constructor for ODE equation for array of results
	public Equation(int totalIncrements, double hInterval, Parameters startParams) {
		
		// Initialize the value object array to the total number of iterations - 
		// Memory heap should be about 60 bytes * totalIncrements
		tIncrements = totalIncrements;
		h = hInterval;
		resultValues = new Parameters[totalIncrements];
		resultValues[0] = new Parameters(0.0D, 0.0D, 0.0D);
		
		// Set the starting values for the parameter array
		resultValues[0].yprime = startParams.yprime;
		resultValues[0].y = startParams.y;
		resultValues[0].t = startParams.t;
		
	}
	
	
	
	
	// Use this class to define the ODE equation needed in the numerical calculation
	// Used by the Euler, Runge-Kutta, etc classes
	public Parameters getResult(Parameters p) {
		
		Parameters pNew = new Parameters(0.0D, 0.0D, 0.0D);
		
		pNew.y = p.y + h*p.yprime;
		pNew.t = p.t + h;
		
		// Example 1 - first order ODE;
		//Y' = (2 - e^(-4*t)-2*Y)  actual solution is  Y =(1 + 0.5*e^(-4*t)-0.5*e^(-2*t))
		pNew.yprime = (2.0D - Math.exp(-4.0D * (pNew.t)) - (2.0D*pNew.y));
		// End Example 1
			
		return(pNew);
	}
	
	public void run() {
		
		int i;
		
		// Populate the result array value object through each increment
		for (i = 0; i < tIncrements-1; i++) {
			
			resultValues[i+1] = getResult(resultValues[i]);
			
		}
	}
	
	
	public Parameters getValues(int i) {
		return(resultValues[i]);
	}

}
