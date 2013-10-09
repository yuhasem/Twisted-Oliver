package tomain;

import java.util.Scanner;

import tocardinst.*;

/**
 * Run this file to start the game.
 * 
 * @author yuhasem. Created Feb 24, 2013.
 */
public class Control {

	/**
	 * Initializes the game (later we will implement deck choice) and starts the
	 * game running.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Card deck[] = new Card[40];
		Card deck2[] = new Card[40];
		for (int i = 0; i < 8; i++) {
			deck[i] = new RedOrb();
			deck2[i] = new RedOrb();
		}
		for (int i = 8; i < 16; i++) {
			deck[i] = new YellowOrb();
			deck2[i] = new YellowOrb();
		}
		for (int i = 16; i < 20; i++) {
			deck[i] = new TransparentCrystal();
			deck2[i] = new TransparentCrystal();
		}
		for (int i = 20; i < 30; i++) {
			deck[i] = new Wolverine();
			deck2[i] = new Wolverine();
		}
		for (int i = 30; i < 35; i++) {
			deck[i] = new Adreniline();
			deck2[i] = new Adreniline();
		}
		for (int i = 35; i < 39; i++) {
			deck[i] = new Shield();
			deck2[i] = new Shield();
		}
		deck[39] = new Plague();
		deck2[39] = new Plague();
		Player player1 = new Player(deck);
		Player player2 = new Player(deck2);
		Field field1 = new Field(player1);
		Field field2 = new Field(player2);
		field1.setOpposingField(field2);
		field2.setOpposingField(field1);
		Scanner input = new Scanner(System.in);
		int turn = 1;
		while (player1.getCollection() < Player.MAX_COLLECTION
				&& player2.getCollection() < Player.MAX_COLLECTION) {
			System.out.println("\n\t\tTURN: " + turn + "\t\tPLAYER: "
					+ field1.getConnectedPlayer());
			if (playerTurn(input, field1, field2)) {
				break;
			}
			System.out.println("\n\t\tTURN: " + turn + "\t\tPLAYER: "
					+ field2.getConnectedPlayer());
			playerTurn(input, field2, field1);
			turn++;
		}
		input.close();
		System.out.println("Game Over");
	}

	static private boolean playerTurn(Scanner input, Field adjacent,
			Field opposing) {
		// Player 1 Start Step
		adjacent.clearEnergyPool();
		boolean orbplay = false;
		// Player 1 Uncall Step
		for (int i = 0; i < adjacent.inPlay.size(); i++) {
			adjacent.inPlay.get(i).called = false;
			adjacent.inPlay.get(i).inPlayEffect(adjacent, opposing);
		}
		// Player 1 Draw
		if (!adjacent.draw()) {
			opposing.getConnectedPlayer().setCollection(Player.MAX_COLLECTION);
			return true;
		}
		System.out.println("");
		// Player 1 Main Step
		String line = "";
		while (line == "") {
			adjacent.printState();
			System.out.print("Action? [");
			if (adjacent.inHand.size() > 0) {
				System.out.print("play, viewHand, ");
			}
			if (adjacent.inPlay.size() > 0) {
				System.out.print("call, sack, viewPlay, ");
			}
			System.out.println("viewOppPlay, proceed]");
			if (input.hasNext()) {
				line = input.next();
				// System.out.println("Got next line");
			}
			if (line.equals("play")) {
				System.out
						.println("Which card? [use the numbers after the card listed in you hand]");
				int toPlay = -1;
				if (input.hasNextInt()) {
					toPlay = input.nextInt();
				}
				if (toPlay >= 0 && toPlay < adjacent.inHand.size()) {
					Card moving = adjacent.inHand.get(toPlay);
					if (moving.getType().charAt(0) == 'i'
							&& moving.getType().charAt(1) == 'o' && orbplay) {
						System.out.println("You have already played an orb");
					} else if (moving.getType().charAt(0) == 'i'
							&& moving.getType().charAt(1) == 'e') {
						System.out
								.println("Which Battler do you want to attach it to?");
						int toAttach = -1;
						if (input.hasNextInt()) {
							toAttach = input.nextInt();
						}
						if (toAttach >= 0 && toAttach < adjacent.inPlay.size()) {
							Card attach = adjacent.inPlay.get(toAttach);
							if (attach.getType().charAt(0) == 'b') {
								Battler attach2 = (Battler) attach;
								Item attaching = (Item) moving;
								attaching.onEquipEffect(attach2, adjacent,
										opposing);
							} else {
								System.out
										.println("Invalid card: not a Battler");
							}
						} else {
							System.out
									.println("Invalid number: not in Play size.");
						}
					} else if (moving.getType().charAt(0) == 's' && moving.getType().charAt(3) == 'c') {
						System.out.println("Professor Oak: Now's not the time to use that!");
					} else if (moving.getType().charAt(0) == 's' && moving.getType().charAt(3) == 't') {
						if (moving.onEnterEffect(adjacent, opposing)){
							//Do we need to put anything here? Traps onPlayEffect doesn't spring immediately.
							//And onEnter controls the actual moving, so it has to check if there is room.
						} else {
							System.out.println("Trap could not enter field."); //more specific error message in onEnterEffect.
						}
					} else {
						moving.onRevealEffect(adjacent, opposing);
						// Allow counter/trap --> this takes place in either onEnter or onPlay depending on the card.
						if (moving.onEnterEffect(adjacent, opposing)) {
							moving.onPlayEffect(adjacent, opposing);
							if (moving.getType().charAt(0) == 'i'
									&& moving.getType().charAt(1) == 'o') {
								orbplay = true;
							}
						} else {
							System.out.println("Failed to Enter field");
						}
					}
				}
				line = "";
			} else if (line.equals("proceed")) {
				// System.out.println("In 'Proceed'");
				// ends the input while loop, letting the attack step begin.
			} else if (line.equals("call")) {
				System.out
						.println("Which card? [use the numbers after card listed in play, (C) means that card is already called]");
				int toCall = -1;
				if (input.hasNextInt()) {
					toCall = input.nextInt();
				}
				if (toCall >= 0 && toCall < adjacent.inPlay.size()) {
					Card calling = adjacent.inPlay.get(toCall);
					calling.onCallEffect(adjacent, opposing);
				}
				line = "";
			} else if (line.equals("sack")) {
				System.out
						.println("Which card? [use the numbers after card listed in play]");
				int toSack = -1;
				if (input.hasNextInt()) {
					toSack = input.nextInt();
				}
				if (toSack >= 0 && toSack < adjacent.inPlay.size()) {
					Card sacking = adjacent.inPlay.get(toSack);
					sacking.onSackEffect(adjacent, opposing);
				}
				line = "";
			} else if (line.equals("viewHand")) {
				System.out
						.println("Which card? [use the numbers after card listed in hand]");
				int toView = -1;
				if (input.hasNextInt()) {
					toView = input.nextInt();
				}
				if (toView >= 0 && toView < adjacent.inHand.size()) {
					Card viewing = adjacent.inHand.get(toView);
					System.out.println(viewing.viewDescrip);
				}
				line = "";
			} else if (line.equals("viewPlay")) {
				System.out
						.println("Which card? [use the numbers after card listed in play]");
				int toView = -1;
				if (input.hasNextInt()) {
					toView = input.nextInt();
				}
				if (toView >= 0 && toView < adjacent.inPlay.size()) {
					Card viewing = adjacent.inPlay.get(toView);
					System.out.println(viewing.viewDescrip);
				}
				line = "";
			} else if (line.equals("viewOppPlay")) {
				System.out
						.println("Which card? [use the numbers after card listed in opponent's play]");
				int toView = -1;
				if (input.hasNextInt()) {
					toView = input.nextInt();
				}
				if (toView >= 0 && toView < opposing.inPlay.size()) {
					Card viewing = opposing.inPlay.get(toView);
					System.out.println(viewing.viewDescrip);
				}
				line = "";
			} else {
				System.out.println("Invalid command");
				line = "";
			}
		}
		// Player 1 Attack Step
		// adjacent.printAttackers(opposing);
		line = "";
		while (line == "") {
			adjacent.printState();
			System.out.print("Action? [");
			if (adjacent.inPlay.size() > 0) {
				System.out.print("call, sack, ");
			}
			if (adjacent.hasAttackers()) {
				System.out.print("attack, ");
			}
			System.out.println("viewHand, viewPlay, viewOppPlay, proceed]");
			if (input.hasNext()) {
				line = input.next();
				// System.out.println("Got next line");
			}
			if (line.equals("call")) {
				System.out
						.println("Which card? [use the numbers after card listed in play, (C) means that card is already called]");
				int toCall = -1;
				if (input.hasNextInt()) {
					toCall = input.nextInt();
				}
				if (toCall >= 0 && toCall < adjacent.inPlay.size()) {
					Card calling = adjacent.inPlay.get(toCall);
					calling.onCallEffect(adjacent, opposing);
				}
				line = "";
			} else if (line.equals("sack")) {
				System.out
						.println("Which card? [use the numbers after card listed in play]");
				int toSack = -1;
				if (input.hasNextInt()) {
					toSack = input.nextInt();
				}
				if (toSack >= 0 && toSack < adjacent.inPlay.size()) {
					Card sacking = adjacent.inPlay.get(toSack);
					sacking.onSackEffect(adjacent, opposing);
				}
				line = "";
			} else if (line.equals("attack")) {
				// do something!
				System.out
						.println("Which card will Attack? [use the numbers after card listed in play, (C) means that card has attacked or been called]");
				int toAtt = -1;
				if (input.hasNextInt()) {
					toAtt = input.nextInt();
				}
				if (toAtt >= 0 && toAtt < adjacent.inPlay.size()) {
					if (adjacent.inPlay.get(toAtt).getType().charAt(0) == 'b') {
						Battler attacking = (Battler) adjacent.inPlay
								.get(toAtt);
						// choose target
						int toTar = -2;
						System.out
								.println("Which card to Target? [use the numbers after card listed in opponent's play]");
						if (input.hasNextInt()) {
							toTar = input.nextInt();
						}
						if (toTar >= -1 && toTar < opposing.inPlay.size()) {
							if (toTar == -1) {
								if (!opposing.hasBattlers()) {
									adjacent.getConnectedPlayer()
											.changeCollection(1);
									attacking.called = true;
								}
							} else if (opposing.inPlay.get(toTar).getType()
									.charAt(0) == 'b') {
								Battler defending = (Battler) opposing.inPlay
										.get(toTar);
								attacking.attack(defending, adjacent, opposing);
							} else {
								System.out
										.println("Invalid card: not a Battler");
							}
						} else {
							System.out
									.println("Invalid entry: Number not within Player's hand size");
						}
					} else {
						System.out.println("Invalid card: not a Battler");
					}
				}
				if (adjacent.getConnectedPlayer().getCollection() > Player.MAX_COLLECTION
						|| opposing.getConnectedPlayer().getCollection() > Player.MAX_COLLECTION) {
					return true;
				}
				line = "";
			} else if (line.equals("proceed")) {
				// program proceeds by recognizing "proceed" does not equal ""
			} else if (line.equals("viewHand")) {
				System.out
						.println("Which card? [use the numbers after card listed in hand]");
				int toView = -1;
				if (input.hasNextInt()) {
					toView = input.nextInt();
				}
				if (toView >= 0 && toView < adjacent.inHand.size()) {
					Card viewing = adjacent.inHand.get(toView);
					System.out.println(viewing.viewDescrip);
				}
				line = "";
			} else if (line.equals("viewPlay")) {
				System.out
						.println("Which card? [use the numbers after card listed in play]");
				int toView = -1;
				if (input.hasNextInt()) {
					toView = input.nextInt();
				}
				if (toView >= 0 && toView < adjacent.inPlay.size()) {
					Card viewing = adjacent.inPlay.get(toView);
					System.out.println(viewing.viewDescrip);
				}
				line = "";
			} else if (line.equals("viewOppPlay")) {
				System.out
						.println("Which card? [use the numbers after card listed in opponent's play]");
				int toView = -1;
				if (input.hasNextInt()) {
					toView = input.nextInt();
				}
				if (toView >= 0 && toView < opposing.inPlay.size()) {
					Card viewing = opposing.inPlay.get(toView);
					System.out.println(viewing.viewDescrip);
				}
				line = "";
			} else {
				line = "";
				System.out.println("Invalid command");
			}
		}
		// Player 1 End Step
		for (int i = 0; i < adjacent.inPlay.size(); i++) {
			String type = adjacent.inPlay.get(i).getType();
			if (type.charAt(0) == 'b') {
				Battler toHeal = (Battler) adjacent.inPlay.get(i);
				toHeal.health = toHeal.defense;
			}
		}
		for (int i = 0; i < opposing.inPlay.size(); i++) {
			String type = opposing.inPlay.get(i).getType();
			if (type.charAt(0) == 'b') {
				Battler toHeal = (Battler) opposing.inPlay.get(i);
				toHeal.health = toHeal.defense;
			}
		}
		return false;
	}

	static private int counterTurn(Field adjacent, Field opposing, int code) {
		// Okay, so input codes may or may not be removed but here's how they go.
		//0: For test cases. In essence, gets called from nowhere.
		//1: Offending action is an attack.
		//2: Offending action is a spell.
		//3: Offending action is an enter effect.
		//4: Offending action is a miscellaneous, counterable effect.
		//
		// Should the Scanner be included as a parameter?

		// Okay, so return codes go like this:
		// -1: Something bad happened and counterTurn is returning without doing
		// the right thing. Receiving end should treat this as an error.
		// 0: counterTurn did nothing to affect the state of the system.
		// Receiving end should proceed as normal.
		// 1: counterTurn ended offending effect. Receiving end should react as
		// necessary.
		// 2: counterTurn removed offending card. Receiving end should react as
		// necessary.
		// 3: counterTurn defended defending card (without affecting offending
		// action). Receiving end should react as necessary.
		// 4: counterTurn changed the state of the Field, but did not end or
		// disrupt the offending action. Receiving action should proceed as
		// normal with caution.
		Scanner input = new Scanner(System.in);
		System.out.println(adjacent.getConnectedPlayer() + " is countering a move by " + opposing.getConnectedPlayer());
		String line = "";
		boolean hasCalledNonOrb = false;
		while (line.equals("")){
			adjacent.printState();
			System.out.print("What action will you take? [");
			if (adjacent.inPlay.size() > 0){
				System.out.print("call, ");
			}
			if (adjacent.hasCounters()){
				System.out.print("counter, ");
			}
			System.out.println("resolve]");
		}
		if (input.hasNext()){
			line = input.next();
		}
		if (line.equals("call")){
			System.out
			.println("Which card? [use the numbers after card listed in play, (C) means that card is already called]");
			int toCall = -1;
			if (input.hasNextInt()) {
				toCall = input.nextInt();
			}
			if (toCall >= 0 && toCall < adjacent.inPlay.size()) {
				Card calling = adjacent.inPlay.get(toCall);
				if (hasCalledNonOrb && calling.getType().charAt(0) != 'o'){
					System.out.println("You have already called a Non-Orb!");
				}
				calling.onCallEffect(adjacent, opposing);
				if (calling.called && calling.getType().charAt(0) != 'o'){
					hasCalledNonOrb = true;
				}
			}
			line = "";
		} else if (line.equals("counter")){
			//
		}
		input.close();
		return -1;
	}

}
