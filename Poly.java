import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * A class that defines a term inside a polynomial.
 * DO NOT MODIFY!!
 */


//0일때도 고려

class Term {
    public int coef; //계수
    public int exp; // 지수

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

    private Term[] terms;   // array of terms, not sorted
    private int next = 0;   // denotes next available slot & (term count)

    /**
     * Creates a new polynomial which can hold up to `termCount` `Term`s.
     * @param termCount
     */
    public Poly(int termCount) {
        // you code goes here
    	terms = new Term[termCount];
    }

    /**
     * Creates a new polynomial with given terms as parameters.
     * @param termCount number of terms
     * @param terms array of terms to be added.
     */
    
    
    //새로운 방정식을 만듦. 더해진 항들의 방정식이 변수.
    public Poly(int termCount, Term... terms) {
        this(termCount);

        if (termCount < terms.length)
            throw new IllegalArgumentException("termCount < terms.length");

        for (int i = 0; i < terms.length; i++) {
            addTerm(terms[i].coef, terms[i].exp);
        }
    }

    /**이 다항식의 차수를 반환 하는 것.
     * Returns the degree of this polynomial.
     * @return degree of polynomial
     */
    public int degree() {
    	int degree = 0;
    	for(int i=0;i<terms.length;i++) {
    		degree += terms[i].exp;
    	}
    	
    	return degree;
//    	return terms[next].exp;
        // your code goes here
    }

    /** 항의 개수를 반환한다. 
     * Returns the number of terms
     * @return the number of terms
     */
    public int getTermCount() {
    	//denotes next available slot
        return next;
    }

    /**
     * Insert a new term into a given polynomial.
     * @param coef coefficient
     * @param exponent exponent
     */
    
//    poly1.addTerm(1,3);
//    poly1.addTerm(2,2);
//    poly1.addTerm(3,1);
//    poly1.addTerm(4,0);
    
    public void addTerm(int coef, int exponent) {
    
    	int n = getTermCount();
    	terms[n] = new Term(coef,exponent);
    	next++;

    }
    	//예외사항 : 텀의 개수가 4개 이상이면.
        // you code goes here

    /**
     * Adds the target polynomial object with the one given as a parameter.
     * As a result, the returned polynomial object will eventually represent
     * the sum of two polynomials (C = A.add(B). Note that A should not be
     * modified as a result of this operation.
     * @param other a polynomial
     * @return a new polynomial (`other` + `this`)
     */
    public Poly add(Poly other) {
    	int len = getTermCount();
    	Term[] new_term = new Term [len];
    	for(int i=0;i<len;i++) {
    		for(int j=0;j<len;j++) {
    			if(terms[i].exp == other.terms[j].exp) {
    				new_term[i] = new Term(terms[i].coef + other.terms[j].coef , i);
    			}
    		}
    	}
        // you code goes here
    	//public Poly(int termCount, Term... terms) 
    	return new Poly(len, new_term);
    }

    /**
     * Multiply the target polynomial object with the one given as a parameter.
     * As a result, the returned polynomial object will eventually represent
     * the product of two polynomials (C = A.mutiply(B). Note that A should not be
     * modified as a result of this operation.
     * @param other a polynomial
     * @return a new polynomial (`other` * `this`)
     */
    public Poly mult(Poly other) {
    	int len = getTermCount();
    	Term[] new_term = new Term [len];
    	for(int i=0;i<len;i++) {
    		for(int j=0;j<len;j++) {
    			if(terms[i].exp == other.terms[j].exp) {
    				new_term[i] = new Term(terms[i].coef * other.terms[j].coef , i);
    			}
    		}
    	}
        // you code goes here
    	//public Poly(int termCount, Term... terms) 
    	return new Poly(len, new_term);
        // you code goes here
    }

    @Override
    
    //sort
    public String toString() {
        // Sample code ... you can freely modify this code if necessary
        Arrays.sort(terms, 0, next, (a, b) -> b.exp - a.exp);
        return Arrays.stream(terms)
                     .filter(i -> i != null)
                     .map(i -> i.toString())
                     .collect(Collectors.joining(" + "));
    }

    
    public static void main(String... args) {
        Poly poly1 = new Poly(4);
        poly1.addTerm(1,3);
        poly1.addTerm(2,2);
        poly1.addTerm(3,1);
        poly1.addTerm(4,0);
        System.out.println(poly1);

        Poly poly2 = new Poly(4);
        poly2.addTerm(2,1);
        poly2.addTerm(1,0);
        poly2.addTerm(3,2);
        poly2.addTerm(4,3);
        System.out.println(poly2);

        Poly poly3 = poly1.add(poly2);
        System.out.println(poly3);

        Poly poly4 = poly1.mult(poly2);
        System.out.println(poly4);
    }
}
