import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final int r = 31;
	static final int M = 1234567891;
	static int[] pow = new int[50];

	static {
		pow[0] = 1;
		long ret = 1;
		for (int i = 1; i < 50; i++) {
			ret = (ret * r) % M;
			pow[i] = (int)ret;
		}
	}

	static int hashCode(String S) {
		long ret = 0;
		for (int i = 0; i < S.length(); i++) {
			ret = (ret + (S.charAt(i) - 'a' + 1) * (long)pow[i]) % M;
		}
		return (int)ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		System.out.println(hashCode(br.readLine()));
	}

}
