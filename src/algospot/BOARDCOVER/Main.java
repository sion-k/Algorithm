package algospot.BOARDCOVER;
import java.util.Scanner;
public class Main {
	public boolean board[][];
	public int Ltypesdydx[][][] = {
	{{0,0}, {0,1}, {1,0}},//r 紐⑥뼇
	{{0,0}, {0,1}, {1,1}},//�꽦 紐⑥뼇
	{{0,0}, {1,-1},{1,0}},//�꽩 �쁿�쑝濡� �뮘吏묒� 紐⑥뼇
	{{0,0}, {1,0}, {1,1}}//�꽩紐⑥뼇
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
		//紐⑤몢 �뜮�뿬�엳�뒗 寃쎌슦
		if(firsty == -1) return 1;
		
		int ret = 0;
		//泥섏쓬 �꽑�깮�븳 �쐞移섏뿉 4媛�吏� ���엯�쓽 �룄�삎�쓣 寃쎌슦留덈떎 紐⑤몢 媛곴컖 �넃怨� �옱洹��샇異쒗븳�떎
		for(int type=0; type<4; type++){
			int tyx[][] = new int[3][2];//3移몄쓽 yx媛믪쓣 ���옣
			for(int i=0; i<3; i++){
				tyx[i][0] = firsty + Ltypesdydx[type][i][0];
				tyx[i][1] = firstx + Ltypesdydx[type][i][1];			
			}
			//洹� 3移몄뿉 紐⑤몢 �넃�쓣�닔 �엳�뒗吏� �솗�씤
			boolean canCover = true;
			for(int i=0; i<3; i++)
				if(!isRange(tyx[i][0], tyx[i][1]))
					canCover = false;
					
			//�넃�쓣�닔 �엳�떎硫� 3移몄뿉 紐⑤몢 �넃怨� �옱洹��샇異쒗븯怨� �떎�떆 嫄룹뼱�궦�떎
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
		//System.out.print("耳��씠�뒪�쓽 �닔瑜� �엯�젰 : ");
		int caseNum = sc.nextInt();
		int ret[] = new int[caseNum];
		for(int c=0; c<caseNum; c++){
			//System.out.print(c+"踰덉㎏ 耳��씠�뒪�쓽 �뻾怨� �뿴�쓣 �엯�젰 : ");
			Main a = new Main(sc.nextInt(), sc.nextInt());
			
			//�엯�젰遺� �떆�옉
			for(int i=0; i<a.height; i++){
				String temp = sc.next();		
				for(int j=0; j<a.width; j++){
					if(temp.charAt(j) == '#')
						a.board[i][j] = true;
					else
						a.board[i][j] = false;
				}  
			}
			//�엯�젰遺� �걹
			ret[c] = a.countCover();
		}
		
		for(int c=0; c<caseNum; c++){
			System.out.println(ret[c]);
		}	
		sc.close();
	}

}