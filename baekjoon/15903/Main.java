import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		br.close();
		PriorityQueue<Long> queue = new PriorityQueue<>();
		long sum = 0;
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(st.nextToken());
			sum += temp;
			queue.offer((long)temp);
		}
		
		for (int i = 0; i < M; i++) {
			long comb = queue.poll() + queue.poll();
			sum += comb;
			queue.offer(comb);
			queue.offer(comb);
		}
		
		System.out.println(sum);
	}

}
