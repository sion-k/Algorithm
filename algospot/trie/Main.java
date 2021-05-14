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

	// �� ��带 ��Ʈ�� �ϴ� Ʈ���̿� ���ڿ� key[i, ...]�� �߰��Ѵ�
	void insert(String key, int i) {
		// ���ڿ��� ������ ���� ���� �ٲٰ� ����
		if (i == key.length()) {
			terminal = true;
		} else {
			int next = toNumber(key.charAt(i));
			// �ش� �ڽ� ��尡 ���ٸ� �����Ѵ�
			if (children[next] == null) children[next] = new TrieNode();
			// �ش� �ڽ� ���� ��� ȣ��
			children[next].insert(key, i + 1);
		}
	}

	// �� ��带 ��Ʈ�� �ϴ� Ʈ���̿� ���ڿ� key[i, ...]�� �����Ǵ� ��带 ã�´�
	// ������ null ��ȯ
	TrieNode find(String key, int i) {
		if (i == key.length()) return this;
		int next = toNumber(key.charAt(i));
		if (children[next] == null) return null;
		return children[next].find(key, i + 1);
	}

}