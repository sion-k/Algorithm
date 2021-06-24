import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		sc.close();
		for (int i = 0, j = 0; i < line.length(); i++, j++) {
			if (j == 10) {System.out.println(); j = 0;}
			System.out.print(line.charAt(i));
		}

	}

}
