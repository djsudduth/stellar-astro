package au.edu.swin.astro.lib;
import java.math.*;

/*
########################################################################################
## Parameters.java
##
## This is the data class to hold the calculated values for the dimensionless density
## and dimensionless radius --the equation classes populate an array of this data class
## during iterations.
## Copyright (C) 2015  Don Sudduth: 9764909@student.swin.edu.au
##
########################################################################################
*/

public class Parameters {
	
	public BigDecimal yprime;
	public BigDecimal y;
	public BigDecimal t;
	public BigDecimal k;
	public BigDecimal m;
	
	public Parameters () {
	}
	
	
	// Constructor if values are passed in
	public Parameters (BigDecimal in_yprime, BigDecimal in_y, BigDecimal in_t) {
		yprime = in_yprime;
		y = in_y;
		t = in_t;
	}
}
