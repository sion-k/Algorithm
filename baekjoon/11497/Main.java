import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			int[] L = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				L[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(L);
			int max = Math.max(L[N - 1] - L[N - 2], L[N - 1] - L[N - 3]);
			int index = N - 4;
			while (index >= 0) {
				max = Math.max(max, L[index + 2] - L[index]);
				index--;
			}
			max = Math.max(max, L[1] - L[0]);
			bw.write(String.valueOf(max));
			bw.newLine();	
		}
		br.close();
		bw.close();
	}

}
