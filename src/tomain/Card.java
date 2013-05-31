package tomain;

/**
 * TODO Put here a description of what this class does.
 *
 * @author yuhasem.
 *         Created Feb 24, 2013.
 */
public abstract class Card {

	private static int IDENTIFICATION = 0;
	protected int id;
	protected String cost;
	protected String name;
	protected boolean called;
	protected int reward;
	protected int bounty;
	protected String viewDescrip;
	
	public Card(){
		this.id = IDENTIFICATION;
		IDENTIFICATION++;
		this.cost = null;
		this.name = "NULL";
		this.called = false;
		this.reward = 0;
		this.bounty = 0;
		this.viewDescrip = "The Null Card";
	}
	
	public abstract void onPlayEffect(Field a, Field b);
	
	public abstract void onRevealEffect(Field a, Field b);
	
	public abstract void onCallEffect(Field a, Field b);
	
	public abstract void onSackEffect(Field a, Field b);
	
	public abstract boolean onEnterEffect(Field a, Field b);
	
	public abstract void inPlayEffect(Field a, Field b);
	
	public String getType(){
		return "c";
	}
}
