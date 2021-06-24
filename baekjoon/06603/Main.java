import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int K; static int[] S;
	static List<Integer> picked;
	static StringBuilder ans;

	static void print() {
		for (int i = 0; i < 6; i++) {
			ans.append(picked.get(i));
			if (i != 5) {ans.append(" ");}
		}
		ans.append("\n");
	}

	// K개의 원수중에서 toPick개를 고르는 경우(오름차 순만)를 모두 시도
	static void BTK(int toPick, int lastPick) {
		if (toPick == 0) {print(); return;}
		for (int pick = lastPick + 1; pick < K; pick++) {
			picked.add(S[pick]);
			BTK(toPick - 1, pick);
			picked.remove(picked.size() - 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int read = Integer.parseInt(st.nextToken());
		while (read != 0) {
			K = read; S = new int[K];
			picked = new ArrayList<>(6);
			ans = new StringBuilder();
			for (int i = 0; i < K; i++) {
				S[i] = Integer.parseInt(st.nextToken());
			}
			BTK(6, -1);
			bw.write(ans.toString());
			st = new StringTokenizer(br.readLine(), " ");
			read = Integer.parseInt(st.nextToken());
			if (read != 0) {bw.newLine();}
		}
		bw.close();
	}

}
