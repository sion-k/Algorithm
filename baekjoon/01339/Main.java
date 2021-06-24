import java.util.Scanner;

public class Main {
	private static int[] A = new int[26];
	private static int[] D = { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = Integer.parseInt(sc.nextLine());
		
		for (int i = 0; i < N; i++) {
			String word = sc.nextLine();
			for (int w = 0; w < word.length(); w++) {
				A[(int)(word.charAt(word.length() - 1 - w) - 'A')] += D[w];
			}
		}
		sc.close();
		
		int sum = 0;
		for (int c = 0; c < 9; c++) {
			int max = 0;
			for (int i =1; i < A.length; i++) {
				max = A[i] > A[max] ? i : max;
			}
			sum += ((9 - c) * A[max]);
			A[max] = 0;
		}
		System.out.println(sum);
	}

}
