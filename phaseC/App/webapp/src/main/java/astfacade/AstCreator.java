package astfacade;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.Tree;

import antlr4.python3.Python3Lexer;
import antlr4.python3.Python3Parser;

/**
 * Creates an AST for the given Python 3 file
 * 
 * @author Deepak
 *
 */
public class AstCreator {
	/**
	 * 
	 * @param file:input Python 3 file
	 * @param encoding
	 * @return encoded file string
	 * @throws IOException
	 */
	private static String readFile(File file, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(file.toPath());
		return new String(encoded, encoding);
	}

	/**
	 * 
	 * @param file
	 * @return parsed AST
	 * @throws IOException
	 */
	public Python3Parser.File_inputContext parse(File file) throws IOException {
		String code = readFile(file, Charset.forName("UTF-8"));
		ANTLRInputStream input = new ANTLRInputStream(code);
		Python3Lexer lexer = new Python3Lexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		Python3Parser parser = new Python3Parser(tokens);

		return parser.file_input();
	}
}
