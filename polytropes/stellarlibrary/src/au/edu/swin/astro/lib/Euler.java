package au.edu.swin.astro.lib;
import java.math.*;

public class Euler {
	
    public int tIncrements = 0;
    public BigDecimal h;
	private int i;
	private Parameters resultValues[];
	
	
	//Initialize the class with the total number of needed increments, interval and  
	//  starting parameters
	//
	 public Euler (int totalIncrements, BigDecimal hInterval, Parameters startParams)	
	 {
		// Initialize the value object array to the total number of iterations - 
		// Memory heap should be about 60 bytes * totalIncrements
		tIncrements = totalIncrements;
		h = hInterval;
		resultValues = new Parameters[totalIncrements];
		resultValues[0] = new Parameters();
			
		// Set the starting values for the parameter array
		resultValues[0].yprime = startParams.yprime;
		resultValues[0].y = startParams.y;
		resultValues[0].t = startParams.t;

	}
	 

	
	public void runIteration(Equation eqODE) {
				
		// Populate the result array value object through each increment
		for (i = 0; i < tIncrements-1; i++) {
			
			System.out.println((resultValues[i].t) + " " + (resultValues[i].y));
			resultValues[i+1] = eqODE.getResult(h, resultValues[i]);
		}
	}
	
	
	
	public Parameters getValues(int i) {
		return(resultValues[i]);
}
	
	


}
