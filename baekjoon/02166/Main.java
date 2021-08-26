import java.io.*;
import java.util.*;

public class Main {

	static long cross(long x1, long y1, long x2, long y2) {
		return x1 * y2 - x2 * y1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int x0 = Integer.parseInt(st.nextToken());
		int y0 = Integer.parseInt(st.nextToken());
		int[] x = new int[N];
		int[] y = new int[N];
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x[i] = Integer.parseInt(st.nextToken());
			y[i] = Integer.parseInt(st.nextToken());
		}
		long area = 0;
		for (int i = 1; i < N - 1; i++)
			area += cross(x[i] - x0, y[i] - y0, x[i + 1] - x0, y[i + 1] - y0);
		System.out.printf("%.1f\n", (double)Math.abs(area) / 2);
	}

}
