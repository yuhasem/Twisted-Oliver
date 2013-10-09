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
public class GreenOrb extends Item {

	/**
	 * 
	 */
	public GreenOrb() {
		this.cost = "";
		this.name = "Green Orb";
		this.title = null;
		this.type = 'o';
		this.viewDescrip = this.name + "\n\tCost: " + this.cost + "\n\tType: Item, Orb\n\t" +
		"On Call: Provides 1 Green energy.\n\tOn Sack: Provides 3 Green energy.";
	}

	/* (non-Javadoc)
	 * @see tomain.Item#onEquipEffect(tomain.Battler, tomain.Field, tomain.Field)
	 */
	@Override
	public boolean onEquipEffect(Battler attach, Field a, Field b) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see tomain.Card#onPlayEffect(tomain.Field, tomain.Field)
	 */
	@Override
	public void onPlayEffect(Field a, Field b) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tomain.Card#onRevealEffect(tomain.Field, tomain.Field)
	 */
	@Override
	public void onRevealEffect(Field a, Field b) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see tomain.Card#onCallEffect(tomain.Field, tomain.Field)
	 */
	@Override
	public void onCallEffect(Field a, Field b) {
		if (this.called){
			return;
		}
		a.provide("1G");
		this.called = true;
	}

	/* (non-Javadoc)
	 * @see tomain.Card#onSackEffect(tomain.Field, tomain.Field)
	 */
	@Override
	public void onSackEffect(Field a, Field b) {
		if (this.called){
			return;
		}
		a.provide("3G");
		a.moveFromXToY(this, a.inPlay, a.inDiscard);
	}

	/* (non-Javadoc)
	 * @see tomain.Card#onEnterEffect(tomain.Field, tomain.Field)
	 */
	@Override
	public boolean onEnterEffect(Field a, Field b) {
		a.moveFromXToY(this, a.inHand, a.inPlay);
		return true;
	}

	/* (non-Javadoc)
	 * @see tomain.Card#inPlayEffect(tomain.Field, tomain.Field)
	 */
	@Override
	public void inPlayEffect(Field a, Field b) {
		// TODO Auto-generated method stub

	}

}
