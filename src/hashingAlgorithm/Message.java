package hashingAlgorithm;

import java.util.ArrayList;

public class Message {
	private ArrayList<Boolean> data = new ArrayList<Boolean>();
	private int messageBits;
	private int zeroBits;

	public Message(String input) {
		for (char letter : input.toCharArray()) {
			int asciiValue = (int) letter;
			boolean[] bits = new boolean[8];
			for (int i = bits.length - 1; i >= 0; i--) {
				bits[i] = (asciiValue & (1 << i)) != 0;
			}
			for (boolean bit : bits) {
				this.data.add(bit);
			}
		}
		this.messageBits = this.data.size();
		this.data.add(true);
		this.zeroBits = 448 - (this.messageBits + 1) % 512;
		for (int i = 0; i < this.zeroBits; i++) {
			this.data.add(false);
		}
		boolean[] endBits = new boolean[64];
		int endNumber = this.messageBits;
		for (int i = endBits.length - 1; i >= 0; i--) {
			endBits[i] = endNumber % 2 == 1;
			endNumber = endNumber / 2;
		}
		for (boolean bit : endBits) {
			this.data.add(bit);
		}
	}

	public ArrayList<Block> getBlocks() {
		ArrayList<Block> blocks = new ArrayList<Block>();
		for (int i = 0; i < this.data.size() / 512; i++) {
			Word[] words = new Word[16];
			for (int blockWords = 0; blockWords < 16; blockWords++) {
				Boolean[] bits = new Boolean[32];
				for (int wordBits = 0; wordBits < 32; wordBits++) {
					bits[wordBits] = this.data.get(i * 512 + blockWords * 32 + wordBits);
				}
				words[blockWords] = new Word(bits);
			}
			blocks.add(new Block(i));
			for (int blockWords = 0; blockWords < 16; blockWords++) {
				blocks.get(i).addWord(words[blockWords], blockWords);
			}
		}
		return blocks;
	}
}
