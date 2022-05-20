package detectObject;

import java.awt.Color;
public class Scaredy extends Main {


	public static void init_Scaredy(int TimeTravelling, long StartTime, int MenuSpeed) {
		//GUI.Menu();
		Mode = "Scaredy";
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
			//FinchTail();
			if (f.isObstacle() == false) {
				FinchTail(TimeTravelling, StartTime, MenuSpeed );
				System.out.println("Searching for Object");
				System.out.println();
				if (End == true) {
					break;
				}
				f.setLED(Color.GREEN);
				RandomDirection();
				Moving(5000, TimeTravelling, StartTime, MenuSpeed );
				Start(TimeTravelling, StartTime, MenuSpeed );


			}
			if (f.isObstacle() == true) {
				
				System.out.println("Running for Object");
				System.out.println();
				f.setLED(Color.RED);
				EncounteredObjects +=1;
				Run(TimeTravelling, StartTime, MenuSpeed );
				Start(TimeTravelling, StartTime, MenuSpeed );
			}
		}

	}


	private static void Run(int TimeTravelling, long StartTime, int MenuSpeed) {
		System.out.println("Run");
		System.out.println();
		Turn180();
		Moving(3000, TimeTravelling, StartTime, MenuSpeed );

	}





}
