import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] S;

	// [left, right]구간의 가장 넓이가 큰 직사각형의 넓이
	static long solve(int left, int right) {
		// 기저 사례 : 구간의 크기가 1
		if (left == right) return S[left];
		int mid = (left + right) / 2;
		// 가운데에 걸치지 않는 답
		long max = Math.max(solve(left, mid), solve(mid + 1, right));
		int low = mid; int high = mid + 1;
		int height = Math.min(S[low], S[high]);
		// 가운데 2개 판자로만 이루어진 경우
		max = Math.max(max, 2 * height);
		// 나머지는 사각형이 [left, right]를 모두 덮을 때 까지 확장해 나간다
		while (left < low || high < right) {
			// 항상 높이가 더 넓은 쪽으로 확장
			if (high < right && (low == left || S[low - 1] < S[high + 1])) {
				high++;
				height = Math.min(height, S[high]);
			} else {
				low--;
				height = Math.min(height, S[low]);
			}
			max = Math.max(max, (long)height * (high - low + 1));
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = br.readLine();
		while (!line.equals("0")) {
			StringTokenizer st = new StringTokenizer(line, " ");
			int N = Integer.parseInt(st.nextToken());
			S = new int[N];
			for (int i = 0 ; i < N; i++)
				S[i] = Integer.parseInt(st.nextToken());
			bw.write(String.valueOf(solve(0, N - 1)));
			bw.newLine();
			line = br.readLine();
		}
		bw.close();
	}

}
