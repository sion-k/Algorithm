import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		long sum = 0;
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(br.readLine());
			pq.offer(-temp);
			sum += temp;
		}
		br.close();
		for (int i = 0; i < N / 3; i++) {
			pq.poll(); pq.poll(); sum += pq.poll();
		}
		System.out.println(sum);
	}

}
