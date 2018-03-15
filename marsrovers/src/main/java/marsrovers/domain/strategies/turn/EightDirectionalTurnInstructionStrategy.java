package marsrovers.domain.strategies.turn;

import marsrovers.domain.Instruction;
import marsrovers.domain.InstructionStrategy;
import marsrovers.domain.Rover;

//we use extended 8 directions so strategy is different
public class EightDirectionalTurnInstructionStrategy implements InstructionStrategy {


    @Override
    public void follow(Rover rover, Instruction instruction) {

        int directionIndex = rover.getCompass().getPointer();

        if (instruction.equals(Instruction.R)) {
            directionIndex = directionIndex + 2;
        } else if (instruction.equals(Instruction.L)) {
            directionIndex = directionIndex - 2;
        } else if (instruction.equals(Instruction.B)) {
            directionIndex = directionIndex + 1;
        } else if (instruction.equals(Instruction.A)) {
            directionIndex = directionIndex - 1;
        }


        directionIndex = resetIfFullLoop(directionIndex);

        rover.getCompass().setPointer(directionIndex);
    }

    private int resetIfFullLoop(int directionIndex) {
        if (directionIndex > 7) {
            directionIndex = directionIndex - 8;
        } else if (directionIndex < 0) {
            directionIndex = 8 + directionIndex;
        }
        return directionIndex;
    }
}
