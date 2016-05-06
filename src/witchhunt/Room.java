package witchhunt;

import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;


public class Room 
{
    private String description;
    private HashMap<String,Room> exits;
    private ArrayList<Item> items;


    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ArrayList<>();
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction,neighbor);
    }

    public void putItem(Item i)
    {
        items.add(i);
    }
    
    public void removeItem(Item i)
    {
        items.remove(i);
    }

    public Room getExit(String direction)
    {
        return exits.get(direction);
    }

    public String getExitString()
    {
        String exitInfo = new String("Exits:");
        Set<String> keys = exits.keySet();
        for(String key : keys){
            exitInfo += " " + key;
        }
        
        return exitInfo;
    }

    public String getDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        String description;
        if(!items.isEmpty()){
            description = "You are " + getDescription() + ".\nYou look around and see:\n";
            for(Item item : items){
                description += "-" + item.getFullDescription() + "\n";
            }
            description += getExitString();
        }
        else{
            description = "You are: " + getDescription()
                        + ".\n" + getExitString();
        }
        return description;
    }
    
    public ArrayList<Item> getItems()
    {
        return items;
    }

}
