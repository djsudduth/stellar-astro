package au.edu.swin.astro.lib;
import java.math.*;

public class DEuler {
	
    public int tIncrements = 0;
    public double h;
	private int i;
	private DParameters resultValues[];
	
	
	//Initialize the class with the total number of needed increments, interval and  
	//  starting parameters
	//
	 public DEuler (int totalIncrements, double hInterval, DParameters startParams)	
	 {
		// Initialize the value object array to the total number of iterations - 
		// Memory heap should be about 60 bytes * totalIncrements
		tIncrements = totalIncrements;
		h = hInterval;
		resultValues = new DParameters[totalIncrements];
		resultValues[0] = new DParameters();
			
		// Set the starting values for the parameter array
		resultValues[0].yprime = startParams.yprime;
		resultValues[0].y = startParams.y;
		resultValues[0].t = startParams.t;

	}
	 

	
	public void runIteration(Equation eqODE) {
				
		// Populate the result array value object through each increment
		for (i = 0; i < tIncrements-1; i++) {
			
			System.out.println((resultValues[i].t) + " " + (resultValues[i].y));
			resultValues[i+1] = eqODE.getDResult(h, resultValues[i]);
		}
	}
	
	
	
	public DParameters getValues(int i) {
		return(resultValues[i]);
}
	
	


}
