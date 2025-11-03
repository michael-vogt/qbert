package qbert.characters;

import qbert.level.Block;

public class Player {

    private Block position;
    private CharacterState state;

    public Player(Block startingPosition) {
        this.position = startingPosition;
        if (startingPosition != null) {
            state = CharacterState.ALIVE;
            startingPosition.visit();
        } else {
            state = CharacterState.DEAD;
        }
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
                case UPPER_LEFT -> position.getNeighborUpperLeft();
                case UPPER_RIGHT -> position.getNeighborUpperRight();
                case LOWER_LEFT -> position.getNeighborLowerLeft();
                case LOWER_RIGHT -> position.getNeighborLowerRight();
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
