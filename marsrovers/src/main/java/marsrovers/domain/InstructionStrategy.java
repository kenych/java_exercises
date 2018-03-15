package marsrovers.domain;

public interface InstructionStrategy {
    public void follow(Rover rover, Instruction instruction);
}
