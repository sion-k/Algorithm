import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		boolean[] dp = new boolean[1000001];
		for (int i = 1; i <= 1000000; i++)
			for (int j = 1; j * j <= i; j++)
				if (!dp[i - j * j]) {
					dp[i] = true;
					break;
				}
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			bw.append(dp[Integer.parseInt(br.readLine())] ? "koosaga" : "cubelover");
			bw.append("\n");
		}
		System.out.print(bw);
	}

}
