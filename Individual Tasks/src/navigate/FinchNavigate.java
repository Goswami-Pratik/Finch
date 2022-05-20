package navigate;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

class FinchNavigate extends UserInterface {
	static Finch myFinch = new Finch(); // Creating the finch.
	static StringBuilder sb = new StringBuilder(); // Creating a string builder to join variables together to form command.

	// Takes in the necessary inputs and makes the finch execute the appropriate commands. The commands executed is added to an array list.
	protected static void functionExecutor(String function, int finchSpeed , int finchDuration, int traceValue) {
		totalCommandsExecuted++; // Counter to checks the number of commands executed for the confused finch mode.
		switch(function) {
		case "F" : case "f": case "F - Move Finch Forward":
			myFinch.setWheelVelocities(finchSpeed, finchSpeed, finchDuration);
			sb.append("F").append(",").append(finchDuration/1000).append(",").append(finchSpeed); // Joins all the variables to form a string.
			break;
		case "B" : case "b": case "B - Move Finch Backwards":
			myFinch.setWheelVelocities(-finchSpeed, -finchSpeed, finchDuration);
			sb.append("B").append(",").append(finchDuration/1000).append(",").append(finchSpeed);
			break;
		case "R" : case "r": case "R - Turn Finch Right":
			myFinch.setWheelVelocities(100, -100, 750);
			myFinch.setWheelVelocities(finchSpeed, finchSpeed, finchDuration);
			sb.append("R").append(",").append(finchDuration/1000).append(",").append(finchSpeed);
			break;
		case "L" : case "l": case "L - Turn Finch Left":
			myFinch.setWheelVelocities(-100, 100, 750);
			myFinch.setWheelVelocities(finchSpeed, finchSpeed, finchDuration);
			sb.append("L").append(",").append(finchDuration/1000).append(",").append(finchSpeed);
			break;
		case "T" : case "t": case "T - Trace Previous Movements":
			sb.append("T").append(",").append(traceValue);
			executedCommands.add(sb.toString()); // Adds the formed string(command) to executedCommands array list
			sb.setLength(0); // Resets the string builder.
			tracingFunctions(traceValue);
			break;
		case "W" : case "w": case "W - Write To Text File":
			sb.append("W");
			executedCommands.add(sb.toString());
			sb.setLength(0);
			try {
				fileWriter();
			} catch (IOException e) { // Catches errors.
				e.printStackTrace();
			}
			break;
		case "X" : case "x": case "X - Execute Commands From A File":
			sb.append("X");
			executedCommands.add(sb.toString());
			sb.setLength(0);
			try {
				fileReader();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "Q": case "q": case "Q - Quit Program":
			myFinch.quit(); // Quits the finch.
			JOptionPane.showMessageDialog(null, "Thank You, For Using Finch Navigate. See You Soon !", "Finch Navigate", JOptionPane.PLAIN_MESSAGE);
			System.exit(0); // Exits the program.
			break;
		default:
			JOptionPane.showMessageDialog(null, "Error: Command Execution Was UnSuccessful.", "Error!",  JOptionPane.ERROR_MESSAGE);			
		}
		if (!sb.toString().equals("")) // Checks if the command being added to the array, is not an empty string.
			executedCommands.add(sb.toString());
		//printer(executedCommands); // Debugging method, activate the printer() method to use.
		sb.setLength(0);
		resetField();
	}

	// Traces the specific number of previously executed commands.
	protected static void tracingFunctions (int traceValue) {
		int executedCommandsSize = executedCommands.size();
		for (int i = executedCommandsSize; i > (executedCommandsSize - traceValue); i--) {
			String[] command = executedCommands.get(i-2).split("[,\n]"); // Firstly because of the difference between array size and index. Secondly to prevent the last T command from being executed. Lastly it splits at a comma and at a new line.
			if (!command[0].equals("T - Trace Previous Movements")) { 
				valueSetter(command);
				functionExecutor(function, finchSpeed, finchDuration * 1000, traceValue); // Multiplies by 1000 to convert it from seconds to milliseconds.
			}
		}
	}

	// Setter method to allocate each value to the corresponding variable.
	protected static void valueSetter(String[] command) {
		if (command.length == 1)
			function = command[0];
		else if (command.length == 2) {
			function = command[0];
			traceValue = Integer.parseInt(command[1]);
		} else {
			function = command[0];
			finchDuration = Integer.parseInt(command[1]);
			finchSpeed = Integer.parseInt(command[2]);
		}
	}

	// Creates a new file writer to write all the executed commands to a text file and the current local time.
	protected static void fileWriter() throws IOException {
		FileWriter fileWriter = new FileWriter("FileCommands.txt"); // Creates a new file writer and a new text file called FileCommands.
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss"); 
		LocalDateTime now = LocalDateTime.now();
		fileWriter.write("Current Time = " + dateTimeFormatter.format(now) + "\n"); // Writes the current time.
		for (String command : executedCommands){ // Loop to write every index of the array to the text file.
			fileWriter.write(command + "\n");
		}
		fileWriter.close();
		
		// Opens the text file(FileCommands using notepad.exe).
		ProcessBuilder textFileOpener = new ProcessBuilder("Notepad.exe", "FileCommands.txt");
		textFileOpener.start();
	}

	// Reads commands from a text file and executes them one by one.
	protected static void fileReader() throws IOException {
		String line;
		try {
			ArrayList<String> textFileCommands = new ArrayList<>(); // Creates temporary array list to store the commands from text file.
			FileReader fileReader = new FileReader("FileCommands.txt");
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			bufferedReader.readLine();
			line = null; // Assigns the first line to null. Skips the first line.
			while ((line = bufferedReader.readLine()) != null) { // Adds each command to array list.
				textFileCommands.add(line);
			}
			bufferedReader.close();
			fileReader.close(); // Closes both file and buffered reader.
			if (textFileCommands.size() >= 3) { // Checks if file contains more than 3 commands.
				for (String fileCommand : textFileCommands) {
					String[] command = fileCommand.split("[,\n]");
					if ((command[0].equals("x") || command[0].equals("X") || command[0].equals("X - Execute Commands From A File")) != true) { // Prevents any X commands from being executed as it cause an infinite loop to occur.
						valueSetter(command);
						functionExecutor(function, finchSpeed, finchDuration * 1000, traceValue);
					}
				}
			} else if (textFileCommands.size() == 0) {
				JOptionPane.showMessageDialog(null, "Error: File Is Empty!!", "Error!",  JOptionPane.ERROR_MESSAGE); // Checks if the file is empty and displays an error message.
			} else
				JOptionPane.showMessageDialog(null, "Error: Please Ensure File Contains Atleast 3 Executable Commands", "Error!",  JOptionPane.ERROR_MESSAGE); // Shows error message if file contains less than 3 commands.
		} catch (Exception e) { 
			e.printStackTrace(); // Catches any errors.
		}
	}

	// Method to check if the Finch is on a levelled surface.
	protected static void finchLevelChecker() {
		int loopCounter = 0;
		do {
			loopCounter++;
			myFinch.sleep(50);
			if (loopCounter == 25) {
				finchLevelMessage.setText("Finch Is Not On Levelled Surface..."); // Updates the text in the label.
				myFinch.buzz(700, 1000); // Makes the finch buzz and sets the beak to red.
				myFinch.setLED(Color.red);
				loopCounter = 0; // Resets the counter.
			}
		}while (!myFinch.isFinchLevel());
		finchLevelMessage.setText("Starting Program..."); // Updates the label.
		myFinch.setLED(Color.GREEN,1000); // Sets the finch's beak to green once the finch is on a levelled surface.
	}

	// Debugging method.. Please Ignore
	/*public static void printer(ArrayList<String> executedCommands) {
		for (String functionss : executedCommands)
			System.out.println(functionss);
		System.out.println("\n");

	}*/

}