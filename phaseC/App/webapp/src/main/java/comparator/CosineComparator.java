/**
 * 
 */
package comparator;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Compares 2 AST's and gives the cosine similarity
 * @author adi
 * @date Feb 27, 2018
 */
public class CosineComparator implements ASTComparator {
	
	
	private String[] tokenizedAst1;
	private String[] tokenizedAst2;
	
	
	/**
	 * @param tokenizedAst1
	 * @param tokenizedAst2
	 */
	public CosineComparator(String[] tokenizedAst1, String[] tokenizedAst2) {

		this.tokenizedAst1 = tokenizedAst1;
		this.tokenizedAst2 = tokenizedAst2;
	}
	
	
	@Override
	public Double compare() {
		
		Map<String, Integer> mapAst1 = buildFrequencyMap(tokenizedAst1);
		Map<String, Integer> mapAst2 = buildFrequencyMap(tokenizedAst2); 
		
		return cosineSimilarity(mapAst1, mapAst2);
	}
	
	/**
	 * make freuquency map of tokens in AST
	 * @param tokenizedAst
	 * @return
	 */
	public static Map<String, Integer> buildFrequencyMap(String[] tokenizedAst) {
        Map<String, Integer> frequencyMap = new HashMap<>();
   
        for (String term : tokenizedAst) {
            Integer n = frequencyMap.get(term);
            n = (n == null) ? 1 : ++n;
            frequencyMap.put(term, n);
        }
        return frequencyMap;
    }
	
	/**
	 * Calculate cosine similarity of two Hashmaps
	 * @param ast1
	 * @param ast2
	 * @return
	 */
	public double cosineSimilarity(Map<String, Integer> ast1, Map<String, Integer> ast2) {

        //Get unique tokens
        HashSet<String> intersection = new HashSet<>(ast1.keySet());
        intersection.retainAll(ast2.keySet());

        double dotProduct = 0;
        double magnitudeA = 0;
        double magnitudeB = 0;

        //Calculate dot product
        for (String item : intersection) {
            dotProduct += ast1.get(item) * ast2.get(item);
        }

        //Calculate magnitude of 1st
        for (String k : ast1.keySet()) {
            magnitudeA += Math.pow(ast1.get(k), 2);
        }

        //Calculate magnitude of 2nd
        for (String k : ast2.keySet()) {
            magnitudeB += Math.pow(ast2.get(k), 2);
        }
        
        //return cosine similarity
        return 100*(dotProduct / Math.sqrt(magnitudeA * magnitudeB));
    }

}
