import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		int speedAccumulatorPokemonA = 0;
		int speedAccumulatorPokemonB = 0;
		Random rand = new Random();
		int userInput;
		Scanner input = new Scanner(System.in);
		PokeBalls pokeballs = new PokeBalls();
		Player human = new Player();
		Player enemy = new Player();
		
		playersSelectPokemon(human, enemy, pokeballs);
		summonPokemon(human, enemy, pokeballs);
		
		do {
			System.out.println();
			System.out.println("Speed Accumulator Human Pokemon: " + speedAccumulatorPokemonA + " + "  + human.getCurrentPokemon().getSpeed() + " = " + (speedAccumulatorPokemonA + human.getCurrentPokemon().getSpeed()));
			speedAccumulatorPokemonA = speedAccumulatorPokemonA + human.getCurrentPokemon().getSpeed();
			System.out.println("Speed Accumulator Enemy Pokemon: " + speedAccumulatorPokemonB + " + "  + enemy.getCurrentPokemon().getSpeed() + " = " + (speedAccumulatorPokemonB + enemy.getCurrentPokemon().getSpeed()));
			speedAccumulatorPokemonB = speedAccumulatorPokemonB + enemy.getCurrentPokemon().getSpeed();
			System.out.println();
			
			if(speedAccumulatorPokemonA >= 100) {
				System.out.println("Press 1 to Switch current Pokemon");
				System.out.println("Press 2 to attack enemy");
				System.out.println("Press 3 to continue");
				do {
					System.out.println("Choose an option: ");
					userInput = input.nextInt();
				}while(userInput > 3 || userInput <=0);
				System.out.println();
			}
			else {
				System.out.println("Press 1 to Switch current Pokemon");
				System.out.println("Press 2 to continue");
				do {
					System.out.println("Choose an option: ");
					userInput = input.nextInt();
				}while(userInput > 2 || userInput <=0);
				System.out.println();
				
			}
			
			if(userInput == 1) {
				human.showPokemon();
				int choice;
				System.out.println("Select a pokemon to switch with: ");
				Pokemon newPokemon = null;
				do {
					choice = input.nextInt();
					newPokemon = human.getPokemonAtIndex(choice);
				}while(newPokemon == null);
				System.out.println("You have swapped " + human.getCurrentPokemon().getName() + " with " + human.getPokemonAtIndex(choice).getName());
				human.summonPokemon(choice);
				
			}else if(userInput == 2) {
				int attackAgain = -1;
				do {
					
					if(speedAccumulatorPokemonA >= 100) {
						human.getCurrentPokemon().showSkill();
						int choice = 0;
						do {
							choice = input.nextInt();
						}while(choice <= 0 || choice > 4);
						int accuracy = rand.nextInt(100) + 1;
						
						if(accuracy > human.getCurrentPokemon().getSkill(choice).getAccuracy()) {
							System.out.println(human.getCurrentPokemon().getName() + "'s attack missed " + enemy.getCurrentPokemon().getName());
							speedAccumulatorPokemonA -= 100;
						}
						else {
							
							int dmg = human.getCurrentPokemon().attack(choice, enemy.getCurrentPokemon());
							if(dmg < 0) {
								System.out.println(human.getCurrentPokemon().getName() + " was unable to cause any damange to enemy's " + enemy.getCurrentPokemon().getName());
							}
							else {
								System.out.println("Human's " + human.getCurrentPokemon().getName() + " attacked enemy's " + enemy.getCurrentPokemon().getName() + " with " + dmg + " damage");
								enemy.getCurrentPokemon().setHp(enemy.getCurrentPokemon().getHp() - dmg);
								System.out.println(enemy.getCurrentPokemon().getName() + " now has " + enemy.getCurrentPokemon().getHp() + " hp");
								speedAccumulatorPokemonA -= 100;
								
								
								
								if(!enemy.getCurrentPokemon().isAlive()) {
									System.out.println("Enemy's " + enemy.getCurrentPokemon().getName() + " has been knocked out!");
									if(enemy.noPokemonLeft()) {
										System.out.println("Human has won the match!");
										return;
									}

								}
							}
						}	
						
					}
					
					if(speedAccumulatorPokemonA >= 100) {
						System.out.println("Press 1 to attack again OR Press 2 to continue");
						do {
							attackAgain = input.nextInt();
						}while(userInput <= 0 || userInput > 2);
					}
					
				}while(speedAccumulatorPokemonA >= 100 && attackAgain == 1);
			}
			
			
			
			if(speedAccumulatorPokemonB >= 100 && enemy.getCurrentPokemon().isAlive()) {
				int randomSkill = rand.nextInt(4) + 1;
				int dmg = enemy.getCurrentPokemon().attack(randomSkill, human.getCurrentPokemon());
				int accuracy = rand.nextInt(100) + 1;
				
				
				if(accuracy > enemy.getCurrentPokemon().getSkill(randomSkill).getAccuracy()) {
					System.out.println(enemy.getCurrentPokemon().getName() + "'s attacked missed " + human.getCurrentPokemon().getName());
					speedAccumulatorPokemonB -= 100;
				}
				else {
					
					if(dmg < 0) {
						System.out.println(enemy.getCurrentPokemon().getName() + " was unable to cause any damange to human's " + human.getCurrentPokemon().getName());
					}
					else {
						human.getCurrentPokemon().setHp(human.getCurrentPokemon().getHp() - dmg);
						System.out.println("Enemy's " + enemy.getCurrentPokemon().getName() + " attacked " + " human's " + human.getCurrentPokemon().getName() + " with " + dmg + " damage");
						System.out.println(human.getCurrentPokemon().getName() + " now has " + human.getCurrentPokemon().getHp() + " hp");
						speedAccumulatorPokemonB -= 100;
						
						
						if(!human.getCurrentPokemon().isAlive()) {
							System.out.println(human.getCurrentPokemon().getName() + " was knocked out!");
							if(human.noPokemonLeft()) {
								System.out.println("Enemy has won the match!");
								return;
							}
							human.showPokemon();
							int choice2;
							System.out.println("Select a pokemon to switch with: ");
							choice2 = input.nextInt();
							System.out.println("You have swapped " + human.getCurrentPokemon().getName() + " with " + human.getPokemonAtIndex(choice2).getName());
							human.summonPokemon(choice2);
							
						}
					}
				}
				
			}else if(!enemy.getCurrentPokemon().isAlive()){
				Pokemon newPokemon = null;
				int choice;
				System.out.print("Enemy swapped " + enemy.getCurrentPokemon().getName() + " with ");
				do {
					choice = rand.nextInt(3) + 1;
					newPokemon = enemy.getPokemonAtIndex(choice);
				}while(newPokemon == null);
				enemy.summonPokemon(choice);
				System.out.println(newPokemon.getName());
			}
			
			
		}while(human.getPokemonLeft() > 0 && enemy.getPokemonLeft() > 0);
		
		


	}
	
	public static void playersSelectPokemon(Player human, Player enemy, PokeBalls pokeballs) {
		int userInput = 1;
		Scanner input = new Scanner(System.in);
		Random rand = new Random();
		
		for(int a = 0; a < 3; a++) {
			pokeballs.showPokemon();
			System.out.println("Please select a pokemon: ");
			do {
				userInput = input.nextInt();
			}while(userInput <= 0 || userInput > pokeballs.getNoOfPokemon());
			human.addPokemon(pokeballs.getPokemon(userInput));
			System.out.println("You have select " + human.getPokemonAtIndex(a + 1).getName());
			System.out.println();
			enemy.addPokemon(pokeballs.getPokemon(rand.nextInt(pokeballs.getNoOfPokemon()) + 1));
			System.out.println("Enemy selected " + enemy.getPokemonAtIndex(a + 1).getName());
			System.out.println();
			
		}
		
	}
	
	public static void summonPokemon(Player human, Player enemy, PokeBalls pokeballs) {
		
		int userInput;
		Scanner input = new Scanner(System.in);
		Random rand = new Random();
		
		human.showPokemon();
		do {
			System.out.println("Please summon a pokemon");
			userInput = input.nextInt();
			
			if(userInput > 0 && userInput <= 3) {
				human.summonPokemon(userInput);
				System.out.println("You have chosen " + human.getCurrentPokemon().getName());
				break;
			}
			
		}while(userInput < 0 || userInput > 3);
		
		enemy.summonPokemon(rand.nextInt(3) + 1);
		System.out.println("Enemy has chosen " + enemy.getCurrentPokemon().getName());
	}

}
