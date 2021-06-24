import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] S = new int[3][2];
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			S[i][0] = Integer.parseInt(st.nextToken());
			S[i][1] = Integer.parseInt(st.nextToken());
		}
		for (int k = 0; k < 2; k++) {
			for (int i = 0; i < 3; i++) {
				boolean unique = true;
				for (int j = 0; j < 3; j++) {
					if (i == j) continue;
					if (S[i][k] == S[j][k]) unique = false;
				}
				if (unique) {
					System.out.print(S[i][k]);
					if (k != 1) System.out.print(" ");
				}
			}
		}
		System.out.println();
	}
	
}
