/**
 * 
 */
package comparator;

/**
 * Compare the given ASTs
 * @author adi
 * @date Feb 27, 2018
 */
public interface ASTComparator {

	/**
	 * compare 2 AST's
	 * @return the degree of similarity
	 * Integer between 0 and 100
	 */
	Double compare();

}
