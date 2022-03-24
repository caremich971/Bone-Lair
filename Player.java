import java.util.ArrayList;

public class Player {
	/* Contains:
	 *  Player's stats and inventory
	 *  Some functions related to player's stats and inventory
	 */
	//base stats
	public int strength; //increases damage
	public int stamina; //increases health
	public int skill; //increases hit and crit
	public int speed; //increases dodge and lets you go first
	public int luck; //increases crit and decreases enemy crit
	
	//other stats
	public int maxHP; //stamina *2 + any other buffs
	public int curHP; //varies constantly
	public Weapon wep; //equipped weapon
	public Armour armour; //equipped armour
	public String pClass; //selected class
	public int fountainDrinks = 0; //times the player has drunk from the fountain of youth. can't be a room variable because you can bottle the water
	
	//inventory arraylist
	ArrayList<Item> inventory = new ArrayList<Item>();
	
	public Player() {
		//randomize base stats. 2 d4s + 5 gives a range of 7-13 but is weighted towards 10
		strength = 5 + Main.rollDice(4, 2);
		stamina = 5 + Main.rollDice(4, 2);
		skill = 5 + Main.rollDice(4, 2);
		speed = 5 + Main.rollDice(4, 2);
		luck = 5 + Main.rollDice(4, 2);
		
		ChooseClass();
	}
	
	public void ChooseClass() { //classes change stats and determine starting gear. may also have special abilities if we have time
		System.out.print("Choose a class:\n\n [1] Knight\n   >> Well-rounded stats.\n\n [2] Rogue\n   >> Fast and skillful, but a little weak.\n\n [3] Barbarian\n   >> Very strong, but bad hit.\n\n [4] Paladin\n   >> Slow, but tanky and reliable.\n\n > ");
		while(true) {
			String in = Main.s.nextLine().toLowerCase().trim();
			switch(in) {
				default:
					continue;
				case "1":
				case "knight": //baseline class
					wep = new Weapon(0);
					armour = new Armour(0);
					pClass = "Knight";
					break;
				case "2":
				case "rogue": //luck-dependent class that relies on dodges and crits for survivability and damage
					skill += 3;
					speed += 3;
					luck += 2;
					strength -= 2;
					stamina -= 2;
					wep = new Weapon(1);
					armour = new Armour(1);
					pClass = "Rogue";
					break;
				case "3":
				case "barbarian": //opposite of rogue, but somehow more luck-dependent due to terrible skill and luck
					strength += 4;
					skill -= 4;
					luck -= 4;
					wep = new Weapon(2);
					armour = new Armour(1);
					pClass = "Barbarian";
					break;
				case "4":
				case "paladin": //"i got terrible rolls on barb and rogue" class
					skill += 2;
					stamina += 3;
					speed -= 4;
					wep = new Weapon(3);
					armour = new Armour(2);
					pClass = "Paladin";
					break;
				case "bonsey": //secret class
					strength += Main.rollDice(6, 2) - Main.rollDice(6, 2);
					stamina += Main.rollDice(6, 2) - Main.rollDice(6, 2);
					speed += Main.rollDice(6, 2) - Main.rollDice(6, 2);
					skill += Main.rollDice(6, 2) - Main.rollDice(6, 2);
					luck += Main.rollDice(6, 2) - Main.rollDice(6, 2);
					wep = new Weapon(-1);
					armour = new Armour(-1);
					pClass = "Almighty Bonsey";
					break;
				case "hackerman": //dev mode
					strength = 255;
					stamina = 255;
					speed = 255;
					skill = 255;
					luck = 255;
					wep = new Weapon(-2);
					armour = new Armour(-2);
					pClass = "Developer";
					break;
			}
			break;
		}
		//once the base stats are finalized, calculate HP
		maxHP = stamina*2;
		curHP = maxHP;
	}
	
	public void checkHP() {
		if(curHP <= 0) {
			System.out.print("\n\nYOU DIED!");
			System.exit(0);
		}
	}
}
