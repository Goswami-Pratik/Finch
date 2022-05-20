package navigate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class UserInterface extends JPanel {
	// Creating variables and defining constants.
	static int finchSpeed = 0;
	static int finchDuration = 0;
	static int traceValue = 0;
	static int totalCommandsExecuted = 0;
	static final  int MIN_SPEED = 0;
	static final int MAX_SPEED = 200;
	static final int MIN_DURATION = 0;
	static final int MAX_DURATION = 6000; // In Milliseconds(ms).
	static final String VERIFY_NUM_CHECK = "[0-9]+"; // Regular Expression to check if input consist of only integers.
	protected static boolean durationWasValid;
	protected static boolean speedWasValid;
	protected static boolean traceValueWasValid;
	protected static String function;

	// Creating list and labels.
	protected static JList<String> functionList;
	private JLabel finchFunctionLabel  = new JLabel("Select Finch Function");
	private static JLabel finchSpeedLabel  = new JLabel("Enter Finch Speed (0-200)");
	private static JLabel finchDurationLabel  = new JLabel("Enter Finch Duration (0-6 Seconds)");
	private static JLabel finchMovesToTraceLabel  = new JLabel("Enter Number Of Moves To Retrace");
	protected static JLabel finchLevelMessage = new JLabel("Place Finch On A Levelled Surface.");
	private static JButton executeButton = new JButton("Execute");

	// Text fields for data entry.
	protected static JTextField finchSpeedField = new JTextField();
	protected static JTextField finchDurationField = new JTextField();
	protected static JTextField finchMovesToTraceField = new JTextField();

	// Array List to store all the commands executed by the finch.
	protected static ArrayList<String> executedCommands = new ArrayList<>();

	// Functions array for selecting the function.
	private static String[] functions = {"F - Move Finch Forward", "B - Move Finch Backwards", "R - Turn Finch Right", "L - Turn Finch Left", "T - Trace Previous Movements", "W - Write To Text File", "X - Execute Commands From A File", "Q - Quit Program"};

	public UserInterface() {
		super(); // Creates a new JPanel with a flow layout.
		setLayout(new BorderLayout());

		//Creating function list.
		functionList = new JList(functions);
		functionList.setVisibleRowCount(4);
		functionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		//Centre the text in the text fields.
		finchSpeedField.setHorizontalAlignment(JTextField.CENTER);
		finchDurationField.setHorizontalAlignment(JTextField.CENTER);
		finchMovesToTraceField.setHorizontalAlignment(JTextField.CENTER);

		//Layout the labels in a panel.
		JPanel labelPane = new JPanel(new GridLayout(0,1));
		labelPane.add(finchFunctionLabel);
		labelPane.add(finchDurationLabel);
		labelPane.add(finchSpeedLabel);
		labelPane.add(finchMovesToTraceLabel);

		//Layout the text fields and set them up.
		JPanel fieldPane = new JPanel(new GridLayout(0,1));
		fieldPane.add(new JScrollPane(functionList));
		fieldPane.add(finchDurationField);
		fieldPane.add(finchSpeedField);
		fieldPane.add(finchMovesToTraceField);
		fieldPane.add(executeButton);

		//Sets a background for the panels.
		fieldPane.setBackground( new Color(0, 63, 252, 20) );
		labelPane.setBackground( new Color(0, 63, 252, 20) );


		//Adds the panels; labels on left, text fields on right. (Format: top, left, bottom, right)
		labelPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 100, 20));
		fieldPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
		add(labelPane, BorderLayout.WEST);
		add(fieldPane, BorderLayout.CENTER);

		// Set default status for the fields and set the background.
		defaultDisplay();
		resetField();
		finchSpeedField.setForeground(Color.BLACK);
		finchDurationField.setForeground(Color.BLACK);

		// Action listener to handle returns from function selector field.
		functionList.addListSelectionListener(event -> {
			if (event.getValueIsAdjusting()) {
				//Switch function to change the visibility of corresponding fields and labels depending on the function selected.
				switch(functionList.getSelectedValue()) {
				case "F - Move Finch Forward": case "B - Move Finch Backwards": case "R - Turn Finch Right": case "L - Turn Finch Left":
					finchSpeedField.setVisible(true);
					finchDurationField.setVisible(true);
					finchMovesToTraceField.setVisible(false);
					finchSpeedLabel.setVisible(true);
					finchDurationLabel.setVisible(true);
					finchMovesToTraceLabel.setVisible(false);
					executeButton.setVisible(true);
					updateTextField();
					break;
				case "T - Trace Previous Movements":
					finchSpeedField.setVisible(false);
					finchDurationField.setVisible(false);
					finchMovesToTraceField.setVisible(true);
					finchSpeedLabel.setVisible(false);
					finchDurationLabel.setVisible(false);
					finchMovesToTraceLabel.setVisible(true);
					executeButton.setVisible(true);
					updateTextField();
					break;
				case "W - Write To Text File": case "X - Execute Commands From A File":
					defaultDisplay();
					executeButton.setVisible(true);
					updateTextField();
					break;
				case "Q - Quit Program":
					JOptionPane.showMessageDialog(null, "Thank You, For Using Finch Navigate. See You Soon !", "Finch Navigate", JOptionPane.PLAIN_MESSAGE);
					System.exit(0); // Quit the program.
					break;
				default:
					defaultDisplay();
					updateTextField();
					break;
				}
			}
		});

		// Action Listener for the execute button.
		executeButton.addActionListener(e -> {
			MyInputVerifier.verify();
			if (totalCommandsExecuted >= 20) // Checks if 20 or more commands have been executed by the finch.
				ConfusedFinch.commandGenerator();
		}
				);

	}

	//Methods to add a red border to the text field and display an error message within the field.
	protected static void finchSpeedFieldError() {
		finchSpeedField.setBorder(new LineBorder(Color.RED,2));
		Toolkit.getDefaultToolkit().beep();
		finchSpeedField.setText("ERROR : ENTER VALUE BETWEEN 0 - 200");
		finchSpeedField.setFont(new Font("TimesRoman BOLD", Font.BOLD, 13));
	}
	protected static void finchDurationFieldError() {
		finchDurationField.setBorder(new LineBorder(Color.RED,2));
		Toolkit.getDefaultToolkit().beep();
		finchDurationField.setText("ERROR : ENTER VALUE BETWEEN 0 - 6");
		finchDurationField.setFont(new Font("TimesRoman BOLD", Font.BOLD, 13));
	}
	protected static void finchTraceValueFieldError() {
		finchMovesToTraceField.setBorder(new LineBorder(Color.RED,2));
		Toolkit.getDefaultToolkit().beep();
		finchMovesToTraceField.setText("ERROR : ENTER VALUE BETWEEN 0 - " + executedCommands.size());
		finchMovesToTraceField.setFont(new Font("TimesRoman BOLD", Font.BOLD, 13));
	}
	
	// Function To Check if the Finch Is On A Levelled Surface.
	protected static void finchLevelCheckerGUI() {
		// Creates a new frame.
		JFrame finchLevelFrame = new JFrame("Navigate Finch");
		finchLevelFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		finchLevelFrame.setSize(610, 450);
		finchLevelFrame.setVisible(true);

		// Creating a new panel.
		JPanel finchLevelPane = new JPanel();
		finchLevelMessage.setFont(new Font("Serif", Font.BOLD, 32));
		finchLevelPane.add(finchLevelMessage, SwingConstants.CENTER);
		finchLevelPane.setBorder(BorderFactory.createEmptyBorder(160, 0, 0, 0));
		finchLevelFrame.add(finchLevelPane, BorderLayout.CENTER);

		FinchNavigate.finchLevelChecker();
		
		// Makes the program wait a second before hiding the frame.
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finchLevelFrame.setVisible(false);
	}

	// Updates the text field.
	public void updateTextField() {
		revalidate();
		repaint();
	}

	// Sets the default state of the label/fields.
	public static void defaultDisplay() {
		finchSpeedField.setVisible(false);
		finchDurationField.setVisible(false);
		finchMovesToTraceField.setVisible(false);
		finchSpeedLabel.setVisible(false);
		finchDurationLabel.setVisible(false);
		finchMovesToTraceLabel.setVisible(false);
		executeButton.setVisible(false);
	}

	// Resets the text field - empty input fields and sets border to black.
	public static void resetField() {
		finchSpeedField.setText("");
		finchDurationField.setText("");
		finchMovesToTraceField.setText("");
		finchSpeedField.setBorder(new LineBorder(Color.BLACK,2));
		finchDurationField.setBorder(new LineBorder(Color.BLACK,2));
		finchMovesToTraceField.setBorder(new LineBorder(Color.BLACK,2));
	}

	// Creating the frame and its components.
	public static void NavigateMain() {
		finchLevelCheckerGUI();
		JFrame frame = new JFrame("Navigate Finch");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JComponent contentPane = new UserInterface();
		frame.setContentPane(contentPane);
		frame.setSize(610, 450);
		frame.setVisible(true);
	}
}