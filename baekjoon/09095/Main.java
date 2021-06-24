import java.util.Scanner;

public class Main {
	static int[] cache = new int[11 + 1];
	
	static int dp(int N) {
		if(N < 0) {return 0;}
		if(N == 0) {return 1;}
		if(cache[N]!=0) {return cache[N];}
		
		int sum = 0;
		for(int i = 1; i <= 3; i++) {
			sum += dp(N - i);
		}
		return cache[N] = sum;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < T; i++) {
			int N = Integer.parseInt(sc.nextLine());
			System.out.println(dp(N));
		}
		sc.close();
	}

}
