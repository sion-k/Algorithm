package codeforce.r693;

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
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] set = new int[3];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++)
				set[Integer.parseInt(st.nextToken())]++;
			int sum = set[1] + 2 * set[2];
			// 2의 개수가 홀수인 경우 1이 2개 미만이면 불가
			if (sum % 2 != 0 || set[1] % 2 != 0 || (set[2] % 2 == 1 && set[1] < 2)) {
				bw.write("NO");
				bw.newLine();
				continue;
			}
			bw.write("YES");
			bw.newLine();
		}
		bw.close();
	}

}