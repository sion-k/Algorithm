package baekjoon.p01406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		LinkedList ls = new LinkedList(br.readLine());
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String com = st.nextToken();
			if (com.equals("L")) {
				ls.left();
			}
			if (com.equals("D")) {
				ls.right();
			}
			if (com.equals("B")) {
				ls.backspace();
			}
			if (com.equals("P")) {
				ls.plus(st.nextToken().charAt(0));
			}
		}
		br.close();
		System.out.println(ls);
	}
}

class LinkedList {
	class Node {
		char data;
		Node prev; Node next;
		public Node(char d, Node p, Node n) {
			data = d; prev = p; next = n;
		}
	}
	Node first; Node last;
	Node cursor; // 초기에 커서는 마지막 원소 다음 원소(last)를 가리키고 있음
	
	// 커서를 왼쪽으로 옮김 가능한지 여부 반환
	boolean left() {
		if (cursor.prev != first) {cursor = cursor.prev; return true;}
		return false;
	}
	
	boolean right() {
		if (cursor != last) {cursor = cursor.next; return true;}
		return false;
	}
	void backspace() {
		if (left()) {
			Node toMove = cursor.next;
			cursor.prev.next = cursor.next;
			cursor.next.prev = cursor.prev;
			cursor = toMove;
		}
	}
	void plus(char data) {
		Node p = new Node(data, cursor.prev, cursor);
		p.prev.next = p; p.next.prev = p;
	}
	public LinkedList(String str) {
		first = new Node('F', null, last);
		last = new Node('L', first, null);
		cursor = last;
		for (int i = 0; i < str.length(); i++) {plus(str.charAt(i));}
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Node p = first.next; p != last; p = p.next) {
			sb.append(p.data);
		}
		return sb.toString();
	}
}