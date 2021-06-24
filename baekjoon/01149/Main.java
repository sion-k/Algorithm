public class Main {
	int[][] array;
	int arrayMin(int[]array){
		int min = array[0];
		for(int i=1;i<array.length;i++)
			if(array[i]<min)
				min = array[i];
		return min;
	}
	
	int func(int i, int j){
		int l=0;
		int []temp = new int[2];
		for(int k=0; k<3; k++){
			if(k==j)
				continue;
			temp[l++] = array[i+1][k];
		}
		return this.array[i][j] + arrayMin(temp);  
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Main a = new Main();
		int n = sc.nextInt();		
		a.array = new int[n][3];
		for(int i=0;i<n; i++)
			for(int j=0; j<3; j++)
				a.array[i][j] = sc.nextInt();  
				
		for(int i=n-2; i>=0; i--)
			for(int j=0; j<3; j++)
				a.array[i][j] = a.func(i,j);
		
		int []temp = new int[3];
		for(int i=0; i<3; i++)
			temp[i] = a.array[0][i];
		System.out.println(a.arrayMin(temp));
		sc.close();
	}
}
