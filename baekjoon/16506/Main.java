import java.io.*;

public class Main {
	static final String[] OPCODE = {
		"ADD"  ,"ADDC"  ,"SUB"  ,"SUBC"  ,
		"MOV"  ,"MOVC"  ,"AND"  ,"ANDC"  ,
		"OR"   ,"ORC"   ,
		"NOT"  , 
		"MULT" ,"MULTC" ,"LSFTL","LSFTLC",
		"LSFTR","LSFTRC","ASFTR","ASFTRC",
		"RL"   ,"RLC"   ,"RR"   ,"RRC"
	};
	
	static final String[] OPCODE_ML = {
		"00000","00001" ,"00010","00011",
		"00100","00101" ,"00110","00111",
		"01000","01001",
		"01010",
		"01100","01101" ,"01110","01111",
		"10000","10001" ,"10010","10011",
		"10100","10101" ,"10110","10111"
	};
	
	static String compile(String ass) {
		String[] split = ass.split(" ");
		
		StringBuilder sb =  new StringBuilder();
		for (int i = 0; i < OPCODE.length; i++) {
			if(split[0].equals(OPCODE[i])) {sb.append(OPCODE_ML[i]);}
		}
		//5
		sb.append("0");
		//rD
		int rD = Integer.parseInt(split[1]);
		String rDML = String.format("%03d", Integer.parseInt(Integer.toBinaryString(rD)));
		sb.append(rDML);
		//rA
		int rA = Integer.parseInt(split[2]);
		String rAML = String.format("%03d", Integer.parseInt(Integer.toBinaryString(rA)));
		sb.append(rAML);
		//rB , #C
		int rB4C = Integer.parseInt(split[3]);
		if (sb.charAt(4) == '0') {
			String rB4CML = String.format("%03d", Integer.parseInt(Integer.toBinaryString(rB4C)));
			sb.append(rB4CML).append("0");
		} else {
			String rB4CML = String.format("%04d", Integer.parseInt(Integer.toBinaryString(rB4C)));
			sb.append(rB4CML);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < N; i++) {
			bw.write(compile(br.readLine()));
			bw.newLine();
		}
		br.close();
		bw.close();
	}

}
