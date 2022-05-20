package detectObject;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Logs extends GUI {


	public static void LogMenu(int TimeTravelling, long StartTime, int MenuSpeed) {
		System.out.println("Logs Menu");
		System.out.println();
		End = true;
		String[] options = {"Yes", "No"};
		int x = JOptionPane.showOptionDialog(null, "Do you want to view the logs?", "Logs",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		if (x == 0) {
			Logs(TimeTravelling, StartTime, MenuSpeed );
		}
		else {
			System.exit(0);
		}
	}
	
	public static void Logs(int TimeTravelling, long StartTime, int MenuSpeed) {
		
		JLabel Title, LMode, LDistance, RTime, TTime, JEncountObj;
		JButton LQuit;
		JFrame Logsf=new JFrame("Asignment 2");



		System.out.println("Showing Logs");
		System.out.println();

		System.out.println("Initilising Logs");
		System.out.println();
		int Spacing = 50;

		Title=new JLabel("Logs");
		Title.setBounds(70, 10, 1000, 100);
		Title.setFont(new Font("Arial Black", Font.PLAIN, FontSize));

		LMode=new JLabel("Mode: "+Mode);
		LMode.setBounds((Fx/2)-Bx/2, ((Fy/4)-Spacing)+20, Bx, By);

		RTime=new JLabel("Run Time: "+CalcFinalTime(StartTime)+"s");
		RTime.setBounds((Fx/2)-Bx/2, (Fy/4)+20, Bx, By);

		TTime=new JLabel("Travel Time: "+TimeTravelling/1000+"s");
		TTime.setBounds((Fx/2)-Bx/2, (int) ((Fy/4)+Spacing)+20, Bx, By);

		DecimalFormat df4 = new DecimalFormat("#.####");
		LDistance=new JLabel("Distance: "+df4.format(CalcDistance(TimeTravelling, MenuSpeed ))+"m");
		LDistance.setBounds((Fx/2)-Bx/2, (int) ((Fy/4)+Spacing*2)+20, Bx, By);

		JEncountObj=new JLabel("Encountered Objects: "+EncounteredObjects);
		JEncountObj.setBounds((Fx/2)-Bx/2, (int) ((Fy/4)+Spacing*3)+20, Bx, By);

		LQuit=new JButton("Quit");
		LQuit.setBounds((Fx/2)-Bx/2, (Fy)-(BGap*2), Bx, By);

		Logsf.add(Title);
		Logsf.add(LMode);
		Logsf.add(RTime);
		Logsf.add(TTime);
		Logsf.add(LDistance);
		Logsf.add(JEncountObj);
		Logsf.add(LQuit);
		Logsf.setSize(Fx, Fy);
		Logsf.setLayout(null);
		Logsf.setVisible(true);


		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {				
				Logsf.dispose();
				JButton button = (JButton) actionEvent.getSource();
				if (button == LQuit) {
					System.exit(0);
				}
			}
		};
		LQuit.addActionListener(actionListener);
		System.out.println("Waiting for User to Quit");
		System.out.println();
	}

	protected static double CalcDistance(int TimeTravelling, int MenuSpeed) {
		System.out.println(TimeTravelling);
		System.out.println(MenuSpeed);
		System.out.println();
		Map<Integer, Double> VelDic = new HashMap<Integer, Double>() {{
			put(10, 0.34155);
			put(9, 0.30519);
			put(8, 0.28215);
			put(7, 0.25245);
			put(6, 0.21878);
			put(5, 0.18117);
			put(4, 0.14256);
			put(3, 0.10692);
			put(2, 0.07425);
		}};
		double Distance = (TimeTravelling/1000)*(VelDic.get(MenuSpeed));
		return Distance;
	}
	
	
	protected static double CalcFinalTime(long StartTime) {
		double duration = System.currentTimeMillis() - StartTime;
		FinalTime = duration/1000;
		return FinalTime;
	}



}
