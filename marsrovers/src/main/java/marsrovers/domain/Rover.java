package marsrovers.domain;

import java.util.ArrayList;
import java.util.List;

public class Rover {
    private Plateau plateau;
    private Position position;
    private Compass compass;
    private List<Instruction> instructions = new ArrayList<>();

    public Rover(Position position, Compass compass) {
        this.position = position;
        this.compass = compass;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public void addInstruction(Instruction instruction) {
        instructions.add(instruction);
    }

    public Position getPosition() {
        return position;
    }

    public Compass getCompass() {
        return compass;
    }

    public Direction getCompassDirectionValue() {
        return compass.getDirection();
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public void explore() {
        instructions.forEach((instruction)->InstructionFactory.strategyBy(instruction).follow(this, instruction));
    }

}
