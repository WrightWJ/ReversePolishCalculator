package project2;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TermTest {

	@Test
	void test() {
		//create term
		Term t = new Term();
		
		//test setters
		t.setCoefficient(3);
		t.setExponent(4);
		
		assertEquals(t.getCoefficient(),3.0,"error, should be 3, is: "+t.getCoefficient());
		//test getters
		t.setExponent(t.getExponent()+1);
		t.setCoefficient(t.getCoefficient()+1);
		
		//test 0's
		Term t2 = new Term(0,0);	
		
		//test compareTo and toString
		t2.compareTo(t);
		t2.toString();
		t.toString();
	}

}
