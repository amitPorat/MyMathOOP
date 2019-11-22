
package myMath;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Amit
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	
	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power() == 0) {
			return getNewZeroMonom();
		}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {
		return this.get_coefficient() == 0;
	}
	// ***************** add your code below **********************
	
	
	public void add(Monom m) {
		if (this._power == m._power  ) {this._coefficient+=m._coefficient;}
		else throw new ArithmeticException("bad math-monoms must have the same power");
	}
	public void subtract(Monom m) {
		if (this._power == m._power  ) {this._coefficient-=m._coefficient;}
		else throw new ArithmeticException("bad math-monoms must have the same power");		
	}
	
	public void multipy(Monom d) {
		this._coefficient*=d._coefficient;
		this._power+=d._power;
	}
	
	public String toString() {
		String ans = "*x^";
		if(this._power != 0)
			return this._coefficient + ans + this._power;
		return "" + this._coefficient;
	}
	

	public Monom(String s) {
		s.toLowerCase();
		if (s.indexOf('x') != -1) {
			if (s.indexOf('x') == 0) {
				_coefficient = 1;
				_power = (int) evalPow(s.substring(1, s.length()));
			} else if (s.indexOf('x') == s.length()) {
				_coefficient = evalCoef(s.substring(0, s.length() - 1));
				_power = 1;
			} else {
				int x_pos = s.indexOf('x');
				_coefficient = evalCoef(s.substring(0, x_pos));
				if (x_pos + 1 < s.length()) {
					_power = (int) evalPow(s.substring(x_pos + 1, s.length()));
				} else {
					_power = 1;
				}
			}
		}
		else {
			_coefficient = evalCoef(s);
			_power = 0;
		}
	}

	// you may (always) add other methods.
	
	
	/**
	 * this function gets String and cast it to valid math expression
	 * possible to extends it to a lot more complex expressions 
	 * @param s
	 * @return  double (simple math expression)
	 */
	public static double evalCoef(String str) {
		double temp_coef = 0.0;
		if(str.charAt(str.length()-1)=='*') {str=str.substring(0, str.length()-1);}
		if(str.indexOf("-") == 0 && str.length()==1) {return -1;}
		//add here try catch statment
		temp_coef = Double.parseDouble(str.substring(0, str.length()));
		return temp_coef;
	}
	public static double evalPow(String str) {
		double temp_pow=0.0;
		if (str != null ) {temp_pow = 1;}
		if (str.indexOf('^') == 0 ){temp_pow=Double.parseDouble(str.substring(1, str.length()));}
		if ( temp_pow < 0) {
			throw new ArithmeticException("negative power not allowed in this program");
		}
		//add here try catch statment
		return temp_pow;
	}
	/**
	 * is this monom equals to m monom. 
	 * also deal numeric errors using the final epsilon 
	 * @param m
	 * @return
	 */
	public boolean equals(Monom m){
		if (Math.abs(this._coefficient - m._coefficient) < EPSILON ) {
			return true;
		}
		return this._power == m._power && this._coefficient == m._coefficient ;
	}
	
	
	
	
	
	
	
	
	//****************** Private Methods and Data *****************
	

	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	
	
	
}
