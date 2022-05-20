package drawShape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;
public class myfinchrobot extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static void DrawShapeMain(){
		
	       JFrame frame = new JFrame("Main Menu");
	       // Frame name
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       // closes on quit
	       frame.setSize(300,300);
	       // W and H
	       frame.setResizable(true);
	       // resize 
	       frame.setVisible(true);
	       // To see gui 
	       JPanel panel = new JPanel();
	       // panel area
	       panel.setLayout(null);
	       
	       JButton square = new JButton("Square");
	       // Making a new button 
	       square.setBounds(80, 50, 100, 100);
	       // Size of button
	       frame.add(square);
	       // Adds to frame
	       square.addActionListener(new Square());
	       // Button listen 
	       // 

	}
	static class Square implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			double num = 96.1538461538;
			// 1 millisecond moved each time
			String input = JOptionPane.showInputDialog(null, "Enter the length: ");
			// Message box that captures string 
			int duration = (Integer.parseInt(input));
			// Turns string into int
			Finch myFinch = new Finch ();
			// call a new  finch
			if (myFinch.isFinchLevel()==true) {
				// If its level
			myFinch.setWheelVelocities(100, 100, (int) (duration*num) );
			// time is input times num which is 10.4 cm every second 
			 myFinch.sleep(1000);
			myFinch.setWheelVelocities(000, 95,1500);
			// Is the finch turning 90 degrees
			 myFinch.sleep(1000);
				myFinch.setWheelVelocities(100, 100, (int) (duration*num) );
			 myFinch.sleep(1000);
			myFinch.setWheelVelocities(000, 95,1500);
			 myFinch.sleep(1000);
				myFinch.setWheelVelocities(100, 100, (int) (duration*num) );
			 myFinch.sleep(1000);
			myFinch.setWheelVelocities(000, 95,1500);
			 myFinch.sleep(1000);
				myFinch.setWheelVelocities(100, 100, (int) (duration*num) );
			 myFinch.sleep(1000);
			 myFinch.buzz(400, 1000);
			 myFinch.quit();
			 
			 System.exit(0);
		}
	}
}
}


	
 
		
	
	






