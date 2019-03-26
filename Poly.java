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
    	
    	//조건이 있는지는 모르겠음
    	
    	int degree;
    	int max = 0;
    	for(int i=0;i<this.getTermCount();i++) {
    		if(max < this.terms[i].exp) {
    			max = this.terms[i].exp;
    		}
    	}
    	degree = max;
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
    	//x^2 + x^3 + x^4
    	//x^6 
    	//length기준으로 배열을 만들면 안되는 이유.. 
    	

    	int len = other.terms.length + this.terms.length; 
    	Term[] new_term = new Term [len];

    	for(int x=0;x<len;x++) {
    		new_term[x] = new Term(0,0);
    	}
    	
    	for(int i=0;i<len;i++) {
    		for(int j = 0;j<this.getTermCount();j++) {
    			if(i == this.terms[j].exp) {
    				new_term[i].coef += this.terms[j].coef;
    				new_term[i].exp = i;
    				
        		}
    		}
    		for(int z = 0;z<other.getTermCount();z++) {
    			if(i == other.terms[z].exp) {
    				new_term[i].coef += other.terms[z].coef;
    				new_term[i].exp = i;
        		}
    		}
    		
    	}
    	
    	
    	
    	
    	//len을 비교해줄 필요가 있는가!
    	
//    	int len1 = getTermCount();
//    	int len2 = other.terms.length;
//    	int max_len, min_len;
//    	if(len1 < len2) {
//    		max_len = len2; min_len = len1;
//    		Term[] new_term = new Term [max_len];
//    		for(int i=0;i<min_len;i++) {
//        		for(int j=0;j<max_len;j++) {
//        			if(terms[i].exp == other.terms[j].exp) {
//        				new_term[j] = new Term(terms[i].coef + other.terms[j].coef , terms[i].exp);
//        			}
//        		}
//        	}
//    	}
//    	else{
//    		max_len = len1; min_len = len2;
//    		Term[] new_term = new Term [max_len];
//    		for(int i=0;i<min_len;i++) {
//        		for(int j=0;j<max_len;j++) {
//        			if(terms[i].exp == other.terms[j].exp) {
//        				new_term[j] = new Term(terms[i].coef + other.terms[j].coef , terms[i].exp);
//        			}
//        		}
//        		
//        		
//        	}
//    	}
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
    	int len1 = terms.length;
    	int len2 = other.terms.length;
    	int max_len, min_len;
    	if(len1 < len2); max_len = len2; min_len = len1;
    	max_len = len1; min_len = len2;
    	//대소 비교 해주어야 함. int len = if(len1 < len2) return; 
    	
    	Term[] new_term = new Term [len1 * len2]; //최대의 개수는 얼마일까요....
    	for(int i=0;i<max_len;i++) {
    		for(int j=0;j<min_len;j++) {
    			new_term[i] = new Term(terms[i].coef * other.terms[j].coef , terms[i].exp );
    		}
    	}
        // you code goes here
    	
    
    	return 
    			
        // you code goes here
    }

    @Override
    
    //sort
    public String toString() {
        // Sample code ... you can freely modify this code if necessary
        Arrays.sort(terms, 0, next, (a, b) -> b.exp - a.exp);
        return Arrays.stream(terms)
                     .filter(i -> i != null)
                     .filter(i -> i.coef != 0)
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
//        poly2.addTerm(0,0);
//        poly2.addTerm(3,2);
        poly2.addTerm(4,3);
        System.out.println(poly2);

        Poly poly3 = poly1.add(poly2);
        System.out.println(poly3);

//        Poly poly4 = poly1.mult(poly2);
//        System.out.println(poly4);
    }
}
