package a6;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  The exits are labelled north,
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 *
 * @author  Jason Huels and Michael Kölling and David J. Barnes
 * @version 5/14/2016
 */
import java.util.HashMap;
import java.util.Set;

public class Room
{
    private String description;
    private HashMap<String, Room> exits;
    private Item item;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<String, Room>();
    }

    /**
     * Default constructor to make TransporterRoom work
     * Never Used.
     */
    public Room(){}

    /**
    *   Add an item to the room
     */
    public void setItem(Item item){
        this.item = item;
    }

    /**
    *   Check for an item (not part of exercises)
    *   @param itemName to check for
    *   @return whether the item exists in the room
     */
    public boolean hasItem(String itemName){
        if(item!= null && itemName.equals(item.getName())) return true;
        return false;
    }

    /**
    *   Remove an item (not part of exercises)
    *   @param itemName to remove
     */
    public void removeItem(String itemName){
        if(item!= null && itemName.equals(item.getName())) this.item = null;
    }

    /**
     * Define the exits of this room.
     * @param direction direction of the exit.
     * @param neighbor The room in the given direction
     */
    public void setExits(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * Return available exits
     * @param direction
     * @return exits
    */
    public Room getExit(String direction){
        return exits.get(direction);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Return a description of the room's exits
     * for example: "Exits: north west"
     * @return A description of the available exits
     */
    public String getExitString(){
        String returnString = "Exits: ";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return a long description of this room, of the form:
     *      You are in the kitchen.
     *      Exits: north west
     * @return A description of the room, including exits.
     */
    public String getLongDescription(){
        String desc = "You are " + description + ".\n";
        if(item!=null){
            desc += "This room contains " + item.getDescription() +".\n";
        }
        return  desc + getExitString();
    }
}
