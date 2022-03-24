
public class Armour extends Item {  //Extends Item, so we can put it in ArrayLists of Items, i.e. the player's inventory
	/* Contains:
	 *  Constructor containing the stats of all armours
	 */
	int defense = 0;
	int encumbrance = 0; //subtracted from speed when it's checked
	int health = 0; //added to max HP
	
	//Armour constructor. I like to use an ID and a switch because it's easier to use and read compared to a constructor with a bunch of variables that you never remember
	public Armour(int id) {
		super(-1); 
		
		switch(id) {
			case 0:
				name = "Broken Chainmail";
				shorthand = "Broken CMail";
				description = "It's full of holes. More than usual.";
				defense = 2;
				break;
			case 1:
				name = "Light Clothes";
				shorthand = "LightClothes";
				description = "Casual wear. You probably should've worn armour...";
				defense = 1;
				encumbrance = -1;
				break;
			case 2:
				name = "Rusty Platemail";
				name = "Rusty PMail";
				description = "Heavy armour. Probably shouldn't have come here in the rain.";
				defense = 3;
				encumbrance = 1;
				break;
			case 3:
				name = "Calcium Cloak";
				shorthand = "CalciumCloak";
				description = "A royal cloak made of calcified bones.";
				defense = 6;
				encumbrance = -1;
				break;
			case 4:
				name = "Hermes' Boots";
				shorthand = "Hermes Boots";
				description = "Increases movement speed. Now to find an aglet...";
				encumbrance = -4;
				break;
			case 5:
				name = "Knightly Chainmail";
				shorthand = "Knight Mail";
				description = "It has the intended number of holes.";
				defense = 5;
				break;
			case 6:
				name = "Ninja Clothes";
				shorthand = "NinjaClothes";
				description = "Not very protective, but it makes you feel cool.";
				defense = 3;
				encumbrance = -2;
				break;
			case 7:
				name = "Paladin's Platemail";
				name = "Pal Mail";
				description = "This version is waterproof.";
				defense = 8;
				encumbrance = 3;
				break;
			case -1:
				name = "Naked Bones";
				defense = -3;
				description = "You aren't wearing clothes. Or skin.";
				break;
			case -2:
				name = "noclip";
				description = "VOTEKICK VOTEKICK HACKER VOTEKICK";
				defense = 255;
				encumbrance = -255;
				break;
		}
		
		if(shorthand.equals(null)) {
			shorthand = name;
		}
	}
}
