import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int c = 0; c < T; c++) {
			System.out.println(sc.nextInt() + sc.nextInt());
		}
		sc.close();
	}

}
