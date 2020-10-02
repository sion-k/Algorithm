package algospot.CLOCKSYNC;

import java.util.Scanner;
public class Main {
	int[] clocks = new int[16];
	public void wind(int i){clocks[i] +=3; if(clocks[i]==15)clocks[i]=3;}
	public void rewind(int i){clocks[i] -=3; if(clocks[i]==0)clocks[i]=12;}
	public boolean aligned(){
		for(int i=0; i<clocks.length; i++)
			if(clocks[i]!=12) return false;
			return true;  
	}
	
	public final int[][] switches = {{0,1,2,-1,-1},{3,7,9,11,-1},
	{4,10,14,15,-1},{0,4,5,6,7},{6,7,8,10,12},{0,2,14,15,-1},
	{3,14,15,-1,-1},{4,5,7,14,15},{1,2,3,4,5},{3,4,5,9,13}};
	public void pushSwitch(int i){
		for(int c=0;c<5;c++){
			if(switches[i][c]==-1) break;
			wind(switches[i][c]);  
		}
	}
	public void pullSwitch(int i){
		for(int c=0;c<5;c++){
			if(switches[i][c]==-1) break;
			rewind(switches[i][c]);  
		}
	}
	int[] pushed;
	public boolean pushAligned(int n, int first){
		if(n==0){if(aligned())return true;else return false;}
		for(int i=first; i<10; i++){
			if(n>30-3*i) return false;//i踰덉㎏ �씤�뜳�뒪瑜� �룷�븿�빐 怨좊Ⅴ寃� �맆 �굹癒몄�媛� 紐⑤몢 3�씠�뿬�룄 n蹂대떎 �옉��寃쎌슦 
			if(pushed[i]==3) continue;
			pushed[i] += 1; pushSwitch(i);
			if(pushAligned(n-1,i)) return true;
			pushed[i] -= 1; pullSwitch(i);  
		}
		return false;
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int cn = sc.nextInt();
		Main a = new Main();		
		for(int c=0; c<cn; c++){
				for(int i=0; i<16; i++) a.clocks[i] = sc.nextInt();
				int ret = -1;
				a.pushed = new int[10];
				for(int i=0; i<=30; i++)
					if(a.pushAligned(i,0)){
					ret = i; break;
					}
				System.out.println(ret);	 	
		}
		sc.close();
	}
}
