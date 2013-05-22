package tk.speedprog.dota2.webapi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import javax.xml.parsers.SAXParser;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import tk.speedprog.dota2.webapi.exceptions.RetryException;

public class Dota2Api {
	// API Key 313FD5AAE6E80CB2BCDB7075689FC995
	private static final String API_GET_MATCH_HISTORY_PATH = "http://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?format=xml";
	private static final String API_GET_MATCH_DETAILS_PATH = "http://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?format=xml";
	private static final String API_GET_MATCH_HISTORY_BY_SEQUENCE_NUM = "http://api.steampowered.com/IDOTA2Match_570/GetMatchHistoryBySequenceNum/v0001/?format=xml";
	private static final String API_GET_PLAYER_SUMMARIES = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?format=xml";
	private static final String API_ACCOUNTID_PREFIX = "&account_id=";
	private static final String API_KEY_PREFIX = "&key=";
	private static final String API_START_AT_MATCH_ID_PREFIX = "&start_at_match_id=";
	private static final String API_STEAMIDS_PREFIX = "&steamids=";
	private static final String API_MATCH_ID_PREFIX = "&match_id=";

	private static final String API_START_AT_MATCH_SEQUENCE_NUM_PREFIX = "&start_at_match_seq_num=";

	// http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?format=xml&key=313FD5AAE6E80CB2BCDB7075689FC995&steamids=76561197960435530
	// 72835237
	// https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?format=xml&key=313FD5AAE6E80CB2BCDB7075689FC995&match_id=

	private static String getContentFromApiUrl(String apiUrl)
			throws RetryException {
		StringBuilder sb = new StringBuilder();
		URL url = null;
		String line;
		try {
			url = new URL(apiUrl);
		} catch (MalformedURLException e) {
			url = null;
			e.printStackTrace();
		}

		BufferedReader in;
		try {
			InputStream is = url.openStream();
			in = new BufferedReader(new InputStreamReader(is));
			try {
				while ((line = in.readLine()) != null)
					sb.append(line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			if (e instanceof FileNotFoundException) {
				e.printStackTrace();
				return null;
			}
			if (e instanceof java.net.ConnectException) {
				try {
					Thread.sleep(1000);
					throw new RetryException(
							"ConnectException: Please try again!");
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			if (e.getMessage().contains("503")) {
				System.out.println("Http Response 503, sleeping for 60s.");
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				throw (new RetryException("Http Error 503, plz try again!"));
			} else if (e.getMessage().contains("500")) {
				System.out.println("Http Response 500, sleeping for 60s.");
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				throw (new RetryException("Http Error 500, plz try again!"));
			} else {
				System.out.println("Exception message: " + e.getMessage());
			}
			e.printStackTrace();
			return null;
		}

		return sb.toString();
	}

	private static InputStream getStreamFromApiUrl(String path)
			throws RetryException {
		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			url = null;
			e.printStackTrace();
		}
		if (url != null) {
			do {
				try {
					return url.openStream();
				} catch (IOException e) {
					if (e instanceof FileNotFoundException) {
						e.printStackTrace();
						return null;
					}
					if (e instanceof java.net.ConnectException) {
						try {
							Thread.sleep(1000);
							throw new RetryException(
									"ConnectException: Please try again!");
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					if (e.getMessage().contains("503")) {
						System.out
								.println("Http Response 503, sleeping for 60s.");
						try {
							Thread.sleep(60000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						throw (new RetryException(
								"Http Error 503, plz try again!"));
					} else if (e.getMessage().contains("500")) {
						System.out
								.println("Http Response 500, sleeping for 60s.");
						try {
							Thread.sleep(60000);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						throw (new RetryException(
								"Http Error 500, plz try again!"));
					} else {
						System.out.println("Exception message: "
								+ e.getMessage());
					}
					e.printStackTrace();
					return null;
				}
			} while (true);
		}
		return null;

	}

	// TODO: this methode does nothing then causing net traffic atm -_-
	public static void getHeroListAsJava(SAXParser saxParser, String apiKey)
			throws SAXException, IOException {
		// https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001/?format=xml&key=313FD5AAE6E80CB2BCDB7075689FC995&language=en_us

		HeroesXMLHandler hxh = new HeroesXMLHandler();
		InputStream apiResultStream = null;
		boolean gotHttpError;
		do {
			gotHttpError = false;
			try {
				apiResultStream = getStreamFromApiUrl("https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001/?format=xml&key="
						+ apiKey + "&language=en_us");
			} catch (RetryException e) {
				gotHttpError = true;
				e.printStackTrace();
			}
		} while (gotHttpError);
		if (apiResultStream != null) {
			saxParser.parse(apiResultStream, hxh);
		}
	}

	public static LinkedList<MatchDetails> getMatchDetailsBySequenceNum(
			String lastSeqNum, String apiKey, SAXParser saxParser)
			throws SAXException, IOException {
		String apiUrl = API_GET_MATCH_HISTORY_BY_SEQUENCE_NUM + API_KEY_PREFIX
				+ apiKey;
		if (lastSeqNum != null) {
			apiUrl = apiUrl + API_START_AT_MATCH_SEQUENCE_NUM_PREFIX
					+ lastSeqNum;
		}
		// InputStream apiResultStream = null;
		String content = null;
		boolean gotHttpError;
		do {
			gotHttpError = false;
			try {
				// apiResultStream = getStreamFromApiUrl(apiUrl);
				content = getContentFromApiUrl(apiUrl);
			} catch (RetryException e) {
				gotHttpError = true;
				e.printStackTrace();
			}
		} while (gotHttpError);
		MatchHistoryBySequenceNumXMLHandler mhbsnxh = new MatchHistoryBySequenceNumXMLHandler();
		/*
		 * if (apiResultStream != null) { saxParser.parse(apiResultStream,
		 * mhbsnxh); return mhbsnxh.getMatchDetails(); } else { }
		 */
		if (content != null) {
			saxParser
					.parse(new InputSource(new StringReader(content)), mhbsnxh);
			return mhbsnxh.getMatchDetails();
		} else {
		}
		return null;
	}

	public static MatchDetails getMatchDetails(String matchId, String apiKey,
			SAXParser saxParser) throws SAXException, IOException {
		MatchDetailsXMLHandler matchDetailsXMLHandler = new MatchDetailsXMLHandler();
		try {
			System.out.println(API_GET_MATCH_DETAILS_PATH + API_KEY_PREFIX
					+ apiKey + API_MATCH_ID_PREFIX + matchId);
			saxParser.parse(API_GET_MATCH_DETAILS_PATH + API_KEY_PREFIX
					+ apiKey + API_MATCH_ID_PREFIX + matchId,
					matchDetailsXMLHandler);
		} catch (SAXException se) {
			return null;
		}
		return matchDetailsXMLHandler.getMatchDetails();
	}

	public static MatchHistoryResult getMatchHistory(String steamId,
			String fromMatchId, String apiKey, SAXParser saxParser)
			throws SAXException, IOException {
		MatchHistoryXMLHandler matchHistoryXMLHandler = new MatchHistoryXMLHandler();
		String request = API_GET_MATCH_HISTORY_PATH + API_KEY_PREFIX + apiKey
				+ API_ACCOUNTID_PREFIX + steamId;
		System.out.println("MatchHistoryRequest: " + request);
		if (fromMatchId != null) {
			long lastMatchId = Long.parseLong(fromMatchId);
			request = request + API_START_AT_MATCH_ID_PREFIX
					+ (lastMatchId + 1);
		}
		InputStream apiResultStream = null;
		boolean gotHttpError;
		do {
			gotHttpError = false;
			try {
				apiResultStream = getStreamFromApiUrl(request);
			} catch (RetryException e) {
				gotHttpError = true;
				e.printStackTrace();
			}
		} while (gotHttpError);

		if (apiResultStream != null) {
			saxParser.parse(apiResultStream, matchHistoryXMLHandler);
			return matchHistoryXMLHandler.getMatchList();
		}

		return null;
	}

	public static String[] getPlayerSummaries(String steamId32, String apiKey,
			SAXParser saxParser) {
		PlayerSummariesXMLHander psxh = new PlayerSummariesXMLHander();
		String url = API_GET_PLAYER_SUMMARIES + API_KEY_PREFIX + apiKey
				+ API_STEAMIDS_PREFIX + getSteamId64FromSteamId32(steamId32);
		InputStream stream = null;
		boolean gotHttpError;
		do {
			gotHttpError = false;
			try {
				stream = getStreamFromApiUrl(url);
			} catch (RetryException e) {
				gotHttpError = true;
				e.printStackTrace();
			}
		} while (gotHttpError);
		try {
			saxParser.parse(stream, psxh);
			return psxh.data;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static String getSteamId64FromSteamId32(String steamId32) {
		BigInteger biSteamId32 = new BigInteger(steamId32);
		BigInteger biStreamId64 = biSteamId32.add(new BigInteger(
				"76561197960265728"));
		return biStreamId64.toString();
	}
}
