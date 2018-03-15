package marsrovers.domain;

import marsrovers.domain.strategies.movement.MoveInstructionStrategy;
import marsrovers.domain.strategies.turn.EightDirectionalTurnInstructionStrategy;

public class InstructionFactory {

    private static InstructionStrategy moveInstruction = new MoveInstructionStrategy();

    //we use extended 8 directions so strategy is different
//    private static FourDirectionalTurnInstructionStrategy turnInstruction = new FourDirectionalTurnInstructionStrategy();

    private static InstructionStrategy turnInstruction = new EightDirectionalTurnInstructionStrategy();


    public static InstructionStrategy strategyBy(Instruction instruction) {
        if (instruction == Instruction.M) {
            return moveInstruction;
        } else  {
            return turnInstruction;
        }

    }
}
