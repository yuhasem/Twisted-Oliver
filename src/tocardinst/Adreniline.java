package tocardinst;

import java.util.Scanner;

import tomain.Battler;
import tomain.Field;
import tomain.Spell;

/**
 * TODO Put here a description of what this class does.
 *
 * @author yuhasem.
 *         Created May 31, 2013.
 */
public class Adreniline extends Spell {
	
	/**
	 * TODO Put here a description of what this constructor does.
	 *
	 */
	public Adreniline(){
		this.cost = "2Y";
		this.name = "Adreniline";
		this.reward = 0;
		this.type[0] = 'm';
		this.type[1] = 'a';
		this.type[2] = 'n';
		this.type[3] = 's';
		this.viewDescrip = this.name + "\n\tCost: " + this.cost + "\n\tType: Mundane Instant Augment Spell\n\t" +
		"Target Battler permanently gets +30:+0/+0.";
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
		System.out.println("Which battler will get this effect?");
		Scanner read = new Scanner(System.in);
		int toEffect = -1;
		if (read.hasNextInt()){
			toEffect = read.nextInt();
		}
		if (toEffect >= 0 && toEffect < a.inPlay.size()){
			if (a.inPlay.get(toEffect).getType().charAt(0) == 'b'){
				if (a.consume(this.cost)){
					Battler affected = (Battler) a.inPlay.get(toEffect);
					affected.changeStats(30, 0, 0);
					a.moveFromXToY(this, a.inHand, a.inDiscard);
					read.close();
					return true;
				} else {
					read.close();
					return false;
				}
			} else {
				System.out.println("Invalid Card: not a Battler");
			}
		} else {
			System.out.println("Invalid Number: not in range of Play");
		}
		read.close();
		return false;
	}

	@Override
	public void inPlayEffect(Field a, Field b) {
		// TODO Auto-generated method stub.

	}
}
