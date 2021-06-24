import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// i번째 그룹의 j번째 카드의 숫자를 저장
	static int[][] S = new int[9][4];
	static double[] cache;
	static final int FULL = (int)(Math.pow(2, 27)) - 1;

	// 비트마스킹을 통해 9개의 그룹을 3비트씩 대응시켜 27비트 필드를 정의한다
	// 000000000/00/00/00/00/00/00/00/00/00
	// 876543210 8  7  6  5  4  3  2  1  0 번째의 그룹의 가장 위에 있는 카드가 몇번째 카드인지 저장
	// ↑ i번째 그룹에 놓인 카드가 없을경우 활성화
	// 필드의 상태가 f와 같을 때, 놀이에 성공할 확률 반환 [0 ~ 1.0]
	static double dp(int f) {
		// 모든 카드를 들어낸 경우
		if (f == FULL) return 1.0;
		if (Double.compare(cache[f], -1) != 0) return cache[f];
		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		cache = new double[(int)(Math.pow(2, 27))];
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 4; j++) {

			}
		}
	}

}
