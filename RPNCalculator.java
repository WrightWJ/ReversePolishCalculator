/*
 * 
 */
package project2;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
// TODO: Auto-generated Javadoc

/**
 * The Class RPNCalculator.
 */
public class RPNCalculator 
{

	/** The sc. */
	private static Scanner sc;

	/** The poly stack. */
	private static Stack<Polynomial> polyStack = new Stack<Polynomial>();

	/** The filter. */
	private static FileNameExtensionFilter filter;

	/** The file IO. */
	private static JFileChooser fileIO;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) 
	{
		filter = new FileNameExtensionFilter("Text Files","txt");
		fileIO = new JFileChooser();
		fileIO.setFileFilter(filter);
		while (fileIO.getSelectedFile()==null) 
		{
			fileIO.showOpenDialog(null);
		}
		//try to get file
		try 
		{
			sc = new Scanner(fileIO.getSelectedFile());
		} 
		catch (FileNotFoundException fnfe) 
		{
			JOptionPane.showMessageDialog(null, "Error, file could not be opened");
			fnfe.printStackTrace();
		}
		while (sc.hasNext()) 
		{
			read(sc.next());
		}
	}
	/**
	 * Reads the string thats passed to the method
	 *
	 * @param read the string from the input file.
	 */
	private static void read(String read) 
	{
		char ch = read.charAt(0);
		switch (ch) {
		//arithmetic functions
		case '+': add();
		break;
		case '-': subtract();
		break;
		case '*': multiply();
		break;
		//ask for polynomial
		case '?': getInput();
		break;
		//exit program
		case 'q': System.exit(1);
		break;
		//print result polynomial
		case '=': print();
		}
	}
	/**
	 * pre: Stack must contain two valid polynomials
	 * post: will add the two polynomials.
	 * Adds the two polynomials
	 */
	private static void add() {
		polyStack.push(polyStack.pop().add(polyStack.pop()));
	}
	/**
	 * pre: Stack must contain two valid polynomials
	 * post: will subtract the two polynomials.
	 * Subtract the two polynomials.
	 */
	private static void subtract() {
		polyStack.push(polyStack.pop().subPoly(polyStack.pop()));
	}
	/**
	 * pre: Stack must contain two valid polynomials
	 * post: will multiply the two polynomials.
	 * Multiply the polynomials previously given.
	 */
	private static void multiply() {
		polyStack.push(polyStack.pop().multPoly(polyStack.pop()));
	}
	/**
	 * pre: Stack must contain a polynomial
	 * post: will print top polynomial.
	 * Prints the result polynomial of whatever methods were run previously, or null if stack is empty..
	 */
	private static void print() {
		if (polyStack.peek()!=null) 
		{
			System.out.println(polyStack.pop().toString());
		}
		else
		{
			System.out.println("Stack is empty, nerd");
		}
	}
	/**
	 * Gets the input from the user in a JOptionPane.
	 *
	 * @return the input
	 */
	@SuppressWarnings("static-access")
	private static void getInput() {
		Polynomial poly = new Polynomial();
		JOptionPane jp = new JOptionPane(null);
		while(!poly.readPolynomial((jp.showInputDialog("Please enter a valid polynomial. \n"
				+ "All terms should have a coefficient, x^, and an exponent"))));
		polyStack.push(poly);
	}
}
