import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2 {
	static BufferedWriter bw =
	new BufferedWriter(new OutputStreamWriter(System.out));
	static int N;
	static ArrayList<Integer> picked = new ArrayList<>();

	static void print() throws IOException {
		for (int i = 0; i < picked.size(); i++) {
			bw.write(String.valueOf(picked.get(i)));
			if (i != N - 1) {bw.write(" ");}
		}
		bw.newLine();
	}

	static void BFC(int lastPick, int toPick) throws IOException {
		if (toPick == 0) {print(); return;}
		for (int i = lastPick + 1; i <= N; i++) {
			picked.add(i);
			BFC(i, toPick - 1);
			picked.remove(picked.size() - 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		BFC(0, M);
		bw.close();
	}

}
