package tk.speedprog.dota2.webapi;
public class Match {
	public String match_id;
	public int start_time;
	public int lobby_type;
	public Player[] player;

	public Match () {
		player = new Player[10];
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(">>>>ID: "+match_id+"\n");
		sb.append(">>>>ST: "+start_time+"\n");
		sb.append(">>>>LT: "+lobby_type+"\n");
		for (Player p : player) {
			if (p != null) {
				sb.append(p.toString());
			} else {
				sb.append(">>>>>>This slot was abandoned!");
			}
		}
		return sb.toString();
	}
}
