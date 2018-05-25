/**
 * 
 */
package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.antlr.v4.runtime.RuleContext;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import astfacade.AstCreator;
import astfacade.AstPrint;

/**
 * Generate the AST for files in a given folder
 * 
 * @author adi
 * @date Mar 22, 2018
 */
public class ASTGenerator {

	private ASTGenerator() {}

	private static final Logger logger = LoggerFactory.getLogger(ASTGenerator.class);
	private static String astParentFolderPath = "AST";

	
	/**
	 * generate all ASTs in the given folder paths and return as array of String
	 * 
	 * @param folderPath1
	 * @param folderPath2
	 * @return String array to two AST folders
	 */
	public static String[] generateASTs(String folderPath1, String folderPath2) {
		// AST Creator obj
		AstCreator astCreator = new AstCreator();
		// AST Printer obj
		AstPrint astPrint = new AstPrint();

		// Checking for AST Parent folder presence
		File astParentFolderObj = new File(astParentFolderPath);
		if (!astParentFolderObj.exists()) {
			if (astParentFolderObj.mkdir()) {
				logger.info(astParentFolderPath + " is created!");
			} else {
				logger.error(astParentFolderPath + " couldn't be created.");
			}
		}
		
		PrintWriter writer = null;

		File pyFolder = new File(folderPath1);
		File[] inputPyFiles = pyFolder.listFiles();
		String astString = "";

		String astFolderPath1 = astParentFolderPath + "/Student1/";
		File astFolderObj1 = new File(astFolderPath1);
		if (!astFolderObj1.exists()) {
			if (astFolderObj1.mkdir()) {
				logger.info(astFolderPath1 + " is created!");
			} else {
				logger.error(astFolderPath1 + " couldn't be created.");
			}
		}
		
		// Flushing out folder to store ASTs of Student 2
		for(File file : astFolderObj1.listFiles()){
			if(file.delete())
				{
				logger.info("file deleted successfully");
				}
			 if (!file.delete()) {
				    logger.info("file delete failed");
				  }
		}


			
		// ASTs creation for all .py files for Student 1
		for (File inputPyFile : inputPyFiles) {
			if (inputPyFile.isFile()) {
				// Parse .py file through ANTLR
				try {
					RuleContext astCtx = astCreator.parse(inputPyFile);
					astString = astPrint.print(astCtx);
				} catch (IOException e) {
					logger.error(e.getMessage());
				}

				String inputFileName = inputPyFile.getName();

				// Writing AST to a .txt file
				FileWriter fileWriter = null;
				try {
					fileWriter = new FileWriter(astFolderPath1 + inputFileName.split("\\.")[0] + ".txt", false);
					writer = new PrintWriter(fileWriter, true);
					writer.print(astString);
					
					
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
				finally
				{
					// closing opened file resources
					try {
						fileWriter.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						logger.error(e.getMessage());
					}
					writer.close();
				}
//				astString_1 = astString.concat(astString);

			}
		}

		pyFolder = new File(folderPath2);
		inputPyFiles = pyFolder.listFiles();
		
		String astFolderPath2 = astParentFolderPath + "/Student2/";
		File astFolderObj2 = new File(astFolderPath2);
		if (!astFolderObj2.exists()) {
			if (astFolderObj2.mkdir()) {
				logger.info(astFolderPath2 + " is created!");
			} else {
				logger.error(astFolderPath2 + " couldn't be created.");
			}
		}
		
		// Flushing out folder to store ASTs of Student 2
		for(File file : astFolderObj2.listFiles()){
			if(file.delete())
			{
			logger.info("file deleted successfully");
			}
		 if (!file.delete()) {
			    logger.info("file delete failed");
			  }
		}
		
//		String astString_2 = "";
		
		// ASTs creation for all .py files for Student 2
		for (File inputPyFile : inputPyFiles) {
			if (inputPyFile.isFile()) {
				// Parse .py file through ANTLR
				try {
					RuleContext astCtx = astCreator.parse(inputPyFile);
					astString = astPrint.print(astCtx);
				} catch (IOException e) {
					logger.error(e.getMessage());
				}

				String inputFileName = inputPyFile.getName();

				// Writing AST to a .txt file
				FileWriter fileWriter = null;
				try {
					fileWriter = new FileWriter(astFolderPath2 + inputFileName.split("\\.")[0] + ".txt", false);
					writer = new PrintWriter(fileWriter, true);
					writer.print(astString);
					
					
				} catch (IOException e) {
					logger.error(e.getMessage());
				}
				finally
				{
					// closing opened file resources
					try {
						if(fileWriter!=null)
						{
						fileWriter.close();
						}
						else
						{
							logger.info("fileWriter object was null");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						logger.error(e.getMessage());
					}
					writer.close();
				}


			}
		}

		return new String[] { astFolderPath1, astFolderPath2};
	}

}
