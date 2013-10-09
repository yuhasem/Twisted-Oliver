package tocardinst;

import tomain.Battler;
import tomain.Field;
import tomain.Item;

public class PurpleOrb extends Item {

	public PurpleOrb() {
		this.cost = "";
		this.name = "Purple Orb";
		this.title = null;
		this.type = 'o';
		this.viewDescrip = this.name + "\n\tCost: " + this.cost + "\n\tType: Item, Orb\n\t" +
		"On Call: Provides 1 Violet energy.\n\tOn Sack: Provides 3 Violet energy.";
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
		a.provide("1V");
		this.called = true;
	}

	@Override
	public void onSackEffect(Field a, Field b) {
		if (this.called){
			return;
		}
		a.provide("3V");
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
