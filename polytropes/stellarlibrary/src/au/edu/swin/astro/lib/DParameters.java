package au.edu.swin.astro.lib;

/*
########################################################################################
## DParameters.java
##
## This is the data class to hold the calculated values for the dimensionless density
## and dimensionless radius --the equation classes populate an array of this data class
## during iterations.  (Uses double data types instead of BigDecimal)
## Copyright (C) 2015  Don Sudduth: 9764909@student.swin.edu.au
##
########################################################################################
*/

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
