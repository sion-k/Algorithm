package baekjoon.p02812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		st.nextToken();
		int K = Integer.parseInt(st.nextToken());
		LinkedList L = new LinkedList(br.readLine());
		br.close();
		L.p = L.first.next;
		while (L.p != L.first && L.p != L.last && K > 0) {
			if (L.p.data < L.p.next.data) {
				L.remove(); K--;
				if (L.p != L.first.next) {
					L.p = L.p.prev;
				}
			} else {
				L.p = L.p.next;
			}
		}
		L.p = L.last.prev;
		while (L.p != L.first && K > 0) {
			L.remove(); K--;
			L.p = L.p.prev;
		}
		System.out.println(L);
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
	Node p; // 초기에 포인터는 마지막 원소 다음 원소(last)를 가리키고 있음
	
	// 포인터 위치 원소 삭제 (땡겨짐)
	void remove() {
		if (p != first && p != last) {
			Node toMove = p.next;
			p.prev.next = p.next;
			p.next.prev = p.prev;
			p = toMove;
		}
	}
	// 포인터 위치 원소 추가 (뒤로 밀려남)
	void add(char data) {
		Node temp = new Node(data, p.prev, p);
		temp.prev.next = temp; temp.next.prev = temp;
	}
	public LinkedList(String str) {
		first = new Node('F', null, last);
		last = new Node('L', first, null);
		p = last;
		for (int i = 0; i < str.length(); i++) {
			add(str.charAt(i));
		}
	}
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Node p = first.next; p != last; p = p.next) {
			sb.append(p.data -'0');
		}
		return sb.toString();
	}
}