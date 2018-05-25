/**
 * 
 */
package comparator;

/**
 * Changes behavior depending on which comparison strategy is being used.
 * Part of Strategy design pattern.
 * @author adi
 * @date Mar 21, 2018
 */
public class DynamicComparator {
	
	Double score = 0d;
	ASTComparator comparatorType;
	private String[] tokenizedAst1;
	private String[] tokenizedAst2;
	
	/**
	 * @param tokenizedAst1
	 * @param tokenizedAst2
	 */
	public DynamicComparator(String[] tokenizedAst1, String[] tokenizedAst2) {

		this.tokenizedAst1 = tokenizedAst1;
		this.tokenizedAst2 = tokenizedAst2;
	}
	
	public DynamicComparator() {
		score = 0d;
		comparatorType = null;
	}
	
	/**
	 * sets the Comparator strategy based on ASTComparator object
	 * @param astC: ASTComparator object
	 */
	public void setComparatorStrategy(ASTComparator astC){
		comparatorType = astC;
	}
	
	/**
	 * call to comparison
	 */
	public void runComparison() {
		score = comparatorType.compare();
	}
	
	
	public Double getScore() {
		return score;
	}

}
