package baekjoon.p15947;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static final String[] LYRICS = {
			"sukhwan", "baby", "sukhwan",  "",
			"", "very", "cute", "",
			"", "in", "bed", "",
			"", "baby"
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		br.close();
		int index = N / 14; //[0,]번째로 부르는 노래
		String ans = "";
		int r = N % 14;
		switch(r) {
		case 3:case 7:case 11:
			int k = 2 + index;
			if (k >= 5) {ans = "tu+ru*" + (k);}
			else {
				ans += "tu";
				for (int i = 0; i < k; i++) {ans += "ru";}
			}
			break;
		case 4:case 8:case 12:
			k = 1 + index;
			if (k >= 5) {ans = "tu+ru*" + (k);}
			else {
				ans += "tu";
				for (int i = 0; i < k; i++) {ans += "ru";}
			}
			break;
		default :
			ans = LYRICS[r];
		}
		System.out.println(ans);
	}

}