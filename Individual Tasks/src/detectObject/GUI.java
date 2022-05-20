package detectObject;

import java.awt.Font;
import java.awt.event.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.*;  
import javax.swing.*;


public class GUI extends Main{
	protected static int Fx = 400;
	protected static int Fy = 500;
	protected static int Bx = 200;
	protected static int By = 50;
	protected static int BGap = 65;
	protected static int FontSize = 35;

	public static void MainMenu(int TimeTravelling, long StartTime, int MenuSpeed) {
		System.out.println("Main Menu");
		System.out.println();
		JButton BCurious, BScaredy, BAny, BQuit;
		JLabel Title;

		Title=new JLabel("Detect Object");
		Title.setBounds(70, 10, 1000, 100);
		Title.setFont(new Font("Arial Black", Font.PLAIN, FontSize));

		JFrame Menuf=new JFrame("Asignment 2");
		BCurious=new JButton("Curious");
		BCurious.setBounds((Fx/2)-Bx/2, (Fy/4), Bx, By);


		BScaredy=new JButton("Scaredy");
		BScaredy.setBounds((Fx/2)-Bx/2, (Fy/4)+BGap, Bx, By);

		BAny=new JButton("Any");
		BAny.setBounds((Fx/2)-Bx/2, (Fy/4)+BGap*2, Bx, By);

		BQuit=new JButton("Quit");
		BQuit.setBounds((Fx/2)-Bx/2, (Fy/4)+BGap*3, Bx, By);

		Menuf.add(Title);
		Menuf.add(BCurious);
		Menuf.add(BScaredy);
		Menuf.add(BAny);
		Menuf.add(BQuit);
		Menuf.setSize(Fx, Fy);
		Menuf.setLayout(null);
		Menuf.setVisible(true);

		ActionListener actionListenerMenu = new ActionListener() {

			public void actionPerformed(ActionEvent actionEvent) {				
				Menuf.dispose();
				LogsAnswer = true;
				JButton button = (JButton) actionEvent.getSource();
				if (button == BCurious) {
					Curious.init_Curious(TimeTravelling, StartTime, MenuSpeed );
				}
				if (button == BScaredy) {
					Scaredy.init_Scaredy(TimeTravelling, StartTime, MenuSpeed );
				}
				if (button == BAny) {
					int x = new Random().nextInt(2);
					if (x == 0) {
						Scaredy.init_Scaredy(TimeTravelling, StartTime, MenuSpeed );
					}
					if (x == 1) {
						Curious.init_Curious(TimeTravelling, StartTime, MenuSpeed );
					}
				}
				if (button == BQuit) {
					System.exit(0);
				}

			}
		};
		BCurious.addActionListener(actionListenerMenu);
		BScaredy.addActionListener(actionListenerMenu);
		BAny.addActionListener(actionListenerMenu);
		BQuit.addActionListener(actionListenerMenu);
		while (LogsAnswer == false) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}




	public static void FinchFlat() {
		System.out.println("Checking if Finch is Flat");
		System.out.println();
		JFrame Flatf=new JFrame("Asignment 2");
		while (f.isFinchLevel() == false) {
			JLabel Title, Text;


			Title=new JLabel("Detect Object");
			Title.setBounds(70, 10, 1000, 100);
			Title.setFont(new Font("Arial Black", Font.PLAIN, FontSize));

			Text=new JLabel("Please Place object on a flat surface :)");
			Text.setFont(new Font("Calibri", Font.PLAIN, (int) (FontSize/1.7)));
			Text.setBounds((Fx/2)-Bx+40, (Fy/4), Bx+1000, By);

			Flatf.add(Title);
			Flatf.add(Text);
			Flatf.setSize(Fx, Fy);
			Flatf.setLayout(null);
			Flatf.setVisible(true);
		}
		Flatf.dispose();
	}




}
