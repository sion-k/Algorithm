import java.io.*;
import java.util.*;

public class C {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] L = new int[N + 1]; int[] R = new int[N + 1];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if (u > v) { int temp = u; u = v; v = temp; }
			R[u]++;
			L[v]++;
		}
		int cnt = 0;
		for (int i = 1; i <= N; i++) if (R[i] == 0) cnt++;
		int Q = Integer.parseInt(br.readLine());
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int o = Integer.parseInt(st.nextToken());
			switch (o) {
			case 1:
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				if (u > v) { int temp = u; u = v; v = temp; }
				if (R[u] == 0) cnt--;
				R[u]++;
				L[v]++;
				break;
			case 2:
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				if (u > v) { int temp = u; u = v; v = temp; }
				R[u]--;
				L[v]--;
				if (R[u] == 0) cnt++;
				break;
			case 3:
				bw.append(cnt).append("\n");
				break;
			}
		}
		System.out.print(bw);
	}

}