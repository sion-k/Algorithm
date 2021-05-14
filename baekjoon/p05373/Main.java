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
			// 6개의 면으로 이루어진 루빅스 큐브
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

// 루빅스 큐브의 한 면
class Face {
	Direction dir; // 루빅스 큐브의 면이 바라보고 있는 방향
	Cube[] cube; // 루빅스 큐브의 면의 가운데 정육면체를 제외하고 시계방향 순서
	
	// dir방향을 바라보는 루빅스 큐브의 한 면을 초기화
	public Face(Direction d) {
		dir = d; cube = new Cube[8];
		for (int i = 0; i < 8; i++) cube[i] = new Cube();
	}
	
	// 이 면을 바라본 채 시계방향, 혹은 시계 반대방향으로 회전
	public void turn(Direction d) {
		switch(d) {
		case LEFT : turnRight(); turnRight(); turnRight(); break;
		case RIGHT : turnRight(); break;
		}
	}
	
	// 면을 바라보는 방향 기준으로 오른쪽으로 회전
	private void turnRight() {
		// 현재 상태의 모습을 그대로 복사한뒤 적절하게 굴린다
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
	
	// Cube들이 루빅스 큐브의 면이 바라보고 있는 방향을 반환
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

//면에 8개 존재하는 정육면체
class Cube {
	Color[] color; // 6방향의 면이 나타내는 색깔
	
	// 초기화는 루빅스 큐브의 초기 색깔 구성과 같음
	public Cube() {
		color = new Color[6];
		for (int i = 0; i < 6; i++) color[i] = Color.values()[i];
	}
	
	// 다른 큐브를 그대로 따라 바꾼다
	public void clone(Cube o) {
		for (int i = 0; i < 6; i++) color[i] = o.color[i];
	}
	
	// d방향으로 굴린다 (상하좌우만 가능)
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
	
	// 왼쪽 혹은 오른쪽으로 회전시킨다 
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