public class Skill {
		private String name;
		private String type;
		private int power;
		private int accuracy;
		
		public Skill(String name, String type, int power, int accuracy) {
			
			this.name = name;
			this.type = type;
			this.power = power;
			this.accuracy = accuracy;
			
		}
		
		public String getName() {
			return name;
		}
		
		public String getType() {
			return type;
		}
		
		public int getPower() {
			return power;
		}
		
		public int getAccuracy() {
			return accuracy;
		}
	}