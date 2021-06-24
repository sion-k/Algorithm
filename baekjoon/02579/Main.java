import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] S;
	static int[] cache;

	// i번째 계단을 밟고있을 때 얻을 수 있는 최대 점수 (위에서 내려온다)
	static int dp(int i) {
		// 계단을 다 내려온 경우
		if (i >= N) {return 0;}
		// 다음 계단이 없는 경우
		if (i == N - 1) {return S[i];}
		if (cache[i] != 0) {return cache[i];}
		
		return cache[i] = S[i] + Math.max(S[i + 1] + dp(i + 3), dp(i + 2));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		cache = new int[N];
		for (int i = N - 1; i >= 0; i--) {
			S[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		System.out.println(dp(0));
	}

}
