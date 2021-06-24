import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] U = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		br.close();
		for (int i = 0; i < N; i++) {
			U[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(U);
		
		int sum = 0;
		for (int i = 0; i < N; i++) {
			if(U[i] - sum > 1) {break;}
			sum += U[i];
		}
		System.out.println(sum + 1);
	}

}
