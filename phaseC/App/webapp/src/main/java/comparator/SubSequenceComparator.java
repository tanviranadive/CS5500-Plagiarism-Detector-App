/**
 * 
 */
package comparator;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * comparator using subsequence matching
 * @author adi
 * @date Feb 27, 2018
 */
public class SubSequenceComparator implements ASTComparator {

	private String[] tokenizedAst1;
	private String[] tokenizedAst2;
	private int degOfTolerance = 3;
	Map<Integer, List<Integer[]>> matchingSequences;
	/**
	 * constructor with deg)fTolerance value
	 * @param tokenizedAst1
	 * @param tokenizedAst2
	 * @param degOfTolerance
	 * @param matching_sequences
	 */
	public SubSequenceComparator(String[] tokenizedAst1, String[] tokenizedAst2, int degOfTolerance) {
		super();
		this.tokenizedAst1 = tokenizedAst1;
		this.tokenizedAst2 = tokenizedAst2;
		this.degOfTolerance = degOfTolerance;
		this.matchingSequences = new TreeMap<>();
	}

	/**
	 * constructor using default degreeOfTolerance
	 * @param tokenizedAst1
	 * @param tokenizedAst2
	 * @param matching_sequences
	 */
	public SubSequenceComparator(String[] tokenizedAst1, String[] tokenizedAst2) {
		super();
		this.tokenizedAst1 = tokenizedAst1;
		this.tokenizedAst2 = tokenizedAst2;
		this.matchingSequences = new TreeMap<>();
	}
	
	
	
	
	
	@Override
	public Double compare() {
		Double total = 0d;
		longestCommonSubsequence(tokenizedAst1, tokenizedAst2);
		for(Integer keys : matchingSequences.keySet()) {			
			total += keys*matchingSequences.get(keys).size();
		}
		return 100*(total/Integer.max(tokenizedAst1.length, tokenizedAst2.length));
	}

	public void longestCommonSubsequence(String[] ast1, String[] ast2) {


		Integer[][]  combinedArr = new Integer[ast1.length+1][ast2.length+1];
		Set<Integer> ast1Set = new HashSet<>();
		Set<Integer> ast2Set = new HashSet<>();
		for(int i=0; i<=ast1.length;i++)
		{
			combinedArr[i][0] = 0;
		}
		for(int j = 0; j<=ast2.length;j++)
		{
			combinedArr[0][j] = 0;
		}
		int counter = 0;
		for(int i= 1; i<=ast1.length;i++)
		{
			for(int j=1;j<=ast2.length;j++)
			{

				if(ast1[i-1].equals(ast2[j-1]) && !ast1Set.contains(i) && !ast2Set.contains(j))
				{					
					combinedArr[i][j] = combinedArr[i-1][j-1] + 1;
					ast1Set.add(i);
					ast2Set.add(j);
					counter++;
				}
				else
				{
					if(counter>degOfTolerance) {
						if(matchingSequences.containsKey(counter)) {
							matchingSequences.get(counter).add(new Integer[] {i, j});
						}
						else {
							matchingSequences.put(counter, new LinkedList<Integer[]>());
							matchingSequences.get(counter).add(new Integer[] {i, j});
						}
						degOfTolerance = counter;
						counter = 0;
					}
					combinedArr[i][j] = Integer.max(combinedArr[i][j-1], combinedArr[i-1][j]);
				}
			}
		}
		if(matchingSequences.containsKey(counter)) {
			matchingSequences.get(counter).add(new Integer[] {ast1.length, ast2.length});
		}
		else {
			matchingSequences.put(counter, new LinkedList<Integer[]>());
			matchingSequences.get(counter).add(new Integer[] {ast1.length, ast2.length});
		}
	}

}
