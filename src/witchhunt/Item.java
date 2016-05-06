package witchhunt;

public class Item
{
    private String name;
    private String description;
    private float weight;
    private boolean pickable;

    
    public Item(String name, String description, float weight)
    {
        this.name = name;
        this.description = description;
        this.weight = weight;
        if (weight > 30){
            pickable = false;
        }
        else{
            pickable = true;
        }
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public float getWeight()
    {
        return weight;
    }

    public boolean isPickable()
    {
        return pickable;
    }

    public String getFullDescription()
    {
        return name+ ": " + description + ", weighting " + weight;
    }
}
