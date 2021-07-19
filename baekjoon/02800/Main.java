import java.io.*;
import java.util.*;

public class Main {
	static String S;
	static ArrayList<Pair> P = new ArrayList<>();
	
	// 비트마스킹으로 b를 보고 수식에서 괄호를 맞게 제거한 후에 list에 더한다
	static void bfc(ArrayList<String> list, int b) {
		boolean[] del = new boolean[S.length() + 1];
		for (int i = 0; i < 10; i++)
			if ((b & (1 << i)) > 0)
				del[P.get(i).u] = del[P.get(i).v] = true;
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < S.length(); i++)
			if (!del[i]) ret.append(S.charAt(i));
		list.add(ret.toString());
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		S = br.readLine();
		Stack<Integer> st = new Stack<>();
		for (int i = 0; i < S.length(); i++) {
			char x = S.charAt(i);
			if (x == '(') st.push(i);
			if (x == ')') P.add(new Pair(st.pop(), i));
		}
		ArrayList<String> list = new ArrayList<>();
		for (int i = 1; i < (1 << P.size()); i++)
			bfc(list, i);
		Collections.sort(list);
		for (int i = 0; i < list.size() - 1; i++)
			if (!list.get(i).equals(list.get(i + 1)))
				bw.append(list.get(i)).append("\n");
		bw.append(list.get(list.size() - 1)).append("\n");
		System.out.print(bw);
	}

}

class Pair {
	int u, v;
	
	Pair (int u, int v) { this.u = u; this.v = v; }
	
}
