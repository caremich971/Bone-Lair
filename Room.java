import java.util.*;

public class Room {
	/* Contains:
	 *  Text of all rooms
	 *  Room commands
	 */
	String id;
	String title;
	
	//hashmaps for flags. used for most room functionality
	HashMap<String, Boolean> flags = new HashMap<String, Boolean>();
	
	//int flags, for certain cases like a counter
	HashMap<String, Integer> intFlags = new HashMap<String, Integer>();
	
	
	//grabbable items are normally handled with flags, but we need this for the library
	ArrayList<Item> items = new ArrayList<Item>();
	
	//direction constants - for entry direction
	static final char NORTH = 'n';
	static final char SOUTH = 's';
	static final char EAST = 'e';
	static final char WEST = 'w';
	static final char UP = 'u';
	static final char DOWN = 'd';
	
	//room constructor - input is a sole integer so we can create all the rooms with a for loop
	public Room(int n) { 
		switch(n) {
			//IMPORTANT: room numerical IDs should be in order and no numbers should be skipped. this is so we can easily make all the rooms; the room numID is otherwise irrelevant
			case 1: //starting at 1 because that's the first ID on the map (lucid.app/lucidchart/eee19849-2090-436f-9463-3d37bb6abd7f/edit?invitationId=inv_0aeb1baa-9193-4733-b685-afc1225b7281)
				id = "start";
				title = "The Bone Lair (Entrance)";
				flags.put("openedBonsey", false);
				flags.put("firstEntry", true);
				break;
			case 2:
				id = "garg hall";
				title = "Gargoyle Hall";
				intFlags.put("triggers", 0);
				flags.put("disabled", false);
				flags.put("dead", false);
				break;
			case 3:
				id = "fountain";
				title = "Sparkling Fountain";
				break;
			case 4:
				id = "gallery";
				title = "Art Gallery";
				flags.put("examinedPainting", false);
				flags.put("foughtWizard", false);
				break;
			case 5:
				id = "t-hall";
				title = "Crossroads";
				intFlags.put("leftDoor", 1);
				intFlags.put("doorStrength", 20);
				break;
			case 6:
				id = "magic mirror";
				title = "Bedroom";
				flags.put("examinedMirror", false);
				flags.put("examinedBed", false);
				flags.put("examinedVase", false);
				break;
			case 7:
				id = "frozen warrior";
				title = "Frozen Warrior";
				flags.put("killedWarrior", false);
				flags.put("openedDoor", false);
				flags.put("bones", true);
				break;
			case 8:
				id = "levers 1";
				title = "Lever Room";
				flags.put("red", false);
				flags.put("blue", false);
				flags.put("green", false);
				flags.put("yellow", false);
				break;
			case 9:
				id = "take1leave1";
				title = "Library";
				intFlags.put("credit", 0);
				items.add(new Item(13));
				items.add(new Item(14));
				items.add(new Armour(4));
				items.add(new Item(1));
				items.add(new Item(5));
				
				flags.put("guarded", true);
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
				title = "Fighting Ring";
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
				title = "Secret Tunnel";
				break;
			case 17:
				id = "sphinx";
				title = "Sphinx's Lair";
				break;
			case 18:
				id = "minotaur";
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
				id = "golden idol";
				title = "The Golden Idol";
				break;
			case 24:
				id = "hand hall";
				title = "Corridor";
				break;
			case 25:
				id = "rocks fall";
				title = "Mineshaft";
				break;
			case 26:
				id = "final boss";
				title = "Bone Throne";
				break;
			default:
				id = "dummy";
				title = "";
				break;
		}
	}
	
	//Enter a room; parameters are the room ID and the direction you came from
	//dir should be a char but w/e
	public static void enterRoom(String sID, char dir) {
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
							room.flags.put("firstEntry", false);
							enterRoom("garg hall", SOUTH);
							break;
						case "w":
							room.flags.put("firstEntry", false);
							enterRoom("gallery", EAST);
							break;
						case "e":
							room.flags.put("firstEntry", false);
							enterRoom("fountain", WEST);
							break;
						case "x":
							if(cmd[1].equals("chest")) {
								if(room.flags.get("openedBonsey")) {
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
								if(room.flags.get("openedBonsey")) {
									System.out.println("\nThe chest is already open.");
									continue;
								} else {
									room.flags.put("firstEntry", false);
									room.flags.put("openedBonsey", true);
									System.out.println("You turn the key. A click can be heard as the chest unlocks. \n\nYou found...");
									Main.enterAnything();
									
									System.out.println("THE SKELETON APPEARS \n ");
									Main.enterAnything();
									
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
						case "n":
							//burn players who cross the hall, but not those who turn around
							if(dir == SOUTH && !room.flags.get("dead") && !room.flags.get("disabled")) {
								boolean dodgeFlame = Main.p.skillCheck(19 - room.intFlags.get("triggers"), "speed") >= 0; //do a speed check...
								
								int flameDamage;
								if(dodgeFlame) { //to reduce flame damage
									flameDamage = 4 - Main.p.armour.defense;
								} else {
									flameDamage = 10 - Main.p.armour.defense;
								}
								if(flameDamage < 0) {
									flameDamage = 0;
								}
								
								//then tell the player they got burned, with changing text based on what happened
								if(room.intFlags.get("triggers") == 0) {
									if(dodgeFlame) {
										System.out.printf("As you walk forward, the gargoyles' eyes and mouths begin to glow orange. You instinctively rush forward to the end of the hall.\n"
												+ "It's a good thing, too - because these gargoyles breathe fire! They singed you a little as you ran past - you take %d damage.%n%n", flameDamage);
									} else {
										System.out.printf("As you walk forward, the gargoyles' eyes and mouths begin to glow orange.\n"
												+ "Suddenly, their mouths unhinge and breathe fire! You get absolutely smoked on your way out, and take %d damage.%n%n", flameDamage);
									}
								} else {
									if(dodgeFlame) {
										System.out.printf("You walk forward, the gargoyles' eyes and mouths begin to glow orange again. This time, you're prepared, and rush to the exit.\n"
												+ "You almost escape, but get singed a little - you take %d damage.%n%n", flameDamage);
									} else {
										System.out.printf("As you walk forward, the gargoyles' eyes and mouths begin to glow orange again. This time, you're prepared, and rush to the exit.\n"
												+ "Sadly, you can't outrun flames. You get absolutely smoked on your way out, and take %d damage.%n%n", flameDamage);
									}
								}
								//Increase the number of triggers
								room.intFlags.put("triggers", room.intFlags.get("triggers") + 1);
								
								//Actually damage the player
								Main.p.curHP -= flameDamage;
								Main.p.checkHP();
								
								Main.enterAnything();
							}
							
							enterRoom("t-hall", SOUTH);
							break;
						case "s":
							//burn players who cross the hall, but not those who turn around
							if(dir == NORTH && !room.flags.get("dead") && !room.flags.get("disabled")) {
								boolean dodgeFlame = Main.p.skillCheck(19 - room.intFlags.get("triggers"), "speed") >= 0; //do a speed check...
								
								int flameDamage;
								if(dodgeFlame) { //to reduce flame damage
									flameDamage = 3 - Main.p.armour.defense;
								} else {
									flameDamage = 10 - Main.p.armour.defense;
								}
								if(flameDamage < 0) {
									flameDamage = 0;
								}
								
								//then tell the player they got burned, with changing text based on what happened
								if(room.intFlags.get("triggers") == 0) {
									if(dodgeFlame) {
										System.out.printf("As you walk forward, the gargoyles' eyes and mouths begin to glow orange. You instinctively rush forward to the end of the hall.\n"
												+ "It's a good thing, too - because these gargoyles breathe fire! They singed you a little as you ran past - you take %d damage.%n%n", flameDamage);
									} else {
										System.out.printf("As you walk forward, the gargoyles' eyes and mouths begin to glow orange.\n"
												+ "Suddenly, their mouths unhinge and breathe fire! You get absolutely smoked on your way out, and take %d damage.%n%n", flameDamage);
									}
								} else {
									if(dodgeFlame) {
										System.out.printf("You walk forward, the gargoyles' eyes and mouths begin to glow orange again. This time, you're prepared, and rush to the exit.\n"
												+ "You almost escape, but get singed a little - you take %d damage.%n%n", flameDamage);
									} else {
										System.out.printf("As you walk forward, the gargoyles' eyes and mouths begin to glow orange again. This time, you're prepared, and rush to the exit.\n"
												+ "Sadly, you can't outrun flames. You get absolutely smoked on your way out, and take %d damage.%n%n", flameDamage);
									}
								}
								//Increase the number of triggers
								room.intFlags.put("triggers", room.intFlags.get("triggers") + 1);
								
								//Actually damage the player
								Main.p.curHP -= flameDamage;
								Main.p.checkHP();
								
								Main.enterAnything();
							}
							
							enterRoom("start", NORTH);
							break;
						case "a":
							//Player decides to attack the gargoyles
							System.out.println("You walk up to the gargoyles and smash them, one by one. Suddenly, one of them comes to life.");
							Main.enterAnything();
							
							Battler.battle(Main.p, 3);
							room.flags.put("dead", true);
							continue;
					}
					break;
				//Room 3 Fountain Room
				case "fountain":
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
						case "s":
							enterRoom("pit room", NORTH);
							break;
						case "e":
							enterRoom("take1leave1", WEST);
							break;
						case "w":
							enterRoom("start", EAST);
							break;
						case "x":
							if(cmd[1].equals("fountain")) {
								System.out.println("A large fountain. It's surprisingly clean. The water flowing through the fountain looks oddly alluring...");
							}
							else {
								System.out.println("\n(Invalid input; try again)");
							}
							continue;
						case "drink":
							if(cmd[1].equals("fountain") || cmd[1].equals("water")) {
								if(Main.p.fountainDrinks == 2) {
									System.out.println("After that last drink there's no way you're going near that fountain again. You don't know what it'll do to you.");
								}

								else if (Main.p.fountainDrinks == 1) {
									System.out.print("After the last drink you feel 10 years younger! \nYou decide to take another sip of the miraculous liquid and you can feel its effects immediately. You soon notice in a panic that the effects are too potent!\nYou feel yourself go from the body of a 25 year old to the body of a 12 year old in seconds and the experience is definitely unpleasant.\nYou lost 2 STR and 2 STM.\n");
									Main.p.stamina = Main.p.stamina-2;
									Main.p.strength = Main.p.strength-2;
									Main.p.curHP = Main.p.maxHP;
									Main.p.fountainDrinks++;
									Main.enterAnything();
								}
								else {
									System.out.print("You walk over to the fountain and slowly take a long sip.\nThe effects start quickly with all of your aches and pains fading away.\nAfter the process you feel good as new!\n");
									Main.p.curHP = Main.p.maxHP;
									Main.p.fountainDrinks++;
									Main.enterAnything();
								}
							} else {
								System.out.println("\n(Invalid input; try again)");
							}
							continue;
					}
				// Room 4 Painting Hall
				case "gallery":
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
						case "s":
							if(room.flags.get("examinedPainting")) {
								enterRoom("levers 1", NORTH);
							}
							else {
								System.out.println("\n(Invalid input; try again)");
								continue;
							}
						case "w":
							enterRoom("dark maze", EAST);
							break;
						case "e":
							enterRoom("start", WEST);
							break;
						case "x":
							if(cmd[1].equals("wizard")) {
								if(room.flags.get("foughtWizard")) {
									System.out.println("\nThe painting is empty.");
									continue;
								} else {
									room.flags.put("foughtWizard", true);
									System.out.println("You approach the painting, but you notice something odd. The wizard seems to be moving, and he definitely doesn't look friendly as he launches his first attack your way.");
									Main.enterAnything();
                
									Battler.battle(Main.p, 1);
									continue;
								}
							
							}
							if(cmd[1].equals("skeleton")) {
								if(room.flags.get("examinedPainting")) {
									System.out.println("\nThe painting has been moved onto the floor revealing a hidden passageway.");
									continue;
								} else {
									room.flags.put("examinedPainting", true);
									System.out.println("\nYou approach the painting of the skeleton and notice something strange - a hinge along one of the edges. \n"
													+ "You pull it open like a door, revealing a secret passage!");
									enterRoom("levers 1", NORTH);
									break;
								}
							}
					}
				
				
				case "t-hall": //Room 5: Crossroads
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
						case "s": 
							enterRoom("garg hall", NORTH);
							break;
						case "w": 
							if(room.intFlags.get("leftDoor") == 0) {
								enterRoom("magic mirror", EAST);
								break;
							} else if(room.intFlags.get("leftDoor") == 1) {
								System.out.println("The exit is blocked by a large, wooden door. You could probably break it down, but it would be hard without an axe.");
								continue;
							} else {
								System.out.println("You try to go through the western exit, but slam your face into a wall. ");
								continue;
							}
						case "e": 
							enterRoom("frozen warrior", WEST);
							break;
						case "a":
							if(room.intFlags.get("leftDoor") == 1) {
								if(cmd[1].equals("door")) {
									//if the player has an axe, then they bypass the strength check to break down the door
									if(Main.p.wep.name.equals("Rusty Axe") | Main.p.wep.name.equals("Warrior's Axe")) {
										System.out.println("You break down the door with your axe, and walk through the doorway.");
										room.intFlags.put("leftDoor", 0); //remove the door
										enterRoom("magic mirror", EAST); //and walk to the next room
										Main.enterAnything();
										break;
									} else {
										//do a strength check to damage the door
										int check = Main.p.skillCheck(room.intFlags.get("doorStrength"), "strength");
										
										//if you pass, you break down the door
										if(check >= 0) {
											System.out.println("You bash your shoulder against the door, and it gives. You walk over the door to the next room.");
											room.intFlags.put("leftDoor", 0); //remove the door
											enterRoom("magic mirror", EAST); //and walk to the next room
											
											Main.enterAnything();
											break;
										} else if (check >= -4) { //if you almost pass, weaken the door and lose a bit of health
											System.out.println("You bash repeatedly against the door, but it doesn't give. However, the hinges seem to have weakened a little.\nAll that bashing hurt, though - you lost 2 health.");
											room.intFlags.put("doorStrength", room.intFlags.get("doorStrength") - (5 + check)); //reduce door strength - the closer you were to passing, the more door strength is lost
											Main.p.curHP -= 2; //player loses 2 health
											Main.p.checkHP();
											
											Main.enterAnything();
											break;
										} else { //if you totally fail, lose a bit of health
											System.out.println("You bash your shoulder agains the door until it hurts, but nothing happens.\nYou lost 2 health.");
											Main.p.curHP -= 2; //player loses 2 health
											Main.p.checkHP();
											
											Main.enterAnything();
										}
									}
								}
							} else {
								System.out.println("\n(Invalid input; try again)");
								continue;
							}
						case "x":
							if(room.intFlags.get("leftDoor") == 1 && cmd[1].equals("door")) {
								System.out.println("The exit is blocked by a large, wooden door. You could probably break it down, but it would be hard without an axe.");
								continue;
							} else {
								System.out.println("\n(Invalid input; try again)");
								continue;
							}
							
					}
					
				//Room 6: Mirror Bedroom
				case "magic mirror": 
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
						case "e":
							enterRoom("t-hall", WEST);
							break;
						case "w":
							if(room.flags.get("examinedMirror")) {
								System.out.println("\n(Invalid input; try again)");
								continue;
							} else {
								//seal the western exit of the t-junction, so this room becomes inaccessible
								Room r = Main.rooms.get("t-hall");
								r.intFlags.put("leftDoor", 2);
								Main.rooms.put("t-hall", r);
								
								//then, teleport to the sphinx's lair
								enterRoom("sphinx", EAST);
								break;
							}
						case "x":
							switch(cmd[1]) {
								default:
									System.out.println("\n(Invalid input; try again)");
									continue;
								case "bed":
									System.out.println("An odd bed. The sheets are made of cobwebs and the mattress is made of bones.");
									if(!room.flags.get("examinedBed")) {
										System.out.println("As you examine the bed, you notice something red sparkling in the mattress. You reach in, and grab it.");
										room.flags.put("examinedBed", true);
										Main.p.addToInv(new Item(3));
									}
									continue;
								case "mirror":
									if(!room.flags.get("examinedMirror")) {
										System.out.println("It's a very fancy mirror, that stands about as tall as you.\n"
												+ "Strangely, you don't see your reflection in it. You reach out to touch the mirror, but your hand passes into it, creating a ripple.\n"
												+ "You wonder what would happen if you passed through the mirror.");
										room.flags.put("examinedMirror", true);
										continue;
									} else { //if they examine it again, they just go through it
										//seal the western exit of the t-junction, so this room becomes inaccessible
										Room r = Main.rooms.get("t-hall");
										r.intFlags.put("leftDoor", 2);
										Main.rooms.put("t-hall", r);
										
										//then, teleport to the sphinx's lair
										enterRoom("sphinx", EAST);
										break;
									}
								case "vase":
									if(!room.flags.get("examinedVase")) {
										System.out.println("Despite the warning, you decide to touch the vase. Nothing happens, so you stick your hand inside.");
										
										//do a skill check
										boolean passed = Main.p.skillCheck(17, "skill") >= 0;
										
										if(passed) { //if you passed, you get a scorpion
											System.out.println("As you reach in, you feel something grab your hand. With some swift finger movements, you grab it and yank it out.\n"
													+ "It's a scorpion! Carefully, you crush it in your hand. You decide to pocket it - maybe you could stab something with it.");
											Main.enterAnything();
											
											Main.p.addToInv(new Weapon(5));
										} else { //if you failed, you get stabbed
											System.out.println("As you reach in, you suddenly feel a sharp, burning pain on your finger! You yelp and smash the vase on the floor.\n"
													+ "A scorpion scuttles away from the remains of the vase. That must be what hurt you.\n"
													+ "You lost 7 health.");

											Main.p.curHP -= 7; //player loses 7 health
											Main.p.checkHP();
										}
										
										room.flags.put("examinedVase", true);
										continue;
									} else {
										System.out.println("\n(Invalid input; try again)");
									}
									continue;
							}
					}
					
				//Room 7: Frozen Warrior
				case "frozen warrior": 
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
						case "w":
							enterRoom("t-hall", EAST);
							break;
						case "x":
							if((cmd[1].equals("warrior") || cmd[1].equals("man")) & !room.flags.get("killedWarrior")) {
								System.out.println("The warrior has some good stuff on him - a nice-looking ring and a nicer-looking axe. He probably wouldn't mind if you took something...");
							} else {
								System.out.println("\n(Invalid input; try again)");
							}
							continue;
						case "a": 
							if((cmd[1].equals("warrior") || cmd[1].equals("man")) & !room.flags.get("killedWarrior")) {
								System.out.println("You draw your weapon, but the warrior does nothing. \n"
										+ "You poke the warrior, to see if he reacts. He does not.\n"
										+ "You decide to slap the warrior in the face, for fun. Suddenly, he roars to life!");
								Main.enterAnything();
								
								Battler.battle(Main.p, 4);
								room.flags.put("killedWarrior", true);
							} else {
								System.out.println("\n(Invalid input; try again)");
							}
							continue;
						case "t":
							if(cmd[1].equals("bones") & room.flags.get("bones")) {
								System.out.println("You pick up a nice-looking bone. The rest evaporate.");
								Main.p.addToInv(new Item(11));
								room.flags.put("bones", false);
							} else if (!room.flags.get("killedWarrior")) {
								//TODO
								if(cmd[1].equals("ring")) {
									System.out.println("Placeholder text");
									Main.enterAnything();
									
									Battler.battle(Main.p, 4);
									room.flags.put("killedWarrior", true);
								} else if (cmd[1].equals("axe")) {
									System.out.println("Placeholder text");
									Main.enterAnything();
									
									Battler.battle(Main.p, 4);
									room.flags.put("killedWarrior", true);
								} else {
									System.out.println("\n(Invalid input; try again)");
								}
							} else {
								System.out.println("\n(Invalid input; try again)");
							}
							
							continue;
					}
					
				case "levers 1": //Room 8: Lever Room
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
						case "n":
							enterRoom("gallery", SOUTH);
							break;
						case "s":
							if(room.flags.get("yellow")) {
								enterRoom("bonsey shrine", NORTH);
								break;
							} else {
								System.out.println("\n(Invalid input; try again)");
								continue;
							}
						case "pull": case "use": case "flip":
							switch(cmd[1]) {
								case "red":
									if(room.flags.get("red")) {
										System.out.println("You already pulled the red lever.");
										continue;
									} else {
										System.out.println("You pull the red lever. You hear some mechanical clicks and whirrs, but otherwise nothing happens.");
										room.flags.put("red", true);
										continue;
									}
									
								case "yellow":
									if(room.flags.get("yellow")) {
										System.out.println("You already pulled the yellow lever.");
										continue;
									} else {
										System.out.println("You pull the yellow lever. Suddenly, a door opens in front of you!");
										room.flags.put("yellow", true);
										continue;
									}

								case "green":
									if(room.flags.get("green")) {
										System.out.println("You already pulled the green lever.");
										continue;
									} else {
										System.out.println("You pull the green lever. Suddenly, a boulder falls on your head!\n"
												+ "It was made of feathers. You took 1 damage.");
										Main.p.curHP--;
										Main.p.checkHP();
										
										room.flags.put("green", true);
										continue;
									}

								case "blue":
									if(room.flags.get("blue")) {
										System.out.println("You already pulled the blue lever.");
										continue;
									} else {
										System.out.println("You pull the blue lever. Suddenly, 2 lil' slimes fall from the ceiling and attack you!");
										Main.enterAnything();
										
										
										
										room.flags.put("blue", true);
										continue;
									}
							}
					} 
					
				//Room 9: Library
				case "take1leave1":
					int x;
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
						case "w":
							//if you steal an item, the guards come to life!
							if(room.intFlags.get("credit") < 0 && room.flags.get("guarded")) {
								System.out.println("You try to leave, even though you took an item without leaving one.\n"
										+ "Suddenly, the guards come to life. \"Stop, thief!\"\n"
										+ "With the power of friendship, they merge into one giant guard. This could be tough...");
								Main.enterAnything();
								Battler.battle(Main.p, 2);
								room.flags.put("guarded", false);
							}
							enterRoom("fountain", EAST);
							break;
						case "s":
							if(room.intFlags.get("credit") < 0 && room.flags.get("guarded")) {
								System.out.println("You try to leave, even though you took an item without leaving one.\n"
										+ "Suddenly, the guards come to life. \"Stop, thief!\"\n"
										+ "With the power of friendship, they merge into one giant guard. This could be tough...");
								Main.enterAnything();
								Battler.battle(Main.p, 2);
								room.flags.put("guarded", false);
							}
							enterRoom("bees", NORTH);
							break;
							
						//leave an item
						case "leave": case "put": case "place":
							try { //if you entered a number, then it continues with the code...
								x = Integer.parseInt(cmd[1]) - 1;
							} catch(NumberFormatException e) { //otherwise, it stops the code
								System.out.println("\n(Invalid input; try again)");
								continue;
							}
							
							try { //place the item, if the item exists
								room.items.add(Main.p.inventory.get(x));
								System.out.println("Left the " + Main.p.inventory.get(x).name + ".");
								Main.p.inventory.remove(x);
								room.intFlags.put("credit", room.intFlags.get("credit") + 1);
								continue;
							} catch(IndexOutOfBoundsException e) { //if the number you inputted doesn't exist, then it tells you and does nothing
								System.out.println("\n(Invalid input; try again)");
								continue;
							}
							
						//take an item
						case "t":
							try { //if you entered a number, then it continues with the code...
								x = Integer.parseInt(cmd[1]) - 1;
							} catch(NumberFormatException e) { //otherwise, it stops the code
								System.out.println("\n(Invalid input; try again)");
								continue;
							}
							
							try { //take the item, if the item exists
								Main.p.inventory.add(room.items.get(x));
								System.out.println("Took the " + room.items.get(x).name + ".");
								room.items.remove(x);
								room.intFlags.put("credit", room.intFlags.get("credit") - 1);
								continue;
							} catch(IndexOutOfBoundsException e) { //if the number you inputted doesn't exist, then it tells you and does nothing
								System.out.println("\n(Invalid input; try again)");
								continue;
							}
					}
					
				case "pit room": //Room 10: Pitfall
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "dark maze": //Room 11: Maze of Darkness
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "friendly guide": //Room 12: Guided Crossroads
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "fight club": //Room 13: Fighting Ring
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "spike pit": //Room 14: Spike Pit Bottom
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "time wizard": //Room 15: Clockwork Abode
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "chest room": //Room 16: Hidden Mimic Room
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "sphinx": //Room 17: Sphinx's Lair
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "minotaur": //Room 18: Minotaur Fight
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "ambrosia": //Room 19: Ambrosia Lab
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "bees": //Room 20: Beehive
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "levers 2": //Room 21: Lever Puzzle
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "bonesy shrine": //Room 22: Bonesy Shrine
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "golden idol": //Room 23: Golden Idol
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "hand hall": //Room 24: Hand Hall
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "rocks fall": //Room 25: Unstable Mineshaft
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}

				case "final boss": //Room 26: Bone Throne
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
					}
			}
			break;
		}
	}
	
	//get the actual text of the room, depending on the room flags and direction of entry
	//this is a separate function because changing the text every time something happens is a massive pain
	public String getDesc(char dir) { 
		String d = "";
		
		switch(id) {
			//Room 1: Entrance
			case "start":
				if(flags.get("firstEntry")) {
					d = "This is it: the Bone Lair, the dungeon that no adventurer has ever come out of alive. \n\n"
						+ "You've decided to come here, seeking the glory of being the first to survive. Not only that, but they say that the dungeon is full of hidden treasures.\n"
						+ "Primed for adventure, you enter the dungeon, on the lookout for treasure.\n\n"
						+ "In front of you lies a treasure chest, with a skeleton-shaped key in the lock. Well, that was easy! \n\n"
						+ "There are also three hallways leading out of this room: one to the north, one to the east, and one to the west. \n"
						+ "However, for whatever reason, you have an overwhelming feeling that you should shout \"H\".";
				} else {
					if(flags.get("openedBonsey")) {
						d = "You're in the entrance of the dungeon. Or, where it would have been; the entrance closed while you fought the skeleton.\n\n"
								+ "All that's here is an empty treasure chest, covered in calcium. Otherwise, the room is empty.\n\n"
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
				if(!flags.get("disabled") && !flags.get("dead")) {
					switch(intFlags.get("triggers")) {
						case 0:
							d = "You enter a long, narrow corridor, with gargoyles lining the walls. \n"
									+ "Nothing else is here, but you get the uncanny feeling that you're being watched. \n"
									+ "There are 2 exits; one north, one south.";
							break;
						case 1:
							if(dir == NORTH) {
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
							if(dir == NORTH) {
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
							if(dir == NORTH) {
								d = "Here again. There are still gargoyles. You want to smash their stupid faces.\n"
									+ "You can return north, or go south and die in a fire.";
							} else {
								d = "Here again. There are still gargoyles. You want to smash their stupid faces.\n"
									+ "You can return south, or go north and die in a fire.";
							}
							break;
					}
				} else if (flags.get("dead")) {
					d = "You're in a hall which was once full of gargoyles.\n"
						+ "However, some uncultured savage seems to have smashed all of the statues in a blind rage. Who would do such a thing?\n"
						+ "All that's left is the exits; one north, one south.";
					break;
				} else {
					//TODO
					d = "TODO: when the gargoyles are disabled";
				}
				break;
				
			case "fountain":
				d = "You enter a pleasant-looking room which resembles a forest clearing. Though you’re still indoors, the floor is covered in grass, and the walls are lined with trees. \n"
						+ "However, what really draws your attention is the large fountain in the middle of the clearing. The water is sparkling, and just standing here you feel healthier.\n"
						+ "There are exits to the west, east, and south, but it might be nice to stay a while...\n";
				break;
				
			//Gallery
			case "gallery":
				if(!flags.get("examinedPainting") && !flags.get("foughtWizard")) {
					d = "You enter a gallery, full of various paintings.\n"
					+ "Two of them catch your attention: a painting of a crazy-looking wizard to your north, and a painting of a familiar-looking skeleton to the south.\n"
					+ "Otherwise, there's nothing of note, other than the exits to the west and the east.";
				}
				else if(flags.get("examinedPainting") && !flags.get("foughtWizard")) {
					d = "You enter a gallery, full of various paintings.\n"
					+  "One of them catches your attention: a painting of a crazy looking wizard to your north.\n"
					+ "Otherwise, there's nothing of note, other than the secret entrance you found to the south and the normal exits to the west and the east.";
				}
				else if(!flags.get("examinedPainting") && flags.get("foughtWizard")) 
					d = "You enter a gallery, full of various paintings.\n"
					+ "One of them catches your attention: a painting of a familiar-looking skeleton to the south.\n"
					+ "Otherwise, there's  nothing of note, other than the exits to the west and the east.";
				else {
					d = "You enter a gallery, full of various paintings.\n"
					+ "There's nothing of note, other than the secret entrance you found to the south and the normal exits to the west and the east.\n";
				}
				break;
				
			//Room 5: T-Hall TODO
				
			//Room 6: Bedroom (mirror room)
			case "magic mirror":
				if(!flags.get("examinedMirror")) {
					if(!flags.get("examinedVase")) {
						d = "You enter what appears to be a decrepit bedroom. Interestingly, the bed is made out of bones.\n"
							+ "Other than the bed, there are 2 things that catch your attention: an ornate mirror, and a big vase that says \"DO NOT TOUCH.\"\n"
							+ "To the east is the hallway from whence you came.";
					} else {
						d = "You enter what appears to be a decrepit bedroom. Interestingly, the bed is made out of bones.\n"
							+ "Other than the bed, only one thing catches your attention: a big, ornate mirror to your west.\n"
							+ "To the east is the hallway from whence you came.";
					}
				} else {
					if(!flags.get("examinedVase")) {
						d = "You enter what appears to be a decrepit bedroom. Interestingly, the bed is made out of bones.\n"
							+ "You also spot a large vase that has \"DO NOT TOUCH\" written on it in big, red letters.\n"
							+ "To the east is the doorway that you came in through. To the west is a mirror which you might be able to go through.";
					} else {
						d = "You enter what appears to be a decrepit bedroom. Interestingly, the bed is made out of bones.\n"
							+ "To the east is the doorway that you came in through. To the west is a mirror which you might be able to go through.";
					}
				}
				break;
				
			//Room 7: Frozen Warrior TODO
				
			//Room 8: Lever Room
			case "levers 1":
				if(!flags.get("yellow")) {
					d = "You enter a small, unimpressive room. \n"
							+ "At the eastern end of the room is 4 levers: in order, red, blue, green, and yellow.\n"
							+ "Maybe you could activate them with the \"pull\" command. You wonder what that means.\n"
							+ "There's also a small exit through the north into the gallery.";
				} else {
					d = "You enter a small, unimpressive room. \n"
							+ "At the eastern end of the room is 4 levers: in order, red, blue, green, and yellow.\n"
							+ "Maybe you could activate them with the \"pull\" command. You wonder what that means.\n"
							+ "There's 2 exits: north and south.";
				}
				break;
				
			//Room 9: Library
			case "take1leave1":
				d = "You enter a library. At the centre of the library, between four bookshelves, is a table, with a number of objects. \n"
					+ "The table has a sign that says “Take One, Leave One,” and contains the following: \n";
				int n = 0;
				for(Item i : items) {
					n++;
					d += " [" + n + "] " + i.name + "\n";
				}
				
				if(intFlags.get("credit") > 0) {
					d += "You've left " + intFlags.get("credit") + " item(s). You should probably take one.\n";
				} else if(intFlags.get("credit") < 0) {
					d += "You've taken " + (intFlags.get("credit")*-1) + " item(s). You should probably leave one.\n";
				} else {
					d += "(You can leave items on the table with the command \"leave/put <slot number>\".\n";
				}
				if(flags.get("guarded")) {
					d += "There are exits to the west and south; both of them are flanked by guards.";
				} else {
					d += "There are also exits to the west and south.";
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
				System.out.println(" <=-=-=-=-=-=-=-=-=-=-=-=>");
				System.out.printf(" |  %-20s |%n", "Command List");
				System.out.println(" <=-=-=-=-=-=-=-=-=-=-=-=>\n");

				System.out.println(" - Interactive Room Commands - Move in or interact with a room.");
				System.out.println("(direction)/(first letter of direction): \n > Move in the specified direction; e.g. type \"north\" or \"n\" to move north.\n");
				System.out.println("take/grab/pickup/t <object>: \n > Attempt to put an object in a room into your inventory.\n");
				System.out.println("open/o <object>: \n > Open a container in a room. May be locked.\n");
				System.out.println("examine/x <object>: \n > Examine an object in a room. This may reveal something hidden.\n");
				System.out.println("smash/hit/attack/destroy/a <object>: \n > Attempt to destroy an object.\n");
				System.out.println("(anything else) <object>: \n > If you think you can interact in a different way, try it! drink, jump, etc. might work, depending on the object.\n");

				System.out.println("\n - Other Room Commands - Other commands that you can use in a room.");
				System.out.println("help/h: \n > Shows the Command List. Synonyms are seperated by slashes; use only one of them. Parameters are in <>.\n");
				System.out.println("inventory/inv/i: \n > Opens inventory (shows your items & stats)\n");
				System.out.println("use/eat/drink/equip <slot number>: \n > Uses or equips the item in the given inventory slot.\n");
				System.out.println("examine/x <slot number>: \n > Examine an object in the given inventory slot.\n");
				System.out.println("quit/q: \n > Quit the game.\n");
				
				System.out.println("\n - Battle Commands - Can be used in battle. ");
				System.out.println("attack/a: \n > Attack foe.\n");
				System.out.println("inventory/inv/i: \n > Opens inventory (shows your items & stats)\n");
				System.out.println("use/eat/drink/equip <item>: \n > Uses or equips the given item.\n");
				System.out.print("back/exit/b: \n > Exit your inventory.\n\n");
				
				Main.enterAnything();
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
						System.out.printf(" %d damage | %d hit | %d crit%n", w.damage, w.hit, w.crit);
							
						System.out.println(w.description);
						
					} else if(i.getClass() == Armour.class) { //examine armour
						Armour a = i.toArmour();
							
						System.out.println("\n\n <=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=>");
						System.out.printf(" |  %-30s |%n", a.name);
						System.out.println(" <=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=>");
						System.out.printf(" %d defense | %d encumbrance%n", a.defense, a.encumbrance);
						
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
					return false;
				}
				
				try { //use the item, if the item exists
					Item i = Main.p.inventory.get(x);
					return i.use(Main.p, x, room);
				} catch(IndexOutOfBoundsException e) { //if the number you inputted doesn't exist, then it tells you and does nothing
					System.out.println("\n(Invalid input; try again)");
					return true;
				}
			
			//EAT AN ITEM IN INVENTORY
			case "eat": case "consume":
				try { //if you entered a number, then it continues with the code...
					x = Integer.parseInt(parameter) - 1;
				} catch(NumberFormatException e) { //otherwise, it stops the code
					return false;
				}
				
				try { //eat the item, if the item exists
					Item i = Main.p.inventory.get(x);
					return i.eat(Main.p, x, room);
				} catch(IndexOutOfBoundsException e) { //if the number you inputted doesn't exist, then it tells you and does nothing
					System.out.println("\n(Invalid input; try again)");
					return true;
				}
			
			//DRINK AN ITEM IN INVENTORY
			case "drink":
				try { //if you entered a number, then it continues with the code...
					x = Integer.parseInt(parameter) - 1;
				} catch(NumberFormatException e) { //otherwise, it stops the code
					return false;
				}
				
				try { //drink the item, if the item exists
					Item i = Main.p.inventory.get(x);
					return i.drink(Main.p, x, room);
				} catch(IndexOutOfBoundsException e) { //if the number you inputted doesn't exist, then it tells you and does nothing
					System.out.println("\n(Invalid input; try again)");
					return true;
				}
			
			//EQUIP AN ITEM IN INVENTORY
			case "equip":
				try { //if you entered a number, then it continues with the code...
					x = Integer.parseInt(parameter) - 1;
				} catch(NumberFormatException e) { //otherwise, it stops the code
					return false;
				}
				
				try { //equip the item, if the item exists
					Item i = Main.p.inventory.get(x);
					return i.equip(Main.p, x, room);
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