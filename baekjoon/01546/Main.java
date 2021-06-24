import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int max = 1;
		
		int[] score = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		br.close();
		for (int i = 0; i < N; i++) {
			score[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, score[i]);
		}
		
		double[] newScore = new double[N];
		double sum = 0;
		for (int i = 0; i < N; i++) {
			newScore[i] = (double)score[i] / max * 100;
			sum += newScore[i];
		}
		System.out.println(sum / N);
		
	}

}
