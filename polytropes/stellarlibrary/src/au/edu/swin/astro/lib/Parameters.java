package au.edu.swin.astro.lib;
import java.math.*;

public class Parameters {
	
	public BigDecimal yprime;
	public BigDecimal y;
	public BigDecimal t;
	public BigDecimal k;
	public BigDecimal m;
	
	public Parameters () {
	}
	
	
	public Parameters (BigDecimal in_yprime, BigDecimal in_y, BigDecimal in_t) {
		yprime = in_yprime;
		y = in_y;
		t = in_t;
	}
}
