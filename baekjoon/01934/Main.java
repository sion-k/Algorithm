import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int gcd(int a, int b) {
		if (b == 0) {return a;}
		return gcd(b, a % b);
	}

	static long lcm(int a, int b) {
		return (long)a * b / gcd(a, b);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		//int T = Integer.parseInt(br.readLine());
		//for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			System.out.println(lcm(A, B));
		//}
		bw.close();
	}

}
