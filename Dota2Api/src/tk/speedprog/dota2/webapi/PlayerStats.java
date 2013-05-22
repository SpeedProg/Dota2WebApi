package tk.speedprog.dota2.webapi;

import java.math.BigInteger;
import java.util.LinkedList;

public class PlayerStats {
	public int[] items;
	public BigInteger kills, deaths, assists, leaverStatus, gold, lastHits,
			denies, gpm, xpm, goldSpent, heroDamage, towerDamage, heroHealing,
			lvl;
	public Player player;
	public LinkedList<AbilityUpgrade> abilityUpgrades;
	public LinkedList<AditionalUnit> aditionalUnites;

	public PlayerStats() {
		player = new Player();
		items = new int[6];
		abilityUpgrades = new LinkedList<AbilityUpgrade>();
		aditionalUnites = new LinkedList<AditionalUnit>();
		kills = new BigInteger("0");
		deaths = new BigInteger("0");
		assists = new BigInteger("0");
		leaverStatus = new BigInteger("0");
		gold = new BigInteger("0");
		lastHits = new BigInteger("0");
		denies  = new BigInteger("0");
		gpm = new BigInteger("0");
		xpm = new BigInteger("0");
		goldSpent = new BigInteger("0");
		heroDamage = new BigInteger("0");
		towerDamage  = new BigInteger("0");
		heroHealing = new BigInteger("0");
		lvl = new BigInteger("0");
	}
}
