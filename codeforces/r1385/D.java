package codeforce.r656;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class D {

	// C-good String으로 만드는데 필요한 최소 대체 수 반환
	static int aGood(String S, char C) {
		if (S.length() == 1) {return S.charAt(0) != C ? 1 : 0;}
		int mid = S.length() / 2;
		// 1번 조건 만족시키기
		int cand1 = 0;
		String preFix = S.substring(0, mid);
		for (int i = 0; i < preFix.length(); i++)
			if (preFix.charAt(i) != C)
				cand1++;
		cand1 += aGood(S.substring(mid), (char)(C + 1));
		// 2번 조건 만족시키기
		int cand2 = 0;
		preFix = S.substring(mid);
		for (int i = 0; i < preFix.length(); i++)
			if (preFix.charAt(i) != C)
				cand2++;
		cand2 += aGood(S.substring(0, mid), (char)(C + 1));
		return Math.min(cand1, cand2);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			br.readLine();
			String S = br.readLine();
			bw.write(String.valueOf(aGood(S, 'a')));
			bw.newLine();
		}
		bw.close();
	}

}