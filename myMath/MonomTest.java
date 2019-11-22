package myMath;
import java.util.ArrayList;
/**
 * This class represents a  tester for the Monom class, 
 */
public class MonomTest {
	public static void main(String[] args) {
		//test0();
		//test1();
		//test2();
		//test3();		
		//test4();
		//test5();
		test6();
	}
	//test the 'string' constructor and "toString" function
	private static void test0() {
		System.out.println("*****  Test0:  *****");
		String[] monoms = {"2","-2", "7x^2","-3.2x^2","0", "-1.6x", "-x","-1x", "-1.0*x","0x^0","x^2","5x^0","0*x"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			System.out.println("input: "+ monoms[i]+ " ,monom coef: " + m.get_coefficient() + " ,monom pow: " + m.get_power() + "    toString back again:  " + m.toString());
		}
	}
	//test "isZero" function and "f" function
	private static void test1() {
		System.out.println("*****  Test1:  *****");
		String[] monoms = {"2","-2", "7x^2","-3.2x^2","0", "-1.6x", "-x","-1x", "-1.0*x","0x^0","x^2","5x^0","0*x"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			double fx = m.f(i);
			System.out.println(i+") "+m +"    \tisZero: "+m.isZero()+"\t f("+i+") = "+fx);
			
		}
	}
	//test contractors and 'equals'
	private static void test2() {
		System.out.println("*****  Test2:  *****");
		ArrayList<Monom> monoms = new ArrayList<Monom>();
		//monoms.add(new Monom(0,5));
		monoms.add(new Monom(-1,1));
		monoms.add(new Monom(-1.3,1));
		//monoms.add(new Monom(-2.2,2));
		
		for(int i=0;i<monoms.size();i++) {
			Monom m = new Monom(monoms.get(i));
			String s = m.toString();
			Monom m1 = new Monom(s);
			boolean e = m.equals(m1);
			System.out.println(i+") "+m +"  and: "+ m1+ " equals? : "+e);
		}
	}
	//test "add"  functions
	private static void test3() {
		System.out.println("*****  Test3:  *****");
		String[] monoms = {"2","-2", "7x^2","-3.2x^2","0", "-1.6x", "-x","-1x", "-1.0*x","0x^0","x^2","5x^0","0*x"};
		String[] monoms1 = {"2","-2", "7x^3","-3.2x^2","0", "-1.6x", "-x","-1x", "-1.0*x","0x^0","x^2","5x^0","0*x"};

		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			Monom m1 = new Monom(monoms1[i]);

			System.out.println(monoms[i] + " + " + monoms1[i] +" = " );
			m.add(m1);
			System.out.println( m.toString() );
			System.out.println();
		}
	}
	//test "multiply"  functions
		private static void test4() {
			System.out.println("*****  Test4:  *****");
			String[] monoms = {"2","-2", "7x^2","-3.2x^2","0", "-1.6x", "-x","-1x", "-1.0*x","0x^0","x^2","5x^0","0*x"};
			String[] monoms1 = {"2","-2", "7x^3","-3.2x^2","0", "-1.6x", "-x","-1x", "-1.0*x","0x^0","x^2","5x^0","0*x"};

			for(int i=0;i<monoms.length;i++) {
				Monom m = new Monom(monoms[i]);
				Monom m1 = new Monom(monoms1[i]);

				System.out.println(monoms[i] + " * " + monoms1[i] +" = " );
				m.multipy(m1);
				System.out.println( m.toString() );
				System.out.println();
			}
		}
	//test derivative function
		private static void test5() {
			System.out.println("*****  Test5:  *****");
			String[] monoms = {"2","-2", "7x^2","-3.2x^2","0", "-1.6x", "-x","-1x", "-1.0*x","0x^0","x^2","5x^0","0*x"};
			for(int i=0;i<monoms.length;i++) {
				Monom m = new Monom(monoms[i]);
				String der = m.derivative().toString();
				System.out.println(i+") "+m +"    \tder: \t"+der);
				
			}
		}
		//test isEquals function with epsilon
				private static void test6() {
					System.out.println("*****  Test6:  *****");
					Monom m = new Monom(5.0000001,2);
					Monom m1 = new Monom(5.0,2);
					System.out.println(m.equals(m1));

				}
		
}
