import java.util.*;

public class Room {
	/* Contains:
	 *  Text of all rooms
	 *  Room commands
	 */
	String id;
	String title;
	
	//using string hashmaps for legibility
	HashMap<String, Integer> flags = new HashMap<String, Integer>();
	
	//room constructor - input is a sole integer so we can create all the rooms with a for loop
	public Room(int n) { 
		switch(n) {
			//IMPORTANT: room numerical IDs should be in order and no numbers should be skipped. this is so we can easily make all the rooms; the room numID is otherwise irrelevant
			case 1: //starting at 1 because that's the first ID on the map (lucid.app/lucidchart/eee19849-2090-436f-9463-3d37bb6abd7f/edit?invitationId=inv_0aeb1baa-9193-4733-b685-afc1225b7281)
				id = "start";
				title = "The Bone Lair (Entrance)";
				flags.put("openedBonsey", 0);
				flags.put("firstEntry", 1);
				break;
			case 2:
				id = "garg hall";
				title = "Gargoyle Hall";
				flags.put("triggers", 0);
				flags.put("disabled", 0);
				flags.put("dead", 0);
				break;
			case 3:
				id = "fountain";
				title = "Sparkling Fountain";
				break;
			case 4:
				id = "gallery";
				title = "Art Gallery";
				flags.put("examinedPainting", 0);
				flags.put("foughtWizard", 0);
				break;
			case 5:
				id = "t-hall";
				title = "Crossroads";
				break;
			case 6:
				id = "magic mirror";
				title = "Bedroom";
				break;
			case 7:
				id = "frozen warrior";
				title = "Frozen Warrior";
				break;
			case 8:
				id = "levers 1";
				title = "Lever Room";
				break;
			case 9:
				id = "take1leave1";
				title = "Library";
				break;
			case 10:
				id = "pit room";
				title = "The Pit";
				break;
			case 11:
				id = "dark maze";
				title = "Maze of Darkness";
				break;
			case 12:
				id = "friendly guide";
				title = "Crossroads";
				break;
			case 13:
				id = "fight club";
				title = "Colosseum";
				break;
			case 14:
				id = "spike pit";
				title = "Spike Pit";
				break;
			case 15:
				id = "time wizard";
				title = "Clockwork Abode";
				break;
			case 16:
				id = "chest room";
				title = "Secret Passage";
				break;
			case 17:
				id = "sphinx";
				title = "Sphinx's Lair";
				break;
			case 18:
				id = "minotaur fight";
				title = "Hallway";
				break;
			case 19:
				id = "ambrosia";
				title = "Alchemy Lab";
				break;
			case 20:
				id = "bees";
				title = "Beehive";
				break;
			case 21:
				id = "levers 2";
				title = "Lever Room";
				break;
			case 22:
				id = "bonsey shrine";
				title = "Bone Shrine";
				break;
			case 23:
				id = "foo";
				title = "Indeterminate";
				break;
			case 24:
				id = "final boss";
				title = "Bone Throne";
				break;
		}
	}
	
	//Enter a room; parameters are the room ID and the direction from which the room was entered
	public static void enterRoom(String sID, String dir) {
		Room room = Main.rooms.get(sID);
		
		while(true) {
			if(room.title != null) { //if there's no title, don't output one. for room objects
				System.out.println("\n\n <=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=>");
				System.out.printf(" |  %-30s |%n", room.title);
				System.out.println(" <=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=>");
			}
			
			System.out.println(room.getDesc(dir));
			System.out.print("\n > ");
			String in = Main.s.nextLine();
			String[] cmd = Main.parseCommand(in);
			
			//First, check if any default commands were inputted. If so, don't execute any more code, because we already executed the command
			if(roomCmds(cmd[0], cmd[1], room)) {
				continue;
			}
			
			//Then, check for whatever commands are specific to that room, and execute them.
			//We want to give each room unique options with unique effects - and the easiest way to do that is to make them manually. Sigh...
			//This is kind of annoying, but we have to put all of this somewhere
			switch(sID) {
				//Room 1: Starting Room
				case "start":
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
						case "n":
							enterRoom("garg hall", "s");
							room.flags.put("firstEntry", 0);
							break;
						case "w":
							room.flags.put("firstEntry", 0);
							enterRoom("gallery", "e");
							break;
						case "e":
							room.flags.put("firstEntry", 0);
							break;
						case "x":
							if(cmd[1].equals("chest")) {
								if(room.flags.get("openedBonsey") == 1) {
									System.out.println("An empty chest. There's nothing here.");
								} else {
									System.out.println("The chest is large, made of silver and steel. The key is in the lock; turning it is very tempting.");
								}
								continue;
							} else {
								System.out.println("\n(Invalid input; try again)");
								continue;
							}
						case "o":
							if(cmd[1].equals("chest")) {
								if(room.flags.get("openedBonsey") == 1) {
									System.out.println("\nThe chest is already open.");
									continue;
								} else {
									room.flags.put("firstEntry", 0);
									room.flags.put("openedBonsey", 1);
									System.out.println("You turn the key. A click can be heard as the chest unlocks. \n\nYou found... (Enter anything to continue...) ");
									in = Main.s.next();
									
									System.out.println("THE SKELETON APPEARS \n\n(Enter anything to continue...) ");
									in = Main.s.next();
									
									Battler.battle(Main.p, 0);
									continue;
								}
							} else {
								System.out.println("\n(Invalid input; try again)");
								continue;
							}
					}
					break;
				
				//Room 2: Gargoyle Hall
				case "garg hall":
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
						case "s":
							break;
					}
					break;
				//Fountain Room
				case "fountain":
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
						case "s":
							enterRoom("pit room", "n");
							break;
						case "n":
							break;
						case "e":
							enterRoom("take1leave1", "w");
							break;
						case "w":
							enterRoom("start","e");
							break;
						case "x":
							break;
					}
				// Room 4 Painting Hall
				case "gallery":
					switch(cmd[0]) {
					default:
						System.out.println("\n(Invalid input; try again)");
						continue;
					case "s":
						if(room.flags.get("examinedPainting") == 1) {
							enterRoom("levers 1", "n");
							break;
						}
						else {
							System.out.println("\n(Invalid input; try again)");
						}
						continue;
					case "w":
						enterRoom("dark maze", "e");
						break;
					case "e":
						enterRoom("start", "w");
						break;
					case "x":
						if(cmd[1].equals("wizard")) {
							if(room.flags.get("foughtWizard") == 1) {
								System.out.println("\nThe painting is empty.");
								continue;
							} else {
								room.flags.put("foughtWizard", 1);
								System.out.println("You approach the painting but you notice something odd. \nThe wizard seems to be moving and he definelty doesn't look friendly as he launches his first attack your way. (Enter anything to continue...) ");
								in = Main.s.next();
								Battler.battle(Main.p, 1);
								continue;
							}
						}
						if(cmd[1].equals("skeleton")) {
							if(room.flags.get("examinedPainting") == 1) {
								System.out.println("\nThe painting has been moved on to the floor revealing a hidden passageway.");
								continue;
							} else {
								room.flags.put("examinedPainting", 1);
								System.out.println("\nYou approach the painting of the skeleton and when examined has a hinge along one of the edges. \nAllowing you to pull it open like a door revealing a secret passage!");
								enterRoom("levers 1", "n");
							}
						}
						
						continue;

					}
					break;
					
			}
			break;
		}
	}
	
	//get the actual text of the room, depending on the room flags and direction of entry
	//this is a separate function because changing the text every time something happens is a massive pain
	public String getDesc(String dir) { 
		String d = "";
		
		switch(id) {
			//Room 1: Entrance
			case "start":
				if(flags.get("firstEntry") == 1) {
					d = "This is it: the Bone Lair, the dungeon that no adventurer has ever come out of alive. \n\n"
						+ "You've decided to come here, seeking the glory of being the first to survive. Not only that, but they say that the dungeon is full of hidden treasures.\n"
						+ "Primed for adventure, you enter the dungeon, on the lookout for treasure.\n\n"
						+ "In front of you lies a treasure chest, with a skeleton-shaped key in the lock. Well, that was easy! \n\n"
						+ "There are also three hallways leading out of this room: one to the north, one to the east, and one to the west. \n"
						+ "However, for whatever reason, you have an overwhelming feeling that you should shout \"H\".";
				} else {
					if(flags.get("openedBonsey") == 1) {
						d = "You're in the entrance of the dungeon. Or, where it would have been; the entrance closed while you fought the skeleton.\n\n"
								+ "All that's here is an empty treasure chest, dusted with calcium. Otherwise, the room is empty.\n\n"
								+ "There are three hallways leading out of this room: one to the north, one to the east, and one to the south.";
					} else {
						d = "You arrive at the entrance of the dungeon. Or, where it would have been; the entrance closed behind you.\n\n"
								+ "The treasure chest is still here, unopened, and the key is still in the lock. It's practically begging to be unlocked.\n"
								+ "The chest starts rattling, and emmitting a muffled noise. On second thought, it seems to be literally begging to be unlocked.\n\n"
								+ "There are three hallways leading out of this room: one to the north, one to the east, and one to the south.";
					}
				}
				break;
				
			//Room 2: Gargoyle Hall
			case "garg hall":
				if(flags.get("disabled") == 0 && flags.get("dead") == 0) {
					switch(flags.get("triggers")) {
						case 0:
							d = "You enter a long, narrow corridor, with gargoyles lining the walls. \n"
									+ "Nothing else is here, but you get the uncanny feeling that you're being watched. \n"
									+ "There are 2 exits; one north, one south.";
							break;
						case 1:
							if(dir == "n") {
								d = "You enter the hall full of gargoyles. Now, they seem to be laughing at you.\n"
									+ "You can return north, or cross the hall to the southern exit.\n"
									+ "But to avoid what happened last time, maybe it would be a good idea to turn back.";
							} else {
								d = "You enter the hall full of gargoyles. Now, they seem to be laughing at you.\n"
									+ "You can return south, or cross the hall to the northern exit.\n"
									+ "But to avoid what happened last time, maybe it would be a good idea to turn back.";
							}
							break;
						case 2:
							if(dir == "n") {
								d = "You enter the hall. The gargoyles are still here. Now they're definitely laughing.\n"
									+ "You should probably return north. If you go south, you'll definitely get burned.\n"
									+ "It would be nice if there was a way to disable these...";
							} else {
								d = "You enter the hall. The gargoyles are still here. Now they're definitely laughing.\n"
									+ "You should probably return south. If you go north, you'll definitely get burned.\n"
									+ "It would be nice if there was a way to disable these...";
							}
							break;
						default: 
							if(dir == "n") {
								d = "Here again. There are still gargoyles. You want to smash their stupid faces.\n"
									+ "You can return north, or go south and die in a fire.";
							} else {
								d = "Here again. There are still gargoyles. You want to smash their stupid faces.\n"
									+ "You can return south, or go north and die in a fire.";
							}
							break;
					}
				} else if (flags.get("dead") == 1) {
					d = "You're in a hall which was once full of gargoyles."
							+ "However, some uncultured savage seems to have smashed all of the statues in rage. Who would do such a thing?"
							+ "All that's left is the exits; one north, one south.";
					break;
				} else {
					d = "TODO";
				}
				break;
				
			//Gallery
			case "gallery":
				if(flags.get("examinedPainting") == 0 && flags.get("foughtWizard") == 0) {
					d = "You enter a gallery, full of various paintings.\n"
					+ "Two of them catch your attention; a painting of a crazy-looking wizard to your north, and a painting of a familiar-looking skeleton to the south.\n"
					+ "Otherwise, there’s nothing of note, other than the exits to the west and the east.";
				}
				else if(flags.get("examinedPainting") == 1 && flags.get("foughtWizard") == 0) {
					d = "You enter a gallery, full of various paintings.\n"
					+  "One of them catches your attention; a painting of a crazy looking wizard to your north.\n"
					+ "Otherwise, there's nothing of note, other than the secret entrance you found to the south and the normal exits to the west and the east.";
				}
				else if(flags.get("examinedPainting") == 0 && flags.get("foughtWizard") == 1) 
					d = "You enter a gallery, full of various paintings.\n"
					+ "One of them catches your attention; a painting of a familiar-looking skeleton to the south.\n"
					+ "Otherwise, there's  nothing of note, other than the exits to the west and the east.";
				else {
					d = "You enter a gallery, full of various paintings.\n"
					+ "There's nothing of note, other than the secret entrance you found to the south and the normal exits to the west and the east.\n";		
						
				}
				break;
		}
		
		return d;
	}

	//For commands like help and inventory which are completely static (i.e. are the same in every room). 
	//Returns true if a command was executed and the room should re-print, false if the room should keep checking for more commands
	public static boolean roomCmds(String cmd, String parameter, Room room) {
		String in;
		int x;
		switch(cmd) {
			//PRINT LIST OF COMMANDS (HELP)
			case "h":
				//output is a bit hard to read. TODO: make the output from the help command nicer
				System.out.println(" <=-=-=-=-=-=-=-=-=-=-=-=>");
				System.out.printf(" |  %-20s |%n", "Command List");
				System.out.println(" <=-=-=-=-=-=-=-=-=-=-=-=>\n");

				System.out.println(" - Room Commands - Can be used while in a room.");
				System.out.println("(direction)/(first letter of direction): \n > Move in the specified direction; e.g. type \"north\" or \"n\" to move north.\n");
				System.out.println("take/grab/pickup/t <object>: \n > Attempt to put an object in a room into your inventory.\n");
				System.out.println("open/o <object>: \n > Open a container in a room. May be locked.\n");
				System.out.println("examine/x <object>: \n > Examine an object in a room. You may have further actions.\n");
				System.out.println("smash/hit/attack/destroy/a <object>: \n > Attempt to destroy an object.\n");
				
				System.out.println("help/h: \n > Shows the Command List. Synonyms are seperated by slashes; use only one of them. Parameters are in <>.\n");
				System.out.println("inventory/inv/i: \n > Opens inventory (shows your items & stats)\n");
				System.out.println("use/eat/drink/equip <slot number>: \n > Uses or equips the item in the given inventory slot.\n");
				System.out.println("examine/x <slot number>: \n > Examine an object in the given inventory slot.\n");
				System.out.println("quit/q: \n > Quit the game.\n");
				
				System.out.println("\n - Battle Commands - Can be used in battle. ");
				System.out.println("attack/a: \n > Attack foe.\n");
				System.out.println("inventory/inv/i: \n > Opens inventory (shows your items & stats)\n");
				System.out.println("use/eat/drink/equip <item>: \n > Uses or equips the given item.\n");
				System.out.print("back/exit/b: \n > Exit your inventory.\n\n(Enter anything to continue...) ");
				
				in = Main.s.next();
				System.out.println("\n");
				return true;
				
			//INVENTORY
			case "i":
				//print out stats
				System.out.println(Main.p.name + " the " + Main.p.pClass);
				System.out.println("HP: " + Main.p.curHP + "/" + Main.p.maxHP);
				System.out.println("STATS:");
				System.out.println(" > " + Main.p.strength + " STR");
				System.out.println(" > " + Main.p.stamina + " STM");
				System.out.println(" > " + Main.p.skill + " SKL");
				System.out.println(" > " + Main.p.speed + " SPE");
				System.out.println(" > " + Main.p.luck + " LCK\n");
				
				//print out inventory
				System.out.println(" <=-=-=-=-=-=-=-=-=-=-=-=>");
				
				System.out.printf(" | %-20s |%n", "Weapon: " + Main.p.wep.shorthand);
				System.out.printf(" | %-20s |%n", "Armour: " + Main.p.armour.shorthand);
				
				if(Main.p.inventory.size() > 0) {
					System.out.printf(" | %-20s |%n", "Items:  ");
					for(int i = 1; i <= Main.p.inventory.size(); i++) {
						System.out.printf(" | %-20s |%n", " [" + i + "] " + Main.p.inventory.get(i-1).shorthand);
					}
				} else {
					System.out.printf(" | %-20s |%n", "Items: (none)");
				}
				
				System.out.println(" <=-=-=-=-=-=-=-=-=-=-=-=>");
				return true;
			
			//EXAMINE ITEM IN INVENTORY
			case "x":
				try { //if you examined a number, then it continues with the code...
					x = Integer.parseInt(parameter) - 1;
				} catch(NumberFormatException e) { //otherwise, it realizes that you're examining something in the room and returns false
					return false;
				}
				
				try {  //print out the description, if the item exists
					Item i = Main.p.inventory.get(x);
					
					if(i.getClass() == Weapon.class) { //examine weapon
						Weapon w = i.toWep();
						
						System.out.println("\n\n <=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=>");
						System.out.printf(" |  %-30s |%n", w.name);
						System.out.println(" <=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=>");
						System.out.printf("%d damage | %d hit | %d crit%n", w.damage, w.hit, w.crit);
							
						System.out.println(w.description);
						
					} else if(i.getClass() == Armour.class) { //examine armour
						Armour a = i.toArmour();
							
						System.out.println("\n\n <=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=>");
						System.out.printf(" |  %-30s |%n", a.name);
						System.out.println(" <=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=>");
						System.out.printf("%d defense | %d encumbrance%n", a.defense, a.encumbrance);
						
						System.out.println(a.description);
					} else { //examine other
						System.out.println("\n\n <=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=>");
						System.out.printf(" |  %-30s |%n", i.name);
						System.out.println(" <=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=>");
								
						System.out.print(i.description);
					}
					
				} catch(IndexOutOfBoundsException e) { //if the number you inputted doesn't exist, then it tells you and does nothing
					System.out.println("\n(Invalid input; try again)");
				}
				return true;
			
			//USE AN ITEM IN INVENTORY
			case "use":
				try { //if you entered a number, then it continues with the code...
					x = Integer.parseInt(parameter) - 1;
				} catch(NumberFormatException e) { //otherwise, it stops the code
					System.out.println("\n(Invalid input; try again)");
					return true;
				}
				
				try { //use the item, if the item exists
					Item i = Main.p.inventory.get(x);
					return i.use(Main.p, x);
				} catch(IndexOutOfBoundsException e) { //if the number you inputted doesn't exist, then it tells you and does nothing
					System.out.println("\n(Invalid input; try again)");
					return true;
				}
			
			//EAT AN ITEM IN INVENTORY
			case "eat": case "consume":
				try { //if you entered a number, then it continues with the code...
					x = Integer.parseInt(parameter) - 1;
				} catch(NumberFormatException e) { //otherwise, it stops the code
					System.out.println("\n(Invalid input; try again)");
					return true;
				}
				
				try { //eat the item, if the item exists
					Item i = Main.p.inventory.get(x);
					return i.eat(Main.p, x);
				} catch(IndexOutOfBoundsException e) { //if the number you inputted doesn't exist, then it tells you and does nothing
					System.out.println("\n(Invalid input; try again)");
					return true;
				}
			
			//DRINK AN ITEM IN INVENTORY
			case "drink":
				try { //if you entered a number, then it continues with the code...
					x = Integer.parseInt(parameter) - 1;
				} catch(NumberFormatException e) { //otherwise, it stops the code
					System.out.println("\n(Invalid input; try again)");
					return true;
				}
				
				try { //drink the item, if the item exists
					Item i = Main.p.inventory.get(x);
					return i.drink(Main.p, x);
				} catch(IndexOutOfBoundsException e) { //if the number you inputted doesn't exist, then it tells you and does nothing
					System.out.println("\n(Invalid input; try again)");
					return true;
				}
			
			//EQUIP AN ITEM IN INVENTORY
			case "equip":
				try { //if you entered a number, then it continues with the code...
					x = Integer.parseInt(parameter) - 1;
				} catch(NumberFormatException e) { //otherwise, it stops the code
					System.out.println("\n(Invalid input; try again)");
					return true;
				}
				
				try { //equip the item, if the item exists
					Item i = Main.p.inventory.get(x);
					return i.equip(Main.p, x);
				} catch(IndexOutOfBoundsException e) { //if the number you inputted doesn't exist, then it tells you and does nothing
					System.out.println("\n(Invalid input; try again)");
					return true;
				}
				
			//EXIT GAME
			case "q":
				System.out.print("Are you sure you want to quit? [Y/N] \n > ");
				
				in = Main.s.next().trim().toLowerCase();
				
				if(in.equals("y") || in.equals("yes")) System.exit(0);
				return true;
		}
		return false;
	}
}