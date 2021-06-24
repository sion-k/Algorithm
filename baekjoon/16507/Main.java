import java.io.*;
import java.util.*;

public class Main {
	static int[][] picture;
	static int[][] partSum;

	static int getMean(int r1, int c1, int r2, int c2) {
		if (r1 == r2 && c1 == c2) {
			return picture[r2][c2];
		}
		if (r1 == 0 && c1 == 0) {
			return partSum[r2][c2];
		}

		long sum = partSum[r2][c2] + partSum[r1 - 1][c1 - 1] - 
				   partSum[r1 - 1][c2] - partSum[r2][c1 - 1] ;
		
		return (int)(sum / ((r2 - r1 + 1) * (c2 - c1 + 1)));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		// picture의 0행은 0, 0열은 0으로 정의한다
		picture = new int[R + 1][C + 1];
		for (int i = 1; i < R + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < C + 1; j++) {
				picture[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		partSum = new int[R + 1][C + 1];
		
		int rowSum = 0;
		for (int i = 1; i < R + 1; i++) {
			rowSum = 0;
			for (int j = 1; j < C + 1; j++) {
				rowSum += picture[i][j];
				partSum[i][j] = partSum[i - 1][j] + rowSum;
			}
		}

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int c1 = Integer.parseInt(st.nextToken());
			int r1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			int r2 = Integer.parseInt(st.nextToken());
			bw.write(String.valueOf(getMean(c1, r1, c2, r2)));
			bw.newLine();
		}
		br.close();
		bw.close();

	}

}
