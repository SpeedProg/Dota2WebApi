package tk.speedprog.dota2.webapi;
import java.util.Iterator;
import java.util.LinkedList;


public class MatchHistoryResult {
	public int status;
	public int numResults;
	public int totalResults;
	public int resultsRemaining;
	public LinkedList<Match> matches;
	
	public void addMatchIfValid(Match m) {
		if (m.lobby_type != 4)
			matches.add(m);
	}
	
	public Iterator<Match> getMatchesIterator() {
		return matches.iterator();
	}
	
	public Match get(int i) {
		return matches.get(i);
	}
	
	public int size() {
		return matches.size();
	}
	public MatchHistoryResult() {
		matches = new LinkedList<Match>();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(">>status: "+status+"\n");
		sb.append(">>numResults: "+numResults+"\n");
		sb.append(">>totalResults: "+totalResults+"\n");
		sb.append(">>resultsRemaining: "+resultsRemaining+"\n");
		for (Match m : matches) {
			sb.append(m.toString());
		}
		return sb.toString();
	}
	
	public int getNumberOfRelevantMatches() {
		int i = 0;
		for (Match match : matches) {
			if (match.lobby_type != 4) {
				i++;
			}
		}
		return i;
	}
}
