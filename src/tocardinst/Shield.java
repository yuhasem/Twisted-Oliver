package tocardinst;

import tomain.Battler;
import tomain.Field;
import tomain.Item;

/**
 * TODO Put here a description of what this class does.
 *
 * @author yuhasem.
 *         Created May 31, 2013.
 */
public class Shield extends Item {
	
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 */
	public Shield(){
		super();
		this.cost = "2R";
		this.name = "A Shield";
		this.reward = 0;
		this.type = 'e';
		this.viewDescrip = this.name + "\n\tCost: " + this.cost + "\n\tType: Item, Equip\n\t" +
		"On Equip Effect: Attached Battler gets +0:+0/+2";
	}

	@Override
	public boolean onEquipEffect(Battler attach, Field a, Field b) {
		for (int i = 0; i < attach.getEquips().length; i++){
			if (attach.getEquips()[i] == null){
				if (a.consume(this.cost)){
					attach.getEquips()[i] = this;
					a.inHand.remove(this);
					attach.changeStats(0, 0, 2);
					return true;
				} else {
					return false;
				}
			}
		}
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
		// TODO Auto-generated method stub.

	}

	@Override
	public void onSackEffect(Field a, Field b) {
		// TODO Auto-generated method stub.

	}

	@Override
	public boolean onEnterEffect(Field a, Field b) {
		// TODO Auto-generated method stub.
		return true;
	}

	@Override
	public void inPlayEffect(Field a, Field b) {
		// TODO Auto-generated method stub.

	}
}
