package blockchain;

import java.util.Random;
import java.util.concurrent.Callable;

import static java.lang.Thread.currentThread;

class BlockMiner implements Callable<Block> {
    BlockChain blockChain;
    int id;

    BlockMiner(BlockChain blockChain) {
        this.blockChain = blockChain;
    }


    public void setId(int id) {
        this.id = id;
    }

    public Block call() {
        return generateNextBlock();
    }

    Block generateNextBlock() {
        int chainSize = blockChain.size();
        String previousBlockHash;
        if (chainSize == 0) {
            previousBlockHash = "0";
        } else {
            Block previousBlock = blockChain.getLastBlock();
            previousBlockHash = previousBlock.generateHash();
        }
        int blockId = chainSize + 1;
        Block nextBlock = new Block(previousBlockHash, blockId);
        nextBlock.minerId = id;
        nextBlock.magicNumber = findMagicNumberFor(nextBlock);
        return nextBlock;
    }

    int findMagicNumberFor(Block block) {
        Random random = new Random(Integer.MAX_VALUE);
        String validHashRegex = String.format("0{%d}\\w*", blockChain.getZeroesInHash());
        int magicNumber;
        String hash;
        do {
            magicNumber = random.nextInt();
            hash = block.generateHashUsing(magicNumber);
        }
        while (!hash.matches(validHashRegex) & !currentThread().isInterrupted());
        return magicNumber;
    }
}