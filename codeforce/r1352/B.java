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
			// N�� ¦���� ��
			if (N % 2 == 0) {
				// K�� ¦���� 1�� �ִ��� ǥ��
				if (K % 2 == 0) {
					if (N < K) {bw.write("NO"); bw.newLine(); continue;}
					bw.write("YES"); bw.newLine();
					while (N > 0 && K != 1) {
						bw.write("1 ");
						N--;
						K--;
					}
					if (N > 0) {bw.write(String.valueOf(N));}
				// K�� Ȧ���� 2�� �ִ��� ǥ��
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
			// N�� Ȧ�� �� ��
			} else {
				// K�� ������ Ȧ����
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