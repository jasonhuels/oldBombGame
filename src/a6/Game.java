package a6;


import java.util.ArrayList;

/**
 *  This class is the main class of the "Bomb Squad Game" application.
 *  The goal of the game is to make your way to the room with the bomb and diffuse it.
 *
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 *
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 *
 * @author  Jason Huels and Michael Kölling and David J. Barnes
 * @version 5/14/2016
 */

public class Game
{
    private Parser parser;
    private Room currentRoom;
    private Boolean canDiffuse = false; // not part of exercises
    private boolean wantToQuit = false;
    private ArrayList<String> names;

    /**
     * Create the game and initialise its internal map.
     */
    public Game()
    {
        FiredUpDB fup = new FiredUpDB();
        names = fup.readCustomers();
        currentRoom = new Scenario().getStartRoom();
        parser = new Parser();
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play()
    {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        Boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Bomb Squad Game!");
        System.out.println("There is a bomb in the breakroom, find your way to it!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
    *   Print the current rooms description
    */
    private void printLocationInfo(){
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command)
    {
        //boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();

        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            look();
        }
        else if (commandWord.equals("eat")) {
            eat();
        }
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("diffuse")) {
            diffuse();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp()
    {
        System.out.println("You need to find the breakroom and diffuse the bomb.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /**
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command)
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    /**
    * Look around the room to get description
    */
    private void look(){
        System.out.println(currentRoom.getLongDescription());
    }

    /**
    * Eat something
    */
    private void eat(){
        System.out.println("You don't have anything to eat.");
    }

    /**
    *   Take an item from the room (not part of exercises)
     */
    private void take(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to take...
            System.out.println("Take what?");
            return;
        }

        String item = command.getSecondWord();

        if (currentRoom.hasItem(item)) {
            System.out.println("You've taken " + item);
            currentRoom.removeItem(item);
            if(item.equals("wirecutters")){
                canDiffuse = true;
            }
            if(item.equals("bomb")){
                System.out.println("The bomb exploded!");
                wantToQuit = true;
            }
        }
        else {
            System.out.println("There is no " + item +" to take");
        }
    }

    /**
     * Diffuse the bomb (not part of exercises, added to give game an end state)
    */
    private void diffuse(){
        if(currentRoom.hasItem("bomb")){
            if(canDiffuse){
                System.out.println("You diffused the bomb!\nYou're a winner!");
                wantToQuit = true;
            }else{
                System.out.println("You need something to cut the wires.");
            }
        }else{
            System.out.println("Nothing to diffuse.");
        }
    }
}
