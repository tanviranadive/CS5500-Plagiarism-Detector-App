/**
 * 
 */
package mail;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author adi
 * @date Mar 30, 2018
 */
public class MailTests {

	@Test
	public void test() {
		SendMail.sendFromGMail("test@g.com", "test", "testMail");
		assertTrue(true);
	}

}
