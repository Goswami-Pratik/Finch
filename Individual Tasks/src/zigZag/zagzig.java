package zigZag;

import java.util.Random;
import java.lang.Math; 
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class zagzig {

	public static Random rand = new Random();
	static int max = 255;
	static int min = 100;
	public static int speed =   rand.nextInt((max - min) + 1) + min;
	
	 
	public static double calcD;
	public static double time;
	public static double totalDistance;
	public static double timeTaken;
	public static double straightLineDistance;

	public void MovingZigZag(Finch myfinch, int paths, int lengthZigzag) {
		

		calcD = (speed * 14)/125;

		time = (lengthZigzag / calcD) * 1000;

		for(int i = 1; i <= paths; i++ )
		{
			
			if(i % 2 == 0)
			{
				myfinch.setLED(Color.blue);
				myfinch.setWheelVelocities(speed,speed,(int) time);
				myfinch.sleep(1000);
				finchTurningR(myfinch);
			}
			else
			{
				myfinch.setLED(Color.green);
				myfinch.setWheelVelocities(speed,speed,(int) time);
				myfinch.sleep(1000);
				finchTurningL(myfinch);
			}

			
		}
		
		TurnToRetrace(myfinch);
		retraceZig(myfinch, paths, lengthZigzag);
	}

	//finch  turning right
	public void finchTurningR(Finch myfinch) {
		
		myfinch.setWheelVelocities(0,150,1000);

	}

	//finch turning left   
	public void finchTurningL(Finch myfinch) {
		
		myfinch.setWheelVelocities(150,0,1000);
	}

	//finch turn to retrace   
	public void TurnToRetrace(Finch myfinch) {
		
		myfinch.setWheelVelocities(0,150,1000);
	}

	public void retraceZig(Finch myfinch,int paths, int lengthZigzag) {

		for(int i = 1; i <= paths; i ++ )
		{
			
			if(i % 2 == 0)
			{
				myfinch.setLED(Color.green);
				myfinch.setWheelVelocities(speed,speed,(int) time);
				myfinch.sleep(1000);
				finchTurningL(myfinch);
			}
			else
			{
				myfinch.setLED(Color.blue);
				myfinch.setWheelVelocities(speed,speed,(int) time);
				myfinch.sleep(1000);
				finchTurningR(myfinch);
			}
		}
	}
	public void TotalDistanceTravelled(int paths, int lengthZigzag) {
	totalDistance = (paths * lengthZigzag);
		System.out.println("The total distance travelled is" + " " + totalDistance);

	}


	public void StraightLineDistance(int lengthZigzag, int paths) {

		double c = (Math.pow(lengthZigzag, 2))+ (Math.pow(lengthZigzag, 2));
		 straightLineDistance = ((Math.sqrt(c) * paths)/2);
		System.out.println("The straight line distance is: " + " " + straightLineDistance);


	}

	public void timeTaken(int paths) {

		timeTaken = (paths * time)/1000;
		System.out.println("The time it took run the program was" + " " +timeTaken);

	}
	// Write the variables into a file
	public void FileWriting () throws IOException {
		
		FileWriter logPrinted = new FileWriter ("zagzig.txt");
		BufferedWriter logInfo = new BufferedWriter(logPrinted);

		String line = " The length of the Zigzag was  " + zig_test.lengthZigzag 
	    + "\n" + "The number of paths were: " + zig_test.paths  
		+ "\n" + "The randomly generated wheel speed is " + speed 
		+ "\n " + "The length of the traversed path was: " + totalDistance
		+ "\n" + "Duration of the time is took the finch to complete the zigzag: " + timeTaken
		+ "\n" + "The straight line distance travelled: " + straightLineDistance; 

		logInfo.write(line);
		logInfo.newLine(); 
		logInfo.close();
		logPrinted.close();
		

	}
	
}



