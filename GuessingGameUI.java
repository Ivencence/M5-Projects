import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;
import java.awt.*;

public class GuessingGameUI {
    public static void main(String[] args) {
        Random rand = new Random();
        int randomNum = rand.nextInt(100) + 1;

        JFrame frame = new JFrame("Guessing Game");
        frame.setSize(500, 500);

        JButton guess = new JButton("GUESS");
        JTextArea instructions = new JTextArea("Welcome to the Improved Guessing Game! \n"
                + "The contestant must guess a random whole number between 1 and 100 in 5 tries.\n"
                + "The program will give feedback on whether the number is bigger or smaller than the previous guess.\n"
                + "The contestants may quit by typing 'quit'. Let the games begin!\n");
        JTextField input = new JTextField();
        JTextField output = new JTextField();

        instructions.setFont(new Font("Arial", Font.PLAIN, 12));
        input.setFont(new Font("Arial", Font.PLAIN, 12));
        output.setFont(new Font("Arial", Font.PLAIN, 12));
        frame.setLayout(new GridLayout(4, 1));

        final int[] attempts = {0};

        guess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (attempts[0] >= 5) {
                    output.setText("No more attempts left! The number was: " + randomNum);
                    return;
                }

                String text = input.getText().trim();

                if (text.equalsIgnoreCase("quit")) {
                    output.setText("You just quit. The number was: " + randomNum);
                    return;
                }

                if (text.matches("\\d+")) {
                    int userGuess = Integer.parseInt(text);

                    attempts[0]++;

                    if (userGuess == randomNum) {
                        output.setText("Congratulations! You guessed the number: " + randomNum);
                        return;
                    } else if (userGuess > randomNum) {
                        output.setText("The number is smaller than your guess. Attempts left: " + (5 - attempts[0]));
                    } else {
                        output.setText("The number is bigger than your guess. Attempts left: " + (5 - attempts[0]));
                    }

                    if (attempts[0] >= 5) {
                        output.setText("Game Over! The number was: " + randomNum);
                    }
                } else {
                    output.setText("Invalid input. Please enter a valid integer or type 'quit'.");
                }
            }
        });

        frame.add(instructions);
        frame.add(input);
        frame.add(guess);
        frame.add(output);
        frame.setVisible(true);
    }
}
