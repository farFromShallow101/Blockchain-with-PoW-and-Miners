package blockchain;

import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int zeroesInHash = 0;
        BlockChainFactory chainFactory = BlockChainFactory.getInstance();
        BlockChain blockChain = chainFactory.generateChainSizeOf(10, zeroesInHash);
    }
}