package baekjoon.p4673;

import java.util.LinkedList;

public class Main {
	public int dfunc(int input) {
		String inputStr = Integer.toString(input);

		int output = input;
		for (int i = 0; i < inputStr.length(); i++)
			output += Integer.parseInt(inputStr.charAt(i) + "");

		return output;
	}

	public static void main(String[] args) {
		Main a = new Main();
		LinkedList<String> pers = new LinkedList<String>();
		LinkedList<String> sn = new LinkedList<String>();
		sn.add(1 + "");
		pers.add(1 + "");
		for (int i = 2; i <= 10000; i++) {
			for (int j = 0; j < pers.size(); j++) {
				if (a.dfunc(Integer.parseInt(pers.get(j))) == i) {
					pers.set(j, Integer.toString(i));
					break;
				} else if (j == (pers.size() - 1)) {
					pers.add(Integer.toString(i));
					sn.add(Integer.toString(i));
					break;
				}
			}
		}
		for (int i = 0; i < sn.size(); i++)
			System.out.println(sn.get(i));
	}
}
