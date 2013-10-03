package tocardinst;

import tomain.Battler;
import tomain.Field;
import tomain.Spell;

/**
 * TODO Put here a description of what this class does.
 *
 * @author yuhasem.
 *         Created May 31, 2013.
 */
public class Plague extends Spell {

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 */
	public Plague() {
		this.cost = "5L";
		this.name = "The Plague";
		this.title = "BOARD_NUKE";
		this.reward = 0;
		this.type[0] = 'l';
		this.type[1] = 'f';
		this.type[2] = 'n';
		this.type[3] = 's';
		this.viewDescrip =  this.name + "\n\tCost: " + this.cost + "\n\tType: Legendary Instant Field Spell\n\t" +
		"When played, this card sends all Batllers to the discard on both sides of the field.";
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
		if (a.consume(this.cost)){
			for (int i = a.inPlay.size() - 1; i >= 0; i--){
				if (a.inPlay.get(i).getType().charAt(0) == 'b'){
					Battler one = (Battler) a.inPlay.get(i);
					one.onDefeatEffect(this, a, b);
				}
			}
			for (int i = b.inPlay.size() - 1; i >= 0; i--){
				if (b.inPlay.get(i).getType().charAt(0) == 'b'){
					Battler one = (Battler) b.inPlay.get(i);
					one.onDefeatEffect(this, b, a);
				}
			}
			a.moveFromXToY(this, a.inHand, a.inDiscard);
			return true;
		}
		return false;
	}

	@Override
	public void inPlayEffect(Field a, Field b) {
		// TODO Auto-generated method stub.

	}

	@Override
	public String getType(){
		return super.getType() + this.type[0] + this.type[1] + this.type[2] + this.type[3];
	}
}
