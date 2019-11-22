package myMath;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;  
import myMath.Monom;
/**
 * This class represents a Polynom with add, subtract, multiply functionality, it also support the following:
 * 1. Riemann's Integral.
 * 2. polynom root.
 * 3. Derivative
 * 4. Other Regular class methods like equals, to string, ETC
 * 
 * @author Amit Porat
 *
 */
public class Polynom implements Polynom_able{
	
	/** The polynom. */
	private ArrayList<Monom> polynom;
	
	/**
	 * Zero (empty polynom).
	 */
	public Polynom() {
		this.polynom = new ArrayList<Monom>();
		//this.polynom.add(0, new Monom(0,0));                   maybe Its needed
	}
	
	/**
	 * constructor: init a Polynom from a String such as:
	 *   {"x^2", "1-x", "-3x+1.2x^5"}
	 *
	 * @param s the s
	 * @throws Exception the exception
	 */
	public Polynom(String s) throws Exception {
		if (cheackValidExpression(s)) {
			this.polynom = new ArrayList<Monom>();
			s.toLowerCase();
			s.replace(" ", "");
			String replacedMinus = s.replaceAll("-", "+-");
			if (replacedMinus.charAt(0) == '+') {
				replacedMinus = replacedMinus.substring(1);
			}
			String[] splitString = replacedMinus.split("\\+");
			for (int i = 0; i < splitString.length; i++) {
				polynom.add(new Monom(splitString[i]));
			}
		} 
		else {
			throw new Exception("NotValidString");
		}
	}
	
	/**
	 * this function checks if a given string could be valid polynom
	 * Called from the constractor(arg = string)
	 * worked with https://www.tutorialspoint.com/java/java_regular_expressions.htm
	 * It looks super elegant then "stringTokenizer" which I implement at the beginning 
	 * [+-]?  --> expression can start with + or -
	 * [0-9]*  --> valid math expression is a collection of digits. * stands for "zero digits or more"
	 * [.]? --> optional floating point
	 * [0-9]* --> same as above 
	 * we marks groups with "()" and mark like "?x?" means : Dont count 'x' as a group
	 * ?:\\* --> group regex without remembering the match txt.
	 * (?:\\^[0-9]+)?)? --> power is optional + stands for "one digit or more"
	 * note: "\\" comes before special chars like '^' for example,  that char usable in regex class.  (same goes if we want to print '/n' without LAREDET SHURA)
	 *
	 * @param s the s
	 * @return true if it is valid expression
	 */
	private boolean cheackValidExpression(String s) {
		return(s.matches("([+-]?[0-9]*[.]?[0-9]*(?:\\*?x?(?:\\^[0-9]+)?)?)*")) ; 
	}
	
	/**
	 * This function compute f(x) given x.
	 *
	 * @param x the x
	 * @return the double
	 */
	@Override
	public double f(double x) {
		double resualt = 0;
		Iterator<Monom> itr=this.iteretor();
		while (itr.hasNext()) {
			Monom m = itr.next();
			resualt+=m.f(x);
		}
		return resualt; 
	}
	
	/**
	 * This function adds monom to this polynom. 
	 * It's handle two cases: 1. there is a monom with equal power in this polynom.
	 * 2. there isn't suitable monom so we add a new one.
	 *
	 * @param m1 the m 1
	 */
	@Override
	public void add(Monom m1) {
		Iterator<Monom> itr = this.iteretor();
		while(itr.hasNext()) {
			Monom m = itr.next();
			if(m.get_power() == m1.get_power()) {
				m.add(m1);
				return;
			}
		}
		this.polynom.add(m1);
	}
	
	/**
	 * adding two polynoms.
	 *
	 * @param p1 the p 1
	 */
	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> itr = p1.iteretor();
		while (itr.hasNext()) {
			Monom m = itr.next();
			add(m);
		}
	}

	
	/**
	 * subtract this polynom and the argument received.
	 *
	 * @param p1 the p 1
	 */
	@Override
	public void substract(Polynom_able p1) {
		Iterator<Monom> itr = p1.iteretor();
		while (itr.hasNext()) {
			Monom m = itr.next();
			m.multipy(new Monom("-1"));
			add(m);
		}
	
	}

	/**
	 * this function multiply this polynom with a given monom.
	 *
	 * @param m1 the m 1
	 */
	@Override
	public void multiply(Monom m1) {
		Iterator<Monom> itr = this.iteretor();
		while(itr.hasNext()) {
			Monom m = itr.next();
			m.multipy(m1);
		}
	}
	
	//baaya- maybe i c use the function above
	/**
	 * multiply two polynoms. I implemented this using new polynom object created.
	 *
	 * @param p1 the p 1
	 */
	@Override
	public void multiply(Polynom_able p1) {
		Polynom newPoly = new Polynom();
		Iterator<Monom> itr = p1.iteretor();
		while(itr.hasNext()) {
			Polynom_able newPoly1 = this.copy();
			Monom monom = itr.next();
			newPoly1.multiply(monom);
			newPoly.add(newPoly1);
			//Iterator<Monom> thisItr = this.iteretor();
			//while(thisItr.hasNext()) {
			//	Monom thisMonom = thisItr.next();
			//	thisMonom.multipy(monom);
			//}
		}
		this.polynom = newPoly.polynom;
	}

	/**
	 * My equals function, equals two polynoms using two suitable iterators.
	 * returns True iff all the monoms are equals.
	 *
	 * @param p1 the p 1
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Polynom_able p1) {
		Iterator<Monom> itr = p1.iteretor();
		Iterator<Monom> thisItr = this.iteretor();
		while(itr.hasNext() && thisItr.hasNext()) {
			Monom monom = itr.next();
			Monom thisMonom = thisItr.next();
			if(!monom.equals(thisMonom)) {
				return false;
			}
		}
		if(itr.hasNext() || thisItr.hasNext())
			return false;
		return true;
	}
	
	/**
	 * this function cheaks if polynom value is zero.
	 *
	 * @return true, if is zero
	 */
	@Override
	public boolean isZero() {
		Iterator<Monom> thisItr = this.iteretor();
		while(thisItr.hasNext()) {
			Monom m = thisItr.next();
			if(!m.isZero())
				return false;
		}
		return true;
	}

	/**
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps
	 * assuming (f(x0)*f(x1)<=0, else should throws runtimeException 
	 * computes f(x') such that:
	 * 	(i) x0<=x'<=x1 && 
	 * 	(ii) |f(x')|<eps
	 * 
	 * in case of two or more roots, the function will return one of them.
	 *
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps the eps
	 * @return an approximated value (root) for this (cont.) function
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		if(this.f(x0)*this.f(x1) > 0)
			throw new RuntimeException("f(x0)*f(x1) must be <= 0");
		
		for(double xTag = x0; xTag <= x1 ; xTag += Double.MIN_VALUE) {
			if(Math.abs(this.f(xTag)) < eps)
				return xTag;
		}
		
		throw new RuntimeException("unexpected state");
	}
	
	/**
	 * creating new (!) polynom copy
	 * so called "deep copy".
	 *
	 * @return the polynom able
	 */
	@Override
	public Polynom_able copy() {
		Polynom_able pol_copy = new Polynom();
		Iterator <Monom> pol_copy_itr = this.iteretor();
		while(pol_copy_itr.hasNext())
		{
			Monom temp = pol_copy_itr.next();
			pol_copy.add(new Monom(temp.get_coefficient(),temp.get_power()));
		}
		return pol_copy;
	}

	/**
	 * Derivative.
	 *
	 * @return the polynom able
	 */
	@Override
	/**
	 * returns new(!) polynom that represent this derivative polynom, 
	 * without any changes of the original polynom
	 */
	public Polynom_able derivative() {
		Polynom_able der_pol = new Polynom();
		Iterator<Monom> der_pol_itr = this.iteretor();
		while(der_pol_itr.hasNext()) {
			Monom temp = der_pol_itr.next();
			temp = temp.derivative();
			der_pol.add(temp);
		}

		return der_pol;
	}

	/**
	 * Area.
	 *
	 * @param x0 the x 0
	 * @param x1 the x 1
	 * @param eps the eps
	 * @return the double
	 */
	@Override
	/**
	 * Compute a Riman's integral from x0 to x1 in eps steps. 
	 * @param x0 starting pooint
	 * @param x1 end point
	 * @param eps positive step value
	 * @return the approximated area above X-axis below this function bounded in the range of [x0,x1]
	 */
	public double area(double x0, double x1, double eps) {
		double area =0.0;
		if (x0 >= x1)
			return 0.0;
		if (x0 == 0)
			x0+=0.000000001;
		for(double xTag = x0; xTag <= x1 ; xTag += eps) {
			if (this.f(xTag)>0)
				area += eps * f(xTag);
			else 
				System.out.println("bad input. f(x) must be positive number as mention is the notes ");				//an other way to solve it: area += eps * Math.abs(f(xTag));

		}
				return area;
	}

	/**
	 * Iteretor.
	 *
	 * @return the iterator
	 */
	@Override
	public Iterator<Monom> iteretor() {
		return polynom.iterator();
	}
	
	/**
	 * return this polynom representing by String data type.
	 *
	 * @return the string
	 */
		@Override
	public String toString() 
	{
		this.polynom.sort(Monom.getComp());
		
		String str = "";
		for(Monom m : this.polynom) {
			str+= m.get_coefficient() < 0 ? m.toString() : '+'+m.toString();
		}
		if(str.charAt(0) == '+') {
			str = str.substring(1);
		}
		return str;
	}
	
	
}
