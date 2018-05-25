/**
 * 
 */
package app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import comparator.CombinedComparator;
import comparator.CosineComparator;
import comparator.DynamicComparator;
import comparator.SubSequenceComparator;

/**
 * Run plagiarism check between 2 folders.
 * 
 * @author Deepak
 * @date Apr 1, 2018
 */
public class Compare {

	String[] tokenizedAst1;
	String[] tokenizedAst2;


	/**
	 * compare folder1 and folder2 with the mentioned strategy
	 * 
	 * @param type
	 * @param folderpath1
	 * @param folderpath2
	 * @return
	 */
	public List<CompareResult> compare(String type, String folderpath1, String folderpath2) {
		ArrayList<CompareResult> resultList = new ArrayList<CompareResult>();
		
		String[] astFolderPaths = ASTGenerator.generateASTs(folderpath1, folderpath2);
		
		DynamicComparator dComp = new DynamicComparator();
		
		String astFolderPath1 = astFolderPaths[0]; // "AST/Student1/";
		String astFolderPath2 = astFolderPaths[1]; // "AST/Student2/";
		
		File astFolder1= new File(astFolderPath1);
		File[] astFiles1 = astFolder1.listFiles();
		
		File astFolder2= new File(astFolderPath2);
		File[] astFiles2 = astFolder2.listFiles();
		
		// Student 1 folder
		for(File astFile1 : astFiles1) {
			try {
				String ast1 = new String(Files.readAllBytes(astFile1.toPath()));
				tokenizedAst1 = ast1.split("\\s+");
			} catch (IOException e) {
				e.getMessage();
			}
		    
			// Student 2 folder
			for(File astFile2 : astFiles2) {
				try {
					String ast2 = new String(Files.readAllBytes(astFile2.toPath()));
					tokenizedAst2 = ast2.split("\\s+");
				} catch (IOException e) {
					e.getMessage();
				}
				
				
				//choose relevant strategy and intialize class using Strategy pattern
				if (type.equals("SubSequence")) {
					dComp.setComparatorStrategy(new SubSequenceComparator(tokenizedAst1, tokenizedAst2));
		
				}
				if (type.equals("Cosine")) {
					dComp.setComparatorStrategy(new CosineComparator(tokenizedAst1, tokenizedAst2));
		
				}
				if (type.equals("Combined")) {
					// getting Cosine similarity score
					dComp.setComparatorStrategy(new CosineComparator(tokenizedAst1, tokenizedAst2));
					dComp.runComparison();
					Double scoreCosine = dComp.getScore();
					// getting SubSequence similarity score
					dComp.setComparatorStrategy(new SubSequenceComparator(tokenizedAst1, tokenizedAst2));
					dComp.runComparison();
					Double scoreSubSequence = dComp.getScore();
					
					// modifying current alpha value for CombinedComparator
					Double deltaAlpha = (scoreCosine - scoreSubSequence)/100;
					CombinedComparator combinedComp = new CombinedComparator(tokenizedAst1, tokenizedAst2);
					combinedComp.setAlpha(combinedComp.getAlpha() + deltaAlpha);
					
					// setting strategy for combined comparator
					dComp.setComparatorStrategy(combinedComp);
				}
				
				dComp.runComparison();

				Double score = dComp.getScore();
				
				// CompareResult obj for the ast1 and ast2
				CompareResult resObj = new CompareResult(astFile1.getName().replace("txt", "py"),
														 astFile2.getName().replace("txt", "py"),
														 score);
				
				// Adding the resObj to the resultList
				resultList.add(resObj);
			}
		}
		return resultList;
	}

}
