package detectObject;

import java.awt.Color;
import java.util.Arrays;
public class Curious extends Main{

	public static void init_Curious(int TimeTravelling, long StartTime, int MenuSpeed) {
		Mode = "Curious";
		System.out.println("Mode: "+Mode);
		System.out.println();
		FinchTail(TimeTravelling, StartTime, MenuSpeed );
		Start(TimeTravelling, StartTime, MenuSpeed );

	}
	private static void Start(int TimeTravelling, long StartTime, int MenuSpeed) {
		System.out.println("Start");
		System.out.println();
		System.out.println("End = "+End);
		System.out.println();
		while (End == false) {
			System.out.println("End = "+End);
			System.out.println();
			FinchTail(TimeTravelling, StartTime, MenuSpeed );
			if (f.isObstacle() == true) {
				
				if (End == true) {
					break;
				}
				System.out.println("Object Detected");
				System.out.println();
				f.setLED(Color.BLUE, 500);
				if (NewObject == true) {
					System.out.println("New Object");
					System.out.println();
					NewObject = false;
					EncounteredObjects += 1;
				}
				Following(TimeTravelling, StartTime, MenuSpeed );
			}
			if (f.isObstacle() == false) {
				if (End == true) {
					break;
				}
				System.out.println("Searching for Object");
				System.out.println();
				RandomDirection();
				Moving(5000, TimeTravelling, StartTime, MenuSpeed );
				Start(TimeTravelling, StartTime, MenuSpeed );
			}
		}

	}
	private static void Following(int TimeTravelling, long StartTime, int MenuSpeed) {
		int Turns = 0;
		while (End == false) {
			f.setLED(Color.GREEN);
			FinchTail(TimeTravelling, StartTime, MenuSpeed );
			System.out.println("Following Object");
			System.out.println();
			boolean Moved = false;
			System.out.println(Arrays.toString(f.getObstacleSensors()));
			if (f.getObstacleSensors()[0] == true && f.getObstacleSensors()[1] == true) {
				System.out.println("Object Stationary");
				System.out.println();
				f.setLED(Color.BLUE);
				if (Moved == false) {
					final long CurrentTime = System.currentTimeMillis();
					while (f.isObstacle() == true) {
						
						System.out.println(System.currentTimeMillis()- CurrentTime);
						if (End == true) {
							break;
						}
						if (System.currentTimeMillis()- CurrentTime >= 7500) {
							System.out.println("Object didnt Move, Finding new Object");
							System.out.println();
							NewObject(TimeTravelling, StartTime, MenuSpeed );
						}
					}
				}	
				Following(TimeTravelling, StartTime, MenuSpeed );
			}
			if (f.getObstacleSensors()[0] == false && f.getObstacleSensors()[1] == false) {
				if (End == true) {
					break;
				}
				System.out.println("Object Forward");
				System.out.println();
				Moving(4000, TimeTravelling, StartTime, MenuSpeed );
				Start(TimeTravelling, StartTime, MenuSpeed );
			}
			if (f.getObstacleSensors()[0] == true && f.getObstacleSensors()[1] == false) {
				if (Turns >= 3) {
					NewObject(TimeTravelling, StartTime, MenuSpeed );
				}
				if (End == true) {
					break;
				}
				System.out.println("Object Left");
				System.out.println();
				f.setWheelVelocities(-100, 100, 400);
				Following(TimeTravelling, StartTime, MenuSpeed );
			}
			if (f.getObstacleSensors()[1] == true && f.getObstacleSensors()[0] == false) {
				if (Turns >= 3) {
					NewObject(TimeTravelling, StartTime, MenuSpeed );
				}
				if (End == true) {
					break;
				}
				System.out.println("Object Right");
				System.out.println();
				f.setWheelVelocities(100, -100, 400);
				Following(TimeTravelling, StartTime, MenuSpeed );
			}
		}
	}
	private static void NewObject(int TimeTravelling, long StartTime, int MenuSpeed) {
			NewObject = true;
			Turn180();
			Start(TimeTravelling, StartTime, MenuSpeed );		
	}
	

}
