import javax.swing.*;

import drawShape.myfinchrobot;
import detectObject.Main;
import searchLight.SearchForLight;
import zigZag.zig_test;
import navigate.UserInterface;


public class GroupGUI{

	public static void main(String[] args) {
		String[] options = {"Detect Object", "Search For Light", "Navigate", "Zig Zag", "Draw Shape", "Quit"};
		int x = JOptionPane.showOptionDialog(null, "Group Project", "Asignment 2",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
		switch(x) {
			case 0:
				Main.DetectObjectMain();
			case 1:
				SearchForLight.SearchLightMain();
			case 2:
				UserInterface.NavigateMain();
			case 3:
				zig_test.ZigZagMain();
			case 4:
				myfinchrobot.DrawShapeMain();
			default:
				System.exit(0);
		}
	}

}









