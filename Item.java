
public class Item {
	/* Contains:
	 *  Constructor containing the stats of all non-weapon or armour items
	 */
	String name;
	String shorthand = null; //displays in inventory for items with names longer than 12 characters
	String description = "";
	
	public Item(int id) {
		switch(id) {
			default:
				name = "Missing Number";
				shorthand = "Missing No.";
				description = "Error.";
				break;
			case -1: //for weapons and armour
				return;
			case 0:
				name = "Bone-Healing Juice";
				shorthand = "BoneHealJuic";
				description = "A small vial of calcium-rich fluid. Heals 15 health.";
				break;
			case 1:
				name = "Bone First Aid";
				shorthand = "Bone-Aid";
				description = "Bone appétit! Heals 30 health.";
				break;
			case 2:
				name = "Tough Steak";
				description = "Probably should've cooked this. Heals 10 health, but also might do something else.";
				break;
			case 3:
				name = "Red Potion";
				description = "A red potion. Smells spicy.";
				break;
			case 4:
				name = "Yellow Potion";
				shorthand = "Yellow Pot";
				description = "A yellow potion. Smells like honey.";
				break;
			case 5:
				name = "Green Potion";
				shorthand = "Green Potion";
				description = "A green potion. Smells like almonds.";
				break;
			case 6:
				name = "Blue Potion";
				description = "A blue potion. Smells... puzzling.";
				break;
			case 7:
				name = "Pink Potion";
				description = "A pink potion. Smells paradoxical.";
				break;
			case 8:
				name = "Golden Omlette";
				shorthand = "Gold Omlette";
				description = "Dropped on the floor. Probably still good.";
				break;
			case 9:
				name = "Ambrosia";
				description = "The drink of the gods. +5 to all stats.";
				break;
			case 10:
				name = "Green Ambrosia";
				description = "I don't think you should drink this.";
				break;
			case 11:
				name = "Bone";
				description = "It looks like a femur.";
				break;
			case 12:
				name = "Fighting Ring";
				shorthand = "Fight Ring";
				description = "Gives you fighting spirit. +1 STR while in your inventory.";
				break;
			case 13:
				name = "Ornate Key";
				description = "A fancy-looking key. It's ticking softly.";
				break;
			case 14:
				name = "Rope";
				description = "A very long rope.";
				break;
			case 15:
				name = "Champion's Belt";
				shorthand = "Champ Belt";
				description = "A comically large belt. +2 STR and SKL while in your inventory.";
				break;
		}
		
		if(shorthand.equals(null)) {
			shorthand = name;
		}
	}
	
	public boolean Use(String name, Player user) { //true if the item was consumed, false otherwise
		switch(name) {
			case "bonehealjuice":
				if(user.curHP != user.maxHP) {
					int restore = 15;
					if(user.curHP + restore > user.maxHP) {
						restore = user.maxHP - user.curHP;
					}
					user.curHP += restore;
					System.out.println("You drink the juice, and your bones are repaired. ");
					return true;
				} else {
					return false;
				}
		}
		return false;
	}
}
