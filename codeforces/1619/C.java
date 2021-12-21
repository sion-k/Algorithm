import java.io.*;
import java.util.*;

public class C {

	static String add(char[] a, char[] b) {
		int N = Math.max(a.length, b.length);
		int[] a2 = new int[N];
		int[] b2 = new int[N];
		for (int i = 0; i < a.length; i++) a2[N - a.length + i] = a[i] - '0';
		for (int i = 0; i < b.length; i++) b2[N - b.length + i] = b[i] - '0';
		Stack<Integer> st = new Stack<>();
		for (int i = N - 1; i >= 0; i--) {
			st.push(a2[i] + b2[i]);
		}
		String ret = "";
		while (!st.isEmpty()) ret = ret + st.pop();
		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			char[] a = st.nextToken().toCharArray();
			int[] b = new int[101];
			char[] s = st.nextToken().toCharArray();
			int i = a.length - 1; int j = s.length - 1;
			int k = b.length - 1;
			while (i >= 0 && j >= 0) {
				if (a[i] < s[j]) {
					b[k--] = s[j] - a[i];
				} else if (a[i] == s[j]) {
					b[k--] = 0;
				} else {
					b[k--] = 10 + s[j] - a[i];
					j--;
				}
				i--; j--;
			}
			while (i >= 0) {
				b[k--] = a[i] - '0';
				i--;
			}
			while (j >= 0) {
				b[k--] = s[j] - '0';
				j--;
			}
			int start = 0;
			for (int t = 0; t < b.length; t++) {
				if (b[t] != 0) {
					start = t;
					break;
				}
			}
			char[] r = new char[b.length - start];
			for (int t = start; t < b.length; t++) {
				r[t - start] = (char)(b[t] + '0');
			}
			boolean flag = add(a, r).equals(String.valueOf(s));
			if (flag) {
				for (char x : r) {
					bw.append(x);
				}
			} else {
				bw.append(-1);
			}
			bw.append("\n");
		}
		System.out.print(bw);
	}

}