/*
 * 
 */
package project2;
/**
 * The Class Term.
 */
public class Term implements Comparable<Term>{
	
	/** The exponent. */
	private double coefficient, exponent;
	
	/**
	 * Instantiates a new term.
	 *
	 * @param co the co
	 * @param ex the ex
	 */
	public Term(double co, double ex) {
		this.coefficient = co;
		this.exponent = ex;
	}
	
	/**
	 * Instantiates a new term.
	 */
	//if no parameters are passed to constructor, create a term with coefficient of 1 and no exponent.
	public Term() {
		this.coefficient = 1;
		this.exponent = 1;
	}
	
	/**
	 * Sets the coefficient.
	 *
	 * @param c the new coefficient
	 */
	public void setCoefficient(double c) {
		this.coefficient = c;
	}
	
	/**
	 * Sets the exponent.
	 *
	 * @param e the new exponent
	 */
	public void setExponent(double e) {
		this.exponent = e;
	}
	
	/**
	 * Gets the exponent.
	 *
	 * @return the exponent
	 */
	public double getExponent() {
		return exponent;
	}
	/**
	 * Gets the coefficient.
	 *
	 * @return the coefficient
	 */
	public double getCoefficient() {
		return coefficient;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder stb = new StringBuilder();
		if (exponent > 1) {
			stb.append(coefficient+"x^"+exponent+" ");
		} else if (exponent == 0) {
			stb.append(" 1 ");
		} else stb.append(coefficient);	
		return stb.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Term o) {
		if (o.getExponent()>this.getExponent()) {
			return 1;
		} else if (o.getExponent()==this.getExponent()) {
			return 0;
		} else return -1;
	}
}
