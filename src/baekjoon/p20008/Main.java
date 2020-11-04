package baekjoon.p20008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int HP;
	static Skill[] SKILL;

	// ���� �ð��� t�̰� HP�� ��ų���� ����� �� �ְ� �Ǵ� �ð����� cool�϶�
	// óġ�ϴµ� �ɸ��� �ð�
	static int BTK(int t, int hp, int[] cool) {
		if (hp <= 0) {return t;}
		int min = 1000;
		boolean used = false;
		for (int i = 0; i < N; i++) { // N���� ��ų �߿���
			if (t >= cool[i]) { // ��� �� �� �ִ� ��ų�� ���
				used = true;
				int temp = cool[i];
				cool[i] = t + SKILL[i].time;
				min = Math.min(min, BTK(t + 1, hp - SKILL[i].damage, cool));
				cool[i] = temp;
			}
		}
		// ��� �� ����� �� ���� ���
		if (!used) {min = Math.min(min, BTK(t + 1, hp, cool));}
		return min;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		SKILL = new Skill[N];
		int HP = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cd = Integer.parseInt(st.nextToken());
			int damage = Integer.parseInt(st.nextToken());
			SKILL[i] = new Skill(cd, damage);
		}
		int[] cool = new int[N];
		System.out.println(BTK(0, HP, cool));
	}

}

class Skill {
	int time; int damage;
	public Skill(int t, int d) {time = t; damage = d;}
}