/**
 * @author WrightWJ
 * 
 * 
 */
package project2;

import org.junit.jupiter.api.Test;

/**
 * The Class PolynomialTest.
 */
class PolynomialTest {
	
	/**
	 * Test. This adds terms to the polynomial in every way possible and prints out various forms. 
	 */
	//this class tests everything
	@Test
	void test() {
		
		//create polys
		Polynomial poly = new Polynomial();
		Polynomial poly2 = new Polynomial();
		//add to polys with string
		poly.readPolynomial("5.0x^3 + 4x^4 + 1x^1 + 3x^1 + 1x^1");
		poly2.readPolynomial("5.0x^3 + 16x^4 + 1x^1 + 3x^0 + 1x^1");
//		manually add terms
		poly.addTerm(new Term(-5,3));
		poly.addTerm(new Term(-3,6));
		poly.addTerm(new Term(5,2));
		poly2.addTerm(new Term(-10,6));
		poly2.addTerm(new Term(51,2));
		
		//create new poly.
		Polynomial result = poly.add(poly2);
		System.out.println(result);
		System.out.println(poly.toString());
		result = poly.subPoly(poly2);
		System.out.println(result);
		
		
		System.out.println(poly.multPoly(poly2));
		
	}

}
