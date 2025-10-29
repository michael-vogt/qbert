package qbert.level;

import qbert.exceptions.MissingBlockException;

import java.util.*;

public class Level {

    private final List<Block> blocks = new ArrayList<>();
    private Block startingBlock;

    public Level() {

    }

    public void addBlock(Block block) {
        if (!blocks.contains(block)) {
            blocks.add(block);
        }
    }

    public Optional<Block> findBlock(int id) {
        return blocks.stream().filter(b -> b.getId() == id).findFirst();
    }

    public List<Block> getBlocks() {
        return blocks;
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

}
