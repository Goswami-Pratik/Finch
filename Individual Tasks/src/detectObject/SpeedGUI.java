package detectObject;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SpeedGUI extends GUI{

	public static void SpeedMenu() {	
		System.out.println("Getting Speed");
		System.out.println();
		JFrame Speedf=new JFrame("Asignment 2");
		JLabel Title;
		JButton Select;
		
		Title=new JLabel("Finch Speed");
		Title.setBounds(70, 10, 1000, 100);
		Title.setFont(new Font("Arial Black", Font.PLAIN, FontSize));
		
		String Options[] = {"2","3","4","5","6","7","8","9","10"};
		JComboBox SpeedMenu=new JComboBox(Options);
		SpeedMenu.setBounds((Fx/2)-Bx/2, (Fy/4), Bx, By); 

		
		Select=new JButton("Select");
		Select.setBounds((Fx/2)-Bx/2, (Fy/4)+BGap, Bx, By);

		Speedf.add(SpeedMenu);
		Speedf.add(Select);
		Speedf.add(Title);
		Speedf.setSize(Fx, Fy);
		Speedf.setLayout(null);
		Speedf.setVisible(true);

		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String x = SpeedMenu.getSelectedItem().toString();
				int y = Integer.valueOf(x);
				Speedf.setVisible(false);	
				int v = (int) (y*25.5);
				setMenuSpeed(v);
				setMenuSpeed(y);
				SpeedCheck = true;
				return;
			}
		};
		
		Select.addActionListener(actionListener);
		while (SpeedCheck == false) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

		


}
