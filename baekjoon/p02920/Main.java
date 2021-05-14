package baekjoon.p02920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int prev = Integer.parseInt(st.nextToken());
		boolean ascending = true; boolean descending = true;
		for (int i = 1; i < 8; i++) {
			int here = Integer.parseInt(st.nextToken());
			if (prev + 1 != here) {ascending = false;}
			if (prev - 1 != here) {descending = false;}
			prev = here;
		}
		if (ascending) {
			System.out.println("ascending");
		} else if (descending) {
			System.out.println("descending");
		} else {
			System.out.println("mixed");
		}
	}
}