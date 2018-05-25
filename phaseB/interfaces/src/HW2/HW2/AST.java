package HW2;

// class AST generates ast given a file or project
public class AST {
	private File file;
	private Project project;
	
	public AST(File f){
		
	}
	
	public AST(Project p){}
	
	public AST generateASTFile(File file){
		return new AST(file);
	}
	
public AST generateASTProject(Project project){
		return new AST(project);
	}

}
