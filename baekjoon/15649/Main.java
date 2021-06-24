import java.util.Scanner;

public class Main {
	int per(int n, int[] array) {
		if (array[array.length - 1] != 0) {
			printArray(array);
			return 0;
		}
		int index = 0;
		while (array[index] != 0)
			index++;
		for (int i = 1; i <= n; i++) {
			if (isThere(array, i))
				continue;
			int[] newArray = new int[array.length];
			arrayCopy(array, newArray);
			newArray[index] = i;
			per(n, newArray);
		}
		return 0;
	}

	void printArray(int[] array) {
		String str = "";
		for (int i = 0; i < array.length; i++) {
			if (i != array.length - 1)
				str = str + array[i] + " ";
			else
				str = str + array[i];
		}
		System.out.println(str);
	}

	boolean isThere(int[] array, int x) {
		for (int i = 0; i < array.length; i++)
			if (array[i] == x)
				return true;
		return false;
	}

	void arrayCopy(int[] array, int[] newArray) {
		for (int i = 0; i < array.length; i++)
			newArray[i] = array[i];
	}

	public static void main(String[] args) {
		Main a = new Main();
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] array = new int[m];
		a.per(n, array);
		sc.close();
	}
}
