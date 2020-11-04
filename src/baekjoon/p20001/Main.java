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
		while(!(line = br.readLine()).equals("������ ����� ��")) {
			if(line.equals("������")) {
				if(s.isEmpty()) {
					s.push("����");s.push("����");
				} else {
					s.pop();
				}
			} else if(line.equals("����")){
				s.push("����");
			}
		}
		br.close();
		if(s.isEmpty()) {
			System.out.println("�������� �����");
		} else {
			System.out.println("����");
		}
	}

}
