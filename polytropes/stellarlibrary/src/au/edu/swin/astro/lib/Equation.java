package au.edu.swin.astro.lib;

public class Equation {
	
	public Parameters[] ResultValues;
	
	// Constructor
	public Equation(int totalIncrements, Parameters startParams) {
		
		// Initialize the value object array to the total number of iterations
		ResultValues = new Parameters[totalIncrements];
		
		// Set the starting values for the parameter array
		ResultValues[0].yprime = startParams.yprime;
		ResultValues[0].y = startParams.y;
		ResultValues[0].t = startParams.t;
		
	}
	
	// Use this class to define the ODE needed in the 
	public void getResult(Parameters p, double h) {
		
	}
	

}
