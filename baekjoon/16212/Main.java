import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		br.close();
		ArrayList<Integer> S = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {S.add(Integer.parseInt(st.nextToken()));}
		Collections.sort(S);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < N; i++) {
			bw.write(String.valueOf(S.get(i)));
			if (i != N - 1) {bw.write(" ");}
		}
		bw.newLine();
		bw.close();
	}

}
