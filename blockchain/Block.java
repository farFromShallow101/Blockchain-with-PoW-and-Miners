package blockchain;

import java.io.Serializable;
import java.util.Date;

class Block implements Serializable {
    private static final long serialVersionUID = 8L;
    String previousHash;
    long timesStamp = new Date().getTime();
    int id;
    int magicNumber;
    int minerId;

    Block(String hash, int id) {
        this.previousHash = hash;
        this.id = id;
    }

    void printInfo() {
        String output = "Block:" +
                "\nCreated by miner # " + minerId +
                "\nId: " + id +
                "\nTimestamp: " + timesStamp +
                "\nMagic number: " + magicNumber +
                "\nHash of the previous block:\n" + previousHash +
                "\nHash of the block:\n" + generateHash();
        System.out.println(output);

    }

    boolean hasValid(int zeroesInHash) {
        String validHashRegex = "0{" + zeroesInHash + "}\\w*";
        String hash = generateHash();
        return hash.matches(validHashRegex);
    }

    String generateHash() {
        return StringUtil.applySha256(previousHash + id + minerId + timesStamp + magicNumber);
    }

    String generateHashUsing(int magicNumber) {
        return StringUtil.applySha256(previousHash + id + minerId + timesStamp + magicNumber);
    }
}