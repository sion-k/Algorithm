import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int F;
	static int U;
	static int D;
	static boolean[] BOOKED;
	static int[] DIS;
	
	static boolean inRange(int f) {return 1 <= f && f <= F;}
	
	static int bfs(int S, int G) {
		if (U == 0 && S < G) {return -1;} if (D == 0 && G < S) {return -1;}
		Queue<Integer> q = new LinkedList<>();
		q.add(S); BOOKED[S] = true; DIS[S] = 0;
		
		while(!q.isEmpty()) {
			int here =  q.poll();
			if (here == G) {return DIS[here];}
			int up = here + U; int down = here - D;
			if (inRange(up) && !BOOKED[up]) {
				q.offer(up); BOOKED[up] = true; DIS[up] = DIS[here] + 1;
			}
			if (inRange(down) && !BOOKED[down]) {
				q.offer(down); BOOKED[down] = true; DIS[down] = DIS[here] + 1;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		br.close();
		F = Integer.parseInt(st.nextToken());
		BOOKED = new boolean[F + 1];
		DIS = new int[F + 1];
		
		int S = Integer.parseInt(st.nextToken());
		int G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		int ret = bfs(S, G);
		if (ret == -1) {
			System.out.println("use the stairs");
		} else {
			System.out.println(ret);
		}
	}

}
