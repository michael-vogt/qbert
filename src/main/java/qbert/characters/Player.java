package qbert.characters;

import qbert.level.Block;

public class Player {

    private Block position;
    private CharacterState state;

    public Player(Block startingPosition) {
        this.position = startingPosition;
        state = (position != null) ? CharacterState.ALIVE : CharacterState.DEAD;
    }

    public Block getPosition() {
        return position;
    }

    public boolean isState(CharacterState state) {
        return this.state == state;
    }

    public void move(Move direction) {
        if (position != null) {
            Block neighbor = switch (direction) {
                case UPPER_LEFT -> neighbor = position.getNeighborUpperLeft();
                case UPPER_RIGHT -> neighbor = position.getNeighborUpperRight();
                case LOWER_LEFT -> neighbor = position.getNeighborLowerLeft();
                case LOWER_RIGHT -> neighbor = position.getNeighborLowerRight();
                default -> null;
            };

            if (neighbor != null) {
                position = neighbor;
                neighbor.visit();
                state = CharacterState.ALIVE;
            } else {
                state = CharacterState.DEAD;
            }
        }
    }

}
