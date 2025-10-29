package qbert.level;

import java.util.Optional;

public class Block {

    private final int id;
    private Block neighborUpperLeft;
    private Block neighborUpperRight;
    private Block neighborLowerLeft;
    private Block neighborLowerRight;

    public Block(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Block getNeighbor(Neighbors neighbor) {
        return switch(neighbor) {
            case NEIGHBOR_UPPER_LEFT -> getNeighborUpperLeft();
            case NEIGHBOR_UPPER_RIGHT -> getNeighborUpperRight();
            case NEIGHBOR_LOWER_LEFT -> getNeighborLowerLeft();
            case NEIGHBOR_LOWER_RIGHT -> getNeighborLowerRight();
        };
    }

    public Block getNeighborUpperLeft() {
        return neighborUpperLeft;
    }

    public Block getNeighborUpperRight() {
        return neighborUpperRight;
    }

    public Block getNeighborLowerLeft() {
        return neighborLowerLeft;
    }

    public Block getNeighborLowerRight() {
        return neighborLowerRight;
    }

    public void setNeighbor(Block newBlock, Neighbors neighbor) {
        switch(neighbor) {
            case NEIGHBOR_UPPER_LEFT -> setNeighborUpperLeft(newBlock);
            case NEIGHBOR_UPPER_RIGHT -> setNeighborUpperRight(newBlock);
            case NEIGHBOR_LOWER_LEFT -> setNeighborLowerLeft(newBlock);
            case NEIGHBOR_LOWER_RIGHT -> setNeighborLowerRight(newBlock);
        }
    }

    public void setNeighborUpperLeft(Block neighborUpperLeft) {
        this.neighborUpperLeft = neighborUpperLeft;
    }

    public void setNeighborUpperRight(Block neighborUpperRight) {
        this.neighborUpperRight = neighborUpperRight;
    }

    public void setNeighborLowerLeft(Block neighborLowerLeft) {
        this.neighborLowerLeft = neighborLowerLeft;
    }

    public void setNeighborLowerRight(Block neighborLowerRight) {
        this.neighborLowerRight = neighborLowerRight;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Block otherBlock) {
            return getId() == otherBlock.getId();
        }

        return false;
    }

    @Override
    public String toString() {
        String str = String.format("Block{id=%d", id);

        for (Neighbors n : Neighbors.values()) {
            Block nb = getNeighbor(n);
            if (nb != null) {
                str += String.format(", %s=%d", n.toArrow(), nb.getId());
            }
        }

        return str + "}";
    }
}
