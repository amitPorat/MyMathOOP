package myMath;

public class PolynomTest {
	public static void main(String[] args) {
		//test1();
		//try {
		//	testRoot();
		//} catch (Exception e) {
		//	e.printStackTrace();
		//}
		try {
			testToString();
} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void test1() {
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		//for(int i=0;i<monoms.length;i++) {
		Monom m = new Monom(monoms[1]);
		p1.add(m);
		//double aa = p1.area(0, 1, 0.0001);
		p1.substract(p1);
		System.out.println(p1);
	}
	
	
	
	public static void test2() {
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.substract(p2);
		System.out.println("p1-p2: "+p1);
		//p1.multiply(p2);
		//System.out.println("(p1+p2)*p2: "+p1);
		//String s1 = p1.toString();
		//Polynom_able pp1 = Polynom.parse(s1);
		//System.out.println("from string: "+pp1);
	}
	
	
	public static void testToString() throws Exception {
		Polynom p1 = new Polynom("-5x^2+3x-7"); 
		Polynom p2 = new Polynom("-5.00000001x^2+3x-7"); 
		System.out.println(p1.equals(p2));
	}
	public static void testToString_f() throws Exception {
		Polynom p1 = new Polynom("-5x^2+3x-7"); 
		System.out.println(p1.toString());
		System.out.println(p1.f(2));
	}
	
	
	public static void testAddPolinomMonom() throws Exception {
		Polynom p = new Polynom("-5x^2+3x-7"); 
		Monom m = new Monom("2x^3"); //failed
		System.out.println("p is: " +p.toString());
		System.out.println("m is: "+m.toString());
		p.add(m);
		System.out.println("p.add(m) is: "+ p.toString());
		System.out.println();
		Polynom p1 = new Polynom("-5x^2+3x-7"); 
		Monom m1 = new Monom("2x^2"); //failed
		System.out.println("p1 is: " +p1.toString());
		System.out.println("m1 is: "+m1.toString());
		p1.add(m1);
		System.out.println("p1.add(m1) is: "+ p1.toString());
		
	}
	
	
	public static void testAddPolinomPolinom() throws Exception {
		Polynom p1 = new Polynom("-5x^2+3x-7"); 
		Polynom p2 = new Polynom("2x^3+7x^2+5x+6"); //failed
		System.out.println("p1 is: " +p1.toString());
		System.out.println("p2 is: "+p2.toString());
		p1.add(p2);
		System.out.println("resualt: " + p1.toString());
	}
	
	
	public static void testMultiply() throws Exception {
		Polynom p1 = new Polynom("-x^2+3x"); 
		Polynom p2 = new Polynom("10x^3+2"); //failed
		p1.multiply(p2);
		System.out.println(p1.toString());		
	}
	
	public static void testRoot() throws Exception {
		Polynom p1 = new Polynom("x-7"); 
		double r = p1.root(6.9999,7.1,0.001);
		System.out.println(String.format("The root of [%s] is: %f", p1, r));
	}
	public static void testAll() {
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		//for(int i=0;i<monoms.length;i++) {
		Monom m = new Monom(monoms[1]);
		p1.add(m);
		//double aa = p1.area(0, 1, 0.0001);
		p1.substract(p1);
		System.out.println(p1);
	}
	public static void testCopy() throws Exception{
		Polynom p = new Polynom("x-7");
		Polynom p1 = (Polynom) p.copy();
		System.out.println(p1.toString());

	}
	public static void testDerivative() throws Exception{
		Polynom p = new Polynom("10x^3+2"); 
		Polynom p1 = (Polynom) p.derivative();
		System.out.println(p1.toString());	}
	
	public static void testEquals() throws Exception{
		Polynom p = new Polynom("10.0*x^3+2"); 
		Polynom p1 =  new Polynom("10x^3+2.0");
		System.out.println(p1.equals(p));	
	}
	public static void testIsZero() throws Exception{
		Polynom p = new Polynom("0x^0");
		System.out.println(p.isZero());

	}


	public static void testArea() throws Exception {
		Polynom p = new Polynom("x^1");
		System.out.println(	p.area(1, 2, 0.000001));

	}

	public static void testSubstract() throws Exception {
		Polynom p1 = new Polynom("-5x^2+3x-7"); 
		Polynom p2 = new Polynom("x^3+5x^2+6"); //failed
		System.out.println("p1 is: " +p1.toString());
		System.out.println("p2 is: "+p2.toString());
		p1.substract(p2);
		System.out.println("resualt: " + p1.toString());
	}

	
}
