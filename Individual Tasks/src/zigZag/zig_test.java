package zigZag;

import java.util.Random;
import java.util.Scanner;
import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.io.IOException;

public class zig_test {

	public static int paths;
	public static int lengthZigzag;
	static Random rand = new Random();
	static int speed = rand.nextInt(154)+ 101;
	static Finch myfinch = new Finch();
	static double calcD;
	static double time;
	private static Scanner input;

	public static void ZigZagMain() {// throws IOException {

		isFinchLevel();

		zagzig Zigzag = new zagzig ();
		//zagzig totalDistance = new zagzig();
		//zagzig straightLine = new zagzig();
		//zagzig timeTaken = new zagzig();
		//zagzig fileWriting = new zagzig();


		input = new Scanner(System.in); 

		System.out.print("Enter how many sections you'd like: ");
		paths = input.nextInt();

		//Asks user to enter how many paths they want

		while (paths > 12 || paths % 2 != 0) {
			System.out.println("Error, invald value! Please enter a number no more than 12 and even:  ");
			paths = input.nextInt();
		}

		//Asks user to enter the distance they want each path to be 
		System.out.print("Enter desired distance: ");
		lengthZigzag = input.nextInt(); 

		while ((lengthZigzag > 85) ||  (lengthZigzag < 15)) {
			System.out.println("Error, invald value! Please enter a number no more than 85 and no less than 15:  ");
			lengthZigzag = input.nextInt();
		}

		//Calls the methods in the other class

		Zigzag.MovingZigZag(myfinch,paths,lengthZigzag);
		Zigzag.TotalDistanceTravelled(paths, lengthZigzag);
		Zigzag.StraightLineDistance(lengthZigzag, paths);
		Zigzag.timeTaken(paths);
		//Zigzag.FileWriting();

	}

	// Checks if Finch is level

	public static void isFinchLevel() {

		while (myfinch.isFinchLevel() == false) {
			System.out.println("Place finch on floor!.");
		}


	}

}


