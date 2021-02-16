package baekjoon.p05052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[] S = new String[N];
			TrieNode trie = new TrieNode();
			TrieNode.flag = true;
			// 트라이를 생성한다
			for (int i = 0; i < N; i++) {
				S[i] = br.readLine();
				trie.insert(S[i], 0);
			}
			// 모든 문자열에 대해 트라이에 검색하면서
			// 찾은 문자가 leaf인지 확인한다
			for (int i = 0; i < N; i++) {
				trie.find(S[i], 0);
				if (!TrieNode.flag) break;
			}
			System.out.println(TrieNode.flag ? "YES" : "NO");
		}
	}

}

class TrieNode {
	static final int ALPHABET = 10;

	static int toNumber(char ch) {return ch - '0';}

	static TrieNode[] children = new TrieNode[ALPHABET];

	boolean terminal; static boolean flag = true;

	// 이 노드를 루트로 하는 트라이에 문자열 key[i, ...]를 추가한다
	void insert(String key, int i) {
		// 문자열이 끝났다면 종료 노드로 설정한다
		if (i == key.length()) {
			terminal = true;
		} else {
			int next = toNumber(key.charAt(i));
			// 해당 자식 노드가 없다면 생성한다
			if (children[next] == null) {
				children[next] = new TrieNode();
			}
			// 해당 자식 노드로 재귀 호출
			children[next].insert(key, i + 1);
		}
	}

	// 이 노드를 루트로 하는 트라이에서 key[i, ...]와 대응되는 노드를 찾는다
	// 노드가 없다면 null을 반환한다
	TrieNode find(String key, int i) {
		if (i == key.length()) return this;
		int next = toNumber(key.charAt(i));
		if (this.terminal) flag = false;
		if (children[next] == null) return null;
		return children[next].find(key, i + 1);
	}

}