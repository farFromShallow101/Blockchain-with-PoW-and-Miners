package blockchain;

import java.io.Serializable;
import java.util.ArrayList;


class BlockChain implements Serializable {
    private static final long serialVersionUID = 9L;
    ArrayList<Block> blocks = new ArrayList<>();
    private int zeroesInHash;

    public int getZeroesInHash() {
        return zeroesInHash;
    }

    public int size() {
        return blocks.size();
    }

    BlockChain(int zeroesInHash) {
        this.zeroesInHash = zeroesInHash;
    }


    Block getBlockBy(int id) {
        return blocks.get(id - 1);
    }

    Block getLastBlock() {
        return (size() > 0) ? blocks.get(size() - 1) : null;
    }

    void add(Block block) {
        if (canAdd(block)) {
            blocks.add(block);
            adjustZeroesInHash();
        }
    }

    void adjustZeroesInHash() {
        long elapsedTime = Stopwatch.getElapsedSeconds();
        printGenerationTimeMsg(elapsedTime);
        if (elapsedTime > 60) {
            zeroesInHash -= 1;
            System.out.printf("N was decreased to %d\n\n", zeroesInHash);
        } else if (elapsedTime < 10) {
            zeroesInHash += 1;
            System.out.printf("N was increased to %d\n\n", zeroesInHash);
        } else
            System.out.println("N stays the same\n\n");
    }

    private static void printGenerationTimeMsg(long sec) {
        System.out.printf("Block was generating for %d seconds\n", sec);
    }

    boolean canAdd(Block block) {
        return block.hasValid(zeroesInHash) & canHaveValid(block);
    }


    boolean canHaveValid(Block block) {
        int id = block.id;
        boolean isValid = true;
        if (id > 1) {
            String previousHash = block.previousHash;
            Block previousBlock = getBlockBy(id - 1);
            String hash = previousBlock.generateHash();
            if (!hash.equals(previousHash)) {
                isValid = false;
            }
        }
        return isValid;
    }


    boolean isValid() {
        boolean chainIsValid = true;
        for (Block block : blocks) {
            if (!canHaveValid(block)) {
                chainIsValid = false;
                break;
            }
        }
        return chainIsValid;
    }

    void printAllBlock() {
        for (Block block : blocks
        ) {
            block.printInfo();
            System.out.println();
        }
    }

}