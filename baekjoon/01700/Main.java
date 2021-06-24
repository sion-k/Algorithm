import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		// 멀티탭에 꽂힌 전기 용품들, 0이면 비어있는 경우
		int[] M = new int[N];
		int K = Integer.parseInt(st.nextToken());
		// 전기 용품의 사용 순서
		int[] S = new int[K];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < K; i++) {S[i] = Integer.parseInt(st.nextToken());}
		int poll = 0;
		for (int toUse = 0; toUse < K; toUse++) {
			// 이미 꽂혀져 있거나, 빈 곳이 있는지 확인한다
			boolean pluged = false; int lastEmpty = -1;
			for (int i = 0; i < N; i++) {
				// 이미 꽂혀져 있는 경우
				if (M[i] == S[toUse]) {pluged = true;}
				// 마지막으로 비어있는 위치를 저장
				if (M[i] == 0) {lastEmpty = i;}
			}
			// 이미 꽂혀져 있으면 넘어가고
			if (pluged) {continue;}
			// 아니라면 빈 곳에 꽂을 수 있다면 꽂고
			else if (lastEmpty != -1) {M[lastEmpty] = S[toUse];}
			// 빈 곳도 없다면 뽑아야 한다. 꽂혀져 있는 것들 중 사용되는게 가장 늦는걸 뽑는다.
			else {
				// 이후로 한번도 사용되지 않는 경우는 100으로 초기화
				int[] firstUse = new int[N];
				Arrays.fill(firstUse, 100);
				// 이미 꽂혀져 있는 모든 전기 용품에 대해 확인
				for (int i = 0; i < N; i++)
					// 다음 사용 순서부터 끝까지 확인해서
					for (int j = toUse + 1; j < K; j++)
						// 다시 사용되는 경우 저장하고 탈출
						if (S[j] == M[i]) {firstUse[i] = j; break;}
				// 사용되는게 가장 늦은걸 찾는다
				int maxIndex = 0;
				for (int i = 0; i < N; i++)
					if (firstUse[i] > firstUse[maxIndex])
						maxIndex = i;
				// 그 위치에 뽑고 새로 꼽는다
				M[maxIndex] = S[toUse];
				poll++;
			}
		}
		System.out.println(poll);
	}

}
