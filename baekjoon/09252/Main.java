import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	static String A; static String B;
	static int[][] cache;

	// A[i, ] B[j, ]의 LCS반환
	static int dp(int i, int j) {
		if (i == A.length() || j == B.length()) {return 0;}
		if (cache[i][j] != -1) {return cache[i][j];}
		if (A.charAt(i) == B.charAt(j)) {
			return cache[i][j] = 1 + dp(i + 1, j + 1);
		}
		return cache[i][j] = Math.max(dp(i + 1, j), dp(i, j + 1));
	}

	static StringBuilder ans = new StringBuilder();

	static void reconstruct(int i, int j) {
		if (i == A.length() || j == B.length()) {return;}
		if (A.charAt(i) == B.charAt(j)) {
			ans.append(A.charAt(i)); reconstruct(i + 1, j + 1); return;
		}
		if (dp(i, j) == dp(i + 1, j)) {reconstruct(i + 1, j);}
		else {reconstruct(i, j + 1);}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		A = br.readLine(); B = br.readLine();
		cache = new int[A.length()][B.length()];
		for (int i = 0; i < A.length(); i++) {Arrays.fill(cache[i], -1);}
		bw.write(String.valueOf(dp(0, 0)));
		bw.newLine();
		reconstruct(0, 0);
		bw.write(ans.toString());
		bw.close();
	}

}
