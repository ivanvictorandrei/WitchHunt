package witchhunt;

import java.util.Stack;
import java.util.ArrayList;


public class Player
{

    private Room currentRoom;
    private Stack<Room> path;
    private ArrayList<Item> backpack;
    private float MAXWEIGHT = 30;


    public Player()
    {
        backpack = new ArrayList<>();
        path = new Stack<>();
    }

    public void setRoom(Room room)
    {
        currentRoom = room;
    }
    
    public String locationInfo()
    {
        return currentRoom.getLongDescription();
    }

    public void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            path.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(locationInfo());
        }
    }

    public void look(Command command)
    {
        if(command.hasSecondWord()){
            System.out.println("Look what?");
        }
        else{
            System.out.println(locationInfo());
        }
    }
    
    public void drink(Command command)
    {
        if(!command.hasSecondWord()){
            System.out.println("Drink what?");
        }
        else{
            String potion = command.getSecondWord();
            boolean exist = false;
            for(Item item : backpack){
                if(potion.equals(item.getName())){
                    exist = true;
                    MAXWEIGHT += 5;
                    backpack.remove(item);
                    System.out.println("You have drank a strength potion.\n" + 
                                       "Your max weight has increased by 5");
                    return;
                }
            }
            if(!exist){
                System.out.println("You do not have such a beverage!");
            }
        }
    }

    public void back(Command command)
    {
        if(command.hasSecondWord()){
            System.out.println("Back what?");
        }
        else if(path.empty()){
            System.out.println("You are already where you began from.");
        }
        else{
            currentRoom = path.pop();
            System.out.println(locationInfo());
        }
    }

    public void take(Command command)
    {
        if(!command.hasSecondWord()){
            System.out.println("Take what?");
        }
        else{
            ArrayList<Item> items = currentRoom.getItems();
            if(items.isEmpty()){
                System.out.println("There are no items in the room.");
                return;
            }
            boolean exist = false;
            String objectName = command.getSecondWord();
            for(Item item : items){
                if(objectName.equals(item.getName())){
                    if(!pickable(item)){
                        System.out.println("This item is too heavy!\n" + 
                                           "It exceeds your max carrying weight of" + MAXWEIGHT + " by " + 
                                           (item.getWeight() - (MAXWEIGHT - getCurrentWeight())));
                        return;
                    }
                    backpack.add(item);
                    currentRoom.removeItem(item);
                    exist = true;
                    return;
                }
            }
            if(!exist){
                System.out.println("There is no such item in this room.");
            }
        }
    }

    public void drop(Command command)
    {
        if(!command.hasSecondWord()){
            System.out.println("Drop what?");
        }
        else{
            if(backpack.isEmpty()){
                System.out.println("You are not carrying any items.");
                return;
            }
            boolean exist = false;
            String objectName = command.getSecondWord();
            for(Item item : backpack){
                if(objectName.equals(item.getName())){
                    currentRoom.putItem(item);
                    backpack.remove(item);
                    exist = true;
                    return;
                }
            }
            if(!exist){
                System.out.println("You are not carrying such an item");
            }
        }
    }
    
    public void backpack(Command command){
        if(command.hasSecondWord()){
            System.out.println("You must only use the 'backpack' word!");
        }
        else{
            if(backpack.isEmpty()){
                System.out.println("You have nothing in your backpack.");
            }
            else{
                System.out.println("You have in your backpack: ");
                for(Item item : backpack){
                    System.out.println("-" + item.getFullDescription());
                }
            }
        }
    }
    
    private float getCurrentWeight()
    {
        float weight = 0;
        for(Item item : backpack){
            weight += item.getWeight();
        }
        return weight;
    }
    
    private boolean pickable(Item i)
    {
        if(i.getWeight() + getCurrentWeight() > MAXWEIGHT){
            return false;
        }
        return true;
    }
}
