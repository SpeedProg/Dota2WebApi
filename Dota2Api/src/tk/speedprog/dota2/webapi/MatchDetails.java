package tk.speedprog.dota2.webapi;
import java.math.BigInteger;
import java.util.LinkedList;


public class MatchDetails {
	public boolean radiantWin;
	public String matchId,
	cluster;
	public BigInteger duration,
	season,
	startTime,
	towerStatusRadiant,
	towerStatusDire,
	barracksStatusRadiant,
	barracksStatusDire,
	firstBloodTime,
	lobbyType,
	humanPlayers,
	leagueid,
	positiveVotes, negativeVotes;
	public int gameMode;
	public String matchSeqNumber;
	public LinkedList<PlayerStats> players;
	
	public MatchDetails() {
		players = new LinkedList<PlayerStats>();
		cluster = null;
		radiantWin = true;
		matchId = null;
	}
}
