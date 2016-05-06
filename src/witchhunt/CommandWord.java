package witchhunt;

public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), DRINK("drink"), BACK("back"), 
                TAKE("take"), DROP("drop"), BACKPACK("backpack"), UNKNOWN("?");
    
    
    private String commandWord;
    
    CommandWord(String aString)
    {
        commandWord = aString;
    }
    
    public String toString()
    {
        return commandWord;
    }
}
