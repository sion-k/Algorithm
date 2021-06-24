import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static String S;

	static boolean isPalin(int i, int j) {
		if (i >= j) {return true;}
		if (S.charAt(i) == S.charAt(j)) {return isPalin(i + 1, j - 1);}
		return false;
	}

	static boolean isPseudoPalin(int i, int j) {
		if (i >= j) {return true;}
		if (S.charAt(i) == S.charAt(j)) {return isPseudoPalin(i + 1, j - 1);}
		else {return isPalin(i + 1, j) || isPalin(i, j - 1);}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			S = br.readLine();
			int ret = 2;
			if (isPalin(0, S.length() - 1)) {ret = 0;}
			else if(isPseudoPalin(0, S.length() - 1)){ret = 1;}
			bw.write(String.valueOf(ret)); bw.newLine();
		}
		bw.close();
	}

}
