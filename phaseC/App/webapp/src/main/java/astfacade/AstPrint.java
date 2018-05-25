/**
 * 
 */
package astfacade;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import antlr4.python3.Python3Parser;

/**
 * prints an AST
 * 
 * @author Deepak
 *
 */
public class AstPrint {

	private boolean ignoringWrappers = true;
	StringBuilder out = new StringBuilder();
	String output;

	public void setIgnoringWrappers(boolean ignoringWrappers) {
		this.ignoringWrappers = ignoringWrappers;
	}

	public String print(RuleContext ctx) {
		out = new StringBuilder();
		output = explore(ctx, 0);
		return output;
	}

	private String explore(RuleContext ctx, int indentation) {
		boolean toBeIgnored = ignoringWrappers && ctx.getChildCount() == 1
				&& ctx.getChild(0) instanceof ParserRuleContext;
		if (!toBeIgnored) {
			String ruleName = Python3Parser.ruleNames[ctx.getRuleIndex()];
			for (int i = 0; i < indentation; i++) {
				out.append(" ");
			}
			out.append(ruleName);
			out.append(" ");
			out.append('\n');
		}
		for (int i = 0; i < ctx.getChildCount(); i++) {
			ParseTree element = ctx.getChild(i);
			if (element instanceof RuleContext) {
				explore((RuleContext) element, indentation + (toBeIgnored ? 0 : 1));
			}
		}

		return out.toString();
	}
}
