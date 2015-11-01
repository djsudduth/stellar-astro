package au.edu.swin.astro.lib;

//This data value class was used to compare Java double precision with Java BigDecimal precision
//  it is a test class only. 
//

public class DParameters {
	
	public double yprime;
	public double y;
	public double t;
	public double k;
	public double m;
	
	public DParameters () {
	}
	
	
	public DParameters (double in_yprime, double in_y, double in_t) {
		yprime = in_yprime;
		y = in_y;
		t = in_t;
	}

}
