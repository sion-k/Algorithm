import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		long[] cache = new long[68];
		cache[0] = cache[1] = 1;
		cache[2] = 2; cache[3] = 4;
		for (int i = 4; i <= 67; i++) {
			cache[i] = cache[i - 1] + cache[i - 2] + cache[i - 3] + cache[i - 4];
		}
		int T = Integer.parseInt(br.readLine());
		for (int c = 0; c < T; c++) {
			bw.write(String.valueOf(cache[Integer.parseInt(br.readLine())]));
			bw.newLine();
		}
		bw.close();
	}

}
