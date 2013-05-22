package tk.speedprog.dota2.webapi;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MatchHistoryXMLHandler extends DefaultHandler {
	boolean matches = false;
	boolean match = false;
	boolean players = false;
	boolean player = false;
	boolean accountId = false, playerSlot = false, heroId = false;
	boolean matchId = false, startTime = false, lobbyType = false;
	boolean result = false, status = false, numResults = false,
			totalResults = false, resultsRemaining = false;
	Match cMatch;
	Player cPlayer;
	private MatchHistoryResult matchHistoryResult;

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {
		if (qName.equalsIgnoreCase("result")) {
			result = true;
			matchHistoryResult = new MatchHistoryResult();
		} else if (qName.equalsIgnoreCase("status")) {
			status = true;
		} else if (qName.equalsIgnoreCase("num_results")) {
			numResults = true;
		} else if (qName.equalsIgnoreCase("total_results")) {
			totalResults = true;
		} else if (qName.equalsIgnoreCase("results_remaining")) {
			resultsRemaining = true;
		}
		if (qName.equalsIgnoreCase("matches")) {
			matches = true;
		} else if (qName.equalsIgnoreCase("match")) {
			match = true;
			cMatch = new Match();
		} else if (qName.equalsIgnoreCase("players")) {
			players = true;
		} else if (qName.equalsIgnoreCase("player")) {
			player = true;
			cPlayer = new Player();
		} else if (qName.equalsIgnoreCase("account_id")) {
			accountId = true;
		} else if (qName.equalsIgnoreCase("player_slot")) {
			playerSlot = true;
		} else if (qName.equalsIgnoreCase("hero_id")) {
			heroId = true;
		} else if (qName.equalsIgnoreCase("match_id")) {
			matchId = true;
		} else if (qName.equalsIgnoreCase("start_time")) {
			startTime = true;
		} else if (qName.equalsIgnoreCase("lobby_type")) {
			lobbyType = true;
		}

	}

	public void endElement(String uri, String localName, String qName) {
		if (qName.equalsIgnoreCase("result")) {
			result = false;
		} else if (qName.equalsIgnoreCase("status")) {
			status = false;
		} else if (qName.equalsIgnoreCase("num_results")) {
			numResults = false;
		} else if (qName.equalsIgnoreCase("total_results")) {
			totalResults = false;
		} else if (qName.equalsIgnoreCase("results_remaining")) {
			resultsRemaining = false;
		}
		if (qName.equalsIgnoreCase("matches")) {
			matches = false;
		} else if (qName.equalsIgnoreCase("match")) {
			match = false;
			matchHistoryResult.addMatchIfValid(cMatch);
		} else if (qName.equalsIgnoreCase("players")) {
			players = false;
		} else if (qName.equalsIgnoreCase("player")) {
			player = false;
			cMatch.player[cPlayer.playerSlot] = cPlayer;
			// System.out.println("Added player: " + (cPlayer.isDire ?
			// cPlayer.playerSlot+5 : cPlayer.playerSlot));
		} else if (qName.equalsIgnoreCase("account_id")) {
			accountId = false;
		} else if (qName.equalsIgnoreCase("player_slot")) {
			playerSlot = false;
		} else if (qName.equalsIgnoreCase("hero_id")) {
			heroId = false;
		} else if (qName.equalsIgnoreCase("match_id")) {
			matchId = false;
		} else if (qName.equalsIgnoreCase("start_time")) {
			startTime = false;
		} else if (qName.equalsIgnoreCase("lobby_type")) {
			lobbyType = false;
		}
	}

	public void characters(char ch[], int start, int length)
			throws SAXException {
		if (result) {
			if (status) {
				matchHistoryResult.status = Integer.parseInt(new String(ch, start, length));
			} else if (numResults) {
				matchHistoryResult.numResults = Integer.parseInt(new String(ch, start, length));
			} else if (totalResults) {
				matchHistoryResult.totalResults = Integer.parseInt(new String(ch, start, length));
			} else if (resultsRemaining) {
				matchHistoryResult.resultsRemaining = Integer.parseInt(new String(ch, start, length));
			} else if (matches) {
				if (match) {
					if (matchId) {
						cMatch.match_id = new String(ch, start, length);
					}
					if (startTime) {
						cMatch.start_time = Integer.parseInt(new String(ch,
								start, length));
					}
					if (lobbyType) {
						cMatch.lobby_type = Integer.parseInt(new String(ch,
								start, length));
					}
					if (players) {
						if (accountId) {
							try {
								cPlayer.accountId = new String(ch, start,
										length);
							} catch (NumberFormatException e) {
								e.printStackTrace();
								System.out
										.println(new String(ch, start, length));
							}
						}
						if (heroId) {
							cPlayer.heroId = Integer.parseInt(new String(ch,
									start, length));
						}
						if (playerSlot) {
							int slot = Integer.parseInt(new String(ch, start,
									length));
							// leftmost bit set == dire
							// 2 right most bits are playerslots
							// TODO: make this none sloppy
							cPlayer.isDire = slot > 127 ? true : false;
							cPlayer.playerSlot = Player.getRealPlayerSlot(slot);
						}
					}
				}
			}
		}

	}

	public MatchHistoryResult getMatchList() {
		return matchHistoryResult;
	}
}
