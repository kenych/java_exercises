package marsrovers.domain.strategies.movement;

import marsrovers.domain.Direction;

import java.util.HashMap;
import java.util.Map;

public class MoveFactory {
    private static Map<Direction, MoveStrategy> strategyMap = new HashMap<>();

    static {
        strategyMap.put(Direction.N, new NorthMoveStrategy());
        //example of extending NW
        strategyMap.put(Direction.NW, new NorthWestMoveStrategy());
        strategyMap.put(Direction.S, new SouthMoveStrategy());
        strategyMap.put(Direction.W, new WestMoveStrategy());
        strategyMap.put(Direction.E, new EastMoveStrategy());
    }

    public static MoveStrategy strategyBy(Direction direction) {
        MoveStrategy moveStrategy = strategyMap.get(direction);
        if (moveStrategy == null) {
            throw new RuntimeException("No move strategy found for: " + direction);
        }

        return moveStrategy;
    }

}
