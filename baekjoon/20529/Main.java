import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static final String[] TYPE = {"ESTJ", "ESTP", "ESFJ", "ESFP", "ENTJ", "ENTP", "ENFJ", "ENFP",
			"ISTJ", "ISTP", "ISFJ", "ISFP", "INTJ", "INTP", "INFJ", "INFP"};

	// 두 유형 사이의 심리적 거리
	static int dist(String u, String v) {
		int dist = 0;
		for (int i = 0; i < 4; i++)
			if (u.charAt(i) != v.charAt(i))
				dist++;
		return dist;
	}

	static final char[] ESTJ = {'E', 'S', 'T', 'J'};

	static int getBin(String s) {
		int index = 0;
		for (int i = 0; i < 4; i++)
			if (s.charAt(i) != ESTJ[i])
				index += (int)Math.pow(2, 3 - i);
		return index;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			int N = Integer.parseInt(br.readLine());
			int[] count = new int[16];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++)
				count[getBin(st.nextToken())]++;
			int min = 12;
			for (int i = 0; i < 16; i++) {
				if (count[i] == 0) continue;
				count[i]--;
				for (int j = i; j < 16; j++) {
					if (count[j] == 0) continue;
					count[j]--;
					for (int k = j; k < 16; k++) {
						if (count[k] == 0) continue;
						count[k]--;
						min = Math.min(min,
						dist(TYPE[i], TYPE[j]) + dist(TYPE[j], TYPE[k]) + dist(TYPE[k], TYPE[i]));
						count[k]++;
					}
					count[j]++;
				}
				count[i]++;
			}
			bw.write(String.valueOf(min));
			bw.newLine();
		}
		bw.close();
	}

}
