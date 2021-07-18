import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<ArrayList<Integer>> adj = new ArrayList<>(N + 1);
		for (int i = 0; i <= N; i++) adj.add(new ArrayList<>());
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			adj.get(u).add(v); adj.get(v).add(u);
		}
		long D = 0; long G = 0;
		for (int here = 1; here <= N; here++) {
			long u = adj.get(here).size();
			for (int there : adj.get(here)) if (here < there)
				D += (u - 1) * (adj.get(there).size() - 1);
			G += u * (u - 1) * (u - 2) / 6;
		}
		String ret = "DUDUDUNGA";
		if (D > 3 * G) ret = "D";
		if (D < 3 * G) ret = "G";
		System.out.println(ret);
	}

}
