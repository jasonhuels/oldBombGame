package a6;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a game scenario including connected rooms and items
 * 
 * @author Jason Huels + Cara Tang
 * @version 05/14/2016
 */
public class Scenario
{
    private ArrayList<Room> rooms;
    private Room startRoom;
    private Random random;

    /**
     * Constructor for objects of class Scenario
     */
    public Scenario()
    {
        random = new Random();
        
        // Set up your rooms, exits, and items
        // Move code from Game.createRooms here
        Room lobby, security, restroom, courtyard, office, breakroom, janitor, manager;

        // create the rooms
        lobby = new Room("in the lobby of the office building");
        security = new Room("in the security office");
        restroom = new Room("in the restroom");
        courtyard = new Room("in a courtyard");
        office = new Room("in the main office");
        breakroom = new Room("in the breakroom.");
        janitor = new Room("in the janitor's closet");
        manager = new Room("in the manager's office");
        TransporterRoom transporter = new TransporterRoom("a mysterious room with glowing walls.", this);

        // initialise room exits
        //Lobby
        lobby.setExits("north", courtyard);
        lobby.setExits("east", restroom);
        lobby.setExits("west", security);
        lobby.setExits("south", transporter);
        //Security
        security.setExits("east", lobby);
        //Restroom
        restroom.setExits("west", lobby);
        //Courtyard
        courtyard.setExits("north", office);
        courtyard.setExits("south", lobby);
        //Office
        office.setExits("north", manager);
        office.setExits("east", janitor);
        office.setExits("south", courtyard);
        office.setExits("west", breakroom);
        //Breakroom
        breakroom.setExits("east", office);
        //Janitor
        janitor.setExits("west", office);
        //Manager
        manager.setExits("south", office);

        // Create items and add them to rooms
        Item bomb, wirecutters;
        bomb = new Item("bomb","the bomb. You need to diffuse it", 50);
        wirecutters = new Item("wirecutters","some wirecutters, these might be useful", 1);
        breakroom.setItem(bomb);
        janitor.setItem(wirecutters);

        // Set the start room
        startRoom = lobby;  // start game in the lobby

        // Create the rooms ArrayList and add all your rooms to it
        rooms = new ArrayList<>();
        rooms.add(lobby);
        rooms.add(security);
        rooms.add(restroom);
        rooms.add(courtyard);
        rooms.add(office);
        rooms.add(janitor);
        rooms.add(manager);
        rooms.add(breakroom);
    }

    /**
     * @return  the start room for this scenario
     */
    public Room getStartRoom()
    {
        // complete this method
        return startRoom;
    }
    
    /**
     * @return  a random room from this scenario
     */
    public Room getRandomRoom()
    {
        // complete this method
        return rooms.get(random.nextInt(rooms.size()));
    }
}
