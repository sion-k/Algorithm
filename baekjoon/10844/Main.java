import java.util.Scanner;

public class Main {
	static int[][] cache;
	static int N;
	
	// [1, N]번째 숫자를 d를 골랐을 때 다오는 계단 수 경우의 수
	static int stair(int i, int d) {
		// 기저 사례 : 마지막 글자는 뭘 고르건 한가지
		if (i == N) {return 1;}
		if (cache[i][d] != 0) {return cache[i][d];}
		if (d == 0) {return cache[i][d] = stair(i + 1, 1);}
		if (d == 9) {return cache[i][d] = stair(i + 1, 8);}
		return cache[i][d] = (stair(i + 1, d - 1) + stair(i + 1, d + 1)) % 1000000000;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		sc.close();
		cache = new int[N + 1][9 + 1];
		
		int stair = 0;
		for (int i = 1; i <= 9; i++) {
			stair = ((stair + stair(1, i)) % 1000000000);
		}
		
		System.out.println(stair);
	}

}
