import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int min = 1000000; int max = -1000000;
		for (int i = 0; i < N; i++) {
			int s = Integer.parseInt(st.nextToken());
			min = Math.min(min, s);
			max = Math.max(max, s);
		}
		br.close();
		System.out.println(min + " " + max);
	}

}
