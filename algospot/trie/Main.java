package algospot.trie;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {
		TrieNode trie = new TrieNode();
		Scanner sc = new Scanner(System.in);
		while(true) {
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			if (st.nextToken().equals("0")) {
				trie.insert(st.nextToken(), 0);
			} else {
				System.out.println(trie.find(st.nextToken(), 0) != null);
			}
		}

	}

}
class TrieNode {
	static final int ALPHABETS = 26;

	static int toNumber(char ch) {return ch - 'A';}

	TrieNode[] children = new TrieNode[ALPHABETS];
	boolean terminal;

	// 이 노드를 루트로 하는 트라이에 문자열 key[i, ...]를 추가한다
	void insert(String key, int i) {
		// 문자열이 끝나면 종료 노드로 바꾸고 종료
		if (i == key.length()) {
			terminal = true;
		} else {
			int next = toNumber(key.charAt(i));
			// 해당 자식 노드가 없다면 생성한다
			if (children[next] == null) children[next] = new TrieNode();
			// 해당 자식 노드로 재귀 호출
			children[next].insert(key, i + 1);
		}
	}

	// 이 노드를 루트로 하는 트라이에 문자열 key[i, ...]와 대응되는 노드를 찾는다
	// 없으면 null 반환
	TrieNode find(String key, int i) {
		if (i == key.length()) return this;
		int next = toNumber(key.charAt(i));
		if (children[next] == null) return null;
		return children[next].find(key, i + 1);
	}

}