
/*
 * 짤 2019 CSE2010 HW #2
 *
 * You can freely modify this class except the signatures of the public methods!!
 */

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

class Term {
	/*
	 * Public field is a bad idea. But, for the sake of simplicity .....
	 */
	public double coeff; // coefficient
	public int expo; // exponent

	public Term(double coeff, int expo) {
		this.coeff = coeff;
		this.expo = expo;
	}

	public static int compare(Term t1, Term t2) {
		if (t1.expo > t2.expo)
			return 1;
		if (t1.expo < t2.expo)
			return -1;
		return 0;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Term))
			return false;
		final Term term = (Term) o;
		return Double.compare(term.coeff, coeff) == 0 && expo == term.expo;
	}
}

/*
 * class DLinkedPolynomial
 */
public class DLinkedPolynomial implements Polynomial {

	private DLinkedList<Term> list = null;

	public DLinkedPolynomial() {
		list = new DLinkedList<>();
	}

	@Override
	/**
	 * Returns the degree of the polynomial.
	 * 
	 * @return degree
	 */

	public int getDegree() {

		int max = 0;
		Node<Term> current = list.getFirstNode();
		while (current != null) {
			if (max < current.getInfo().expo) {
				max = current.getInfo().expo;
				current = list.getNextNode(current);
			}
			current = list.getNextNode(current);
		}
		return max;
	}

	@Override

	/**
	 * Returns the coefficient of the term with exponent "exp".
	 * 
	 * @param exp exponent of the term
	 * @return the coefficient
	 */

//	Comparator<Term> comparator = (t1, t2) -> {
//        return t1.equals(t2) ? 0 : 1;
//    };
//
//    Node<Term> node = list.find(new Term(50.0, 50), comparator);
//    assertEquals(new Term(50.0, 50), node.getInfo());

	public double getCoefficient(final int expo) {
		Node<Term> current = list.getFirstNode();
		while (current != null) {
			if (current.getInfo().expo == expo) {
				break;
			} else {
				current = list.getNextNode(current);
			}
		}
		return current.getInfo().coeff;
		// 없으면 무엇을 반환????
	}

	/**
	 * Add this polynomial object with the polynomial "p" and returns the resulting
	 * polynomial (c = a + b) This object should "NOT" be changed as a result of
	 * this operation.
	 * 
	 * @param p polynomial to be added
	 * @return a polynomial
	 */
	@Override
	public Polynomial add(final Polynomial p) {

//		Polynomial new_p = new DLinkedPolynomial();
//		Node<Term> p1_current = list.getFirstNode();
//		
//		int p_degree = p.getDegree();
//		while(p1_current != null || p_degree>=0) {
//			if(p1_current != null) {
//				new_p.addTerm(p1_current.getInfo().coeff, p1_current.getInfo().expo);
//				p1_current = list.getNextNode(p1_current);
//			}
//			
//			for(int i=0;i<p_degree;i++) {
//				new_p.addTerm(p.getCoefficient(i), i);
//			}
//			
//		}
//		return new_p;
	}

	@Override
	public Polynomial mult(final Polynomial p) {
	}

	@Override

	public void addTerm(final double coeff, int expo) {

		Node<Term> add = new Node<>(new Term(coeff, expo));
		// 4
		if (list.isEmpty()) {
			list.addFirst(add);
		} else {
			Node<Term> current = list.getFirstNode();
			Node<Term> key = list.getFirstNode();

			// 2,1,3
			// first = 2
			int cur_exp = 0;
			while (current != null) {
				cur_exp = current.getInfo().expo; // ->2
				if (cur_exp > expo) {
					key = current;
					current = list.getNextNode(current);
				} else if (cur_exp < expo) {
					current = list.getNextNode(current);
				} else {
					current.getInfo().coeff += coeff;
					break;
				}
			}

			if (key.getInfo().expo > expo && current == null) {
				list.addAfter(key, add);
			}

			else if (key.getInfo().expo < expo && current == null) {
				list.addBefore(key, add);
			}

		}

	}

	/**
	 * Returns the result of the polynomial evaluation for a given input "x".
	 * 
	 * @param x the value to be evaluated by this polynomial
	 * @return an evaluated value at point "x"
	 */

	@Override
	
//	* @throws NoSuchTermExistsException if no such term exists
	
	public void removeTerm(final int expo) {
		
		
//		Exception NoSuchTermExistsException = null;
//		try {
//			Node<Term> current = list.getFirstNode();
//			while (current != null) {
//				if (current.getInfo().expo == expo) {
//					list.remove(current);
//					break;
//				} else {
//					current = list.getNextNode(current);
//				}
//			}
//			if (current == null) {
//				
//			}
//		}catch(Exception e) {
//			throw NoSuchTermExistsException;
//		}

		
		Node<Term> current = list.getFirstNode();
		while (current != null) {
			if (current.getInfo().expo == expo) {
				list.remove(current);
				break;
			} else {
				current = list.getNextNode(current);
			}
		}

		if (current == null) {
			Exception NoSuchTermExistsException;
			throw NoSuchTermExistsException;
		}
	}

	@Override
	public double evaluate(final double val) {
		double sum = 0.0;
		Node<Term> current = list.getFirstNode();
		while (current != null) {
			sum += current.getInfo().coeff * Math.pow(val, current.getInfo().expo);
			current = list.getNextNode(current);
		}
		return sum;
	}

	@Override
	public int termCount() {
		return this.list.size();

	}

	@Override
	public String toString() {
		if (list.isEmpty())
			return "Empty Polynomial";
		else {
			String[] terms = new String[termCount()];
			int i = 0;
			Node<Term> current = list.getFirstNode();
			do {
				terms[i++] = current.getInfo().coeff + "x^" + current.getInfo().expo;
				current = list.getNextNode(current);
			} while (current != null);
			return Arrays.stream(terms).collect(Collectors.joining("+"));
		}
	}

	public static void main(String[] args) {

		Polynomial f = new DLinkedPolynomial();

		f.addTerm(3.0, 2);
		f.addTerm(7.0, 0);
		f.removeTerm(3);
//		Polynomial p = new DLinkedPolynomial();
//		
//		p.addTerm(2.0, 2);
//		p.addTerm(1.0, 3);
//		p.addTerm(3.0, 1);
//		p.addTerm(4.0, 0);
//		p.addTerm(2.0, 2);
//		p.addTerm(1.0, 3);
//		p.addTerm(4.0, 0);
//		p.addTerm(3.0, 1);
//		
//		System.out.println(p.toString());
//		System.out.println(p.getDegree());

	}

}
