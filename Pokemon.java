

public class Pokemon {
	
	private String name;
	private String type;
	private int attack;
	private int defense;
	private int hp;
	private int speed;
	private Skill[] skill;
	private int noOfSkill;

	
	
	public Pokemon(String name, String type, int attack, int defense, int hp, int speed) {
		
		this.name = name;
		this.type = type;
		this.attack = attack;
		this.defense = defense;
		this.hp = hp;
		this.speed = speed;
		skill = new Skill[4];
		noOfSkill = 0;
		
	}
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void addSkill(String name, String type, int power, int accuracy) {
		Skill s = new Skill(name, type, power, accuracy);
		skill[noOfSkill] = s;
		noOfSkill++;
	}
	
	public void showSkill() {
		
		for(int a = 0; a < noOfSkill; a++) {
			System.out.print(a + 1 + " ");
			System.out.println("Name: " + skill[a].getName());
			System.out.println("Type: " + skill[a].getType());
			System.out.println("Power: " + skill[a].getPower());
			System.out.println("Accuracy: " + skill[a].getAccuracy());
		}
	}
	
	public boolean isAlive() {
		if(hp < 0) {
			return false;
		}
		return true;
	}
	
	public int attack(int index, Pokemon enemy) {
		if(index <= 0 || index > 3) {
			return -1;
		}
		Skill selectedSkill = skill[index - 1];
		String enemyType = enemy.getType();
		double damageMultiplier = 0.0;
		
		if(selectedSkill.getType() == "grass" && enemyType == "water") {
			damageMultiplier = 2;
		}
		else if(selectedSkill.getType() == "fire" && enemyType == "grass") {
			damageMultiplier = 2;
		}
		else if(selectedSkill.getType() == "water" && enemyType == "fire") {
			damageMultiplier = 2;
		}
		else if(selectedSkill.getType() == "grass" && enemyType == "fire") {
			damageMultiplier = 0.5;
		}
		else if(selectedSkill.getType() == "fire" && enemyType == "water") {
			damageMultiplier = 0.5;
		}
		else if(selectedSkill.getType() == "water" && enemyType == "grass") {
			damageMultiplier = 0.5;
		}
		else {
			damageMultiplier = 1;
		}
		
		double damage = (((this.attack * selectedSkill.getPower() 
				/ enemy.getDefense()) / 20) + 2) 
				* damageMultiplier;
		
		return (int) Math.round(damage);

		
	}
	
	public Skill getSkill(int index) {
		return skill[index  - 1];
	}

	
	
}
