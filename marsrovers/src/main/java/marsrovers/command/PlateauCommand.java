package marsrovers.command;

import com.google.common.base.Preconditions;
import marsrovers.domain.Plateau;
import marsrovers.domain.Position;

public class PlateauCommand {

    static final String SPACE_SEPARATOR = " ";

    private String coordinatesLine;

    public PlateauCommand(String coordinatesLine) {
        this.coordinatesLine = coordinatesLine;
    }

    public String getCoordinatesLine() {
        return coordinatesLine;
    }

    public Plateau parse() {
        String[] coordinates = coordinatesLine.split(SPACE_SEPARATOR);

        if (coordinates.length != 2) {
            throw new RuntimeException("coordinates: " + coordinatesLine + " are wrong. Plateau coordinates must contain two digits separated by single space");
        }

        Position position = new Position();
        try {
            position.setX(Integer.parseInt(coordinates[0]));
            position.setY(Integer.parseInt(coordinates[1]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("coordinates: " + coordinatesLine + " are wrong, Plateau coordinates must contain two positive numbers");
        }

        Preconditions.checkArgument(position.getX() >= 0 && position.getY() >= 0, "coordinates: " + coordinatesLine + " are wrong, Plateau coordinates must be positive numbers");

        return new Plateau(position);
    }

}
