package searchLight;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class movements {
	
	// All the important variables are declared here.
	
		protected static Finch Finch = new Finch();
	
		protected static boolean isFinchOnFloor = false;
		protected static boolean isFinchOnTail = false;
	
		protected static long startTime = 0;
		protected static long duration = 0;
	
		protected static int startLeft;
		protected static int startRight;
	
		protected static ArrayList <Integer> leftLightValues = new ArrayList <Integer>();
		protected static ArrayList <Integer> rightLightValues = new ArrayList <Integer>();
	
		protected static boolean lightFound = false;
		protected static int lightCounter = 0;
		
		protected static int hitCounter = 0;
	
	/* This method is used many places throughout the code.
	 * Its aim is to check if the position of the Finch has changed.
	 */
	protected static void FinchStatus ()
	{
		if ( Finch.isFinchLevel() == true)
		{
			isFinchOnFloor = true;
			isFinchOnTail = false;
		}
		else
		{
			isFinchOnFloor = false;
		}
			
		if (Finch.isBeakUp() == true)
		{
			isFinchOnTail = true;
			isFinchOnFloor = false;
		}
		else
		{
			isFinchOnTail = false;
		}
	}
	
	// If this method is called it adds the light sensor values to the ArrayLists.
	private static void addToArray(int r, int l)
	{
		rightLightValues.add(r);
		leftLightValues.add(l);
	}
	
	/* This part of code executes the search for the light.
	 * It contains a timer because after 4 seconds the Finch changes directions.
	 * It sets the beak of the Finch yellow.
	 * If the current light sensor values are different than the start values it adds them to the ArrayLists.
	 * And if they are higher than the start values plus 20 (to make sure it found an actual light source) it starts to follow it.
	 */
	protected static void search()
	{
		while(isFinchOnFloor == true)
		{
			long timerStart = System.currentTimeMillis();
			FinchStatus();
			if (isFinchOnTail == true)
			{
				GUI.Question();
				break;
			}
			
			while ((System.currentTimeMillis()/1000) - (timerStart/1000) <= 4)
			{
				FinchStatus();
				FinchHitsTheWall();
				if (isFinchOnTail == true)
				{
					break;
				}
				Finch.saySomething("Search.", 400);
				
				Finch.setLED(Color.YELLOW);
				Finch.setWheelVelocities(50, 50);
				
				int rightLight = Finch.getRightLightSensor();
				int leftLight = Finch.getLeftLightSensor();
			
				if ((rightLight != startRight) || (leftLight != startLeft))
				{
					addToArray(rightLight, leftLight);
					if ((rightLight > startRight+20) || ((leftLight > startLeft+20)))
					{
						lightFound = true;
						lightCounter++;
						follow();
					}
					else
					{
						lightFound = false;
					}
				}
			}
			
			timerStart = 0;
			turn();
			FinchStatus();
			if (isFinchOnTail == true)
			{
				break;
			}
		}
	}
	
	// This method makes the Finch turn with 90 degrees to either left or right.
	private static void turn()
	{
		Finch.saySomething("Turn.");
		Finch.setLED(Color.GREEN);
		Finch.setWheelVelocities(0, 0, 500);
		if (randomDirectionGenerator() == "right")
		{
			Finch.setWheelVelocities(80, -80, 1000);
		}
		else
		{
			Finch.setWheelVelocities(-80, 80, 1000);
		}
	}
	
	// This method random generates if the Finch will turn to left or right.
	private static String randomDirectionGenerator ()
	{
		String direction;
		String directions[] = {"right","left"};
		Random random = new Random();
		int x = random.nextInt(2);
		direction = directions[x];
		return direction;
	}
	
	/* This method makes the Finch follow the light source.
	 * If the light values drop under the start values it starts to search again.
	 * The Finch's beak will get brighter if the Finch gets closer to the light source.
	 */
	private static void follow()
	{
		FinchStatus();
		while (lightFound == true)
		{
			Finch.saySomething("Follow.", 400);
			int left = Finch.getLeftLightSensor();
			int right = Finch.getRightLightSensor();
			addToArray(right, left);
			
			if ((right < startRight) || (left < startLeft))
			{
				lightFound = false;
				break;
			}
			
			if(left >= right)
			{
				Finch.setLED(left,0,0);
			}
			else
			{
				Finch.setLED(right,0,0);
			}
			Finch.setWheelVelocities(right, left);
			FinchHitsTheWall();
			FinchStatus();
			if (isFinchOnTail == true)
			{
				GUI.Question();
				break;
			}
		}
	}
	
	// This method warns the user if the Finch hits into an object.
	protected static void FinchHitsTheWall()
	{
		if ((Finch.isObstacleLeftSide() || Finch.isObstacleRightSide()))
		{
			hitCounter++;
			String a = "Please provide the Finch space during the execution.";
			String b = "";
			GUI.Error(a,b);
		}
	}

	// This method returns the highest light value during the execution.
	protected static int highest()
	{
		int highest;
		int highestLeft = startLeft;
		for(int i = 0; i <= (leftLightValues.size()-1); i++)
		{
			if (highestLeft <= leftLightValues.get(i))
			{
				highestLeft = leftLightValues.get(i);
			}
			i++;
		}
		int highestRight = startRight;
		for(int i = 0; i <= (rightLightValues.size()-1); i++)
		{
			if (highestRight <= rightLightValues.get(i))
			{
				highestRight = rightLightValues.get(i);
			}
			i++;
		}
		if (highestLeft <= highestRight)
		{
			highest = highestRight;
		}
		else
		{
			highest = highestLeft;
		}
		return highest;
	}
	
	// This method returns the lowest light value during the execution.
	protected static int lowest()
	{
		int lowest;
		int lowestLeft = startLeft;
		for(int i = 0; i <= (leftLightValues.size()-1); i++)
		{
			if (lowestLeft >= leftLightValues.get(i))
			{
				lowestLeft = leftLightValues.get(i);
			}
			i++;
		}
		int lowestRight = startRight;
		for(int i = 0; i <= (rightLightValues.size()-1); i++)
		{
			if (lowestRight >= rightLightValues.get(i))
			{
				lowestRight = rightLightValues.get(i);
			}
			i++;
		}
		if (lowestLeft >= lowestRight)
		{
			lowest = lowestRight;
		}
		else
		{
			lowest = lowestLeft;
		}
		return lowest;
	}
	
	// This method returns the average light value during the execution.
	protected static int average()
	{
		int sumRight = 0;
		for (int i = 0; i <= (rightLightValues.size()-1); i++)
		{
			sumRight += rightLightValues.get(i);
		}
		int sumLeft = 0;
		for (int i = 0; i <= (leftLightValues.size()-1); i++)
		{
			sumLeft += leftLightValues.get(i);
		}
		int sizeRight = rightLightValues.size();
		int sizeLeft = leftLightValues.size();
		int average = (sumRight + sumLeft) / (sizeRight + sizeLeft);
		return average;
	}

}
