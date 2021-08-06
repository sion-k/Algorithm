import java.io.*;
import java.util.*;

public class Main {
	static long[] cache = new long[64];
	
	// 2^x
	static long pow(int x) {
		if (x == -1) return 0;
		if (x == 0) return 1;
		if (cache[x] != 0) return cache[x];
		if (x % 2 == 1) return cache[x] = 2 * pow(x - 1);
		return cache[x] = pow(x / 2) * pow(x / 2);
	}
	
	static long g(long x) {
		long ret = 0;
		for (int i = 0; pow(i) <= x; i++)
			ret += (pow(i) - pow(i - 1)) * (x / pow(i));
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		Long A = Long.parseLong(st.nextToken());
		Long B = Long.parseLong(st.nextToken());
		System.out.println(g(B) - g(A - 1));
	}

}
