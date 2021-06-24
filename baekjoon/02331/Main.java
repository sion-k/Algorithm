import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int P;
	static int DUP;
	static boolean[] VISIT = new boolean[1000000];

	static int next(int s) {
		int sum = 0;
		while (s != 0) {
			sum += Math.pow((s % 10), P);
			s = s / 10;
		}
		return sum;
	}

	static int DFS(int here) {
		VISIT[here] = true;
		int next = next(here);
		if(VISIT[next]) {DUP = next; return 1;}
		return 1 + DFS(next);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		DFS(A); VISIT = new boolean[1000000];
		VISIT[DUP] = true;
		if (VISIT[A]) {System.out.println(0);}
		else {System.out.println(DFS(A));}
	}

}
