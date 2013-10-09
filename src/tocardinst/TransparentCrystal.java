package tocardinst;

import java.util.Scanner;

import tomain.Battler;
import tomain.Field;
import tomain.Item;

/**
 * TODO Put here a description of what this class does.
 *
 * @author yuhasem.
 *         Created May 27, 2013.
 */
public class TransparentCrystal extends Item {

	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 */
	public TransparentCrystal(){
		this.cost = "1C";
		this.name = "Transparent Crystal";
		this.title = null;
		this.type = 'o';
		this.viewDescrip = this.name + "\n\tCost: " + this.cost + "\n\tType: Item, Orb\n\t" +
		"On Call Effect: Provide 1 Clear energy.\n\tOn Sack Effect: Provide 2 Clear Energy.\n\t" +
		"On Sack Effect: Costs 2 Black Energy: Provide 2 Clear Energy. This card gets shuffled " +
		"Into the deck, instead of being sent to the discard.";
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
		a.provide("1C");
		this.called = true;
	}

	@Override
	public void onSackEffect(Field a, Field b) {
		if (this.called){
			return;
		}
		System.out.println("Would you like to consume two black energy and have this card shuffled into you deck instead of the discard? [y/n]: ");
		Scanner read = new Scanner(System.in);
		String line = "n";
		if (read.hasNext()){
			line = read.next();
		}
		if (line.equals("y") || line.equals("Y") || line.equals("yes")){
			if (a.consume("2L")) {
				a.provide("2C");
				a.moveFromXToY(this, a.inPlay, a.inDeck);
				a.shuffle(a.inDeck);
			} else {
				System.out.println("could not find two black energy");
			}
		} else {
			a.provide("2C");
			a.moveFromXToY(this, a.inPlay, a.inDiscard);
		}
		read.close();
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
}
