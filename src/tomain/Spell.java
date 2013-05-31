package tomain;

/**
 * TODO Put here a description of what this class does.
 *
 * @author yuhasem.
 *         Created Feb 25, 2013.
 */
public abstract class Spell extends Card {

	protected char type[] = new char[4]; //s for instant, f for infinity
	protected String title;
	
	public Spell() {
		super();
		this.name = "NULL SPELL";
		this.type[0] = 'm';
		this.type[1] = 'a';
		this.type[2] = 'n';
		this.type[3] = '1';
		this.title = null;
	}

	@Override
	public String getType(){
		return "s";
	}
}
