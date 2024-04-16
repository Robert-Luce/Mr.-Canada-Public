package hashingAlgorithm;

public class Constant extends Word {
	private final static double[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
			73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181,
			191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307,
			311 };

	public Constant(int index) {
		super(getNumber(index), 32);
	}

	private static int getNumber(int index) {
		double prime = Constant.primes[index];
		double cubedRoot = Math.cbrt(prime);
		double fractionalPart = cubedRoot % 1.0;
		int number = (int) (fractionalPart * Math.pow(2, 32));
		return number;
	}
}