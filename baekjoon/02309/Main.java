import java.util.*;

public class Main {
	static int[] S;
	static ArrayList<Integer> picked = new ArrayList<>();
	
	static int sum() {
		int sum = 0;
		for (Integer i : picked) {sum += i;}
		return sum;
	}
	
	static boolean com(int n, int toPick) {
		if (toPick == 0) {return sum() == 100;}
		int earlist = -1;
		if (picked.size() != 0) {
			for (int i =0 ; i < S.length; i++) {
				if (S[i] == picked.get(picked.size() - 1)) {
					earlist  = i; break;
				}
			}
		}
		for (int i = earlist + 1; i < n; i++) {
			picked.add((Integer)(S[i]));
			if (com(n, toPick - 1)) {return true;}
			picked.remove((Integer)(S[i]));
		}
		return false;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		S = new int[9];
		for (int i = 0; i < 9; i++) {S[i] = sc.nextInt();}
		sc.close();
		com(9, 7);
		Collections.sort(picked);
		for(Integer i : picked) {
			System.out.println(i);
		}

	}

}
