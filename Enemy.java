
public class Enemy {
	/* Contains:
	 *  Constructor containing the stats of all enemies
	 */
	String name;
	String description;
	String defeatText;
	int damage;
	int HP;
	int hit;
	int speed;
	int luck;
	int defense;
	int crit;
	int maxHP;
	
	Item[] drops = null;

	//Enemy constructor. I like to use an ID and a switch because it's easier to use and read compared to a constructor with a bunch of variables that you never remember
	public Enemy(int id) {
		switch(id) {
			default:
			case 0:
				name = "BONSEY"; //All caps for stylization
				maxHP = 30;
				HP = maxHP;
				damage = 6;
				hit = 82;
				speed = 5;
				luck = 2;
				defense = 0;
				crit = 1;
				drops = new Item[] { new Item(0) };
				defeatText = "The skeleton rattles, raises its hands, and scuttles out the western exit like a crab.\n\n"
						+ "You defeated BONSEY!";
				description = "A skeleton. Very spooky.";
				break;
				
			case 1:
				name = "WIZARD"; //All caps for stylization
				maxHP = 26;
				HP = maxHP;
				damage = 10;
				hit = 68;
				speed = 7;
				luck = 5;
				defense = 0;
				crit = 1;
				drops = new Item[] { new Item(16) };
				defeatText = "The wizard reels from your attack. \"You think you've defeated me?,\" he says. \"Take this!,\" he cries, throwing a flask of potion at you.\n"
						+ "The flask hits you in the face, but doesn't explode. It drops to the ground, and makes a cartoon splat noise as it lands. The contents of the flask grow legs and run away.\n"
						+ "The wizard sighs, and slinks away moodily.\n\n"
						+ "You defeated the WIZARD!";
				description = "";
				break;
				
			case 2: 
				name = "MEGA GUARD"; //All caps for stylization
				maxHP = 40;
				HP = maxHP;
				damage = 10;
				hit = 95;
				speed = 6;
				luck = 5;
				defense = 4;
				crit = 2;
				defeatText = "You defeated the MEGA GUARD!";
				break;
				
			case 3:
				name = "GARGOYLE"; //All caps for stylization
				maxHP = 25;
				HP = maxHP;
				damage = 10;
				hit = 100;
				speed = 10;
				luck = 3;
				defense = 5;
				crit = 5;
				defeatText = "After smashing this one, you smash all the other gargoyles easily.\n\n"
						+ "You defeated the GARGOYLE!";
				break;
				
			case 4: 
				name = "WARRIOR"; //All caps for stylization
				maxHP = 25;
				HP = maxHP;
				damage = 12;
				hit = 70;
				speed = 9;
				luck = 5;
				defense = 1;
				crit = 5;
				defeatText = "You defeated the WARRIOR!";
				drops = new Item[] {
						new Item(12),
						new Weapon(4)
				};
				break;

			case 5: 
				name = "LIL' SLIME"; //All caps for stylization
				maxHP = 25;
				HP = maxHP;
				damage = 5;
				hit = 110;
				speed = 4;
				luck = 2;
				defense = 0;
				crit = 1;
				defeatText = "You defeated the LIL' SLIME!";
				break;

			case 6: 
				name = "LIL' SLIME"; //All caps for stylization
				maxHP = 25;
				HP = maxHP;
				damage = 5;
				hit = 110;
				speed = 4;
				luck = 2;
				defense = 0;
				crit = 1;
				defeatText = "You defeated the LIL' SLIME!";
				break;
		}
	}
}