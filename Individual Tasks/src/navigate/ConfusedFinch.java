package navigate;

import java.util.Random;

import javax.swing.JOptionPane;

class ConfusedFinch extends FinchNavigate {
	
	// Method to randomly pick 5 previous executed commands and execute them one by one.
	protected static void commandGenerator() {
		JOptionPane.showMessageDialog(null, "Finch Has Entered Into Confused Mode", "Warning!", JOptionPane.PLAIN_MESSAGE);
		Random generator = new Random(); // Creates a random number generator.
		for (int commandsExecuted = 1; commandsExecuted <= 5; commandsExecuted++) {
			int index = generator.nextInt(executedCommands.size()); // Generates number between 0 and the size of the executed commands array list.
			myFinch.setLED(255, 0 , 0); // Sets the finch beak to red.
			String[] command = executedCommands.get(index).split("[,\n]"); // Splits at a comma and at a new line.
			FinchNavigate.valueSetter(command);
			FinchNavigate.functionExecutor(function, finchSpeed, finchDuration * 1000, traceValue); // Multiplies by 1000 to covert from second to millisecond.
			myFinch.setLED(0, 0, 0, 500); // Resets the finch beak.
		}
		
		// Generates either 1 or 2. If its 1 then clears the array list and displays a warning message.
		if (generator.nextInt(3) == 1) { 
			executedCommands.clear();
			JOptionPane.showMessageDialog(null, "Finch Has Lost Its Memory! All Previously Executed Commands Are Lost.", "Warning!",  JOptionPane.WARNING_MESSAGE);
		}
		totalCommandsExecuted = 0; // Resets the counter.
	}
}
