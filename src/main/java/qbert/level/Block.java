package qbert.level;

public class Block {

    private final int id;
    private int row = 0;
    private int column = 0;
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

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
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
        if (neighborUpperLeft != null) {
            neighborUpperLeft.setRow(row - 1);
            neighborUpperLeft.setColumn(column - 1);
        }
        this.neighborUpperLeft = neighborUpperLeft;
    }

    public void setNeighborUpperRight(Block neighborUpperRight) {
        if (neighborUpperRight != null) {
            neighborUpperRight.setRow(row - 1);
            neighborUpperRight.setColumn(column);
        }
        this.neighborUpperRight = neighborUpperRight;
    }

    public void setNeighborLowerLeft(Block neighborLowerLeft) {
        if (neighborLowerLeft != null) {
            neighborLowerLeft.setRow(row + 1);
            neighborLowerLeft.setColumn(column);
        }
        this.neighborLowerLeft = neighborLowerLeft;
    }

    public void setNeighborLowerRight(Block neighborLowerRight) {
        if (neighborLowerRight != null) {
            neighborLowerRight.setRow(row + 1);
            neighborLowerRight.setColumn(column + 1);
        }
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

        str += String.format(", row=%d, column=%d}", row, column);
        return str;
    }

}
