package competition.C;

import java.util.Scanner;

public class Main {
	// fraction  : a / b
	static class Fraction {
		int a; int b;
		
		public Fraction(int a, int b) {
			int temp;
			while((temp = gcd(a,b)) != 1) {
				a = a / temp;
				b = b / temp;
			}
			this.a = a;
			this.b = b;
		}
		
		@Override
		public String toString() {
			return a + " " + b;
		}
	}
	
	static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}

	static int gcd(int a, int b) {
		if (a % b == 0) return b;
		return gcd(b, a % b);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		Fraction[] F = new Fraction[N];
		for (int i = 0; i < N; i++) {
			F[i] = new Fraction(sc.nextInt(), sc.nextInt());
		}
		sc.close();
		// �и��� �ּ� ����� ���ϱ�
		int blcm = F[0].b;
		for (int i = 0; i < N; i++) {
			if(blcm > F[i].b) {
				blcm = lcm(blcm, F[i].b);
			} else {
				blcm = lcm(F[i].b, blcm);
			}
		}
		
		// ���ڵ� �ּ� ������� ��н�Ű��
		for (int i = 0; i < N; i++) {
			int fac = blcm / F[i].b;
			F[i].a = F[i].a * fac;
		}
		
		// ���ڵ��� �ִ� ����� ���ϱ�
		int agcd = F[0].a;
		for (int i = 0; i < N; i++) {
			if(agcd > F[i].a) {
				agcd = gcd(agcd, F[i].a);	
			} else {
				agcd = gcd(F[i].a, agcd);		
			}
		}
		System.out.println(new Fraction(agcd, blcm));
	}

}
