import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		boolean[][] MAP = new boolean[N][N];
		boolean[][] newMap = new boolean[N * K][N * K];
		for (int y = 0; y < N; y++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int x = 0; x < N; x++) {
				MAP[y][x] = st.nextToken().equals("1");
				for (int dy = 0; dy < K; dy++) {
					for (int dx = 0; dx < K; dx++) {
						newMap[y * K + dy][x * K + dx] = MAP[y][x];
					}
				}
			}
		}
		for (int i = 0; i < N * K; i++) {
			for (int j = 0; j < N * K; j++) {
				bw.write(newMap[i][j] ? "1" : "0");
				bw.write(" ");
			}
			bw.newLine();
		}
		bw.close();
	}

}
