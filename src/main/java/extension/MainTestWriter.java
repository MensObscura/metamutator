package extension;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import configuration.Config;

public class MainTestWriter {
	
	private PrintWriter printW;
	private List<String> files;
	private String repertoryPath;
	public static Config conf;
	
	public MainTestWriter(String repertoryPath) throws IOException {
		files = new ArrayList<String>();
		
		File repertory = new File(repertoryPath);
		this.initFilesList(repertory);	
		
		FileWriter file = new FileWriter(repertoryPath+"/MainTest.java");
		
		this.repertoryPath = repertoryPath;
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
					if (!filename.equals("MainTest.java"))
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
		
		importPackage(repertoryPath,"");
		
		printW.println("@RunWith(Suite.class)");
		
		writeSuite();
		
		printW.println("public class MainTest {");
		printW.println("@BeforeClass");
		printW.println("public static void suiteSetup() {");
		
		printW.println("Selector sel;");
		printW.println("Interpreter bsh = new Interpreter();");
				
		writeConfig();		
		
		printW.println("}");
		printW.println("}");
		
		printW.close();
	}

	private void importPackage(String path, String _package) {
		File f = new File(path);
		if (f.isFile())
			return;
		for (File sf : f.listFiles()) {
			if (sf.isDirectory()) {
				printW.println("import "+_package+sf.getName()+".*;");
				for (File sf2 : sf.listFiles()) {
					if (_package.length() != 0)
						importPackage(path+"/"+sf.getName(),_package+"."+sf.getName()+".");
					else
						importPackage(path+"/"+sf.getName(),sf.getName()+".");
				}
			}
		}		
	}

	private void writeConfig() {
		
		//We get the config, and we put it in the test code.
		conf = Config.getInstance();
		Map<String,Map> config = conf.getConfig();

		Set<String> cles = config.keySet();
		
		for (String cle : cles) {
			printW.println("new "+cle+"();");

			Map sousmap = config.get(cle);
			Set<String> cles2 = sousmap.keySet();
			
			for (String cle2 : cles2) {
				printW.println("sel=Selector.getSelectorByName(\""+cle2+"\");");
				printW.println("sel.choose("+sousmap.get(cle2)+");");
			}
		
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
