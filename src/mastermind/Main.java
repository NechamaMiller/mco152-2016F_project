package mastermind;

import java.util.Scanner;

public class Main 
{
	public static void main (String[] args)
	{
		greeting();
		Game game = new Game();	
		System.out.println();
		do
		{
			game.checkGuess(getGuess());
			
			if (game.isGameWon())
			{
				System.out.println("You guessed my code! It was " + game.getKey() + ". You win!!!");
			}
			else
			{
				System.out.println("\nHere are your guesses so far:");
				for (int i=0; i<game.getNumGuesses(); i++)
				{
					System.out.println("\nGuess number " + (i+1) + ":\n" + game.getGuesses()[i]);
				}
			}
		}
		while (game.getNumGuesses()<10 && !game.isGameWon());
		
		if (!(game.isGameWon()))
		{
			System.out.println("Game over! You lose! My code was " + game.getKey() + ". Better luck next time...");
		}
	}
	
	public static void greeting()
	{
		System.out.println("Welcome to mastermind. Here are the rules of the game:\n"
				+ "Before we start, I will generate a secret code. Your job is to break that code!\n"
				+ "There are " + Color.values().length + " possible colors to choose from. They are: " + displayColors() + ".\n"
				+ "My code can be any combination of 4 of these colors - repeats are allowed.\n"
				+ "You will have ten turns to guess my code.\n"
				+ "On each turn, you will enter four colors, and I will tell you how many whites you scored on this round, and how many reds.\n"
				+ "A \"white\" means you got the right color, but in the wrong place and a \"red\" means you got a color in the right place.\n"
				+ "If you guess my code before time is up, you win!\n"
				+ "Good luck!");
	}
	
	public static String displayColors()
	{
		StringBuilder sb = new StringBuilder();
		
		for (int i=0; i<Color.values().length; i++)
		{
			sb.append(Color.values()[i].toString().toLowerCase());
			
			if (i<(Color.values().length-2))
			{
				sb.append(", ");
			}
			else if(i == (Color.values().length-2))
			{
				sb.append(" and ");
			}
		}
		
		return sb.toString();
	}
	public static Color[] getGuess()
	{
		Scanner input = new Scanner(System.in);
		
		Color[] colors = new Color[4];
		
		for (int i=0; i<colors.length;i++)
		{
			System.out.print("Enter choice number " + (i+1) + " from the following colors: " + displayColors() + ":");			
			
			String color = input.nextLine().trim().toUpperCase();
			
			while (!(isValidColor(color)))
			{
				System.out.print("Color number " + (i+1) + " wasn't valid. Please try again:");
				color = input.nextLine().trim().toUpperCase();
			}
			
			colors[i] = Color.valueOf(color);
		}
		
		return colors;
	}
	
	public static boolean isValidColor(String input)
	{
		if (input == null)
		{
			return false;
		}
				
		for (Color color: Color.values())
		{
			if (color.toString().equals(input.toUpperCase()))
			{
				return true;
			}
		}
		
		return false;
	}
}
