package searchLight;

public class SearchForLight extends movements {
	
	SearchForLight()
	{
		//The program first tells the user what to do in order to see the execution
		GUI.Instructions();
		Finch.sleep(3500); //Enough time for the user to read the messages 
		
		/* This loop checks which position the Finch is in.
		 * If the Finch is on the floor (or a flat surface) it sets isFinchOnFloor true so the program can start.
		 * If the Finch is on its tail it sets isFinchOnTail true which can be because the user wants to see the log or because of an error.
		 * If it is neither on its tail or on a flat surface an error message will pop up.
		 */
		
		while ((isFinchOnFloor&&isFinchOnTail) == false)
		{
			FinchStatus();
			if((isFinchOnFloor || isFinchOnTail) == true)
			{
				if (isFinchOnFloor == true)
				{
					startTime = System.currentTimeMillis();
				}
				break;
			}
			else
			{
				String a = "Oops. Looks like something went wrong.";
				String b = "Please place the Finch on the floor.";
				GUI.Error(a,b);
				Finch.sleep(3000);	
			}
		}
		
		/* This part of the code sets the first light values from the sensors.
		 * It adds the values to ArrayLists.
		 * The search for the light begins.
		 */
		if(isFinchOnFloor == true)
		{
			startRight = Finch.getRightLightSensor();
			rightLightValues.add(startRight);
			startLeft = Finch.getLeftLightSensor();
			leftLightValues.add(startLeft);
			search();
		}
		
		//If the Finch is on the its tail a question will pop up to the user if they want to see the log of the execution or not.
		if (isFinchOnTail == true)
		{
			GUI.Question();
		}
	}
	
	public static void SearchLightMain() 
	{
		new SearchForLight();
	}
}
