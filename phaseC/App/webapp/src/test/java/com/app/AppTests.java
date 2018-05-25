package com.app;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

import app.App;


public class AppTests {
	



	private static final Logger LOGGER = Logger.getLogger(AppTests.class.getName());
	private static Object str = null;
	private static Object wrong = "String";
	
	/**
	 * This is the check the output for App class
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodappone() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for App Class");
		App checker = new App();
		try {
			str = checker.checker();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.info("Exception occured");
		}
		assertNotEquals(100.0, str);
	}
	
	/**
	 * This is the check the output for App class
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodapptwo() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for App Class");
		App checker = new App();
		try {
			wrong = checker.checker();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.info("Exception occured");
		}
		assertNotEquals(99.0, wrong);
	}
	
	/**
	 * This is the check the output for App class
	 * 
	 * @param Unused
	 * @return void This returns nothing
	 */
	@Test
	public void TestMethodappthree() {
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Test for App Class");
		App checker = new App();
		try {
			str = checker.mailchecker();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.info("Exception occured");
		}
		assertEquals(str,"Sent");
	}

	
	
}


