package tomain;

import java.util.ArrayList;
import java.util.Random;

/**
 * TODO Put here a description of what this class does.
 *
 * @author yuhasem.
 *         Created Feb 24, 2013.
 */
public class Field {

	private Player connectedPlayer;
	public ArrayList<Card> inPlay;
	public ArrayList<Card> inHand;
	public ArrayList<Card> inDeck;
	public ArrayList<Card> inDiscard;
	private ArrayList<Character> energy;
	private Field opposingField;
	//R for red, O for orange, Y for yellow, G for green, B for Blue, V for violet, W for white, L for black, C for clear
	
	public Field(Player player){
		this.connectedPlayer = player;
		this.inDeck = new ArrayList<Card>();
		this.inDiscard = new ArrayList<Card>();
		this.inHand = new ArrayList<Card>();
		this.inPlay = new ArrayList<Card>();
		this.energy = new ArrayList<Character>();
		
		this.inDeck = this.connectedPlayer.getDeck();
		//System.out.println(this.inDeck.size());
		this.shuffle(this.inDeck);
		for (int i = 0; i < 7; i++){
			this.draw();
		}
	}
	
	public void provide(String energy){
		for (int i = 0; i < energy.length(); i = i + 2){
			for (int j = 0; j < Integer.parseInt(Character.toString(energy.charAt(i))); j++){
				this.energy.add(energy.charAt(i + 1));
			}
		}
	}
	
	public boolean consume(String cost){
		//Add rules for W, L, and C later
		ArrayList<Character> store = this.energy;
		for (int i = 0; i < cost.length(); i = i + 2){
			out:
			for (int j = 0; j < Integer.parseInt(Character.toString(cost.charAt(i))); j++){
				char toConsume = cost.charAt(i + 1);
				if (this.energy.contains(toConsume)){
					this.energy.remove(new Character(cost.charAt(i + 1)));
				} else if (toConsume == 'L'){
					for (int k = 0; k < this.energy.size(); i++){
						if (this.energy.get(k) != 'W'){
							this.energy.remove(this.energy.get(k));
							continue out;
						}
					}
					//TODO: Black energy is still broken but less severely so.
					this.energy = store;
					return false;
				} else if (toConsume == 'C') {
					if (this.energy.size() > 0){
						this.energy.remove(0);
					} else {
						this.energy = store;
						return false;
					}
				} else {
					if (this.energy.contains(new Character('C'))){
						this.energy.remove(new Character('C'));
					} else {
						this.energy = store;
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void moveFromXToY(Card toMove, ArrayList<Card> x, ArrayList<Card> y){
		if (x.remove(toMove)){
			y.add(toMove);
		}
	}

	/**
	 * Returns the value of the field called 'opposingField'.
	 * @return Returns the opposingField.
	 */
	public Field getOpposingField() {
		return this.opposingField;
	}

	/**
	 * Sets the field called 'opposingField' to the given value.
	 * @param opposingField The opposingField to set.
	 */
	public void setOpposingField(Field opposingField) {
		this.opposingField = opposingField;
	}
	
	public boolean draw(){
		if (this.inDeck.size() == 0){
			return false;
		}
		this.moveFromXToY(this.inDeck.get(0), this.inDeck, this.inHand);
		return true;
	}
	
	public void shuffle(ArrayList<Card> toShuffle){
		ArrayList<Card> deck = new ArrayList<Card>();
		Random generator = new Random();
		while (toShuffle.size() > 0){
			int random = generator.nextInt(toShuffle.size());
			deck.add(toShuffle.remove(random));
		}
		for (int i = 0; i < deck.size(); i++){
			toShuffle.add(deck.get(i));
		}
	}
	
	public void clearEnergyPool(){
		this.energy = new ArrayList<Character>();
	}
	
	//TODO: Can we get this to display battler stats, please?
	public void printState(){
		System.out.println("-----Field State---------------------------");
		System.out.println("Opposing Player: " + this.getOpposingField().getConnectedPlayer().toString());
		System.out.print("\tCurrent Energy Pool: ");
		for (int i = 0; i < this.opposingField.energy.size(); i++){
			System.out.print(this.opposingField.energy.get(i) + " ");
		}
		System.out.println("");
		System.out.println("\tCollection: " + this.getOpposingField().getConnectedPlayer().getCollection());
		System.out.println("\tIn Deck: " + this.getOpposingField().inDeck.size());
		System.out.println("\tIn Discard: " + this.getOpposingField().inDiscard.size());
		System.out.println("\tIn Hand: " + this.getOpposingField().inHand.size());
		System.out.println("\tIn Play: ");
		for (int i = 0; i < this.getOpposingField().inPlay.size(); i++){
			System.out.print("\t\t" + this.getOpposingField().inPlay.get(i).name + "(" + i + ")");
			if (this.getOpposingField().inPlay.get(i).called){
				System.out.print("(C)");
			}
			if (this.getOpposingField().inPlay.get(i).getType().charAt(0) == 'b'){
				Battler stat = (Battler) this.getOpposingField().inPlay.get(i);
				System.out.print(stat.getStats());
			}
			System.out.println(", ");
		}
		System.out.println("\n");
		System.out.println("This Player: " + this.getConnectedPlayer().toString());
		System.out.print("\tCurrent Energy Pool: ");
		for (int i = 0; i < this.energy.size(); i++){
			System.out.print(this.energy.get(i) + " ");
		}
		System.out.println("");
		System.out.println("\tIn Play: ");
		for (int i = 0; i < this.inPlay.size(); i++){
			System.out.print("\t\t" + this.inPlay.get(i).name + "(" + i + ")");
			if (this.inPlay.get(i).called){
				System.out.print("(C)");
			}
			if (this.inPlay.get(i).getType().charAt(0) == 'b'){
				Battler stat = (Battler) this.inPlay.get(i);
				System.out.print(stat.getStats());
			}
			System.out.println(", ");
		}
		System.out.println("\tIn Hand: ");
		for (int i = 0; i < this.inHand.size(); i++){
			System.out.print("\t\t" + this.inHand.get(i).name + "(" + i + ") ");
			if (this.inHand.get(i).getType().charAt(0) == 'b'){
				Battler stat = (Battler) this.inHand.get(i);
				System.out.print(stat.getStats());
			}
			System.out.println(", ");
		}
		System.out.println("\tCollection: " + this.getConnectedPlayer().getCollection());
		System.out.println("\tIn Deck: " + this.inDeck.size());
		System.out.println("\tIn Discard: " + this.inDiscard.size());
		System.out.println("");
		System.out.println("-------------------------------------------");
	}
	
	public Player getConnectedPlayer(){
		return this.connectedPlayer;
	}
	
	public boolean hasAttackers(){
		for (int i = 0; i < this.inPlay.size(); i++){
			if (this.inPlay.get(i).getType().charAt(0) == 'b'){
				if (this.inPlay.get(i).getType().charAt(1) == 'a'){
					continue;
				}
				return true;
			}
		}
		return false;
	}
}
