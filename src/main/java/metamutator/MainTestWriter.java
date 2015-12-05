package metamutator;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainTestWriter {
	
	private PrintWriter printW;
	private List<String> files;
	private String _package;
	
	public MainTestWriter(PrintWriter pw, String testpackage, String repertoryPath) {
		printW = pw;
		_package = testpackage;
		files = new ArrayList<String>();
		
		File repertory = new File(repertoryPath);
		this.initFilesList(repertory);		

	}
	
	private static boolean isJAVAFile(File file) {
		String filename = file.getName();
		if (filename.lastIndexOf(".") != -1 && filename.lastIndexOf(".") != 0) {
			return filename.substring(filename.lastIndexOf(".")+1).equals("java");
		}
		else
			return false;
	}
	
	private void initFilesList(File repertory) {
		for (File f : repertory.listFiles()) {
			if (f.isDirectory()) {
				this.initFilesList(f);
			}
			else {
				if (isJAVAFile(f)) {
					String filename = f.getName();
					files.add(filename);
				}
			}
		}
		
	}

	public void writeMainTest() {
		
		printW.println("import org.junit.BeforeClass;");
		printW.println("import org.junit.runner.RunWith;");
		printW.println("import org.junit.runners.Suite;");
		printW.println("import org.junit.runners.Suite.SuiteClasses;");
		printW.println("import bsh.Interpreter;");
		printW.println("import metamutator.Selector;");
		printW.println("import "+_package+".*;");
		
		printW.println("@RunWith(Suite.class)");
		
		this.writeSuite();
		
		printW.println();
		printW.println("public class MainTest {");
		printW.println("@BeforeClass");
		printW.println("public static void suiteSetup() {");
		
		printW.println("Interpreter bsh = new Interpreter();");
		this.writeConstructorsCalls();
		
		this.writeConfig();
		
		printW.println("Selector sel=Selector.getSelectorByName(\"_s1\");");
		printW.println("sel.choose(1);");
		
		
		printW.println("}");
		printW.println("}");	
	}

	private void writeConfig() {
		//TODO : choisir les bonnes configurations avec fichier config.
		
	}

	private void writeConstructorsCalls() {
		for (int i=0; i < files.size(); i++) {
			String classfilename = files.get(i).replace("Test.java", "()");
			printW.println("new "+classfilename+";");
		}	
	}

	private void writeSuite() {
		printW.println("@SuiteClasses({");
		for (int i=0; i < files.size(); i++) {
			String classfilename = files.get(i).replace(".java", ".class");
			if (i != files.size() - 1)
				printW.print(classfilename+",");
			else
				printW.print(classfilename);
		}
		printW.print("})");
	}

}
