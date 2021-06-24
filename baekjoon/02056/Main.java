import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int[] T;
	static int[] cache;
	static ArrayList<ArrayList<Integer>> adj;

	static int dp(int i) {
		if (cache[i] != 0) {return cache[i];}
		int max = 0;
		for (int there : adj.get(i)) {max = Math.max(max, dp(there));}
		return cache[i] = max + T[i];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		T = new int[N + 1];
		cache = new int[N + 1];
		adj = new ArrayList<>(N + 1);
		adj.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {adj.add(new ArrayList<>());}

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			T[i] = Integer.parseInt(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			for (int j = 0; j < P; j++) {
				adj.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		cache[1] = T[1];
		int max = 0;
		for (int i = 1; i <= N; i++) {max = Math.max(max, dp(i));}
		System.out.println(max);
	}

}
