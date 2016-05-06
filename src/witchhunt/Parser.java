package witchhunt;

import java.util.Scanner;

public class Parser 
{
    private CommandWords commands;
    private Scanner reader;


    public Parser() 
    {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    public Command getCommand() 
    {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if(tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if(tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

            return new Command(commands.getCommand(word1), word2);
    }

    public String getAllCommands()
    {
        return commands.getCommandList();
    }
}
