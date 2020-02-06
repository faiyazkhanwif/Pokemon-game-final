
public class Player {
	
	private Pokemon[] pokemon;
	private int noOfPokemon;
	private Pokemon currentPokemon;
	private int pokemonLeft;
	
	public Player() {
		pokemon = new Pokemon[3];
		noOfPokemon = 0;
		currentPokemon = null;
		pokemonLeft = 0;
	}
	
	public void addPokemon(Pokemon p) {
		pokemon[noOfPokemon] = p;
		noOfPokemon++;
		pokemonLeft++;
	}
	
	public boolean summonPokemon(int index) {
		if(!pokemon[index - 1].isAlive() || index <= 0 || index > 3) {
			return false;
		}
		
		if(currentPokemon != null) {
			if(!currentPokemon.isAlive()) {
				pokemonLeft--;
			}
			
			Pokemon temp = currentPokemon;
			currentPokemon = pokemon[index - 1];
			pokemon[index - 1] = temp;
			return true;
		}
		else {
			currentPokemon = pokemon[index - 1];
			pokemon[index - 1] = null;
		}
		
		return true;
		
	}
	
	public int getPokemonLeft() {
		return pokemonLeft;
	}
	
	public void currentPokemonSkills() {
		currentPokemon.showSkill();
	}
	
	public Pokemon getCurrentPokemon() {
		return currentPokemon;
	}
	
	public void showPokemon() {
		
		for(int a = 0; a < noOfPokemon; a++) {
			if(pokemon[a] == null) {
				continue;
			}
			else if(pokemon[a].isAlive()) {
				System.out.println((a+1) + ". " + pokemon[a].getName());
			}
		}
	}
	
	public Pokemon getPokemonAtIndex(int index) {
		if(pokemon[index - 1] == null) {
			return null;
		}
		return pokemon[index - 1];
	}
	
	public boolean noPokemonLeft() {
		int deadCount = 0;
		for(int a = 0; a < 3; a++) {
			if(pokemon[a] != null) {
				if(!pokemon[a].isAlive()) {
					deadCount++;
				}
			}
		}
		
		if(!currentPokemon.isAlive()) {
			deadCount++;
		}
		
		if(deadCount == 3) {
			return true;
		}
		return false;
	}
	
	
}
