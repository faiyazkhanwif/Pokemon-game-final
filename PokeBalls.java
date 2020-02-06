import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



public class PokeBalls {
	
	private Pokemon[] pokemon;
	private int noOfPokemon;
	
	public PokeBalls() throws IOException {
		pokemon = new Pokemon[20];
		noOfPokemon = 0;
		getPokemonData();
		
	}
	
	public void showPokemon() {
		
		for(int a = 0; a < noOfPokemon; a++) {
			System.out.println((a+1) + ". " + pokemon[a].getName());
		}
	}
	
	public Pokemon getPokemon(int index) {
		Pokemon p = pokemon[index - 1];
		
		Pokemon[] temp = new Pokemon[noOfPokemon - 1];
		int currentSave = 0;
		
		for(int a = 0; a < noOfPokemon; a++) {
			if(!(a == (index - 1))) {
				temp[currentSave] = pokemon[a];
				currentSave++;
			}
		}
			noOfPokemon--;
			pokemon = temp;
			return p;
	}
	
	public int getNoOfPokemon() {
		return noOfPokemon;
	}
	
	private void getPokemonData() throws IOException {
		File file = new File("./src/PokemonData.txt");
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String[] temp = new String[30];
		int current = 0;
		String data = "";
		while((data = br.readLine()) != null) {
			temp[current] = data;
			current++;
			if(data.equals("")) {
				pokemon[noOfPokemon] = new Pokemon(temp[0], temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]),
						Integer.parseInt(temp[4]), Integer.parseInt(temp[5]));
				pokemon[noOfPokemon].addSkill(temp[6], temp[7], Integer.parseInt(temp[8]), Integer.parseInt(temp[9]));
				pokemon[noOfPokemon].addSkill(temp[10], temp[11], Integer.parseInt(temp[12]), Integer.parseInt(temp[13]));
				pokemon[noOfPokemon].addSkill(temp[14], temp[15], Integer.parseInt(temp[16]), Integer.parseInt(temp[17]));
				pokemon[noOfPokemon].addSkill(temp[18], temp[19], Integer.parseInt(temp[20]), Integer.parseInt(temp[21]));
				current = 0;
				noOfPokemon++;
			}
			
		}
		fr.close();
		br.close();
	}
}
