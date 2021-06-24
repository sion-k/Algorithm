import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int V = sc.nextInt();
		sc.close();
		// (V - A)까지 올라가려면 며칠이 걸리는지
		int days = (V - A) / (A - B);
		int lifted = days * (A - B);
		if (V - lifted > A) {days++;}
		System.out.println(days + 1);
	}

}
