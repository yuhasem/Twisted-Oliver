package tomain;

/**
 * TODO Put here a description of what this class does.
 *
 * @author yuhasem.
 *         Created Feb 25, 2013.
 */
public abstract class Spell extends Card {

	protected char type[] = new char[4];
	protected String title;
	
	public Spell() {
		super();
		this.name = "NULL SPELL";
		this.type[0] = 'm'; //m for mundane, l for legendary
		this.type[1] = 'a'; //a for augment, f for field, t for targeter
		this.type[2] = 'n'; //n for non-counter, c for counter, t for trap
		this.type[3] = 's'; //s for instant, f for infinity
		this.title = null;
	}

	@Override
	public String getType(){
		return "s" + this.type[0] + this.type[1] + this.type[2] + this.type[3];
	}
}
