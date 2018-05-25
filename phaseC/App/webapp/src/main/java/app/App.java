package app;

import java.io.IOException;
import java.nio.file.Paths;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mail.SendMail;

/**
 * App runner class Creats AST and Prints the same
 * 
 * @author Deepak
 *
 */
public class App {

	private static final Logger logger = LoggerFactory.getLogger(App.class);
	private static double score = 0.0;

	public String checker() throws IOException 
	{

		logger.info("Info log");
		Compare comparer = new Compare();
		double scoretest = 0.5;
//				comparer.compare("Cosine", Paths.get("").toAbsolutePath() + "/TestFolders/student3",
//				Paths.get("").toAbsolutePath() + "/TestFolders/student1");
		System.out.println(score);
		return Double.toString(scoretest);
		
		
	}
	
	@NotNull
	public String mailchecker() throws IOException
	{
		App application = new App();
		application.checker();
		 
		try {
			SendMail.sendFromGMail("gandhimihirj@gmail.com", "plagiarism check", "score is" + score);
			
		}
		 catch(NullPointerException e)
        {
            logger.info("NullPointerException Caught");
        }
		return "Sent";
	}
}
