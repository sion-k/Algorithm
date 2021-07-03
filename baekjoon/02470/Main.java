import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
        int N = in.nextInt();
		int[] S = new int[N];
		for (int i = 0; i < N; ++i) S[i] = in.nextInt();
		Arrays.sort(S);
		int head = 0; int tail = N - 1;
		int abs = Math.abs(S[head] + S[tail]);
		int min = abs; int p = 0; int q = N - 1;
		while (head < tail) {
			// S[head]와 더했을 때 가장 0에 가까운 S[tail]까지 이동시킨다
			while (head < tail - 1 &&
			Math.abs(S[head] + S[tail - 1]) < abs) {
				tail--;
				abs = Math.abs(S[head] + S[tail]);
			}
			if (min > abs) {
				min = abs;
				p = head; q = tail;
			}
			head++;
			abs = Math.abs(S[head] + S[tail]);
		}
		System.out.printf("%d %d\n", S[p], S[q]);
	}

}

class InputReader {

	private final InputStream stream;
	private final byte[] buf = new byte[8192];
	private int curChar, snumChars;

	public InputReader(InputStream st) {
		this.stream = st;
	}

	public int read() {
		if (snumChars == -1)
			throw new InputMismatchException();
		if (curChar >= snumChars) {
			curChar = 0;
			try {
				snumChars = stream.read(buf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (snumChars <= 0)
				return -1;
		}
		return buf[curChar++];
	}

	public int nextInt() {
		int c = read();
		while (isSpaceChar(c)) {
			c = read();
		}
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		int res = 0;
		do {
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public long nextLong() {
		int c = read();
		while (isSpaceChar(c)) {
			c = read();
		}
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
		}
		long res = 0;
		do {
			res *= 10;
			res += c - '0';
			c = read();
		} while (!isSpaceChar(c));
		return res * sgn;
	}

	public int[] nextIntArray(int n) {
		int a[] = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = nextInt();
		}
		return a;
	}

	public String readString() {
		int c = read();
		while (isSpaceChar(c)) {
			c = read();
		}
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}

	public String nextLine() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isEndOfLine(c));
		return res.toString();
	}

	public boolean isSpaceChar(int c) {
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	}

	private boolean isEndOfLine(int c) {
		return c == '\n' || c == '\r' || c == -1;
	}
}