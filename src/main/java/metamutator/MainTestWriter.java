package metamutator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import configuration.Config;

public class MainTestWriter {
	
	private PrintWriter printW;
	private List<String> files;
	private String _package;
	public static Config conf;
	
	public MainTestWriter(String testpackage, String repertoryPath) throws IOException {
		_package = testpackage;
		files = new ArrayList<String>();
		
		File repertory = new File(repertoryPath);
		this.initFilesList(repertory);	
		
		FileWriter file = new FileWriter(repertoryPath+"/MainTest.java");
		
		printW  = new PrintWriter(file);

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
		
		writeSuite();
		
		printW.println("public class MainTest {");
		printW.println("@BeforeClass");
		printW.println("public static void suiteSetup() {");
		
		printW.println("Interpreter bsh = new Interpreter();");
		
		writeConstructorsCalls();
		
		writeConfig();
		
		printW.println("Selector sel=Selector.getSelectorByName(\"_s1\");");
		printW.println("sel.choose(1);");
		
		
		printW.println("}");
		printW.println("}");
		
		printW.close();
	}

	private void writeConfig() {
		
		//We get the config, and we put it in the test code.
		conf = Config.getInstance();
		Map<String,Map> config = conf.getConfig();

		Set<String> cles = config.keySet();
		
		for (String cle : cles)
			System.out.println(cle);
		
		
		
		/*
		Set selector = config.get(_class).keySet(); //TODO la j'ai besoin du nom de la class current pour savoir quel sel prendre.

		Iterator itr = selector.iterator();
		while(itr.hasNext()) {
			String currentSel = (String) itr.next();
			String option = (String) config.get(_class).get(currentSel); //TODO ici aussi du coup

			printW.println("Selector sel=Selector.getSelectorByName(\""+currentSel+"\");");
			printW.println("sel.choose();");

		}
		*/	
	}

	private void writeConstructorsCalls() {
		for (int i=0; i < files.size(); i++) {
			String classfilename = files.get(i).replace("Test.java", "()");
			printW.println("new "+classfilename+";");
		}	
	}

	private void writeSuite() {
		printW.print("@SuiteClasses({");
		for (int i=0; i < files.size(); i++) {
			String classfilename = files.get(i).replace(".java", ".class");
			if (i != files.size() - 1)
				printW.print(classfilename+",");
			else
				printW.print(classfilename);
		}
		printW.println("})");
	}

}
