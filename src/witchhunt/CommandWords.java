package witchhunt;

import java.util.HashMap;

public class CommandWords
{
    private HashMap<String, CommandWord> validCommands;

    
    public CommandWords()
    {
        validCommands = new HashMap<>();
        for(CommandWord command : CommandWord.values()){
            if(command != CommandWord.UNKNOWN){
                validCommands.put(command.toString(),command);
            }
        }
    }

    public CommandWord getCommand(String aString)
    {
        CommandWord command = validCommands.get(aString);
        if(command!=null){
            return command;
        }
        else{
            return CommandWord.UNKNOWN;
        }
    }

    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }
    
    public String getCommandList()
    {
        String commands = "";
        for(String command : validCommands.keySet()){
            commands += command + " ";
        }
    return commands;
    }
}
