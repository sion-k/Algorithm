import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class Main {
	public static class Room {
		public ArrayList<Player> players = new ArrayList<>();
		public static int maximum;
		public int boundary;
		
		public Room(Player p) {boundary = p.level; players.add(p);}
		
		public boolean addPlayer(Player p) {
			if(p.level < boundary - 10 || boundary + 10 < p.level || players.size() == maximum) {
				return false;
			} else {
				players.add(p);
				return true;
			}
		}
	}

	public static class Player implements Comparable<Player> {
		public int level;
		public String nickName;
		
		public Player(String l, String n) {
			level = Integer.parseInt(l);
			nickName = n;
		}
		
		@Override
		public int compareTo(Player o) {
			return nickName.compareTo(o.nickName);
		}
		
		@Override
		public String toString() {
			return level + " " + nickName;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int p = Integer.parseInt(st.nextToken());
		Room.maximum = Integer.parseInt(st.nextToken());
		
		ArrayList<Room> room = new ArrayList<>();
		for (int i = 0; i < p; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			Player player = new Player(st.nextToken(), st.nextToken());
			
			boolean added = false;
			// 방을 돌면서 입장 시킬 수 있으면 입장시키고 나옴
			for(Room r : room) {
				if(r.addPlayer(player)) {added = true;break;}
			}
			// 다 돌았는데 매칭 가능한 방이 없다면 새로운 방 추가
			if(!added) {room.add(new Room(player));}
		}
		
		for(Room r : room) {
			if(r.players.size() == Room.maximum) {
				System.out.println("Started!");
			} else {
				System.out.println("Waiting!");
			}
			Collections.sort(r.players);
			for (Player player : r.players) {
				System.out.println(player);
			}
		}
		
	}

}
