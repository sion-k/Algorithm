package baekjoon.p2798;

import java.util.*;

public class Main {
	public int blackJack(int[] array, int goal) {
		int max = 0;
		for (int i = 0; i < array.length - 2; i++) {
			for (int j = i + 1; j < array.length - 1; j++) {
				for (int k = j + 1; k < array.length; k++) {
					if (array[i] + array[j] + array[k] > max && array[i] + array[j] + array[k] <= goal)
						max = array[i] + array[j] + array[k];
					if (max == goal)
						return max;
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] array = new int[sc.nextInt()];
		int goal = sc.nextInt();
		for (int i = 0; i < array.length; i++)
			array[i] = sc.nextInt();
		Main a = new Main();
		System.out.println(a.blackJack(array, goal));
		sc.close();
	}

}
