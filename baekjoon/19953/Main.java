import java.io.*;
import java.util.*;

public class Main {
	
	static final int[] dy = { 1, -1, 0, 0 };
	static final int[] dx = { 0, 0, -1, 1 };
	static final int[] dr = { 3, 2, 0, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int v = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken()) - 1;
		int x = 0; int y = v; int d = 3;
		v = (v * m) % 10; 
		int[] px = new int[5]; int[] py = new int[5];
		for (int i = 1; i <= 4; i++) {
			px[i] = v * dx[d]; py[i] = v * dy[d]; d = dr[d];
			px[i] += px[i - 1]; py[i] += py[i - 1];
			v = (v * m) % 10;
		}
		x += t / 4 * px[4]; y += t / 4 * py[4];
		x += px[t % 4]; y += py[t % 4];
		System.out.printf("%d %d\n", x, y);
	}

}
