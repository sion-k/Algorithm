package baekjoon.p10828;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<Integer> s = new Stack<Integer>();

		for (int i = 0; i < N; i++) {
			String[] com = br.readLine().split(" ");
			if (com.length == 2) {
				s.push(Integer.parseInt(com[1]));
				continue;
			}
			if (com[0].equals("pop")){
				bw.write((s.size() == 0 ? -1 : s.pop()) +"");
				bw.newLine();
				continue;
			}
			if (com[0].equals("size")){
				bw.write(s.size() + "");
				bw.newLine();
				continue;
			}
			if (com[0].equals("empty")){
				bw.write((s.isEmpty() ? 1 : 0) + "");
				bw.newLine();
				continue;
			}
			if (com[0].equals("top")){
				bw.write((s.size() == 0 ? -1 : s.peek()) + "");
				bw.newLine();
				continue;
			}
		}
		br.close();
		bw.close();
	}
}
