import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());

		ArrayList<Integer> S = new ArrayList<>(N);
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int read = Integer.parseInt(st.nextToken());
			if (read < X) {S.add(read);}
		}
		br.close();
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < S.size(); i++) {
			bw.write(String.valueOf(S.get(i)));
			if (i != S.size() - 1) {bw.write(" ");}
		}
		bw.close();
	}

}
