import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static String A;
	static String B;
	static int[][] cache;

	// A[i, ] B[j, ]의 LCS반환
	static int dp(int i, int j) {
		if (i >= A.length() || j >= B.length()) {return 0;}
		if (cache[i][j] != -1) {return cache[i][j];}
		if (A.charAt(i) == B.charAt(j)) {
			return cache[i][j] = 1 + dp(i + 1, j + 1);
		}
		return cache[i][j] = Math.max(dp(i + 1, j), dp(i , j + 1));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = br.readLine(); B = br.readLine();
		cache = new int[A.length()][B.length()];
		for (int i = 0; i < A.length(); i++) {Arrays.fill(cache[i], -1);}
		System.out.println(dp(0, 0));
	}

}
