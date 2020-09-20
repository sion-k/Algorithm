package BOARDCOVER;
import java.util.Scanner;
public class Main {
	public boolean board[][];
	public int Ltypesdydx[][][] = {
	{{0,0}, {0,1}, {1,0}},//r 모양
	{{0,0}, {0,1}, {1,1}},//ㄱ 모양
	{{0,0}, {1,-1},{1,0}},//ㄴ 옆으로 뒤집은 모양
	{{0,0}, {1,0}, {1,1}}//ㄴ모양
	};
	public int height;
	public int width;
	
	boolean isRange(int y, int x){
		if((0<=y&&y<height)&&(0<=x&&x<width)&&!board[y][x])
			return true;
		else
			return false;  
	}
	int countCover(){
		int firsty = -1; int firstx = -1;
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++)
				if(!board[i][j]){
					firsty = i; firstx = j; i = height; break; 
				}
		//모두 덮여있는 경우
		if(firsty == -1) return 1;
		
		int ret = 0;
		//처음 선택한 위치에 4가지 타입의 도형을 경우마다 모두 각각 놓고 재귀호출한다
		for(int type=0; type<4; type++){
			int tyx[][] = new int[3][2];//3칸의 yx값을 저장
			for(int i=0; i<3; i++){
				tyx[i][0] = firsty + Ltypesdydx[type][i][0];
				tyx[i][1] = firstx + Ltypesdydx[type][i][1];			
			}
			//그 3칸에 모두 놓을수 있는지 확인
			boolean canCover = true;
			for(int i=0; i<3; i++)
				if(!isRange(tyx[i][0], tyx[i][1]))
					canCover = false;
					
			//놓을수 있다면 3칸에 모두 놓고 재귀호출하고 다시 걷어낸다
			if(canCover){
				for(int i=0; i<3; i++)
					board[tyx[i][0]][tyx[i][1]] = true;  
				ret += countCover();
				for(int i=0; i<3; i++)
					board[tyx[i][0]][tyx[i][1]] = false;
			}
		}
		return ret;  
	}
	int countNotCovered(){
		int notCovered = 0;
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++)
				if(!board[i][j])
					notCovered++;
		return notCovered;  
	}
	public String toString(){
		String str = "";
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){
				if(board[i][j])
					str += "#";
				else
					str += ".";  
			}
			if(i!=height-1)
				str += "\n";
		}
		
		return str;
	}
	public Main(int h, int w){
		this.board = new boolean[h][w];
		this.height = h;
		this.width = w;  
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//System.out.print("케이스의 수를 입력 : ");
		int caseNum = sc.nextInt();
		int ret[] = new int[caseNum];
		for(int c=0; c<caseNum; c++){
			//System.out.print(c+"번째 케이스의 행과 열을 입력 : ");
			Main a = new Main(sc.nextInt(), sc.nextInt());
			
			//입력부 시작
			for(int i=0; i<a.height; i++){
				String temp = sc.next();		
				for(int j=0; j<a.width; j++){
					if(temp.charAt(j) == '#')
						a.board[i][j] = true;
					else
						a.board[i][j] = false;
				}  
			}
			//입력부 끝
			ret[c] = a.countCover();
		}
		
		for(int c=0; c<caseNum; c++){
			System.out.println(ret[c]);
		}	
		sc.close();
	}

}