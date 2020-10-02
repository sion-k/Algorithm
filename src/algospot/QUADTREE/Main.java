package algospot.QUADTREE;
import java.util.Scanner;
public class Main {
	public String ref(String s){
		if(s.length()==1) return s;
		int first = 0; if(s.charAt(0)=='x') first ++;
		int pn = 0; String[] p = new String[4];
		
		for(int i = first; i<4+first; i++){
			if(s.charAt(i)=='x'){
				p[pn] = ref(s.substring(i));
				i += (p[pn].length()-1); first += (p[pn].length()-1);
				pn++;  
			}  
			else
				p[pn++] = s.charAt(i)+"";
		}
		return "x" + p[2] + p[3] + p[0] + p[1];  
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cn = sc.nextInt();
		Main a = new Main();
		for(int i=0; i<cn; i++)
			System.out.println(a.ref(sc.next()));  
	    sc.close();
	}

}
