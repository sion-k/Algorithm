package algospot.FENCE;
import java.util.Scanner;
public class Main {
	public int[] array;
	public int max(int a, int b){if(a>b)return a; else return b;}
	public int min(int a, int b){if(a<b)return a; else return b;}
	public int trackMin(int left, int right){//min value of [left, right)
		int tempMin = array[left];
		for(int i=left+1; i<right; i++)
			tempMin = min(tempMin, array[i]);
		return tempMin;  
	}
	public int cut(int left, int right){//max value of [left, right)
		int length = right - left;
		int mid = (left + right)/2;
		if(length==1) return array[left];
		if(length==2){
			int partMax = array[left];
			partMax = max(partMax, array[left+1]);
			partMax = max(partMax, 2*min(array[left],array[left+1]));
			return partMax;
		}
		int partMax = cut(left, mid);
		int midMax = 0;
		//Searching all interval that includes [mid,mid+1]
		for(int trackLeft=0; trackLeft<=mid; trackLeft++)
			for(int trackRight=mid+1;trackRight<right;trackRight++)
			midMax = max(midMax, (trackRight-trackLeft+1)*trackMin(trackLeft,trackRight+1));
			
		partMax = max(partMax, midMax);
		partMax = max(partMax, cut(mid+1, right));
		return partMax;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cn = sc.nextInt();
		for(int c=0; c<cn; c++){
			int length = sc.nextInt();
			Main a = new Main();a.array = new int[length];
			for(int i=0; i<length; i++)
				a.array[i] = sc.nextInt();
			System.out.println(a.cut(0,length));  
		}
		sc.close();
	}

}
