package com.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Test;

import app.ASTGenerator;
import app.Compare;
import app.CompareResult;

/**
 * <h1>JUnit tests written to verify the output from the Compare Class
 * <p>
 * 
 * @author Mihir Gandhi
 * @version 1.0
 * @since 2018-03-23
 */

public class CompareTests {

	Path currentPath = Paths.get("").toAbsolutePath();
	private String strategyone = "SubSequence";
	private String strategytwo = "Cosine";
	private String strategythree = "Combined";
	private String folderPath1 = currentPath + "/TestFolders/student1";
	private String folderPath2 = currentPath + "/TestFolders/student2";
	private String folderPath3 = currentPath + "/TestFolders/student3";
	private String folderPath4 = currentPath + "/TestFolders/student4";
	private String folderPath5 = currentPath + "/TestFolders/student5";
	private String folderPath6 = currentPath + "/TestFolders/testInvalidInput";

	private static final Logger LOGGER = Logger.getLogger(CompareTests.class.getName());

	/**
	 * This is the check the output for Subsequence strategy
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodsubone() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for Compare Class");
		Compare compareObj = new Compare();
		List<CompareResult> score = compareObj.compare(strategyone, folderPath1, folderPath1);
		for(CompareResult s:score )
		{
		assertTrue(100.0 == s.getScore());
		}
	}

	
	
	/**
	 * This is the check the output for Subsequence strategy
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodsubfour() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for Compare Class");
		Compare compareObj = new Compare();
		List<CompareResult> score = compareObj.compare(strategyone, folderPath1, folderPath4);
		for(CompareResult s:score )
		{
		assertTrue(100.0 == s.getScore());
		}
	}


	/**
	 * This is the check the output for Subsequence strategy
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodsubfive() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for Compare Class");
		Compare compareObj = new Compare();
		List<CompareResult> score = compareObj.compare(strategyone, folderPath1, folderPath5);
		for(CompareResult s:score )
		{
		assertTrue(100.0 == s.getScore());
		}

	}



	
	/**
	 * This is the check the output for Cosine strategy
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodcosineone() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for Compare Class");
		Compare compareObj = new Compare();
		List<CompareResult> score = compareObj.compare(strategytwo, folderPath1, folderPath1);
		
		for(CompareResult s:score )
		{
		assertTrue(100.0 == s.getScore());
		}
	}



	/**
	 * This is the check the output for Cosine strategy
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodcosinethree() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for Compare Class");
		Compare compareObj = new Compare();
		List<CompareResult> score = compareObj.compare(strategytwo, folderPath1, folderPath3);
		
		for(CompareResult s:score )
		{
		assertFalse(90.0 == s.getScore());
		}
	}

	/**
	 * This is the check the output for Cosine strategy
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodcosinefour() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for Compare Class");
		Compare compareObj = new Compare();
		List<CompareResult> score = compareObj.compare(strategytwo, folderPath1, folderPath4);
		for(CompareResult s:score )
		{
		assertTrue(100.0 == s.getScore());
		}
	}

	/**
	 * This is the check the output for Cosine strategy
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodcosinefive() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for Compare Class");
		Compare compareObj = new Compare();
		List<CompareResult> score = compareObj.compare(strategytwo, folderPath1, folderPath5);
		for(CompareResult s:score )
		{
		assertTrue(100.0 == s.getScore());
		}
	}

	/**
	 * This is the check the output for Combined strategy
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodcombinedone() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for Compare Class");
		Compare compareObj = new Compare();
		List<CompareResult> score = compareObj.compare(strategythree, folderPath1, folderPath1);
		for(CompareResult s:score )
		{
		assertTrue(100.0 == s.getScore());
		}

	}

	

	

	/**
	 * This is the check the output for Combined strategy
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodcombinedfour() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for Compare Class");
		Compare compareObj = new Compare();
		List<CompareResult> score = compareObj.compare(strategythree, folderPath1, folderPath4);
		for(CompareResult s:score )
		{
		assertTrue(100.0 == s.getScore());
		}
	}

	/**
	 * This is the check the output for Combined strategy
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodcombinedfive() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for Compare Class");
		Compare compareObj = new Compare();
		List<CompareResult> score = compareObj.compare(strategythree, folderPath1, folderPath5);
		for(CompareResult s:score )
		{
		assertTrue(100.0 == s.getScore());
		}

	}
	
	
	/**
	 * This is the check the output for Combined strategy
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodcombinedfiveex() throws IOException{
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for Compare Class");
		Compare compareObj = new Compare();
		List<CompareResult> score = compareObj.compare(strategythree, folderPath1, folderPath5);
		for(CompareResult s:score )
		{
		assertTrue(100.0 == s.getScore());
		}

	}
	

	/**
	 * This is the check the output for Cosine strategy
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodcombined1e() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for Compare Class");
		Compare compareObj = new Compare();
		List<CompareResult> score = compareObj.compare(strategythree, folderPath1, folderPath2);
		assertNotEquals(100.0, score);
	}
	
	
	
	/**
	 * This is the check the output for Cosine strategy
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodcombined2e() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for Compare Class");
		Compare compareObj = new Compare();
		List<CompareResult> score = compareObj.compare(strategythree, folderPath1, folderPath1);
		for(CompareResult s:score )
		{
		assertTrue(100.0 == s.getScore());
		}

	}

	/**
	 * This is the check the output for Cosine strategy
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodcombined3e() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for Compare Class");
		Compare compareObj = new Compare();
		List<CompareResult> score = compareObj.compare(strategythree, folderPath1, folderPath2);
		assertNotEquals(100.0, score);
	}


	@Test(expected= NullPointerException.class)
	public void TestInvalidPath1(){
		 ASTGenerator.generateASTs("sample124",folderPath1);
	}
	
	@Test(expected= NullPointerException.class)
	public void TestInvalidPath2(){
		 ASTGenerator.generateASTs(folderPath1,"sample124");
	}
	
	@Test
	public void TestMissingFolder() throws IOException{
		String path = "AST";
		File f = new File(path);
		FileUtils.deleteDirectory(f);
		 String[] x = ASTGenerator.generateASTs(folderPath1,folderPath2);
		 assertTrue(x.length != 0);
	}
	
	
}


	