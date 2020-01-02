package a6;


/**
 * Created by Jason Huels on 5/14/2016.
 */
public class TransporterRoom extends Room{
    private Scenario scenario;

    public TransporterRoom(String description, Scenario scenario) {
        super(description);
        this.scenario = scenario;
    }

    /**
     * Return a random room. independent of the direction parameter
     * @param direction Ignored
     * @return A Random Room
     */
    public Room getExit(String direction){
        return findRandomRoom();
    }

    /**
     * Return a description of the room's exits
     * for example: "Exits: north west"
     * @return A description of the available exits
     */
    public String getExitString(){
        return "There are no exits in this room. \nYou should leave ASAP.";
    }

    /**
     * Choose a random Room
     * @return A random Room.
     */
    private Room findRandomRoom(){
        return scenario.getRandomRoom();
    }
}
