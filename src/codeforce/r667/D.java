package codeforce.r667;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			char[] n = new char[19]; Arrays.fill(n, ' ');
			String str = st.nextToken();
			for (int i = 0, j = 19 - str.length(); i < str.length(); i++, j++) {
				n[j] = str.charAt(i);
			}
			int s = Integer.parseInt(st.nextToken());
			int digit = 0;
			for (int i = 0; i < n.length; i++) {digit += (n[i] - '0');}
			int p = 18;
			while (n[p] != ' ' && digit > s) {
				digit -= (n[p] - '0');
				n[p] = '0'; p--;
				if (n[p - 1] == ' ') {n[p - 1] = '1'; digit++;}
				System.out.println(digit);
			}
			System.out.println(Arrays.toString(n));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 19; i++) {sb.append(n[i]);}
			long start = Long.parseLong(str);
			long end = Long.parseLong(sb.toString().trim());
			bw.write(String.valueOf(end - start));
			bw.newLine();
		}
		bw.close();
	}

}
