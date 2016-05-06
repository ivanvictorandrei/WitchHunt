package witchhunt;

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
        
    
    public Game() 
    {
        player = new Player();
        createRooms();
        parser = new Parser();
    }

    
    private void createRooms()
    {
      

        Room outside = new Room("outside the main entrance of the church");
        Room graversHut = new Room("in the graver's hut");
        Room graveyard = new Room("in the graveyard");
        Room crypt = new Room("in the crypt");
        Room caveBegining = new Room("at the begining of a cave");
        Room caveEnding = new Room("at the end of a cave");
        Room treasureChamber = new Room("in a treasure hideout");
        Room churchEntrance = new Room("beyond the church entrance");
        Room greatHall = new Room("in the great hall of the church");
        Room altar = new Room("in the church's altar");
        Room secretChamber = new Room("in a secret chamber behind the altar");
        Room woods = new Room("in the woods");
        Room well = new Room("by a weel in the woods");
        Room werewolfLair = new Room("at the werewolf's lair");
        Room clearing = new Room("in a clearing in the woods");
        Room loneHouse = new Room("in the lone house in the clearing");
        Room witchHideout = new Room("in the witch hideout");
        

        Item potion = new Item("potion", "a potion of strength", (float)0.5);
        Item torch = new Item("torch", "a torch to light the way", (float)3.0);
        Item table = new Item("table", "a random table", (float)30.0);
        

        outside.setExit("east",woods);
        outside.setExit("south", churchEntrance);
        woods.setExit("east",well);
        woods.setExit("south",werewolfLair);
        woods.setExit("west",outside);
        well.setExit("west",woods);
        werewolfLair.setExit("north",woods);
        werewolfLair.setExit("south", clearing);
        clearing.setExit("north",werewolfLair);
        clearing.setExit("south", loneHouse);
        loneHouse.setExit("north",clearing);
        loneHouse.setExit("west", secretChamber);
        churchEntrance.setExit("north",outside);
        churchEntrance.setExit("south",greatHall);
        churchEntrance.setExit("west", graveyard);
        greatHall.setExit("north",churchEntrance);
        greatHall.setExit("south",altar);
        altar.setExit("north",greatHall);
        altar.setExit("south",secretChamber);
        secretChamber.setExit("north",altar);
        secretChamber.setExit("east",loneHouse);
        secretChamber.setExit("down",caveEnding);
        caveEnding.setExit("up",secretChamber);
        caveEnding.setExit("west",caveBegining);
        caveEnding.setExit("east",witchHideout);
        graversHut.setExit("east",graveyard);
        graveyard.setExit("west",graversHut);
        graveyard.setExit("east", churchEntrance);
        graveyard.setExit("south",crypt);
        crypt.setExit("north",graveyard);
        crypt.setExit("down", caveBegining);
        caveBegining.setExit("up",crypt);
        caveBegining.setExit("east", caveEnding);
        caveBegining.setExit("south", treasureChamber);
        treasureChamber.setExit("north",caveBegining);
        witchHideout.setExit("west",caveEnding);
        crypt.putItem(torch);
        altar.putItem(potion);
        altar.putItem(torch);
        altar.putItem(table);
        

        player.setRoom(outside);
    }

    public void play() 
    {            
        printWelcome();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Witchhunt!");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        
        switch(command.getCommandWord()){
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;
            case HELP:
                printHelp(command);
                break;
            case GO:
                player.goRoom(command);
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
            case LOOK:
                player.look(command);
                break;
            case DRINK:
                player.drink(command);
                break;
            case BACK:
                player.back(command);
                break;
            case TAKE:
                player.take(command);
                break;
            case DROP:
                player.drop(command);
                break;
            case BACKPACK:
                player.backpack(command);
                break;
        }
        

        return wantToQuit;
    }

    private void printHelp(Command command)
    {
        if(command.hasSecondWord()){
            System.out.println("Help what?");
        }
        else{
            System.out.println("You are hunting a terrible witch, hiding in a mysterious place.");
            System.out.println();
            System.out.println("Your command words are:");
            System.out.println(parser.getAllCommands());
            System.out.println();
        }
    }

    private void printLocationInfo()
    {
            System.out.println(player.locationInfo());
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
    
    
}
