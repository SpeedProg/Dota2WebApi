package tk.speedprog.dota2.webapi;


import java.math.BigInteger;
import java.util.LinkedList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MatchHistoryBySequenceNumXMLHandler extends DefaultHandler {
	private boolean result = false, matches = false, match = false,
			players = false, player = false, accountId = false,
			playerSlot = false, heroId = false, kills = false, deaths = false,
			assists = false, leaverStatus = false, gold = false,
			lastHits = false, denies = false, gpm = false, xpm = false,
			goldSpent = false, heroDamage = false, towerDamage = false,
			heroHealing = false, level = false, season = false,
			radiantWin = false, duration = false, startTime = false,
			matchId = false, matchSeqNum = false, towerStatusRadiant = false,
			towerStatusDire = false, barracksStatusRadiant = false,
			barracksStatusDire = false, cluster = false,
			firstBloodTime = false, lobbyType = false, humanPlayers = false,
			leagueId = false, positiveVotes = false, negativeVotes = false,
			gameMode = false;

	private boolean[] item = { false, false, false, false, false, false };
	private PlayerStats cPlayerStats;
	private Player cPlayer;
	private MatchDetails cMatchDetails;
	private LinkedList<MatchDetails> matchesList = new LinkedList<MatchDetails>();

	/**
	 * Handles the start of an element.
	 */
	public final void startElement(final String uri, String localName,
			String qName, final Attributes attributes) {
		if (qName.equalsIgnoreCase("result")) {
			result = true;
		} else if (qName.equalsIgnoreCase("matches")) {
			matches = true;
		} else if (qName.equalsIgnoreCase("match")) {
			cMatchDetails = new MatchDetails();
			match = true;
		} else if (qName.equalsIgnoreCase("match_seq_num")) {
			matchSeqNum = true;
		} else if (qName.equalsIgnoreCase("players")) {
			players = true;
		} else if (qName.equalsIgnoreCase("season")) {
			season = true;
		} else if (qName.equalsIgnoreCase("radiant_win")) {
			radiantWin = true;
		} else if (qName.equalsIgnoreCase("duration")) {
			duration = true;
		} else if (qName.equalsIgnoreCase("start_time")) {
			startTime = true;
		} else if (qName.equalsIgnoreCase("match_id")) {
			matchId = true;
		} else if (qName.equalsIgnoreCase("tower_status_radiant")) {
			towerStatusRadiant = true;
		} else if (qName.equalsIgnoreCase("tower_status_dire")) {
			towerStatusDire = true;
		} else if (qName.equalsIgnoreCase("barracks_status_radiant")) {
			barracksStatusRadiant = true;
		} else if (qName.equalsIgnoreCase("barracks_status_dire")) {
			barracksStatusDire = true;
		} else if (qName.equalsIgnoreCase("cluster")) {
			cluster = true;
		} else if (qName.equalsIgnoreCase("first_blood_time")) {
			firstBloodTime = true;
		} else if (qName.equalsIgnoreCase("lobby_type")) {
			lobbyType = true;
		} else if (qName.equalsIgnoreCase("human_players")) {
			humanPlayers = true;
		} else if (qName.equalsIgnoreCase("leagueid")) {
			leagueId = true;
		} else if (qName.equalsIgnoreCase("positive_votes")) {
			positiveVotes = true;
		} else if (qName.equalsIgnoreCase("negative_votes")) {
			negativeVotes = true;
		} else if (qName.equalsIgnoreCase("game_mode")) {
			gameMode = true;
		} else if (qName.equalsIgnoreCase("player")) {
			player = true;
			cPlayerStats = new PlayerStats();
			cPlayer = new Player();
		} else if (qName.equalsIgnoreCase("account_id")) {
			accountId = true;
		} else if (qName.equalsIgnoreCase("player_slot")) {
			playerSlot = true;
		} else if (qName.equalsIgnoreCase("hero_id")) {
			heroId = true;
		} else if (qName.equalsIgnoreCase("kills")) {
			kills = true;
		} else if (qName.equalsIgnoreCase("deaths")) {
			deaths = true;
		} else if (qName.equalsIgnoreCase("assists")) {
			assists = true;
		} else if (qName.equalsIgnoreCase("leaver_status")) {
			leaverStatus = true;
		} else if (qName.equalsIgnoreCase("gold")) {
			gold = true;
		} else if (qName.equalsIgnoreCase("lastHits")) {
			lastHits = true;
		} else if (qName.equalsIgnoreCase("denies")) {
			denies = true;
		} else if (qName.equalsIgnoreCase("gold_per_min")) {
			gpm = true;
		} else if (qName.equalsIgnoreCase("xp_per_min")) {
			xpm = true;
		} else if (qName.equalsIgnoreCase("gold_spent")) {
			goldSpent = true;
		} else if (qName.equalsIgnoreCase("hero_damage")) {
			heroDamage = true;
		} else if (qName.equalsIgnoreCase("tower_damage")) {
			towerDamage = true;
		} else if (qName.equalsIgnoreCase("hero_healing")) {
			heroHealing = true;
		} else if (qName.equalsIgnoreCase("level")) {
			level = true;
		} else if (qName.equalsIgnoreCase("lobby_type")) {
			lobbyType = true;
		} else if (qName.substring(0, qName.length() - 1).equalsIgnoreCase(
				"item_")) {
			int itemSlot = Integer
					.parseInt(qName.substring(qName.length() - 1));
			item[itemSlot] = true;
		}

	}

	/**
	 * Handles the end of an Element.
	 */
	public final void endElement(final String uri, final String localName,
			final String qName) {
		if (qName.equalsIgnoreCase("result")) {
			result = false;
		} else if (qName.equalsIgnoreCase("matches")) {
			matches = false;
		} else if (qName.equalsIgnoreCase("match")) {
			match = false;
			matchesList.add(cMatchDetails);
		} else if (qName.equalsIgnoreCase("match_seq_num")) {
			matchSeqNum = false;
		} else if (qName.equalsIgnoreCase("players")) {
			players = false;
		} else if (qName.equalsIgnoreCase("season")) {
			season = false;
		} else if (qName.equalsIgnoreCase("radiant_win")) {
			radiantWin = false;
		} else if (qName.equalsIgnoreCase("duration")) {
			duration = false;
		} else if (qName.equalsIgnoreCase("start_time")) {
			startTime = false;
		} else if (qName.equalsIgnoreCase("match_id")) {
			matchId = false;
		} else if (qName.equalsIgnoreCase("tower_status_radiant")) {
			towerStatusRadiant = false;
		} else if (qName.equalsIgnoreCase("tower_status_dire")) {
			towerStatusDire = false;
		} else if (qName.equalsIgnoreCase("barracks_status_radiant")) {
			barracksStatusRadiant = false;
		} else if (qName.equalsIgnoreCase("barracks_status_dire")) {
			barracksStatusDire = false;
		} else if (qName.equalsIgnoreCase("cluster")) {
			cluster = false;
		} else if (qName.equalsIgnoreCase("first_blood_time")) {
			firstBloodTime = false;
		} else if (qName.equalsIgnoreCase("lobby_type")) {
			lobbyType = false;
		} else if (qName.equalsIgnoreCase("human_players")) {
			humanPlayers = false;
		} else if (qName.equalsIgnoreCase("leagueid")) {
			leagueId = false;
		} else if (qName.equalsIgnoreCase("positive_votes")) {
			positiveVotes = false;
		} else if (qName.equalsIgnoreCase("negative_votes")) {
			negativeVotes = false;
		} else if (qName.equalsIgnoreCase("game_mode")) {
			gameMode = false;
		} else if (qName.equalsIgnoreCase("player")) {
			player = false;
			cPlayerStats.player = cPlayer;
			cMatchDetails.players.add(cPlayerStats);
		} else if (qName.equalsIgnoreCase("account_id")) {
			accountId = false;
		} else if (qName.equalsIgnoreCase("player_slot")) {
			playerSlot = false;
		} else if (qName.equalsIgnoreCase("hero_id")) {
			heroId = false;
		} else if (qName.equalsIgnoreCase("kills")) {
			kills = false;
		} else if (qName.equalsIgnoreCase("deaths")) {
			deaths = false;
		} else if (qName.equalsIgnoreCase("assists")) {
			assists = false;
		} else if (qName.equalsIgnoreCase("leaver_status")) {
			leaverStatus = false;
		} else if (qName.equalsIgnoreCase("gold")) {
			gold = false;
		} else if (qName.equalsIgnoreCase("lastHits")) {
			lastHits = false;
		} else if (qName.equalsIgnoreCase("denies")) {
			denies = false;
		} else if (qName.equalsIgnoreCase("gold_per_min")) {
			gpm = false;
		} else if (qName.equalsIgnoreCase("xp_per_min")) {
			xpm = false;
		} else if (qName.equalsIgnoreCase("gold_spent")) {
			goldSpent = false;
		} else if (qName.equalsIgnoreCase("hero_damage")) {
			heroDamage = false;
		} else if (qName.equalsIgnoreCase("tower_damage")) {
			towerDamage = false;
		} else if (qName.equalsIgnoreCase("hero_healing")) {
			heroHealing = false;
		} else if (qName.equalsIgnoreCase("level")) {
			level = false;
		} else if (qName.equalsIgnoreCase("lobby_type")) {
			lobbyType = false;
		} else if (qName.substring(0, qName.length() - 1).equalsIgnoreCase(
				"item_")) {
			int itemSlot = Integer
					.parseInt(qName.substring(qName.length() - 1));
			item[itemSlot] = false;
		}
	}

	/**
	 * Handling character data.
	 * 
	 * @param ch
	 *            the characters of the XML
	 * @param start
	 *            the current character data start
	 * @param length
	 *            the current character data length
	 * @throws SAXException
	 *             thrown if some parsing error occurs
	 */
	public final void characters(final char[] ch, final int start,
			final int length) throws SAXException {
		String cString = new String(ch, start, length);
		if (cString.equals("4294967295") && (!accountId)) {
			cString = "-1";
		}
		if (result) {
			if (matches) {
				if (match) {
					if (players) {
						if (player) {
							if (accountId) {
								cPlayer.accountId = cString;
							} else if (playerSlot) {
								cPlayer.playerSlot = Player.getRealPlayerSlot(Integer
										.parseInt(cString));
								cPlayer.isDire = Player.isPlayerDire(Integer
										.parseInt(cString));
							} else if (heroId) {
								cPlayer.heroId = Integer.parseInt(cString);
							} else if (kills) {
								cPlayerStats.kills = new BigInteger(cString);
							} else if (deaths) {
								cPlayerStats.deaths = new BigInteger(cString);
							} else if (assists) {
								cPlayerStats.assists = new BigInteger(cString);
							} else if (leaverStatus) {
								cPlayerStats.leaverStatus = new BigInteger(cString);
							} else if (gold) {
								cPlayerStats.gold = new BigInteger(cString);
							} else if (lastHits) {
								cPlayerStats.lastHits = new BigInteger(cString);
							} else if (denies) {
								cPlayerStats.denies = new BigInteger(cString);
							} else if (gpm) {
								cPlayerStats.gpm = new BigInteger(cString);
							} else if (xpm) {
								cPlayerStats.xpm = new BigInteger(cString);
							} else if (goldSpent) {
								cPlayerStats.goldSpent = new BigInteger(cString);
							} else if (heroDamage) {
								cPlayerStats.heroDamage = new BigInteger(cString);
							} else if (towerDamage) {
								cPlayerStats.towerDamage = new BigInteger(cString);
							} else if (heroHealing) {
								// TODO: change every Integer/Float/Double to BigInteger or BigDecimal to much hero healing for  integert
								cPlayerStats.heroHealing = new BigInteger(cString);
							} else if (level) {
								cPlayerStats.lvl = new BigInteger(cString);
							} else {
								int itemSlot = 6; // there are only 0 ... 5
								for (int s = 0; s < item.length; s++) {
									if (item[s]) {
										itemSlot = s;
										break;
									}
								}
								if (itemSlot < 6) {
									cPlayerStats.items[itemSlot] = Integer
											.parseInt(cString);
								} else {
									// TODO: some error message and reports
								}
							}
						}
					} else if (season) {
						cMatchDetails.season = new BigInteger(cString);
					} else if (radiantWin) {
						cMatchDetails.radiantWin = Boolean.parseBoolean(cString);
					} else if (duration) {
						cMatchDetails.duration = new BigInteger(cString);
					} else if (startTime) {
						cMatchDetails.startTime = new BigInteger(cString);
					} else if (matchId) {
						cMatchDetails.matchId = cString;
//						System.out.println("madtchId: "+cMatchDetails.matchId);
					} else if (matchSeqNum) {
						cMatchDetails.matchSeqNumber = cString;
					} else if (towerStatusRadiant) {
						cMatchDetails.towerStatusRadiant = new BigInteger(cString);
					} else if (towerStatusDire) {
						cMatchDetails.towerStatusDire = new BigInteger(cString);
					} else if (barracksStatusDire) {
						cMatchDetails.barracksStatusDire = new BigInteger(cString);
					} else if (barracksStatusRadiant) {
						cMatchDetails.barracksStatusRadiant = new BigInteger(cString);
					} else if (cluster) {
						cMatchDetails.cluster = cString;
					} else if (firstBloodTime) {
						cMatchDetails.firstBloodTime = new BigInteger(cString);
					} else if (lobbyType) {
						cMatchDetails.lobbyType = new BigInteger(cString);
					} else if (humanPlayers) {
						cMatchDetails.humanPlayers = new BigInteger(cString);
					} else if (leagueId) {
						cMatchDetails.leagueid = new BigInteger(cString);
					} else if (positiveVotes) {
						cMatchDetails.positiveVotes = new BigInteger(cString);
					} else if (negativeVotes) {
						cMatchDetails.negativeVotes = new BigInteger(cString);
					} else if (gameMode) {
						cMatchDetails.gameMode = Integer.parseInt(cString);
					}
				}
			}
		}
	}

	public LinkedList<MatchDetails> getMatchDetails() {
		return matchesList;
	}
}
