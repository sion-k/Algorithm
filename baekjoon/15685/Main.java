import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static boolean[][] P = new boolean[101][101];
	
	static final int[] dy = {-1, 1, 0, 0};
	static final int[] dx = {0, 0, -1, 1};
	static final int[] clockwise = {2, 3, 1, 0}; // 상하좌우 방향을 시계방향으로 회전
	
	static List<List<Integer>> curve = new ArrayList<>();
	
	// (y, x)위치 d방향에서 g세대 드래곤 커브를 그린다
	static void f(int y, int x, int d, int g) {
		P[y][x] = true;
		for (int i = 0; i < (1 << g); i++) {
			y += dy[curve.get(d).get(i)];
			x += dx[curve.get(d).get(i)];
			P[y][x] = true;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int d = 0; d < 4; d++) {
			List<Integer> temp = new ArrayList<>();
			temp.add(d); // 0세대 드래곤 커브 (오른쪽 방향 기준)
			for (int g = 1; g <= 10; g++)
				for (int i = temp.size() - 1; i >= 0; i--) 
					temp.add(clockwise[temp.get(i)]);
			curve.add(temp);
		}
		
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = new int[] { 3, 0, 2, 1 }[Integer.parseInt(st.nextToken())];
			int g = Integer.parseInt(st.nextToken());
			f(y, x, d, g);
		}
		int cnt = 0;
		for (int i = 0; i < 100; i++) 
			for (int j = 0; j < 100; j++) 
				if (P[i][j] && P[i + 1][j] && P[i][j + 1] && P[i + 1][j + 1]) cnt++;
		System.out.println(cnt);
	}
	
}
