package au.edu.swin.astro.lib;

public class RungeKutta {
	
	
	Parameters resultP = new Parameters();
	final int INCREMENTS = 40;
	int i;

	public RungeKutta() {

	}
	
	/*
	public void runIteration() {

		Parameters startParams = new Parameters(-1.0D, 1.0D, 0.0D);
		
		Equation eqFirstODE = new Equation(INCREMENTS, 0.01D, startParams);
		eqFirstODE.run();
		
		for (i = 0; i < INCREMENTS; i++) {
			resultP = eqFirstODE.getValues(i);
			System.out.println(Double.toString(resultP.t) + " " + Double.toString(resultP.y));
			
		}
	}
	*/


   public static void main(String args[]) {
    	
    	RungeKutta e = new RungeKutta();
    	//e.runIteration();
    	//double z = (2.0 - Math.exp(-4.0 * (0.1)) - (2.0*0.9));
        //System.out.println(Double.toString(z));
    }

}
