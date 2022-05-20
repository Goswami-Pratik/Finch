package searchLight;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;

public class GUI extends movements {
	
	// This method makes a base for the other GUI.
	public static void Empty(JFrame x , JPanel y)
	{
		x.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		x.setSize(400, 400);
		x.setLayout(new GridLayout(1, 1));
		x.add(y,BorderLayout.CENTER);
		x.add(y); 
		
		BoxLayout boxlayout = new BoxLayout(y, BoxLayout.Y_AXIS);
		y.setLayout(boxlayout);
		y.setBorder(new EmptyBorder(new Insets(60,100,60,100)));
	}
	
	// This method contains the instructions what the user will receive at the beginning of the program.
	public static void Instructions()
	{
		final JFrame frame = new JFrame("SearchForLight");
		final JFrame frame1 = new JFrame("SearchForLight");
		JPanel panel = new JPanel();
		JLabel label1 = new JLabel("Please place the Finch on the floor", JLabel.CENTER);
		JButton buttonNEXT = new JButton("NEXT");
		
		Empty(frame,panel);

		buttonNEXT.addActionListener( new ActionListener()
		{ 
			public void actionPerformed(ActionEvent e)
			{  
				frame.dispose();
				buttonNEXT.setVisible(false);
		
				JLabel label2 = new JLabel("If you want to finish the program please place the Finch on its tail.", JLabel.CENTER);
				JButton buttonOK= new JButton("OK");
				
				Empty(frame1,panel);
				
				buttonOK.addActionListener( new ActionListener()
				{ 
					public void actionPerformed(ActionEvent e)
					{  
						frame1.dispose();
						frame1.setVisible(false);
					}
				}
				);
				
				label1.setVisible(false);
				panel.add(label2);
				panel.add(buttonOK);
				
				frame1.pack();
				frame1.setVisible(true);
				
			}
		}
		);   
		
		panel.add(label1);
		panel.add(buttonNEXT);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	/* This method asks the user the user if he/she wants to see the log of the execution.
	 * If the Finch is on its tail at the start of the execution it will display an error message.
	 * If the user chose 'Yes' it displays the log.
	 * If the user chose 'No' the program will exit.
	 */
	public static void Question()
	{
		long endTime = System.currentTimeMillis();
		duration = (endTime - startTime)/1000;
		if (duration != (endTime/1000))
		{
			//Finch.playClip("H:\\eclipseWorkspace\\SearchForLight\\src\\searchLight\\Magyar.wav");
			
			final JFrame frame = new JFrame("SearchForLight");
			JPanel panel = new JPanel();
			
			Empty(frame, panel);
			
			JLabel label = new JLabel("Do you want to see the log of the execution?", JLabel.CENTER);
			JButton yes = new JButton("Yes");
			JButton no = new JButton("No");
		
			yes.addActionListener( new ActionListener()
			{ 
				public void actionPerformed(ActionEvent e)
				{  
					LogOfTheExecution();
					frame.setVisible(false);
				}
			}
								 );
		
			no.addActionListener( new ActionListener()
			{ 
				public void actionPerformed(ActionEvent e)
				{  
					System.exit(0);
				}
			}
								);
		
			panel.add(label);
			panel.add(yes);
			panel.add(no);
		
			frame.pack();
			frame.setVisible(true);	
		}
		else 
		{
			String a = "Invalid execution.";
			String b = "Try again.";
			Error(a,b);
		}
	}
	
	//This method contains the GUI for the log of the execution.
	public static void LogOfTheExecution()
	{	
		Finch.quit();
		
		final JFrame frame = new JFrame("SearchForLight");
		JPanel panel = new JPanel();
	
		Empty(frame,panel);
				
		String a = String.valueOf(highest());
		String b = String.valueOf(lowest());
		String c = String.valueOf(average());
		String d = String.valueOf(lightCounter);
		String e = String.valueOf(hitCounter);
				
		String[][] data = {{"Left and right light sensor values at the beginning of the execution: ",startLeft + " and " + startRight},
						   {"The highest light sensor value recorded during the execution was: ", a}, 
						   {"The lowest light sensor value recorded during the execution was: ",b}, 
						   {"The average light sensor value recorded during the execution was: ",c}, 
						   {"The duration of the execution was: ", duration + " secs"},
						   {"The number of times the finch detected light: ",d},
						   {"The number of times the finch hit an object during the execution: ", e}};
		String[] column = {"Description", "Value(s)"};
				
		JTable table = new JTable(data, column);
		JScrollPane scrollPane = new JScrollPane(table);
		TableColumn Column = table.getColumnModel().getColumn(0);
		Column.setPreferredWidth(400);
		        
		JButton button = new JButton("EXIT");
		button.setSize(50, 25);
		        
		button.addActionListener( new ActionListener()
		{ 
			public void actionPerformed(ActionEvent e)
			{  
				System.exit(0);
			}
		}
		);
		    
		panel.add(scrollPane);
		panel.add(button);
		    
		frame.pack();
		frame.setVisible(true);
	}
	
	//This method is the base for every (all 3) error message which can might pop up during the execution.
	public static void Error(String labelName1, String labelName2)
	{
		final JFrame frame = new JFrame("SearchForLight");
		JPanel panel = new JPanel();
		JLabel label1 = new JLabel(labelName1, JLabel.CENTER);
		JLabel label2 = new JLabel(labelName2, JLabel.CENTER);
		JButton button = new JButton ("OK");
		
		Empty(frame,panel);
		
		button.addActionListener( new ActionListener()
		{ 
			public void actionPerformed(ActionEvent e)
			{  
				frame.dispose();
			}
		}
								);
		
		panel.add(label1);
		panel.add(label2);
		panel.add(button);
		
		frame.pack();
		frame.setVisible(true);
	}
}
