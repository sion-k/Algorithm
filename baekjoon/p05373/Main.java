package baekjoon.p05373;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static Direction toDir(char ch) {
		switch(ch) {
		case 'U': return Direction.UP;
		case 'D': return Direction.DOWN;
		case 'F': return Direction.FRONT;
		case 'B': return Direction.BACK;
		case 'L': return Direction.LEFT;
		case 'R': return Direction.RIGHT;
		}
		return null;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			// 6���� ������ �̷���� ��� ť��
			Face[] rubik = new Face[6];
			rubik[0] = new Face(Direction.UP);
			rubik[3] = new Face(Direction.BACK);
			for (int i = 0; i < 3; i++) rubik[3].cube[i] = rubik[0].cube[2 - i];
			
			rubik[5] = new Face(Direction.RIGHT);
			for (int i = 0; i < 3; i++) rubik[5].cube[i] = rubik[0].cube[4 - i];
			rubik[5].cube[3] = rubik[3].cube[7];
			rubik[5].cube[4] = rubik[3].cube[6];
			
			rubik[2] = new Face(Direction.FRONT);
			for (int i = 0; i < 3; i++) rubik[2].cube[i] = rubik[0].cube[6 - i];
			rubik[2].cube[3] = rubik[5].cube[7];
			rubik[2].cube[4] = rubik[5].cube[6];
			
			rubik[4] = new Face(Direction.LEFT);
			rubik[4].cube[0] = rubik[0].cube[0];
			rubik[4].cube[1] = rubik[0].cube[7];
			rubik[4].cube[2] = rubik[0].cube[6];
			rubik[4].cube[3] = rubik[2].cube[7];
			rubik[4].cube[4] = rubik[2].cube[6];
			rubik[4].cube[6] = rubik[3].cube[4];
			rubik[4].cube[7] = rubik[3].cube[3];
			
			rubik[1] = new Face(Direction.DOWN);
			rubik[1].cube[0] = rubik[2].cube[6];
			rubik[1].cube[1] = rubik[2].cube[5];
			rubik[1].cube[2] = rubik[2].cube[4];
			rubik[1].cube[3] = rubik[5].cube[5];
			rubik[1].cube[4] = rubik[5].cube[4];
			rubik[1].cube[5] = rubik[3].cube[4];
			rubik[1].cube[5] = rubik[3].cube[5];
			rubik[1].cube[6] = rubik[4].cube[6];
			rubik[1].cube[7] = rubik[4].cube[5];
			
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				String command = st.nextToken();
				Direction pos = toDir(command.charAt(0));
				Direction dir = command.charAt(1) == '+' ? Direction.RIGHT : Direction.LEFT;
				rubik[pos.ordinal()].turn(dir);
			}
			System.out.println(rubik[0]);
		}
	}
	
}

enum Direction { UP, DOWN, FRONT, BACK, LEFT, RIGHT }
enum Color { WHITE, YELLOW, RED, ORANGE, GREEN, BLUE }

// ��� ť���� �� ��
class Face {
	Direction dir; // ��� ť���� ���� �ٶ󺸰� �ִ� ����
	Cube[] cube; // ��� ť���� ���� ��� ������ü�� �����ϰ� �ð���� ����
	
	// dir������ �ٶ󺸴� ��� ť���� �� ���� �ʱ�ȭ
	public Face(Direction d) {
		dir = d; cube = new Cube[8];
		for (int i = 0; i < 8; i++) cube[i] = new Cube();
	}
	
	// �� ���� �ٶ� ä �ð����, Ȥ�� �ð� �ݴ�������� ȸ��
	public void turn(Direction d) {
		switch(d) {
		case LEFT : turnRight(); turnRight(); turnRight(); break;
		case RIGHT : turnRight(); break;
		}
	}
	
	// ���� �ٶ󺸴� ���� �������� ���������� ȸ��
	private void turnRight() {
		// ���� ������ ����� �״�� �����ѵ� �����ϰ� ������
		Cube[] rolled = new Cube[8];
		for (int i = 0; i < 8; i++) {rolled[i] = new Cube(); rolled[i].clone(cube[i]);}
		for (int i = 0; i < 8; i++) {
			switch(dir) {
			case UP : rolled[i].rotate(Direction.RIGHT); break;
			case DOWN : rolled[i].rotate(Direction.LEFT); break;
			case FRONT : rolled[i].roll(Direction.RIGHT); break;
			case BACK : rolled[i].roll(Direction.LEFT); break;
			case LEFT : rolled[i].roll(Direction.FRONT); break;
			case RIGHT : rolled[i].roll(Direction.BACK); break;
			}
		}
		int i = 0;
		while (i < 8) {
			cube[(i + 2) % 8].clone(rolled[i]);
			i++;
		}
	}
	
	private static final char[] lower = { 'w', 'y', 'r', 'o', 'g', 'b' };
	private static final int[] sort = {0, 1, 2, 7, 0, 3, 6, 5, 4};
	
	// Cube���� ��� ť���� ���� �ٶ󺸰� �ִ� ������ ��ȯ
	@Override
	public String toString() {
		int d = dir.ordinal();
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			if (i == 4) ret.append(lower[d]);
			else ret.append(lower[cube[sort[i]].color[d].ordinal()]);
			if (i == 2 || i == 5) ret.append("\n");
		}
		return ret.toString();
	}
	
}

//�鿡 8�� �����ϴ� ������ü
class Cube {
	Color[] color; // 6������ ���� ��Ÿ���� ����
	
	// �ʱ�ȭ�� ��� ť���� �ʱ� ���� ������ ����
	public Cube() {
		color = new Color[6];
		for (int i = 0; i < 6; i++) color[i] = Color.values()[i];
	}
	
	// �ٸ� ť�긦 �״�� ���� �ٲ۴�
	public void clone(Cube o) {
		for (int i = 0; i < 6; i++) color[i] = o.color[i];
	}
	
	// d�������� ������ (�����¿츸 ����)
	public void roll(Direction d) {
		switch(d) {
		case FRONT : rollFront(); break;
		case BACK : rollFront(); rollFront(); rollFront(); break;
		case LEFT : rollRight(); rollRight(); rollRight(); break;
		case RIGHT : rollRight(); break;
		}
	}
	
	private void rollFront() {
		Color temp = color[0];
		color[0] = color[3];
		color[3] = color[1];
		color[1] = color[2];
		color[2] = temp;
	}
	
	private void rollRight() {
		Color temp = color[0];
		color[0] = color[4];
		color[4] = color[1];
		color[1] = color[5];
		color[5] = temp;
	}
	
	// ���� Ȥ�� ���������� ȸ����Ų�� 
	public void rotate(Direction d) {
		switch(d) {
		case LEFT : clockwise(); clockwise(); clockwise(); break;
		case RIGHT : clockwise(); break;
		}
	}
	
	private void clockwise() {
		Color temp = color[3];
		color[3] = color[4];
		color[4] = color[2];
		color[2] = color[5];
		color[5] = temp;
	}
	
}