package detectObject;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

import java.util.Random;


public class Main{

	public static int Speed =255;
	public static boolean SpeedCheck = false;
	
	private static int TimeTravelling = 0;
	private final static long StartTime = System.currentTimeMillis();
	private static int MenuSpeed = 10;

	protected static boolean End = false; //Where or not the finch's code has ended
	protected static String Mode = "";
	protected static Finch f = new Finch();
	protected static int EncounteredObjects = 0;
	protected static boolean NewObject = true; // looking for a new objects
	protected static int EncounterObjects = 0;
	protected static boolean LogsAnswer = false;
	protected static boolean GUIAnswer = false;
	protected static double FinalTime = 0;
	protected boolean NewObjects = true;
	protected static boolean Live = false;
	


	public static void DetectObjectMain() {
		GUI.FinchFlat();
		SpeedGUI.SpeedMenu();
		GUI.MainMenu(TimeTravelling, StartTime, getMenuSpeed() );
	}	


	public static void FinchTail(int TimeTravelling, long StartTime, int MenuSpeed) {
		System.out.println("Checking if Tail is Up");
		System.out.println();
		if (f.isBeakUp()) {
			System.out.println("Stopping Finch");
			System.out.println();
			Logs.LogMenu(TimeTravelling, StartTime, MenuSpeed );

		}
		System.out.println("beak not up");
	}

	protected static void RandomDirection() {
		int x = new Random().nextInt(2);
		if (x == 0) { //left
			f.setWheelVelocities(-255, 255, 200);
		}
		if (x == 1) { //right
			f.setWheelVelocities(255, -255, 200);
		}
	}
	protected static void Moving(int Time, int TimeTravelling, long StartTime, int MenuSpeed) {
		for (int i = 0; i < Time/500; i++) {
			if (End == true) {
				break;
			}
			System.out.println("Move");
			System.out.println();
			f.setWheelVelocities(Speed, Speed, 500);
			TimeTravelling += 500;
			FinchTail(TimeTravelling, StartTime, MenuSpeed );
			if (f.isObstacle() == true) {
				break;
			}

		}
	}
	protected static void Turn180() {
		f.setWheelVelocities(-Speed, -Speed, 250);
		TimeTravelling += 250;
		f.setWheelVelocities(255, -255, 555);
	}


	public static int getMenuSpeed() {
		return MenuSpeed;
	}


	public static void setMenuSpeed(int menuSpeed) {
		MenuSpeed = menuSpeed;
	}	



}