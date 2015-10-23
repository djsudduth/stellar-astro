package au.edu.swin.astro.lib;

public class Euler {
	
	public Euler() {

		Parameters resultP = new Parameters();
		final int INCREMENTS = 40;
		int i;
		
		Parameters startParams = new Parameters(1.0D, 1.0D, 0.0D);

		
		Equation eqFirstODE = new Equation(INCREMENTS, 0.1D, startParams);
		eqFirstODE.run();
		
		for (i = 0; i < INCREMENTS; i++) {
			resultP = eqFirstODE.getValues(i);
			
		}
		
		
		
	}

}
