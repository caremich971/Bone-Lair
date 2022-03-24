
public class RoomObject { //Less complex rooms without flags or titles. Mostly copied from Room, but we don't want to inherit
	// Contains:
	// - Text of all room objects

	String id;
	
	public RoomObject(String id) {
		this.id = id;
	}
	
	public static void inspectObj(String id, Room parent) { //mostly identical to enterRoom
		RoomObject obj = new RoomObject(id);
		
		while(true) {
			System.out.println(obj.getDesc(parent));
			System.out.print("\n > ");
			String[] cmd = Main.parseCommand(Main.s.nextLine());
			
			//First, check if any default commands were inputted. If so, don't execute any more code, because we already executed the command
			if(Room.roomCmds(cmd[0], cmd[1], parent)) {
				continue;
			}
			
			//Then, check if the player wants to go back to the greater room
			if(cmd[0].equals("b")) {
				return;
			}
			
			//Then, check for whatever commands are specific to that object, and execute them.
			switch(id) {
				case "bonseyChest":
					switch(cmd[0]) {
						default:
							System.out.println("\n(Invalid input; try again)");
							continue;
						case "o":
							if(parent.flags.get("openedBonsey") == 1) {
								System.out.println("\nThe chest is already open.");
								continue;
							} else {
								parent.flags.put("firstEntry", 0);
								parent.flags.put("openedBonsey", 1);
								System.out.println("You turn the key. A click can be heard as the chest unlocks. \n\nYou found... (Enter anything to continue...) ");
								String in = Main.s.next();
								
								System.out.println("THE SKELETON APPEARS \n\n(Enter anything to continue...) ");
								in = Main.s.next();
								
								Battler.battle(Main.p, 0);
								return;
							}
					}
			}
			break;
		}
	}
	
	public String getDesc(Room p) {
		String d = "";
		
		switch(id) {
			case "bonseyChest":
				if(p.flags.get("openedBonsey") == 1) {
					d = "An empty chest. There's nothing here.";
				} else {
					d = "The chest is large, made of silver and steel. ";
				}
				break;
		}
		
		return d;
	}
}