# Blockchain-with-PoW-and-Miners
Generates a blockchain with 10 blocks<br>
Implements Proof of Work consensus algorithm<br>
Uses multithreading to make mining easier<br>
Uses serialization to check if the blockchain exists on the hard drive beforehand and checks for its validity<br>
Each block hash has a different number of prefix zeroes. The prefix zeroes in the block hashes are matched using a random magic number in each block<br>
The generation of this "magic number" can take quite some time for longer prefix lengths. Whenever the generation process takes too much time/too less time, then the blockchain regulates the required prefix length by increasing it or by decreasing it resp.
