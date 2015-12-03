import static org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod;

import metamutator.BinaryOperatorMetaMutator;
import metamutator.Selector;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import spoon.Launcher;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.NameFilter;
import bsh.Interpreter;

public class BinaryOperatorMetaMutatorTest {
	
	public static void copy(File sourceLocation, File targetLocation) throws IOException {
	    if (sourceLocation.isDirectory()) {
	    	FileUtils.copyDirectory(sourceLocation, targetLocation);
	    } else {
	    	FileUtils.copyFile(sourceLocation, targetLocation);
	    }
	}

    @Test
    public void testBinaryOperatorMetaMutator() throws Exception {
        
    	assertTrue(true);
    	
    	
		// Normalement les paramètres du main :
		String projectdirectory = "/home/thibaud/M2/OPL/projetTest";
		String dest = "/src/main";
		
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
		
		final spoon.Launcher launcher = new Launcher();
		launcher.setArgs(arguments);
				
		launcher.addProcessor(new BinaryOperatorMetaMutator());
		
		launcher.run();
        
    }
}