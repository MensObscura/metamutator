package metamutator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;

import spoon.Launcher;

public class MutatorLauncher {
	
	public static void copy(File sourceLocation, File targetLocation) throws IOException {
	    if (sourceLocation.isDirectory()) {
	    	FileUtils.copyDirectory(sourceLocation, targetLocation);
	    } else {
	    	FileUtils.copyFile(sourceLocation, targetLocation);
	    }
	}

	/**
	 * @param args
	 * @throws Exception
	 * 
	 * paramètres pour lancer l'initialisation :
	 * 		-init <path du projet> <path des sources>
	 * ex : -init /home/jean-loup/M2/OPL/projetTest /src/main/java
	 * 
	 *      **********************************************
	 * 
	 * paramètres pour lancer la modification du mutant :
	 * 		-test <path des tests> <package du test> <nom de la classe à tester> <nom de la classe qui teste>
	 * ex : -test /home/jean-loup/M2/OPL/projetTestMutant/src/test/java pack Toto TotoTest
	 */
	public static void main(String[] args) throws Exception {
		
		if (args.length == 3 && args[0].equals("-init")) {
			
			String projectdirectory = args[1];
			String dest = args[2];
				
			String newprojetdirectory = projectdirectory+"Mutant";
				
			try {
				copy(new File(projectdirectory), new File(newprojetdirectory));
			} catch (IOException e) {
				throw new Exception("Not found directory "+projectdirectory);
			}
				
				
			final String[] arguments = {
					"-i", projectdirectory+dest,
					"-o", newprojetdirectory+dest
			};
				
			final Launcher launcher = new Launcher();
			launcher.setArgs(arguments);
						
			launcher.addProcessor(new BinaryOperatorMetaMutator());
				
			launcher.run();
				
			//TODO: Générer fichier de config
			
		}
		else if (args.length == 5 && args[0].equals("-test")) {
			
			String testsdirectory = args[1];
			
			String testpackage = args[2];
			
			String _class = args[3];
			
			String testclass = args[4];
			
			FileWriter file = new FileWriter(testsdirectory+"/MainTest.java");
			
			PrintWriter out = new PrintWriter(file);
			
			out.println("import org.junit.BeforeClass;");
			out.println("import org.junit.runner.RunWith;");
			out.println("import org.junit.runners.Suite;");
			out.println("import org.junit.runners.Suite.SuiteClasses;");
			out.println("import bsh.Interpreter;");
			out.println("import metamutator.Selector;");
			out.println("import "+testpackage+".*;");
			
			out.println("@RunWith(Suite.class)");
			out.println("@SuiteClasses({"+testclass+".class})");
			
			
			out.println("public class MainTest {");
			out.println("@BeforeClass");
			out.println("public static void suiteSetup() {");
			
			out.println("Interpreter bsh = new Interpreter();");
			out.println("new "+_class+"();");
			
			//TODO : choisir les bonnes configurations avec fichier config.
			
			out.println("Selector sel=Selector.getSelectorByName(\"_s1\");");
			out.println("sel.choose(1);");
			
			
			out.println("}");
			out.println("}");			
			
			out.close();
			
		}
		else {
			
		}
		
	}

	
}
