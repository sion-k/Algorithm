import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		Node root = new Node("");
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int K = Integer.parseInt(st.nextToken());
			String[] S = new String[K];
			for (int j = 0; j < K; j++) S[j] = st.nextToken();
			root.insert(-1, S);
		}
		root.print(0, bw);
		System.out.print(bw);
	}

}
class Node implements Comparable<Node> {
	String name;
	PriorityQueue<Node> children;
	
	Node(String n) { name = n; children = new PriorityQueue<>(); }
	
	// 이 노드를 루트로 하는 서브트리에 S[i + 1:]을 삽입
	void insert(int i, String[] S) {
		if (i == S.length - 1) return;
		for (Node ch : children) if (ch.name.equals(S[i + 1])) {
			ch.insert(i + 1, S);
			return;
		}
		Node t = new Node(S[i + 1]);
		t.insert(i + 1, S);
		children.offer(t);
	}
	
	void print(int i, StringBuilder bw) {
		while (!children.isEmpty()) {
			for (int t = 0; t < i; t++) bw.append("--");
			Node ch = children.poll();
			bw.append(ch).append("\n");
			ch.print(i + 1, bw);
		}
	}
	
	@Override
	public String toString() { return name; }
	
	@Override
	public int compareTo(Node o) { return name.compareTo(o.name); }
	
}
