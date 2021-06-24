import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int dist(int N) {
		int ret = 1;
		int sum = 2;
		int d = 6;
		while (N >= sum) {
			ret++;
			sum += d;
			d += 6;
		}
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println(dist(N));
	}

}
