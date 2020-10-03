package baekjoon.p01001;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		br.close();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write((Integer.parseInt(st.nextToken()) - Integer.parseInt(st.nextToken())) +"");
		bw.newLine();
		bw.close();
	}

}
