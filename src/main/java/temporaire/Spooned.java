package temporaire;

import metamutator.BinaryOperatorMetaMutator;
import metamutator.VariabletoNullMetaMutator;
import spoon.Launcher;


public class Spooned {

	public static void main(String[] args) {
		final String[] arguments = {
				"-i", "src/test/java/",
				"-o", "/home/thibaud/M2/OPL/metamutator/spooned"
		};

		final Launcher launcher = new Launcher();
		launcher.setArgs(arguments);

		launcher.addProcessor(new BinaryOperatorMetaMutator());
		launcher.addProcessor(new VariabletoNullMetaMutator());

		launcher.run();
	}

}
