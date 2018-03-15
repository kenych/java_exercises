package marsrovers.domain;

public class Plateau {
   private final Position coordinates;

    public Plateau(Position coordinates) {
        this.coordinates = coordinates;
    }

    public Position getCoordinates() {
        return coordinates;
    }
}
