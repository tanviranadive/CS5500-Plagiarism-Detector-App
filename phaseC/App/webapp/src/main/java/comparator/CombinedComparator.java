/**
 * 
 */
package comparator;

/**
 * @author adi
 * @date Mar 21, 2018
 */
public class CombinedComparator implements ASTComparator {

	ASTComparator c1;
	ASTComparator c2;
	Double alpha = 0.5d;
	private String[] tokenizedAst1;
	private String[] tokenizedAst2;
	
	
	public CombinedComparator(String[] tokenizedAst1, String[] tokenizedAst2) {

		this.tokenizedAst1 = tokenizedAst1;
		this.tokenizedAst2 = tokenizedAst2;
	}

	
	@Override
	public Double compare() {
		c1 = new CosineComparator(tokenizedAst1, tokenizedAst2);
		c2 = new SubSequenceComparator(tokenizedAst1, tokenizedAst2);
		Double score;
		score = alpha*c1.compare() + (1-alpha)*c2.compare();
		return score;
	}
	/**
	 * set the degree of contribution from 2 strategies
	 * @param alpha
	 */
	public void setAlpha(Double alpha) {
		this.alpha = alpha;
	}
	
	/**
	 * @return the object variable alpha
	 */
	public Double getAlpha(){
		return this.alpha;
	}

}
