package baekjoon.p16433;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char carrot = 'v'; char field = '.';
		if ((R % 2 == 1 && C % 2 == 0) || (R % 2 == 0 && C % 2 == 1)) {
			char temp = carrot;
			carrot = field;
			field = temp;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i % 2 == j % 2) {bw.write(carrot);}
				else {bw.write(field);}
			}
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}