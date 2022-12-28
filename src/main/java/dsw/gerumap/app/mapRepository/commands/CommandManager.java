package dsw.gerumap.app.mapRepository.commands;

import dsw.gerumap.app.core.Command;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    List<Command> commands;
    int currentCommand = 0;

    public CommandManager() {
        commands = new ArrayList<>();
    }

    public void addCommand(Command command){
        while(this.currentCommand < this.commands.size()) {
            this.commands.remove(this.currentCommand);
        }
        this.commands.add(command);
        currentCommand++;
    }

    public void doCommand(){

        if (this.currentCommand < this.commands.size()) {

            this.commands.get(this.currentCommand++).doCommand();
        }
    }

    public void  undoCommand(){

        if (this.currentCommand > 0) {
            this.commands.get(--this.currentCommand).undoCommand();
        }

    }

}
