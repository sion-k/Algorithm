package codeforce.r690;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Pattern;

public class B {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			String S = br.readLine();
			if (S.equals("2020"))
				bw.write("YES");
			else if (Pattern.compile(".*2020").matcher(S).matches() ||
					 Pattern.compile("2.*020").matcher(S).matches() ||
					 Pattern.compile("20.*20").matcher(S).matches() ||
					 Pattern.compile("202.*0").matcher(S).matches() ||
					 Pattern.compile("2020.*").matcher(S).matches())
				bw.write("YES");
			else
				bw.write("NO");
			bw.newLine();
		}
		bw.close();
	}

}