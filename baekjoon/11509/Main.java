import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] S = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			S[i] = Integer.parseInt(st.nextToken());
		}
		int[] A = new int[1000001];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (A[S[i]] == 0) {
				cnt++; A[S[i] - 1]++;
			} else {
				A[S[i]]--; A[S[i] - 1]++;
			}
		}
		System.out.println(cnt);
	}

}
