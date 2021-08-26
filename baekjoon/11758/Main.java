import java.io.*;
import java.util.*;

public class Main {

	static int cross(int x1, int y1, int x2, int y2) {
		return x1 * y2 - x2 * y1;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int x3 = Integer.parseInt(st.nextToken());
		int y3 = Integer.parseInt(st.nextToken());
		int c = cross(x2 - x1, y2 - y1, x3 - x2, y3 - y2);
		if (c > 0) c = 1;
		if (c < 0) c = -1;
		System.out.println(c);
	}

}
