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
	 * 		-test <path des tests> <package du test>
	 * ex : -test /home/jean-loup/M2/OPL/projetTestMutant/src/test/java package
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
			
			
		}
		else if (args.length == 3 && args[0].equals("-test")) {
			
			String testsdirectory = args[1];
			
			String testpackage = args[2];
									
			MainTestWriter writer = new MainTestWriter(testpackage, testsdirectory);
			
			writer.writeMainTest();
						
		}
		else {
			 //TODO BAD USE -> make usage()
		}
		
	}

	
}
