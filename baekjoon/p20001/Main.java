package baekjoon.p20001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		Stack<String> s = new Stack<>();
		while(!(line = br.readLine()).equals("고무오리 디버깅 끝")) {
			if(line.equals("고무오리")) {
				if(s.isEmpty()) {
					s.push("문제");s.push("문제");
				} else {
					s.pop();
				}
			} else if(line.equals("문제")){
				s.push("문제");
			}
		}
		br.close();
		if(s.isEmpty()) {
			System.out.println("고무오리야 사랑해");
		} else {
			System.out.println("힝구");
		}
	}

}
