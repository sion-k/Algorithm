package competition.hoddingTest.A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		String iA = st.nextToken();
		String iB = st.nextToken();
		int amax = 2;
		for (int i = 0; i < iA.length(); i++) {
			int here = iA.charAt(i);
			if ('a' <= here && here <= 'z')
				here = here - 'a' + 10;
			else 
				here = here - '0';
			amax = Math.max(amax, here + 1);
		}
		int bmax = 2;
		for (int i = 0; i < iB.length(); i++) {
			int here = iB.charAt(i);
			if ('a' <= here && here <= 'z')
				here = here - 'a' + 10;
			else 
				here = here - '0';
			bmax = Math.max(bmax, here + 1);
		}
		int check = 0;
		long X = 0; int p = 0; int q = 0;
		for (int A = amax; A <= 36; A++) {
			for (int B = bmax; B <= 36; B++) {
				if (A == B) continue;
				BigInteger t1 = new BigInteger(iA, A);
				BigInteger t2 = new BigInteger(iB, B);
				if (t1.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) >= 0) continue;
				if (t2.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) >= 0) continue;
				if (Long.parseLong(iA, A) == Long.parseLong(iB, B)) {
					X = Long.parseLong(iA, A);
					p = A; q = B;
					check++;
				}
			}
		}
		String ret = "";
		if (check == 0) ret = "Impossible";
		else if (check == 1) {
			ret = X + " " + p + " " + q;
		} else {
			ret = "Multiple";
		}
		System.out.println(ret);
	}
	
}