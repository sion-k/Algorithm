package competition.kakao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class C {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> right = new PriorityQueue<>();
		for (int i = 0; i <= K; i++) left.offer(i);
		for (int i = K + 1; i < N; i++) right.offer(i);
		Stack<Integer> deleted = new Stack<>();
		for (int c = 0; c < M; c++) {
			st = new StringTokenizer(br.readLine(), " ");
            char op = st.nextToken().charAt(0);
            switch (op) {
            case 'U':
                int X = Integer.parseInt(st.nextToken());
                for (int i = 0; i < X; i++) right.offer(left.poll());
                break;
            case 'D':
                X = Integer.parseInt(st.nextToken());
                for (int i = 0; i < X; i++) left.offer(right.poll());
                break;
            case 'C':
                deleted.push(left.poll());
                if (!right.isEmpty()) left.offer(right.poll());
                break;
            case 'Z':
                int restore = deleted.pop();
                if (restore < left.peek()) left.offer(restore);
                else right.offer(restore);
                break;
            }
            System.out.println(left.peek());
		}
		boolean[] last = new boolean[N];
		while (!left.isEmpty()) last[left.poll()] = true;
		while (!right.isEmpty()) last[right.poll()] = true;
		StringBuilder answer = new StringBuilder();
		for (int i = 0; i < N; i++)
			answer.append(last[i] ? "O" : "X");
		System.out.println(answer);
	}
	
}