package codeforce.r1352;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			if (N % 2 == 1 && K % 2 == 0) {
				bw.write("NO"); bw.newLine(); continue;
			}
			// N이 짝수일 때
			if (N % 2 == 0) {
				// K가 짝수면 1로 최대한 표현
				if (K % 2 == 0) {
					if (N < K) {bw.write("NO"); bw.newLine(); continue;}
					bw.write("YES"); bw.newLine();
					while (N > 0 && K != 1) {
						bw.write("1 ");
						N--;
						K--;
					}
					if (N > 0) {bw.write(String.valueOf(N));}
				// K가 홀수면 2로 최대한 표현
				} else {
					if (N < 2 * K) {bw.write("NO"); bw.newLine(); continue;}
					bw.write("YES"); bw.newLine();
					while (N >= 2 && K != 1) {
						bw.write("2 ");
						N -= 2;
						K--;
					}
					if (N > 0) {bw.write(String.valueOf(N));}
				}
			// N이 홀수 일 때
			} else {
				// K는 무조건 홀수임
				if (N < K) {bw.write("NO"); bw.newLine(); continue;}
				bw.write("YES"); bw.newLine();
				while (N > 0 && K != 1) {
					bw.write("1 ");
					N--;
					K--;
				}
				if (N > 0) {bw.write(String.valueOf(N));}
			}
			bw.newLine();
		}
		bw.close();
	}

}