package qbert.level;

import qbert.exceptions.MissingBlockException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Level {

    private List<Block> blocks = new ArrayList<>();
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
        if (startingBlock != null) {
            List<Block> traversedBlocks = new ArrayList<>();
            List<Block> alreadyVisitedBlocks = new ArrayList<>();
            traverseBlocks(traversedBlocks, startingBlock, alreadyVisitedBlocks);
            return traversedBlocks;
        }
        return blocks;
    }

    public void setStartingBlock(Block startingBlock) {
        if (findBlock(startingBlock.getId()).isPresent()) {
            this.startingBlock = startingBlock;
        } else {
            throw new MissingBlockException("The given starting block is not part of the level yet!");
        }
    }

    private void traverseBlocks(List<Block> listTraversedBlocks, Block block, List<Block> listVisitedBlocks) {
        if (listVisitedBlocks.contains(block)) {
            return;
        }

        listVisitedBlocks.add(block);

        if (!listTraversedBlocks.contains(block)) {
            listTraversedBlocks.add(block);
        }

        for (Neighbors neighbor : Neighbors.values()) {
            Block neighborBlock = block.getNeighbor(neighbor);
            if (neighborBlock != null) {
                traverseBlocks(listTraversedBlocks, neighborBlock, listVisitedBlocks);
            }
        }

    }

}
