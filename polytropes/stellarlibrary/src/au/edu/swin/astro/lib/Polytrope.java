package au.edu.swin.astro.lib;
import java.math.*;

public class Polytrope {
	
	static int INCREMENTS = 20;
	static BigDecimal H = new BigDecimal("0.01");
	int i;
	public Parameters resultP;

	
	public static void main(String[] args) {
		
		// Setup the ODE starting values of Y', Y and t
		Parameters startParams = new Parameters(new BigDecimal("0.0000000000"), new BigDecimal("1.0000000000"), new BigDecimal("0.0000000001"));
		
		// Select the equation to use for this test
		//Equation eulerEq = new Equation();
		
		// Create a Euler test 
		//Euler eulerOde = new Euler(INCREMENTS, H, startParams);
		//eulerOde.runIteration(eulerEq);
		
		
		// Select the equation to use for this test
		LaneEmdenEquation leEulerEq = new LaneEmdenEquation(1);
		
		// Create a Euler test 
		Euler euler2Ode = new Euler(INCREMENTS, H, startParams);
		euler2Ode.runIteration(leEulerEq);
		
		
		 BigDecimal h = new BigDecimal("0.01");
		 BigDecimal y = new BigDecimal("0.9438730483");
		// BigDecimal c = new BigDecimal("879.35");
		 BigDecimal d = new BigDecimal("-0.1714642698");

		// BigDecimal total = BigDecimal.ZERO;
		 BigDecimal zz = d.multiply(h);
		 zz = zz.add(y);
		 BigDecimal pp = zz;
		 System.out.println(pp);

	}

}
