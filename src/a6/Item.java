package a6;

/**
 * Created by Jason Huels on 4/22/2016.
 */
public class Item {
    private String description;
    private int weight;
    private String name; // not part of exercises

    /*
    *   Create an item with a description and weight
    *   @param description
    *   @param weight
     */
    public Item(String name, String description, int weight){
        this.name = name;
        this.description = description;
        this.weight = weight;
    }

    /*
    *   @return items description
     */
    public String getDescription(){
        return description;
    }

    /*
    *   @return items name (not part of exercises)
     */
    public String getName(){
        return name;
    }

    /*
    *   @return items weight (not part of exercises)
     */
    public int getWeight(){
        return weight;
    }
}
