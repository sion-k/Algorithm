package baekjoon.p16472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String S;
	
	// S[t]�� alpha�� �߰��Ѵٸ� ���� �ٸ� ���ĺ��� ������ ��� �Ǵ��� ��ȯ
	static int getNum(int[] alpha, int num, int t) {
		return num + (alpha[S.charAt(t) - 'a'] == 0 ? 1 : 0);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		S = br.readLine();
		int N = S.length();
		int head = 0; int tail = 0;
		int[] alphabet = new int[26]; alphabet[S.charAt(0) - 'a']++; int num = 1;
		int max = 1;
		// head���� �����ϴ� �ĺ� ������ ��� �˻�
		while (head < N) {
			// tail�� �ִ��� �ø���
			while (tail + 1 < N && getNum(alphabet, num, tail + 1) <= K) {
				int t = S.charAt(++tail) - 'a';
				if (alphabet[t] == 0) num++;
				alphabet[t]++;
			}
			max = Math.max(max, tail - head + 1);
			int h = S.charAt(head++) - 'a';
			if (--alphabet[h] == 0) num--;
		}
		System.out.print(max);
	}

}