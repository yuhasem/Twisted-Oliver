/**
 * 
 */
package tocardinst;

import tomain.Battler;
import tomain.Field;
import tomain.Item;

/**
 * @author yuhasem
 * 
 */
public class OrangeOrb extends Item {

	/**
	 * 
	 */
	public OrangeOrb() {
		super();
		this.cost = "";
		this.name = "Orange Orb";
		this.title = null;
		this.type = 'o';
		this.viewDescrip = this.name
				+ "\n\tCost: "
				+ this.cost
				+ "\n\tType: Item, Orb\n\tOnCall: Provides 1 Orange energy.\n\tOn Sack: Provides 3 Orange energy.";
	}

	@Override
	public boolean onEquipEffect(Battler attach, Field a, Field b) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPlayEffect(Field a, Field b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRevealEffect(Field a, Field b) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCallEffect(Field a, Field b) {
		if (this.called){
			return;
		}
		a.provide("1O");
		this.called = true;
	}

	@Override
	public void onSackEffect(Field a, Field b) {
		if (this.called){
			return;
		}
		a.provide("3O");
		a.moveFromXToY(this, a.inPlay, a.inDiscard);

	}

	@Override
	public boolean onEnterEffect(Field a, Field b) {
		a.moveFromXToY(this, a.inHand, a.inPlay);
		return true;
	}

	@Override
	public void inPlayEffect(Field a, Field b) {
		// TODO Auto-generated method stub

	}

}
