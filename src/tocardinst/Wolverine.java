package tocardinst;

import tomain.Battler;
import tomain.Card;
import tomain.Field;
import tomain.Item;

/**
 * TODO Put here a description of what this class does.
 *
 * @author yuhasem.
 *         Created May 27, 2013.
 */
public class Wolverine extends Battler {
	
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 */
	public Wolverine(){
		this.cost="1R1Y";
		this.name = "Wolverine";
		this.reward = 2;
		this.speed = 60;
		this.attack = 2;
		this.defense = 1;
		this.type = 'c';
		this.viewDescrip = this.name + "\n\tCost: " + this.cost + "\n\tType: Batller, Creature\n\t" +
		"Speed: " + this.speed + "\n\tAttack/Defense: " + this.attack + "/" + this.defense + "\n\t" +
		"Reward: " + this.reward + "\n\t" + "No Additional Effects.";
		this.equips = new Item[1];
		this.equips[0] = null;
	}
	
	@Override
	public void onAttackEffect(Card defender, Field a, Field b) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void onDefenseEffect(Card attacker, Field a, Field b) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void onTakeDamageEffect(Field a, Field b) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void onDealDamageEffect(Field a, Field b) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void onDefeatEffect(Card killer, Field a, Field b) {
		b.getConnectedPlayer().changeCollection(this.reward+this.bounty);
		if (this.equips[0] != null){
			a.inDiscard.add(this.equips[0]);
			this.equips[0] = null;
		}
		a.moveFromXToY(this, a.inPlay, a.inDiscard);
	}

	@Override
	public void onKillEffect(Card killed, Field a, Field b) {
		this.bounty++;
	}

	@Override
	public void onPlayEffect(Field a, Field b) {
		//no
	}

	@Override
	public void onRevealEffect(Field a, Field b) {
		// TODO Auto-generated method stub.

	}

	@Override
	public void onCallEffect(Field a, Field b) {
		this.called = true;
		if (this.equips[0] != null){
			this.equips[0].onCallEffect(a, b);
		}
	}

	@Override
	public void onSackEffect(Field a, Field b) {
		if (this.called){
			return;
		}
		if (this.equips[0] != null){
			a.inDiscard.add(this.equips[0]);
			this.equips[0] = null;
		}
		a.moveFromXToY(this, a.inPlay, a.inDiscard);
	}

	@Override
	public boolean onEnterEffect(Field a, Field b) {
		if (a.consume(this.cost)){
			a.moveFromXToY(this, a.inHand, a.inPlay);
			return true;
		} else {
			return false;
		}
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
