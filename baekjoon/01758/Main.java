import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		ArrayList<Integer> tip = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			tip.add(Integer.parseInt(br.readLine()));
		}
		br.close();
		Collections.sort(tip);
		long sum = 0; int rank = 1;
		for (int i = N - 1; i >= 0; i--) {
			int temp = (tip.get(i) - (rank - 1));
			if (temp > 0) {
				sum += temp;
			}
			rank++;
		}
		System.out.println(sum);
	}

}
