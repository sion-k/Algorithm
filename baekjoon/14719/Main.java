import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] S = new int[M];
		st = new StringTokenizer(br.readLine(), " ");
		for (int j = 0; j < M; j++)
			S[j] = Integer.parseInt(st.nextToken());
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			boolean wall = false; int length = 0;
			for (int j = 0; j < M; j++) {
				if (S[j] >= i) {
					if (!wall) {wall = true;}
					else {sum += length; length = 0;}
				} else if (!wall) {continue;}
				else {length++;}
			}
		}
		System.out.println(sum);
	}

}
