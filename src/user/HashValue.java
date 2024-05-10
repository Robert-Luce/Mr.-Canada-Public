package user;
/**
 * @author lucerc
 */
public class HashValue extends Word {
	private final static double[] primes = { 2, 3, 5, 7, 11, 13, 17, 19 };

	public HashValue(int index) {
		super(getNumber(index), 32);
	}

	public HashValue(Boolean[] bits) {
		super(bits);
	}

	private static int getNumber(int index) {
		double prime = primes[index];
		double cubedRoot = Math.sqrt(prime);
		double fractionalPart = cubedRoot % 1.0;
		int number = (int) (fractionalPart * Math.pow(2, 32));
		return number;
	}
}