package tk.speedprog.dota2.webapi;

public class Player {
	public String accountId;
	public boolean isDire;
	public int playerSlot;
	public int heroId;
	public String name;
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(">>>>>>AccountId: "+accountId+"\n");
		sb.append(">>>>>>isDire: "+isDire+"\n");
		sb.append(">>>>>>playerSlot: "+playerSlot+"\n");
		sb.append(">>>>>>heroId: "+heroId+"\n");
		return sb.toString();
	}
	
	/**
	 * Returns the real playerslot 0..9.
	 * 
	 * @param slot
	 *            the players slot gotten from the web api
	 * @return the players slot
	 */
	public static int getRealPlayerSlot(int slot) {
		if ( Player.isPlayerDire(slot)) {
			return ((slot - 128) + 5);
		} else {
			return slot;
		}
	}
	
	public static boolean isPlayerDire(int slot) {
		return (slot > 127 ? true : false);
	}
}
