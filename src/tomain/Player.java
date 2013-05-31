package tomain;

import java.util.ArrayList;

/**
 * TODO Put here a description of what this class does.
 *
 * @author yuhasem.
 *         Created Feb 24, 2013.
 */
public class Player {

	public static final int MAX_COLLECTION = 30;
	
	private int collection;
	private ArrayList<Card> deck;
	static int number = 0;
	int myNum;
	
	public Player(Card[] deck){
		this.setCollection(0);
		this.deck = new ArrayList<Card>();
		for (int i = 0; i < deck.length; i++){
			this.deck.add(deck[i]);
		}
		number++;
		myNum = number;
	}

	/**
	 * Returns the value of the field called 'lifeTotal'.
	 * @return Returns the lifeTotal.
	 */
	public int getCollection() {
		return this.collection;
	}

	/**
	 * Sets the field called 'lifeTotal' to the given value.
	 * @param lifeTotal The lifeTotal to set.
	 * @return false if life drops to 0
	 */
	public boolean setCollection(int lifeTotal) {
		this.collection = lifeTotal;
		if (this.collection >= MAX_COLLECTION){
			return false;
		}
		return true;
	}
	
	public boolean changeCollection(int delta){
		this.collection += delta;
		if (this.collection >= MAX_COLLECTION){
			return false;
		}
		return true;
	}
	
	public ArrayList<Card> getDeck(){
		return this.deck;
	}
	
	@Override
	public String toString(){
		return "Player "+this.myNum;
	}
}
