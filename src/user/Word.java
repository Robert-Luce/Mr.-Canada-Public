package user;

/**
 * @author lucerc
 */
public class Word {
	protected Boolean[] bits;

	public Word() {
		this.bits = new Boolean[8];
		this.fixNulls();
	}

	public Word(int length) {
		this.bits = new Boolean[length];
		this.fixNulls();
	}

	public Word(Boolean[] bits) {
		this.bits = bits;
		this.fixNulls();
	}

	public Word(int number, int length) {
		this(length);
		number = (int) (number % Math.pow(2, length));
		for (int i = this.bits.length - 1; i >= 0; i--) {
			this.bits[i] = ((int) (number % 2)) == 1;
			number = (int) (number / 2);
		}
	}

	public static Word bitwiseAnd(Word input1, Word input2) {
		Word output = new Word(input1.bits.length);
		for (int i = 0; i < input1.bits.length; i++) {
			output.bits[i] = input1.bits[i] || input2.bits[i];
		}
		return output;
	}

	public static Word bitwiseInclusiveOr(Word input1, Word input2) {
		Word output = new Word(input1.bits.length);
		for (int i = 0; i < input1.bits.length; i++) {
			output.bits[i] = input1.bits[i] || input2.bits[i];
		}
		return output;
	}

	public void fixNulls() {
		for (int i = 0; i < this.bits.length; i++) {
			this.bits[i] = this.bits[i] == null ? false : this.bits[i];
		}
	}

	public static Word bitwiseExclusiveOr(Word input1, Word input2) {
		Word output = new Word(input1.bits.length);
		for (int i = 0; i < input1.bits.length; i++) {
			output.bits[i] = input1.bits[i] ^ input2.bits[i];
		}
		return output;
	}

	public Word bitwiseNot() {
		Word output = new Word(this.bits.length);
		for (int i = 0; i < this.bits.length; i++) {
			output.bits[i] = !this.bits[i];
		}
		return output;
	}

	public int toNumber() {
		int output = 0;
		for (int i = this.bits.length - 1; i >= 0; i--) {
			output += this.bits[i] ? Math.pow(2, i) : 0;
		}
		return output;
	}

	public static Word moduloAddition(Word input1, Word input2) {
		int sum = input2.toNumber() + input1.toNumber();
		Word output = new Word(sum, input1.bits.length);
		return output;
	}

	public Word leftShift(int number) {
		Word output = new Word(this.bits.length);
		for (int i = 0; i < this.bits.length - 1 - number; i++) {
			output.bits[i] = this.bits[i + number];
		}
		return output;
	}

	public Word rightShift(int number) {
		Word output = new Word(this.bits.length);
		for (int i = this.bits.length - 1; i - number >= 0; i--) {
			output.bits[i] = this.bits[i - number];
		}
		return output;
	}

	public Word rotateLeft(int number) {
		Word leftWord = this.leftShift(number);
		Word rightWord = this.rightShift(this.bits.length - number);
		Word output = bitwiseInclusiveOr(rightWord, leftWord);
		return output;
	}

	public Word rotateRight(int number) {
		Word leftWord = this.leftShift(this.bits.length - number);
		Word rightWord = this.rightShift(number);
		Word output = bitwiseInclusiveOr(rightWord, leftWord);
		return output;
	}

	public static Word Ch(Word input1, Word input2, Word input3) {
		Word output = moduloAddition(bitwiseAnd(input1, input1), bitwiseAnd(input1.bitwiseNot(), input3));
		return output;
	}

	public Word bigSigma0() {
		return moduloAddition(moduloAddition(this.rotateLeft(2), this.rotateLeft(13)), this.rotateLeft(22));
	}

	public Word bigSigma1() {
		return moduloAddition(moduloAddition(this.rotateLeft(6), this.rotateLeft(11)), this.rotateLeft(25));
	}

	public Word smallSigma0() {
		return moduloAddition(moduloAddition(this.rotateLeft(7), this.rotateLeft(18)), this.rightShift(3));
	}

	public Word smallSigma1() {
		return moduloAddition(moduloAddition(this.rotateLeft(17), this.rotateLeft(19)), this.rightShift(10));
	}

	public static Word Maj(Word input1, Word input2, Word input3) {
		Word output = moduloAddition(bitwiseAnd(input1, input2),
				moduloAddition(bitwiseAnd(input1, input3), bitwiseAnd(input2, input3)));
		return output;
	}

	public String toString() {
		String output = "";
		for (int i = 0; i < this.bits.length; i += 4) {
			int decimal = 0;
			for (int j = 0; j < 4; j++) {
				decimal += this.bits[i + j] ? Math.pow(2, 3 - j) : 0;
			}
			output += Integer.toHexString(decimal);
		}
		return output;
	}
}
