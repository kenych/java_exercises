package marsrovers.reader;

import marsrovers.command.CommandList;
import marsrovers.command.PlateauCommand;
import marsrovers.command.RoverCommand;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextInputFileReader implements InputReader {


    private final String inputFile;

    public TextInputFileReader(String inputFile) {
        this.inputFile = inputFile;
    }

    public CommandList read() {

        PlateauCommand plateauCommand = null;
        RoverCommand roverCommand = null;
        List<RoverCommand> roverCommands = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(TextInputFileReader.class.getClassLoader().getResource(inputFile).getFile()))) {

            int lineNumber = 1;
            for (String line; (line = bufferedReader.readLine()) != null; ) {

                if (lineNumber == 1) {
                    plateauCommand = new PlateauCommand(line);
                } else {
                    if (lineNumber % 2 == 0) {
                        roverCommand = new RoverCommand(line);
                        roverCommands.add(roverCommand);
                    } else {
                        roverCommand.setInstructionsLine(line);
                    }
                }
                lineNumber++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("input file not found: " + inputFile);
        } catch (IOException e) {
            throw new RuntimeException("file " + inputFile + " read error+:" + e.getMessage());
        }

        return new CommandList(plateauCommand, roverCommands);
    }


}
