import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A class that defines a term inside a polynomial. DO NOT MODIFY!!
 */

//0�϶��� ���

class Term {
	public int coef; // ���
	public int exp; // ����

	public Term(int coef, int exp) {
		this.coef = coef;
		this.exp = exp;
	}

	@Override
	public String toString() {
		return coef + "x^" + exp;
	}
}

/**
 * A Polynomial ADT
 */
public class Poly {

	private Term[] terms; // array of terms, not sorted
	private int next = 0; // denotes next available slot & (term count)

	/**
	 * Creates a new polynomial which can hold up to `termCount` `Term`s.
	 * 
	 * @param termCount
	 */
	public Poly(int termCount) {
		// you code goes here
		terms = new Term[termCount];
	}

	/**
	 * Creates a new polynomial with given terms as parameters.
	 * 
	 * @param termCount number of terms
	 * @param terms     array of terms to be added.
	 */

	// ���ο� �������� ����. �������ų� ������ �׵��� �������� ����.
	public Poly(int termCount, Term... terms) {
		this(termCount);

		if (termCount < terms.length)
			throw new IllegalArgumentException("termCount < terms.length");

		for (int i = 0; i < terms.length; i++) {
			addTerm(terms[i].coef, terms[i].exp);
		}
	}

	/**
	 * �� ���׽��� ������ ��ȯ �ϴ� ��. Returns the degree of this polynomial.
	 * 
	 * @return degree of polynomial
	 */
	public int degree() {

		// ���� �����ִ��� �𸣰���.

		int degree;
		int max = 0;
		for (int i = 0; i < this.getTermCount(); i++) {
			if (max < this.terms[i].exp) {
				max = this.terms[i].exp;
			}
		}
		degree = max;
		return degree;
		// your code goes here
	}

	/**
	 * ���� ������ ��ȯ�Ѵ�. Returns the number of terms
	 * 
	 * @return the number of terms
	 */
	public int getTermCount() {
		// denotes next available slot
		return next;
	}

	/**
	 * Insert a new term into a given polynomial.
	 * 
	 * @param coef     coefficient
	 * @param exponent exponent
	 */

//    poly1.addTerm(1,3);
//    poly1.addTerm(2,2);
//    poly1.addTerm(3,1);
//    poly1.addTerm(4,0);

	public void addTerm(int coef, int exponent) {

		int n = getTermCount();
		if (coef != 0) {
			// ����� 0�� �ƴϸ� �� �߰�
			terms[n] = new Term(coef, exponent);
			next++;
		}
	}


	/**
	 * Adds the target polynomial object with the one given as a parameter. As a
	 * result, the returned polynomial object will eventually represent the sum of
	 * two polynomials (C = A.add(B). Note that A should not be modified as a result
	 * of this operation.
	 * 
	 * @param other a polynomial
	 * @return a new polynomial (`other` + `this`)
	 */
	public Poly add(Poly other) {
		// x^2 + x^3 + x^4 + x^6 -> �ΰ��� ���� ������ ��� �ٸ� ���
		// max_length�������� �迭�� ����� �ȵǴ� ����..

		int len = other.getTermCount() + this.getTermCount();
		Term[] new_term = new Term[len];

		for (int x = 0; x < len; x++) {
			new_term[x] = new Term(0, 0);
		}

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < this.getTermCount(); j++) {
				if (i == this.terms[j].exp) {
					new_term[i].coef += this.terms[j].coef;
					new_term[i].exp = i;
				}
			}
			for (int z = 0; z < other.getTermCount(); z++) {
				if (i == other.terms[z].exp) {
					new_term[i].coef += other.terms[z].coef;
					new_term[i].exp = i;
				}
			}
		}

		// �� �������� ���̸� ������ �ʿ�� ����.

		return new Poly(len, new_term);
	}

	/**
	 * Multiply the target polynomial object with the one given as a parameter. As a
	 * result, the returned polynomial object will eventually represent the product
	 * of two polynomials (C = A.mutiply(B). Note that A should not be modified as a
	 * result of this operation.
	 * 
	 * @param other a polynomial
	 * @return a new polynomial (`other` * `this`)
	 */
	public Poly mult(Poly other) {
		// �ִ��� ���� -> ��ġ�� ���� ���� ��� -> n+m ������ �� ����

		int len1 = this.getTermCount();
		int len2 = other.getTermCount();
		int len = len1 + len2 + 1; //������ �������� �׿� ������ ���̱� ������
		Term[] new_term = new Term[len];

		for (int x = 0; x < len; x++) {
			new_term[x] = new Term(0, 0);
		}

		for (int i = 0; i < len1; i++) {
			for (int j = 0; j < len2; j++) {
				int new_exp = this.terms[i].exp + other.terms[j].exp;
				new_term[new_exp].coef += this.terms[i].coef * other.terms[j].coef;
				new_term[new_exp].exp = new_exp;
				//new_exp-1�� �ȵǴ� ���� -> 0-1=-1��° ������ ���� ����
			}
		}

		return new Poly(len, new_term);

	}

	@Override

	// sort
	public String toString() {
		// Sample code ... you can freely modify this code if necessary
		Arrays.sort(terms, 0, next, (a, b) -> b.exp - a.exp);
		return Arrays.stream(terms).filter(i -> i != null).map(i -> i.toString()).collect(Collectors.joining(" + "));
	}
	
//	.filter(i -> i.coef != 0)�� ���ַ� ������, addTerm���� ��� 0�� �Ÿ� �� �����Ƿ� ��. �˾ƴ� �� ��.

	public static void main(String... args) {

		Poly poly1 = new Poly(4);
		poly1.addTerm(1, 3);
		poly1.addTerm(2, 2);
		poly1.addTerm(3, 1);
		poly1.addTerm(4, 0);

		Poly poly2 = new Poly(4);
		poly2.addTerm(2, 1);
		poly2.addTerm(1, 0);
		poly2.addTerm(4, 3);

		Poly poly3 = poly1.mult(poly2);
		System.out.println(poly3);

	}
}
