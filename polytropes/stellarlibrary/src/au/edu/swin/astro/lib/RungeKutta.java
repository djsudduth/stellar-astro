package au.edu.swin.astro.lib;
import java.math.BigDecimal;

//This is the Runge-Kutta 4th order method - the underlying equation is called four times
//  to calculate both the slopes and k1, m1 through k4 and m4 values
//
public class RungeKutta {
	
	    private int tIncrements = 0;
	    private BigDecimal h;
		private int i;
		private Parameters resultValues[];
		private double yintersection, yprimeintersection;
		
		// Initialize the class with the total number of needed increments, interval and  
		//   starting parameters
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
		 

		// Runs the RK4 iterations utilizing BigDecimal-Decimal128 which is a decimal floating-point 
		//   numbering format that occupies 16 bytes (128 bits) in computer memory. It supports 34 decimal digits of 
		//   significand and an exponent range of -6143 to +6144.
		//
		public void runIteration(Equation eqODE) {
			
			// Scope these variables only within the function
			BigDecimal k1, k2, k3, k4, kd1, kd2, kd3, kd4;
			BigDecimal m1, m2, m3, m4, md1, md2, md3, md4;
			BigDecimal h2, y, yprime, t, yf, zf, two, ylast, yprimelast;
			Parameters temp, rk1, rk2, rk3, rk4;
			
			// Initial divisors
			two = new BigDecimal("2.0");
			h2 = h.divide(two);

			// Populate the result array value object through each increment
			// Calculations need to execute Y''/Z' and Y'/Z for k and m values
			for (i = 0; i < tIncrements-1; i++) {
				
				// Save a copy of Y', Y and t
				y = resultValues[i].y;
				ylast = y;
				yprime = resultValues[i].yprime;
				yprimelast = yprime;
				t = resultValues[i].t;
				
				// Get k1 and m1
				rk1 = eqODE.getResult(h, resultValues[i]);
				k1 = rk1.k;
				m1 = rk1.m;
				kd1 = k1.divide(two);
				md1 = m1.divide(two);
				temp = new Parameters();  // Populate temporary data parameters for RK values
				temp.y = y.add(kd1);
				temp.yprime = yprime.add(md1);
				temp.t = t.add(h2);
				rk1 = null;
				
				// Get k2 and m2
				rk2 = eqODE.getResult(h, temp);
				k2 = rk2.k;
				m2 = rk2.m;
				kd2 = k2.divide(two);
				md2 = m2.divide(two);
				temp = new Parameters();  // Populate temporary data parameters for RK values
				temp.y = y.add(kd2);
				temp.yprime =yprime.add(md2);	
				temp.t = t.add(h2);
				rk2 = null;
				
				// Get k3 and m3
				rk3 = eqODE.getResult(h, temp);
				k3 = rk3.k;
				m3 = rk3.m;
				kd3 = k3;
				md3 = m3; 
				temp = new Parameters();  // Populate temporary data parameters for RK values
				temp.y = y.add(kd3);
				temp.yprime = yprime.add(md3);
				temp.t = t.add(h);
				rk3 = null;
				
				// Get k4 and m4
				rk4 = eqODE.getResult(h, temp);
				k4 = rk4.k;
				m4 = rk4.m;
				rk4 = null;
					
				// Calculate the RK4 increments for Y and Y' in the 2nd order ODE 
				//    (1/6)(k1 + 2k2 + 2k3 + k4) - same for m
				//
				yf = k1.add(k2.multiply(two)).add(k3.multiply(two)).add(k4).multiply(new BigDecimal("0.1666666666667"));
				y = y.add(yf);
				zf = m1.add(m2.multiply(two)).add(m3.multiply(two)).add(m4).multiply(new BigDecimal("0.1666666666667"));
				yprime = yprime.add(zf);
				
				// Update the data array with the new RK4 values
				resultValues[i+1] = new Parameters();
				resultValues[i+1].y = y;
				resultValues[i+1].yprime = yprime;
				resultValues[i+1].t = h.add(t);
				
				// Find the y intersection (if found)
				if ((y.compareTo(BigDecimal.ZERO) < 0) && (ylast.compareTo(BigDecimal.ZERO) > 0)) {
					double v1 = y.doubleValue();
					double v2 = ylast.doubleValue();
					
					// Interpolate the difference across y intersection
					double diff = (v2)/(v2 - v1);
					double t1 = t.doubleValue();
					double h1 = h.doubleValue();
					yintersection = t1 + (h1*diff);
					
					double w1 = yprime.doubleValue();
					double w2 = yprimelast.doubleValue();
					
					// Interpolate the difference across y intersection
					double wdiff = (w1 - w2);
					yprimeintersection = w1 - (wdiff*diff);
					wdiff = 0.0D;

					
				}
				
			}
		}
		
		
		// Returns the values in the array
		//
		public Parameters getValues(int i) {
			return(resultValues[i]);
		}
		
		// Returns the yintersection 
		public double getYIntersection() {
			return(yintersection);
		}
		
		// Returns the yprimeintersection 
		public double getYPrimeIntersection() {
			return(yprimeintersection);
		}

		
}
