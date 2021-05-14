package baekjoon.p05052;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder ans = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			TrieNode trie = new TrieNode();
			String[] S = new String[N];
			for (int i = 0; i < N; i++) {
				S[i] = br.readLine();
				trie.insert(0, S[i]);
			}
			boolean check = true;
			for (int i = 0; i < N; i++)
				if (trie.find(0, S[i]) == null) check = false;
			ans.append(check ? "YES" : "NO").append("\n");
		}
		System.out.print(ans);
	}

}

class TrieNode {
	TrieNode[] children = new TrieNode[26];
	boolean terminal;
	
	static int toNumber(char ch) {return ch - '0';}

	// key[i,]�� �� ��带 ��Ʈ�� �ϴ� Ʈ���̿� ����
	void insert(int i, String key) {
		if (i == key.length()) {terminal = true; return;}
		int next = toNumber(key.charAt(i));
		// �ش� �ڽ� ��尡 ���ٸ� ����
		if (children[next] == null) children[next] = new TrieNode();
		// �ڽ� ���� ��� ȣ��
		children[next].insert(i + 1, key);
	}
	
	// �� ��带 ��Ʈ�� �ϴ� Ʈ���̿��� key[i,]�� �����Ǵ� ��带 ã�´�
	// ������ null ��ȯ
	TrieNode find(int i, String key) {
		if (i == key.length()) return this;
		int next = toNumber(key.charAt(i));
		// �ش� �ڽ� ��尡 ���ٸ� �����Ǵ� ��尡 ����
		if (children[next] == null || terminal) return null;
		// �ڽ� ���� ��� ȣ��
		return children[next].find(i + 1, key);
	}
	
}