import java.util.Scanner;

public class Main {
	private static int addTerm(String addRex) {
		int sum = 0;
		String[] term = addRex.split("\\+");
		for (String t : term)
			sum += Integer.parseInt(t);
		return sum;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] exp = sc.nextLine().split("\\-");
		int ret = addTerm(exp[0]);
		if (exp.length > 1) {
			for (int i = 1; i < exp.length; i++) {
				ret -= addTerm(exp[i]);
			}
		}
		System.out.println(ret);
		sc.close();
	}

}
