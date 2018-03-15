package marsrovers.domain.strategies.turn;

import marsrovers.domain.Instruction;
import marsrovers.domain.InstructionStrategy;
import marsrovers.domain.Rover;

public class FourDirectionalTurnInstructionStrategy implements InstructionStrategy {


    @Override
    public void follow(Rover rover, Instruction instruction) {

        int directionIndex = rover.getCompass().getPointer();

        if (instruction.equals(Instruction.R)) {
            directionIndex++;
        } else if (instruction.equals(Instruction.L)) {
            directionIndex--;
        }

        directionIndex = resetIfFullLoop(directionIndex);

        rover.getCompass().setPointer(directionIndex);
    }

    private int resetIfFullLoop(int directionIndex) {
        if (directionIndex == 4) {
            directionIndex = 0;
        } else if (directionIndex == -1) {
            directionIndex = 3;
        }
        return directionIndex;
    }
}
