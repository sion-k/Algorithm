package baekjoon.p01181;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	static ArrayList<String> S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		S = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {S.add(br.readLine());}
		Collections.sort(S, new newOrder());
		String prev = S.get(0);
		bw.write(prev); bw.newLine();
		for (int i = 1; i < N; i++) {
			if (!prev.equals(S.get(i))) {
				bw.write(S.get(i)); bw.newLine();
				prev = S.get(i);
			}
		}
		bw.close();
	}

}

class newOrder implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		return o1.length() == o2.length() ?
		o1.compareTo(o2) : o1.length() - o2.length();
	}

}