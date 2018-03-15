package marsrovers.command;

import com.google.common.base.Preconditions;
import marsrovers.domain.*;


public class RoverCommand {
    static final String SPACE_SEPARATOR = " ";

    private String positionLine;
    private String instructionsLIne;

    public RoverCommand(String positionLine) {
        this.positionLine = positionLine;
    }

    public void setInstructionsLine(String instructionsLIne) {
        this.instructionsLIne = instructionsLIne;
    }

    public String getPositionLine() {
        return positionLine;
    }

    public String getInstructionsLIne() {
        return instructionsLIne;
    }

    public Rover parse() {
        String[] coordinates = positionLine.split(SPACE_SEPARATOR);

        if (coordinates.length != 3) {
            throw new IllegalArgumentException("position: " + positionLine + " is wrong, rover position must contain three characters separated by single space");
        }

        Position position = new Position();
        try {
            position.setX(Integer.parseInt(coordinates[0]));
            position.setY(Integer.parseInt(coordinates[1]));

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("position: " + positionLine + " is wrong, rover position must contain two numbers followed by compass direction");
        }

        Preconditions.checkArgument(position.getX() >= 0 && position.getY() >= 0, "position: " + positionLine + " is wrong, rover position must be two positive numbers");

        Compass compass;
        try {
            compass = new Compass(Direction.valueOf(coordinates[2]));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("position: " + positionLine + " is wrong, rover direction must be within " + Direction.names());
        }

        if(instructionsLIne==null || instructionsLIne.length()==0){
            throw new IllegalArgumentException("instructions: " + instructionsLIne + " is wrong, rover instructions must be within " + Instruction.names());
        }

        Rover rover = new Rover( position, compass);

        for (int i = 0; i < instructionsLIne.length(); i++) {
            Character instructionChar = instructionsLIne.charAt(i);
            Instruction instruction;
            try {
                instruction = Instruction.valueOf(String.valueOf(instructionChar));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("instructions: " + instructionsLIne + " is wrong, rover instructions must be within " + Instruction.names());
            }
            rover.addInstruction(instruction);
        }
        return rover;

    }
}
