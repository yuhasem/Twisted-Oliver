package tomain;

/**
 * TODO Put here a description of what this class does.
 *
 * @author yuhasem.
 *         Created Feb 25, 2013.
 */
public abstract class Item extends Card {

	protected char type; //o for orbs, e for equips, b for baubles, l for valuables.
	protected String title;
	
	public Item(){
		super();
		this.name = "NULL ITEM";
		this.type = 'v';
		this.title = "NULL";
	}

	public abstract boolean onEquipEffect(Battler attach, Field a, Field b);
	
	@Override
	public String getType(){
		return "i";
	}
}
