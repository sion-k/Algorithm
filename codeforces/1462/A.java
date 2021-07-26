package codeforce.r690;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class A {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			Deque<Integer> dq = new LinkedList<Integer>();
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++)
				dq.add(Integer.parseInt(st.nextToken()));
			StringBuilder ans = new StringBuilder();
			while (!dq.isEmpty()) {
				ans.append(dq.pollFirst());
				ans.append(" ");
				if (!dq.isEmpty()) {
					ans.append(dq.pollLast());
					ans.append(" ");
				}
			}
			bw.write(ans.toString().trim());
			bw.newLine();
		}
		bw.close();
	}

}