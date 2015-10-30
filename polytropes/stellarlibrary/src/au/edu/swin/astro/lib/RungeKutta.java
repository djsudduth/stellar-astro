package au.edu.swin.astro.lib;
import java.math.BigDecimal;

public class RungeKutta {
	
	    public int tIncrements = 0;
	    public BigDecimal h;
		private int i;
		private Parameters resultValues[];

		
		
		//Initialize the class with the total number of needed increments, interval and  
		//  starting parameters
		//
		 public RungeKutta (int totalIncrements, BigDecimal hInterval, Parameters startParams)	
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
			
			BigDecimal k1, k2, k3, k4, kd1, kd2, kd3, kd4;
			BigDecimal m1, m2, m3, m4, md1, md2, md3, md4;
			BigDecimal h2, y, yprime, t, yf, zf, two;
			Parameters temp, rk1, rk2, rk3, rk4;
			
			two = new BigDecimal("2.0");
			h2 = h.divide(two);

			// Calculations need to run for Y'' and Y'
			
			// Save a copy of Y', Y and t
			y = resultValues[i].y;
			yprime = resultValues[i].yprime;
			t = resultValues[i].t;
			
			// Get k1 and m1
			rk1 = eqODE.getResult(h, resultValues[i]);
			k1 = rk1.k;
			m1 = rk1.m;
			kd1 = k1.divide(two);
			md1 = m1.divide(two);
			temp = new Parameters();
			temp.y = y.add(kd1);
			temp.yprime = yprime.add(md1);
			temp.t = t.add(h2);
			
			
			rk2 = eqODE.getResult(h, temp);
			k2 = rk2.k;
			m2 = rk2.m;
			kd2 = k2.divide(two);
			md2 = m2.divide(two);
			temp = new Parameters();
			temp.y = y.add(kd2);
			temp.yprime =yprime.add(md2);	
			temp.t = t.add(h2);
			
			
			rk3 = eqODE.getResult(h, temp);
			k3 = rk3.k;
			m3 = rk3.m;
			kd3 = k3;
			md3 = m3; 
			temp = new Parameters();
			temp.y = y.add(kd3);
			temp.yprime = yprime.add(md3);
			temp.t = t.add(h);
			
			
			rk4 = eqODE.getResult(h, temp);
			k4 = rk4.k;
			m4 = rk4.m;
				

			yf = k1.add(k2.multiply(two)).add(k3.multiply(two)).add(k4).multiply(new BigDecimal("0.1666666666667"));
			y = y.add(yf);
			zf = m1.add(m2.multiply(two)).add(m3.multiply(two)).add(m4).multiply(new BigDecimal("0.1666666666667"));
			yprime = yprime.add(zf);
			
			resultValues[i+1] = new Parameters();
			resultValues[i+1].y = y;
			resultValues[i+1].yprime = yprime;
			resultValues[i+1].t = rk4.t;
			
			System.out.println(yprime + " " + k1 + " " + m1 + " " + k2 + " " + m2 + " " + k3 + " " + m3);
			// Populate the result array value object through each increment
			for (i = 0; i < tIncrements-1; i++) {
				
				//System.out.println((resultValues[i].t) + " " + (resultValues[i].y));
				//resultValues[i+1] = eqODE.getResult(h, resultValues[i]);
			}
			

		}
		
		
		
		public Parameters getValues(int i) {
			return(resultValues[i]);
		}
		
}
