package baekjoon.p11723;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int S;
	static final String[] COM =
	{"add", "remove", "check", "toggle", "all", "empty"};

	static void add(int x) {S |= (1 << x - 1);}
	static void remove(int x) {S &= ~(1 << x - 1);}
	static boolean check(int x) {return (S & (1 << x - 1)) > 0;}
	static void toggle(int x) {S ^= (1 << x - 1);}
	static void all() {S = (1 << 20) - 1;}
	static void empty() {S = 0;}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String com = st.nextToken();
			int c = 0;
			while (c < COM.length) {
				if (com.equals(COM[c])) {break;}
				c++;
			}
			int x = 0;
			if (st.hasMoreTokens()) {x = Integer.parseInt(st.nextToken());}
			switch(c) {
			case 0:
				add(x);break;
			case 1:
				remove(x); break;
			case 2:
				bw.write(String.valueOf(check(x) ? 1 : 0));
				bw.newLine();
				break;
			case 3:
				toggle(x); break;
			case 4:
				all(); break;
			case 5:
				empty(); break;
			}
		}
		bw.close();
	}

}