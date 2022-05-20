import edu.cmu.ri.createlab.terk.robot.finch.Finch;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class SimonGame {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("How many finches do you have: ");
        int howManyFinches = input.nextInt();
        Finch[] finches = new Finch[howManyFinches];

        // Function to create finches.
        for (int i = 1; i <= finches.length; i++) finches[i] = new Finch();

        boolean gameIsOn;
        int score = 0;
        int lives = 3;

        // Creates String ArrayLists.
        ArrayList<String> computerGeneratedColoursArray = new ArrayList<>();
        ArrayList<String> playerInputColoursArray = new ArrayList<>();

        System.out.println("\n\n          Welcome To Simon Game. \nPlace Your Hand In front Of The Finch To Start.");

        // Function to check if the finch is blocked to start the game. If there is no movement then it will display the error message in every 3 secs.
        if (!finches[1].isObstacle()) {
            int i = 0;
            do {
                i++;
                finches[1].sleep(300);
                //System.out.println(i);
                if (i == 10) {
                    System.out.println("     Looks like something went wrong... \n           Please try again.");
                    finches[1].buzz(2500, 1000);
                    i = 0;
                }
                finches[1].setLED(Color.RED, 1000);
            }
            while (!finches[1].isObstacle());
        }

        gameIsOn = true;
        System.out.println("           Game Starting.");

        while (gameIsOn) {

            if (lives > 0) {

                // It generates a random colour and adds it to the computerGeneratedColoursArray.
                computerGeneratedColoursArray.add(getRandomColour());

                //  *Debugging - disconsider this comment* System.out.println(computerGeneratedColoursArray);

                // Function to read each index in the array and flicker each corresponding colour on the allocated finch.
                for (int j = 0; j < computerGeneratedColoursArray.size(); ++j) {
                    String currentIndexColour = computerGeneratedColoursArray.get(j);
                    INNER_LOOP:
                    if ("RED".equals(currentIndexColour)) {
                        finches[1].setLED(Color.RED, 1500);
                        finches[1].sleep(500);
                        break INNER_LOOP;
                    } else if ("BLUE".equals(currentIndexColour)) {
                        finches[2].setLED(Color.BLUE, 1500);
                        finches[2].sleep(500);
                        break INNER_LOOP;
                    } else if ("YELLOW".equals(currentIndexColour))  {
                        finches[3].setLED(Color.YELLOW, 1500);
                        finches[3].sleep(500);
                        break INNER_LOOP;
                    } else {
                        finches[4].setLED(Color.GREEN, 1500);
                        finches[4].sleep(500);
                        break INNER_LOOP;
                    }
                }

                System.out.println("       Players Turn.......");

                // Function to take players' input by checking which finch is blocked and then adding colour to the playerInputColoursArray.
                if (!finches[1].isObstacle() || !finches[2].isObstacle() || !finches[3].isObstacle() || !finches[4].isObstacle()) {
                    for (int j = 0; j < computerGeneratedColoursArray.size(); ++j) {
                        INNER_LOOP2:
                        while (true) {
                            if (finches[1].isObstacle()) {
                                finches[1].setLED(Color.RED, 1000);
                                playerInputColoursArray.add("RED");
                                finches[1].sleep(500);
                                break INNER_LOOP2;
                            } else if (finches[2].isObstacle()) {
                                finches[2].setLED(Color.BLUE, 1000);
                                playerInputColoursArray.add("BLUE");
                                finches[2].sleep(500);
                                break INNER_LOOP2;
                            } else if (finches[3].isObstacle()) {
                                finches[3].setLED(Color.YELLOW, 1000);
                                playerInputColoursArray.add("YELLOW");
                                finches[3].sleep(500);
                                break INNER_LOOP2;
                            } else if (finches[4].isObstacle()) {
                                finches[4].setLED(Color.GREEN, 1000);
                                playerInputColoursArray.add("GREEN");
                                finches[4].sleep(500);
                                break INNER_LOOP2;
                            }
                        }
                    }
                    // *Debugging - disconsider this comment* System.out.println(playerInputColoursArray);
                }

                // Check if the computerGeneratedColoursArrays matches the playerInputColoursArray.
                boolean checkingArrays = computerGeneratedColoursArray.equals(playerInputColoursArray); //returns true because lists are equal

                // If the players' input is correct, then it increments the score and continues.
                if (checkingArrays == true) {
                    score++;
                    System.out.print("Correct - Score : " + score + "\n");
                    playerInputColoursArray.clear();
                }

                // If the input is incorrect, decrements live by 1 and clears both the arrays.
                else {
                    lives--;
                    System.out.println("Game Over");
                    System.out.print("Final Score : " + score + "\n");
                    System.out.println("Remaining Lives : " + lives + "\nTry Again");

                    // It ask if the players want to continue playing further, else exits the game.
                    Scanner input2 = new Scanner(System.in);
                    System.out.print("Want To Play Again? (1 = Yes / 2 = No) :  ");
                    int wantToPlayAgain = input2.nextInt();

                    if (wantToPlayAgain == 1) {
                        computerGeneratedColoursArray.clear();
                        playerInputColoursArray.clear();
                        score = 0;
                        gameIsOn = true;
                    } else {
                        System.out.println("Thank You For Playing, See You Soon !!!");
                        gameIsOn = false;
                        for (int i = 1; i <= finches.length; i++) finches[i].quit();
                        System.exit(0);
                    }
                }
            }
            // Exits the game if not enough attempt left (lives = 0).
            else {
                System.out.println("Defeated \nThank You For Playing, See You Soon !!!");
                gameIsOn = false;
            }
        }
        // Function quits all the finch created that were created at the start and exits.
        for (int i = 1; i <= finches.length; i++) finches[i].quit();
        System.exit(0);
    }

    //Function to generate a random colour.
    private static String getRandomColour() {
        Random rand = new Random();
        String[] colour = {"RED", "YELLOW", "GREEN", "BLUE"};
        return colour[rand.nextInt(4)];
    }
}