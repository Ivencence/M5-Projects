import java.util.Scanner;
import java.util.Random;

public class GuessingGame {
    public static void main(String[] args) {
        Random rand = new Random();
        int randomNum = rand.nextInt(100) + 1;
        Scanner myObj = new Scanner(System.in);

        System.out.println("Welcome to the Improved Guessing Game!");
        System.out.println("The contestant must guess a random whole number between 1 and 100 in 5 tries.");
        System.out.println("The program will give feedback on whether the number is bigger or smaller than the previous guess.");
        System.out.println("The contestants may quit by typing 'quit'. Let the games begin!");


        int i = 1;
        while (i <= 5) {
            int number = checkForInt(myObj);

            if(number ==0){
                System.out.println("You just quit. The number is: " + randomNum);
            break;
            }
            if (number == randomNum) {
                    System.out.println("Congratulations! You won!");
            break;
                }

                else if(number > randomNum) {
                    System.out.println("You guessed incorrectly. The number is smaller than your guess.");
                }


                else {
                    System.out.println("You guessed incorrectly. The number is bigger than your guess.");
                }

                int attemptsLeft = 5 - i;
                System.out.println("You have " + attemptsLeft + " attempts left.");
                i++;
            }



        }


        public static int checkForInt(Scanner myObj) {
    System.out.println("Please, enter a number:");
            if (myObj.hasNextInt()) {
               return myObj.nextInt();
            }else {
                String input = myObj.next();

                if (input.equalsIgnoreCase("quit")) {
                    return 0;
                } else {
                    System.out.println(input + " is not a valid number.");
                    return checkForInt(myObj);
                }
            }

    }
}
