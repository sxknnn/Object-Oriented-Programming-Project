package game;

import java.util.Scanner;
import java.util.Random;

class Traveller {
	public String name;

	public void Classification() {
		Scanner in = new Scanner(System.in);
		System.out.print("\nHello Traveller!\nEnter your name: ");
		name = in.nextLine();
		System.out.println("Welcome to the dungeon " + name);
	}

	public String getName() {
		return name;
	}
}



class FirstLevel {

	public void RandomNumber() {
		Scanner in = new Scanner(System.in);
		Random r1 = new Random();
		int randomNumber = r1.nextInt(10) + 1;
		int guess;
		int attempts = 5;
		System.out.println("\nThis is the first level. Before you proceed any further, complete this level. ");

		while (attempts > 0) {
			System.out.print("\nGuess the number (1-10): ");
			guess = in.nextInt();

			if (guess < randomNumber) {
				System.out.println("Too low! Try again.");
			} else if (guess > randomNumber) {
				System.out.println("Too high! Try again.");
			} else {
				System.out.println("\nCongratulations! You guessed the number.");
				System.out.println("You may proceed to the next level.");
				return; // exit the method if the guess is correct
			}

			attempts--;
			System.out.println("You have " + attempts + " attempts left.");
		}

		System.out.println("You ran out of attempts. You failed this level.");
		Main.main(null);
	}

}

class SecondLevel {
	public void GuessTheWord() {
		Scanner in = new Scanner(System.in);
		String[] words = { "apple", "banana", "orange", "grape", "watermelon" };
		Random random = new Random();
		String selectedWord = words[random.nextInt(words.length)];
		char[] dashedWord = new char[selectedWord.length()];

		for (int i = 0; i < selectedWord.length(); i++) {
			dashedWord[i] = '_';
		}

		System.out.println("\nThis is the second level. Here, you have to guess the name of a fruit. Guess the word:");

		do {
			System.out.println("Current Progress: " + new String(dashedWord));
			System.out.print("Enter a letter: ");
			char guess = in.next().charAt(0);
			guess = Character.toLowerCase(guess);

			boolean correctGuess = false;

			for (int i = 0; i < selectedWord.length(); i++) {
				if (selectedWord.charAt(i) == guess) {
					dashedWord[i] = guess;
					correctGuess = true;
				}
			}

			if (!correctGuess) {
				System.out.println("Incorrect guess. Try again.");
			}

		} while (new String(dashedWord).contains("_"));

		System.out.println("\nCongratulations! You guessed the word.");
		System.out.println("You have successfully completed this level!");
		System.out.println("You may proceed to the next level\n-------------------\n");

	}
}

class ThirdLevel {
	public void MonsterFightGame() {
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		Player player = new Player("Hero", 100, 25, 15, 50);
		String gruntMoves[] = new String[] { "!Spicy Snowballs!", "!!Arctic Gale Slash!!", "!!!Permafrost Pulse!!!" };
		Enemy Grunt = new Enemy("Iceborne Malignity", 100, 15, 5, gruntMoves);
		int attackOrder[] = { 0, 0, 2, 0, 1, 1, 0, 0, 1, 0 };
		int attackCounter = 0;

		String gruntIntro = "In the desolate Frozen Wastes, the malevolent Iceborne Malignity has emerged,\r\n"
				+ "a horde of frost-twisted grunt monsters. Born of an ancient curse, their frosty\r\n"
				+ "gaze chills the once-hopeful lands. Villages tremble as the creatures encroach,\r\n"
				+ "leaving icy desolation. Storms gather overhead, signaling doom as the hero ventures \r\n"
				+ "into the heart of the Frozen Wastes. Armed with determination, the protagonist unravels\r\n"
				+ "the curse, confronting the Malignity's anguished howls.\n";
		for (int i = 0; i < gruntIntro.length(); i++) {
			System.out.printf("%c", gruntIntro.charAt(i));
			try {
				Thread.sleep(20);// 0.02s pause between characters
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}

		System.out.println("You enter into a fight for your very life!!!");
		while (player.getHealth() > 0 && Grunt.isAlive()) {
			printStatsBox("Enemy info -", Grunt);
			printStatsBox("Player info - ", player);
			
			System.out.println("\nWhat do you want to do?");
			System.out.println("1 - Attack, 2 - Heal, 3 - Display Player Stats, 4 - Display Enemy Stats");
			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.println("Please choose type of attack - ");
				System.out.println("1 - Light Attack, 2 - Medium Attack");
				System.out.println("3 - Heavy Attack, 4 - Magic Attack");
				int attackSelect = sc.nextInt();
				do {
					switch (attackSelect) {
						case 1:
							Grunt.takeDamage(player.getDamage(1));
							break;
						case 2:
							Grunt.takeDamage(player.getDamage(2));
							break;
						case 3:
							Grunt.takeDamage(player.getDamage(3));
							break;
						case 4:
							Grunt.takeDamage(player.getDamage(4));
							player.decreaseMagicPoints(20);
							break;
						default:
							System.out.println("Please enter a valid value");
							break;
					}
				} while (attackSelect < 1 || attackSelect > 4);

				System.out.println(
						"The monsters let out violent screeches and use " + gruntMoves[attackOrder[attackCounter]]);
				player.takeDamage((int) Grunt.getDamage(attackOrder[attackCounter++]));
				if (player.getHealth()<= 0) {
					System.out.println("You died. Game Over");
					Main.main(null);
				}
			} else if (choice == 2) {
				int healValue = random.nextInt(20 - 7) + 7;
				// Player chooses to heal
				System.out.println("A majestic light fills you from within, you heal " + healValue);
				player.heal(healValue);
				// Boss attacks
				System.out.println("The monsters let out violent screeches and use " + gruntMoves[attackOrder[attackCounter]]);
				player.takeDamage((int) Grunt.getDamage(attackOrder[attackCounter++]));
				if (player.getHealth()<= 0) {
					System.out.println("You died. Game Over");
					Main.main(null);
				}
			} else if (choice == 3) {
				player.displayStats();
			} else if (choice == 4) {
				Grunt.displayStats();
			} else {
				System.out.println("Please choose a valid option between 1 - 4");
			}
		}

		if (player.getHealth() > 0) {
			System.out.println("\nCongratulations! The monsters are defeated, you pass this level!!");
		} else {
			System.out.println("\nGame over! The monsters were too strong for you.");
		}

		//sc.close();
	}

	private static void printStatsBox(String title, Player player) {
		int boxWidth = 30;
		System.out.println("+" + "-".repeat(boxWidth - 2) + "+");
		System.out.println("|" + centerText(title, boxWidth - 2) + " |");
		System.out.println("|" + "-".repeat(boxWidth - 2) + "|");
		System.out.println("| Name: " + player.getName()
				+ spaces(boxWidth - 12 - String.valueOf(player.getName()).length()) + "   |");
		System.out.println("| Health: " + player.getHealth()
				+ spaces(boxWidth - 12 - String.valueOf(player.getHealth()).length()) + " |");
		System.out.println("+" + "-".repeat(boxWidth - 2) + "+");
	}

	private static void printStatsBox(String title, Enemy enemy) {
		int boxWidth = 30;
		System.out.println("+" + "-".repeat(boxWidth - 2) + "+");
		System.out.println("|" + centerText(title, boxWidth - 2) + "|");
		System.out.println("|" + "-".repeat(boxWidth - 2) + "|");
		System.out.println("| Name: " + enemy.getName()
				+ spaces(boxWidth - 12 - String.valueOf(enemy.getName()).length()) + "   |");
		System.out.println("| Health: " + enemy.getHealth()
				+ spaces(boxWidth - 12 - String.valueOf(enemy.getHealth()).length()) + " |");
		System.out.println("+" + "-".repeat(boxWidth - 2) + "+");
	}

	private static String centerText(String text, int width) {
		int padding = (width - text.length()) / 2;
		return spaces(padding) + text + spaces(padding);
	}

	private static String spaces(int count) {
		return " ".repeat(count);
	}
}

class FourthLevel {
	
	Player player = new Player("Hero", 100, 25, 15, 50);
	
	public void DiceRollingGame() {
		
		System.out.print("This is the fourth level where you have to wager your luck and get the roll equal to your target value.\n");
		
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the target sum: ");
		int targetSum = scanner.nextInt();
		
		int currentSum = 0;
		int rollCount = 0;
		boolean isEqual = false;

		while (currentSum < targetSum && isEqual == false) {
			int roll = rollDice();
			currentSum += roll;
			rollCount++;

			System.out.println("\n-------------------\nRoll " + rollCount + "\n-------------------\nYou rolled a " + roll
					+ " \nCurrent sum: " + currentSum + "\n-------------------\n");

			if (currentSum == targetSum) {
				System.out.println("Congratulations! You reached the target sum.\n-------------------\n");
				isEqual = true;
				break;
			}
			else if (currentSum > targetSum) {
				System.out.println("Current number too high, you lost! Try Again! :>\n");
				
				currentSum = 0;
				rollCount = 0;
				player.takeDamage(15);
				System.out.println("You took damage. Bad luck hurts.");
				
				if (player.getHealth()<= 0) {
					System.out.println("You died. Game Over");
					Main.main(null);
				}
//				DiceRollingGame();
			}

			System.out.print("Do you want to roll again? (y/n): ");
			char choice = scanner.next().charAt(0);

			if (choice == 'n') {
				System.out.println("You chose not to roll. Your current count number is: " + currentSum);
				break;
			}
		}
		
		
	}

	private static int rollDice() {
		Random rand = new Random();
		return rand.nextInt(6) + 1;
	}

}



class FifthLevel
{
	public void MonsterFightGame()
	{
	Scanner sc = new Scanner(System.in);
	Random random = new Random();
	Player player = new Player("Hero", 100, 25, 15, 50);
	String bossMoves[] = new String[]{"Icicle spear!", "Blizzard!!", "Frostburn Nova!!!"};
	Enemy Boss = new Enemy("Minister Blizzard", 100, 15, 10, bossMoves);
	int attackOrder[] = {0,0,2,0,1,1,0,0,1,0};
	int attackCounter = 0;

	String bossIntro = "As the chilling winds howl and frost encases the battleground, a figure emerges from the blizzard's heart.\r\n"
						+ "Minister Blizzard, a towering colossus draped in robes of ice, commands the frozen tempests at will.\r\n"
						+ "Glacial spikes jut from his shoulders, crackling with elemental power, while his eyes gleam like frozen sapphires, filled with an ancient malevolence.\r\n"
						+ "With each step, the ground freezes, and the air thickens with an icy aura, heralding a formidable battle against this tyrant of frost and snow.\n" ;
	for(int i = 0; i < bossIntro.length(); i++)
	{
	    System.out.printf("%c", bossIntro.charAt(i));
	    try{
	        Thread.sleep(20);//0.02s pause between characters
	    }catch(InterruptedException ex){
	        Thread.currentThread().interrupt();
	    }
	}

	System.out.println("You enter into a fight for your very life!!!");
	while (player.getHealth()>0 && Boss.isAlive()) 
	        {
				printStatsBox("Boss info ", Boss);
				printStatsBox("Player info", player);

	            System.out.println("\nWhat do you want to do?");
				System.out.println("1 - Attack, 2 - Heal, 3 - Display Player Stats, 4 - Display Enemy Stats");
	            int choice = sc.nextInt();

	            if (choice == 1) 
	            {
					System.out.println("Please choose type of attack - ");
					System.out.println("1 - Light Attack, 2 - Medium Attack");
					System.out.println("3 - Heavy Attack, 4 - Magic Attack");
					int attackSelect = sc.nextInt();
					if (Boss.getHealth()>0){
						do
					{
						switch (attackSelect) 
						{
							case 1:
								Boss.takeDamage(player.getDamage(1));
								break;
							case 2:
								Boss.takeDamage(player.getDamage(2));
								break;
							case 3:
								Boss.takeDamage(player.getDamage(3));
								break;
							case 4:
								Boss.takeDamage(player.getDamage(4));
								player.decreaseMagicPoints(20);
								break;
							default:
								System.out.println("Please enter a valid value");		
								break;
						}		
					} while (attackSelect<1 || attackSelect>4);		

					}
					else{
						break;
					}
					System.out.println("The monster lets out a mighty roar and uses " + bossMoves[attackOrder[attackCounter]]);
					player.takeDamage((int)Boss.getDamage(attackCounter++));
					if (player.getHealth()<= 0) {
						System.out.println("You died. Game Over");
						Main.main(null);
					}
	            } 
	            else if (choice == 2) 
	            {
					int healValue = random.nextInt(20 - 7) + 7;
	                // Player chooses to heal
	                System.out.println("A majestic light fills you from within, you heal " + healValue);
					player.heal(healValue);  
					// Boss attacks
					System.out.println("The monster lets out a mighty roar and uses " + bossMoves[attackOrder[attackCounter]]);
					player.takeDamage((int)Boss.getDamage(attackCounter++));
					if (player.getHealth()<= 0) {
						System.out.println("You died. Game Over");
						Main.main(null);
					}
	            } 
	            else if (choice == 3)
	            {
	                player.displayStats();
	            }
				else if (choice == 4)
				{
					Boss.displayStats();
				}
				else 
				{
					System.out.println("Please choose a valid option between 1 - 4");
				}
	        }

	        if (player.getHealth()>0)
	        {
	            System.out.println("\nCongratulations! The boss is defeated, you win the game!!!!");
	        } 
	        else 
	        {
	            System.out.println("\nGame over! The monster was too strong for you.");
	        }
	
	sc.close();
	}
	private static void printStatsBox(String title, Player player) 
	    {
	        int boxWidth = 30;
	        System.out.println("+" + "-".repeat(boxWidth - 2) + "+");
	        System.out.println("|" + centerText(title, boxWidth - 2) + " |");
	        System.out.println("|" + "-".repeat(boxWidth - 2) + "|");
	        System.out.println("| Name: " + player.getName() + spaces(boxWidth - 12 - String.valueOf(player.getName()).length()) + "   |");
	        System.out.println("| Health: " + player.getHealth() + spaces(boxWidth - 12 - String.valueOf(player.getHealth()).length()) + " |");
	        System.out.println("+" + "-".repeat(boxWidth - 2) + "+");
	    }
		private static void printStatsBox(String title, Enemy enemy) 
	    {
	        int boxWidth = 30;
	        System.out.println("+" + "-".repeat(boxWidth - 2) + "+");
	        System.out.println("|" + centerText(title, boxWidth - 2) + "|");
	        System.out.println("|" + "-".repeat(boxWidth - 2) + "|");
	        System.out.println("| Name: " + enemy.getName() + spaces(boxWidth - 12 - String.valueOf(enemy.getName()).length()) + "   |");
	        System.out.println("| Health: " + enemy.getHealth() + spaces(boxWidth - 12 - String.valueOf(enemy.getHealth()).length()) + " |");
	        System.out.println("+" + "-".repeat(boxWidth - 2) + "+");
	    }
	private static String centerText(String text, int width) 
		{
	        int padding = (width - text.length()) / 2;
	        return spaces(padding) + text + spaces(padding);
	    }

	    private static String spaces(int count) 
	    {
	        return " ".repeat(count);
	    }
}

public class Main {
	public static void main(String[] args) {
		String art = "  _____      __      _     ____     ___       ___   ______     ______      _____   _________   ________ \r\n"
				+ " / ____\\    /  \\    / )   / __ \\   (  (       )  ) (_  __ \\   (   __ \\    (_   _) (_   _____) (___  ___)\r\n"
				+ "( (___     / /\\ \\  / /   / /  \\ \\   \\  \\  _  /  /    ) ) \\ \\   ) (__) )     | |     ) (___        ) )   \r\n"
				+ " \\___ \\    ) ) ) ) ) )  ( ()  () )   \\  \\/ \\/  /    ( (   ) ) (    __/      | |    (   ___)      ( (    \r\n"
				+ "     ) )  ( ( ( ( ( (   ( ()  () )    )   _   (      ) )  ) )  ) \\ \\  _     | |     ) (           ) )   \r\n"
				+ " ___/ /   / /  \\ \\/ /    \\ \\__/ /     \\  ( )  /     / /__/ /  ( ( \\ \\_))   _| |__  (   )         ( (    \r\n"
				+ "/____/   (_/    \\__/      \\____/       \\_/ \\_/     (______/    )_) \\__/   /_____(   \\_/          /__\\   ";

		System.out.println(art); // Print the ASCII art
		Traveller p1 = new Traveller();
		p1.Classification();
		FirstLevel level1 = new FirstLevel();
		level1.RandomNumber();
		SecondLevel level2 = new SecondLevel();
		level2.GuessTheWord();
		ThirdLevel level3 = new ThirdLevel();
		level3.MonsterFightGame();
		System.out.println("\n\n");
		FourthLevel level4 = new FourthLevel();
		level4.DiceRollingGame();
		System.out.println("\n\n");
		FifthLevel level5 = new FifthLevel();
		level5.MonsterFightGame();
		return;
	}
}
