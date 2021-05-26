package baekjoon.p03053;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		double R = Double.parseDouble(br.readLine());
		System.out.println(String.format("%f\n%f", R * R * Math.PI, (double)R * R * 2));
	}
	
}
