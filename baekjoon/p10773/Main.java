package baekjoon.p10773;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		long sum = 0;
		Stack<Integer> s = new Stack<>();
		for (int i = 0; i < K; i++) {
			int read = Integer.parseInt(br.readLine());
			sum += read;
			if (read != 0) {s.push(read);}
			else {sum -= s.pop();}
		}
		System.out.println(sum);
		br.close();
		
	}

}