/*
 * 
 */
package project2;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Please note the style change, I saw this different block style on a video online and decided
 * to try it out. I like how clean it makes the code. It's also incredibly readable. I will try
 * to program like this in the future.
 * @author WrightWJ
 *
 */
public class Polynomial{
	
	/** The ordered list of terms. */
	OrderedList<Term> ol;

	/**
	 * Instantiates a new polynomial.
	 */
	public Polynomial() 
	{
		ol = new OrderedList<Term>();
	}
	//if you only have coefficient and exponent, but not the term object, then pass this method
	//new Term(coefficient, exponent);
	/**
	 * Adds the term to the ordered list.
	 * pre: must be passd a valid term
	 * post: will add term to the ordered list, in correct order..
	 * @param t the term to be added.
	 */
	//set coefficient to 0 to remove it.
	public void addTerm(Term t) 
	{
		//if coefficient is 0, don't add.
		if (t.getCoefficient()!=0)
			ol.add(t);
	}
	
	/**
	 * Change term coefficient
	 * pre: must be passed a valid term and coefficient.
	 * post: will work as intended.
	 * @param t the term to be changed, or removed if coefficient is 0.
	 * @param coEff the coefficient to override the old one
	 * @return true, if successful, false if not.
	 */
	public boolean changeTerm(Term t, double coEff) {
		if (coEff==0) 
		{
			ol.remove(t);
		} 
		else 
		{
			for (Term search: ol)
			{
				if (search.equals(t)) 
				{
					t.setCoefficient(coEff);
					return true;
				} 
				else
				{ 
					continue;
				}
			}
		}
		return false;
	}
	
	/**
	 * Read polynomial from passed String.
	 *pre: must be passed a valid polynomial with exponents on every term.
	 *post: will work as intended.
	 * @param wrapperString String that will be split into terms, and then into individual coefficients and 
	 * @return true, if successful
	 */
	//returns true if successful, false if input is invalid or .
	public boolean readPolynomial(String wrapperString) 
	{
		//used to change size of arraylist so it will not go out of bounds.
		int varyingLength;
		ArrayList<String[]> aList = new ArrayList<String[]>();
		if (wrapperString == null || wrapperString.equals("")) 
		{
			return false;
		}
		//array of string containing the terms that were in the first string.
		String[] secondStep = wrapperString.split("[\\s+\\s]|[\\s\\s]");
		
		//for every string in the array that is not empty, split it into just numbers, coefficient and exponents.
		for (String s: secondStep) {
			if (s.isEmpty()) {
				continue;
			}
			aList.add(s.split("[x\\^]"));
		}
		
		//if size is less than 2, there is only one term. 
		//To be entirely honest, this took a lot of trial and error and I still don't know why it works.
		if (aList.size() < 2) {
			varyingLength = aList.size()+1;
		} else varyingLength = 2;
		for (int i = 0; i < aList.size();i++) 
		{
			ol.add(new Term(Double.parseDouble(aList.get(i)[0]),Double.parseDouble(aList.get(i)[varyingLength])));
		}
		return true;
	}
	
	/**
	 * Adds the passed polynomial to the current one.
	 *pre: must be passed a valid polynomial with exponents on every term.
	 *post: will work as intended.
	 * @param poly the poly to be added
	 * @return the polynomial result.
	 */
	public Polynomial add(Polynomial poly) 
	{
		ArrayList<Term> terms = new ArrayList<Term>();
		Polynomial sum = new Polynomial();
		for (Term t: ol)
		{
			for (Term t2: poly.ol)
			{
				if (terms.contains(t2)) 
				{
					break;
				}
				if (t.compareTo(t2)==0) 
				{
					sum.addTerm(new Term((t.getCoefficient()+t2.getCoefficient()),t2.getExponent()));
					terms.add(new Term((t.getCoefficient()+t2.getCoefficient()),t2.getExponent()));
					t.setCoefficient(t.getCoefficient()+t2.getCoefficient());
					t2.setCoefficient(0);
				}
			}
		}

		for (Term t: poly.ol) 
		{
			sum.addTerm(t);
		}
		return sum;
	}
	
	/**
	 * Subtract passed polynomial from this polynomial..
	 *pre: must be passed a valid polynomial with exponents on every term.
	 *post: will work as intended.
	 * @param poly the polynomial
	 * @return the polynomial, added with the inverse of the passed function.
	 */
	public Polynomial subPoly(Polynomial poly) {
		for (Term t: poly.ol) {
			t.setCoefficient(-1* (t.getCoefficient()));
		}
		return add(poly);
	}

	/**
	 * Mult poly.
	 *pre: must be passed a valid polynomial with exponents on every term.
	 *post: will work as intended.
	 * @param poly the polynomial to be multiplied with the current polynomial.
	 * @return the polynomial result.
	 */
	public Polynomial multPoly(Polynomial poly) 
	{
		Polynomial product = new Polynomial();
		Iterator<Term> iter = ol.iterator();

		while (iter.hasNext()) 
		{
			Term olNext = iter.next();
			Iterator<Term> iterPoly = poly.ol.iterator();

			while (iterPoly.hasNext()) 
			{
				Term polyNext = iterPoly.next();

				Term getProduct = new Term((olNext.getCoefficient() * polyNext.getCoefficient()),
						olNext.getExponent()+ polyNext.getExponent());
				product.addTerm(getProduct);
			}
		}

		return product;
	}


	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		StringBuilder stb = new StringBuilder();
		int i = 0;
		for (Term t: ol) 
		{
			if (t.getCoefficient()>1 && i>0) 
			{
				if (t.getExponent()>1) {
					stb.append(" + "+t.toString());
					i++;
				} else if (t.getExponent()==1){
					stb.append(" + " +t.getCoefficient()+"x");
				} else {
					stb.append(" + "+t.getCoefficient());
				}
			} 
			else if (t.getCoefficient()==1)
			{
				stb.append(" + x^"+t.getExponent());
				continue;
			}
			else
			{
				i++;
				stb.append("" +t.toString());
			}
		}
		i=0;
		return stb.toString();
	}
}
