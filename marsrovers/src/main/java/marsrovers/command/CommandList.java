package marsrovers.command;

import java.util.List;

public class CommandList {
    final private PlateauCommand plateauCommand;
    final private List<RoverCommand> roverCommands;

    public CommandList(PlateauCommand plateauCommand, List<RoverCommand> roverCommands) {
        this.plateauCommand = plateauCommand;
        this.roverCommands = roverCommands;
    }

    public PlateauCommand getPlateauCommand() {
        return plateauCommand;
    }

    public List<RoverCommand> getRoverCommands() {
        return roverCommands;
    }
}
