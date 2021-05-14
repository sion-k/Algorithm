package baekjoon.p16508;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static String TITLE;
	static boolean[] FOUND;
	static int MAX = 1600000;
	static int BOOK;
	static int[] PRICE;
	static String[] WORD;

	static int choose(int book) {
		boolean allFound = true;
		for (boolean found : FOUND) {if (!found) {allFound = false;break;}}
		if (allFound) {return 0;}
		if (book == BOOK) {return MAX;}
		
		int notChoosed = choose(book + 1);
		boolean[] temp = new boolean[TITLE.length()];
		System.arraycopy(FOUND, 0, temp, 0, TITLE.length());
		
		for (int w = 0; w < WORD[book].length(); w++) {
			for (int t = 0; t < TITLE.length(); t++) {
				if (!FOUND[t] && WORD[book].charAt(w) == TITLE.charAt(t)) {
					FOUND[t] = true;
					break;
				}
			}
		}
		
		int choosed = choose(book + 1) + PRICE[book];
		FOUND = temp;
		
		return Math.min(notChoosed, choosed);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TITLE = sc.nextLine();
		FOUND = new boolean[TITLE.length()];

		BOOK = Integer.parseInt(sc.nextLine());
		PRICE = new int[BOOK];
		WORD = new String[BOOK];

		StringTokenizer st = null;
		for (int i = 0; i < BOOK; i++) {
			st = new StringTokenizer(sc.nextLine(), " ");
			PRICE[i] = Integer.parseInt(st.nextToken());
			WORD[i] = st.nextToken();
		}
		sc.close();

		int ret = choose(0);
		if (ret >= MAX) {
			System.out.println("-1");
		} else {
			System.out.println(ret);
		}

	}

}
