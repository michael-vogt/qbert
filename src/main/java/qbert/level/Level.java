package qbert.level;

import qbert.exceptions.MissingBlockException;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Level {

    private final List<Block> blocks = new ArrayList<>();
    private Block startingBlock;
    private int minRow = Integer.MAX_VALUE;
    private int maxRow = Integer.MIN_VALUE;
    private int minColumn = Integer.MAX_VALUE;
    private int maxColumn = Integer.MIN_VALUE;

    public Level() {

    }

    public void addBlock(Block block) {
        if (!blocks.contains(block)) {
            blocks.add(block);
            recalculateDimension();
        }
    }

    public Optional<Block> findBlock(int id) {
        return blocks.stream().filter(b -> b.getId() == id).findFirst();
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public Dimension getDimension() {
        recalculateDimension();
        return new Dimension(maxColumn - minColumn + 1, maxRow - minRow + 1);
    }

    public Block getStartingBlock() {
        return startingBlock;
    }

    public void setStartingBlock(Block startingBlock) {
        if (findBlock(startingBlock.getId()).isPresent()) {
            this.startingBlock = startingBlock;
        } else {
            throw new MissingBlockException("The given starting block is not part of the level yet!");
        }
    }

    private void recalculateDimension() {
        minRow = Integer.MAX_VALUE;
        maxRow = Integer.MIN_VALUE;
        minColumn = Integer.MAX_VALUE;
        maxColumn = Integer.MIN_VALUE;

        for (Block block : blocks) {
            int row = block.getRow();
            int column = block.getColumn();
            minRow = Math.min(minRow, row);
            maxRow = Math.max(maxRow, row);
            minColumn = Math.min(minColumn, column);
            maxColumn = Math.max(maxColumn, column);
        }
    }

}
