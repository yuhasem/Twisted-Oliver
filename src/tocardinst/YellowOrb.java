package tocardinst;

import tomain.Battler;
import tomain.Field;
import tomain.Item;

/**
 * TODO Put here a description of what this class does.
 *
 * @author yuhasem.
 *         Created May 27, 2013.
 */
public class YellowOrb extends Item {

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 */
	public YellowOrb(){
		this.cost = "";
		this.name = "Yellow Orb";
		this.title = null;
		this.type = 'o';
		this.viewDescrip = this.name + "\n\tCost: " + this.cost + "\n\tType: Item, Orb\n\t" +
		"On Call: Provides 1 Yellow Energy.\n\tOn Sack: Provides 3 Yellow Energy.";
	}
	
	@Override
	public boolean onEquipEffect(Battler attach, Field a, Field b) {
		return false;
	}

	@Override
	public void onPlayEffect(Field a, Field b) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void onRevealEffect(Field a, Field b) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void onCallEffect(Field a, Field b) {
		if (this.called){
			return;
		}
		a.provide("1Y");
		this.called = true;

	}

	@Override
	public void onSackEffect(Field a, Field b) {
		if (this.called){
			return;
		}
		a.provide("3Y");
		a.moveFromXToY(this, a.inPlay, a.inDiscard);

	}

	@Override
	public boolean onEnterEffect(Field a, Field b) {
		a.moveFromXToY(this, a.inHand, a.inPlay);
		return true;
	}

	@Override
	public void inPlayEffect(Field a, Field b) {
		// TODO Auto-generated method stub.

	}
	
	@Override
	public String getType(){
		return super.getType() + this.type;
	}

}
