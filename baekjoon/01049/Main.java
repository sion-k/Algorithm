import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int minSet = 1000; int minPiece = 1000;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			minSet = Math.min(minSet, Integer.parseInt(st.nextToken()));
			minPiece = Math.min(minPiece, Integer.parseInt(st.nextToken()));
		}
		br.close();
		int sum = 0;
		if (minSet >= 6 * minPiece) {sum = N * minPiece;}
		else {
			sum = (N / 6) * minSet + Math.min(minSet, (N % 6) * minPiece);
		}
		System.out.println(sum);
	}

}
