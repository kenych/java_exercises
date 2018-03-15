package marsrovers;

import marsrovers.command.CommandList;
import marsrovers.command.PlateauCommand;
import marsrovers.command.RoverCommand;
import marsrovers.domain.Plateau;
import marsrovers.domain.Rover;
import marsrovers.reader.InputReader;

import java.util.ArrayList;
import java.util.List;

public class ApplicationMarsRovers {

    InputReader inputReader;

    public ApplicationMarsRovers(InputReader inputReader) {
        this.inputReader = inputReader;
    }

    public void deploy() {
        CommandList commandList = inputReader.read();

        PlateauCommand plateauCommand = commandList.getPlateauCommand();
        Plateau plateau = plateauCommand.parse();

        List<RoverCommand> roverCommands = commandList.getRoverCommands();
        List<Rover> rovers = new ArrayList<>();

        roverCommands.forEach((roverCommand) -> {
            Rover rover = roverCommand.parse();
            rover.setPlateau(plateau);
            rover.explore();
            rovers.add(rover);
        });

        rovers.forEach(
                (rover) -> System.out.println(rover.getPosition().getX()
                        + " "
                        + rover.getPosition().getY()
                        + " "
                        + rover.getCompass().getDirection())
        );
    }
}
