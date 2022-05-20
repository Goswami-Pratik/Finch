package navigate;

import java.awt.Color;
import javax.swing.border.LineBorder;

class MyInputVerifier extends UserInterface{
	protected static void verify() {
		// Assigns default values.
		durationWasValid = false;
		speedWasValid = false;
		traceValueWasValid = false;
		function = functionList.getSelectedValue(); // Retrieves the function selected and stores it.

		// To carry out the necessary input checks depending on the function selected.
		switch(function) {
		case "F - Move Finch Forward": case "B - Move Finch Backwards": case "R - Turn Finch Right": case "L - Turn Finch Left":
			if (!finchSpeedField.getText().equals("") && finchSpeedField.getText().matches(VERIFY_NUM_CHECK)) { // Regular Expression to check if the input string only contains integers.
				finchSpeed = Integer.parseInt(finchSpeedField.getText());
				speedWasValid = checkFinchSpeedField(finchSpeed); // Checks if the input is within the set valid range.
			}else
				finchSpeedFieldError();
			if (!finchDurationField.getText().equals("") && finchDurationField.getText().matches(VERIFY_NUM_CHECK)) {
				finchDuration = Integer.parseInt(finchDurationField.getText()) * 1000; // Retrieve text and parse the value.
				durationWasValid = checkFinchDurationField(finchDuration);
			}else
				finchDurationFieldError();
			break;
		case "T - Trace Previous Movements":
			if (!finchMovesToTraceField.getText().equals("") && finchMovesToTraceField.getText().matches(VERIFY_NUM_CHECK)) {
				traceValue = Integer.parseInt(finchMovesToTraceField.getText()); // Retrieve text and parse the value.
				traceValueWasValid = checkFinchMovesToTraceField(traceValue);
			}else
				finchTraceValueFieldError();
			break;
		case "W - Write To Text File": case "X - Execute Commands From A File":
			FinchNavigate.functionExecutor(function, finchSpeed, finchDuration, traceValue); // Proceeds to function Executor as no inputs are taken.
			break;
		default:
			System.exit(0); // Quits the program, this shouldn't ever happen.
		}

		// If all inputs are valid, then proceeds to the functionExecutor.
		if ((speedWasValid && durationWasValid) || traceValueWasValid)
			FinchNavigate.functionExecutor(function, finchSpeed, finchDuration, traceValue);
	}

	// Checks if speed is within the range(0 - 200).
	protected static boolean checkFinchSpeedField(int finchSpeed) {

		if (finchSpeed < MIN_SPEED || finchSpeed > MAX_SPEED) {
			finchSpeedFieldError();
			speedWasValid = false;
		}else {
			finchSpeedField.setBorder(new LineBorder(Color.BLACK,2));
			speedWasValid = true;
		}
		return speedWasValid;
	}

	// Checks if duration is within the range(0 - 6).
	protected static boolean checkFinchDurationField(int finchDuration) {

		if (finchDuration < MIN_DURATION || finchDuration > MAX_DURATION) {
			finchDurationFieldError();
			durationWasValid = false;
		}else {
			finchDurationField.setBorder(new LineBorder(Color.BLACK,2));
			durationWasValid = true;
		}
		return durationWasValid;
	}

	// Checks if the trace value is less than the number of executed commands. 
	protected static boolean checkFinchMovesToTraceField(int traceValue) {

		if (traceValue > executedCommands.size()) {
			finchTraceValueFieldError();
			traceValueWasValid = false;
		}else {
			finchMovesToTraceField.setBorder(new LineBorder(Color.BLACK,2));
			traceValueWasValid = true;
		}
		return traceValueWasValid;
	}
}