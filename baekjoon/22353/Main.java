import java.io.*;
import java.util.*;

public class Main {
	static int a;
	static double k;
	static double[][] cache = new double[101][100];
	
	static double dp(int i, double d) {
		if (d >= 1) return i * a;
		return d * i * a + (1 - d) * dp(i + 1, d + d * k);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		a = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken()) / 100.;
		System.out.printf("%.7f\n", dp(1, d / 100.));
	}

}
