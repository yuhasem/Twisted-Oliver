package tomain;

/**
 * TODO Put here a description of what this class does.
 *
 * @author yuhasem.
 *         Created Feb 25, 2013.
 */
public abstract class Battler extends Card{

	protected char type; //c for creatures, w for warriors, a for walls, l for legendaries
	protected int speed;
	protected int attack;
	protected int defense;
	protected int health;
	protected String title;
	protected Item[] equips;
	
	public Battler() {
		super();
		this.name = "NULL BATTLER";
		this.speed = 0;
		this.attack = 0;
		this.defense = 0;
		this.health = 0;
		this.type = 'w';
		this.title = null;
		this.isTargetlable = true;
	}

	public abstract void onAttackEffect(Card attacked, Field a, Field b);
	
	public abstract void onDefenseEffect(Card attacker, Field a, Field b);
	
	public abstract void onTakeDamageEffect(Field a, Field b);
	
	public abstract void onDealDamageEffect(Field a, Field b);
	
	public abstract void onDefeatEffect(Card killer, Field a, Field b);
	
	public abstract void onKillEffect(Card killed, Field a, Field b);
	
	public void attack(Battler defender, Field afield, Field dfield){
		if(this.called){
			return;
		}
		this.onAttackEffect(defender, afield, dfield);
		this.called = true;
		defender.onDefenseEffect(this, dfield, afield);
		if (defender.speed > this.speed){
			this.health -= defender.attack;
			if (this.health <= 0){
				this.onDefeatEffect(defender, afield, dfield);
				defender.onKillEffect(this, dfield, afield);
				return;
			}
			this.onTakeDamageEffect(afield, dfield);
			defender.onDealDamageEffect(dfield, afield);

			defender.health -= this.attack;
			if (defender.health <= 0){
				defender.onDefeatEffect(this, dfield, afield);
				this.onKillEffect(defender, afield, dfield);
				return;
			}
			defender.onTakeDamageEffect(dfield, afield);
			this.onDealDamageEffect(afield, dfield);
		} else {
			defender.health -= this.attack;
			if (defender.health <= 0){
				defender.onDefeatEffect(this, dfield, afield);
				this.onKillEffect(defender, afield, dfield);
				return;
			}
			defender.onTakeDamageEffect(dfield, afield);
			this.onDealDamageEffect(afield, dfield);

			this.health -= defender.attack;
			if (this.health <= 0){
				this.onDefeatEffect(defender, afield, dfield);
				defender.onKillEffect(this, dfield, afield);
				return;
			}
			this.onTakeDamageEffect(afield, dfield);
			defender.onDealDamageEffect(dfield, afield);
		}
	}
	
	@Override
	public String getType(){
		return "b" + type;
	}
	
	public String getStats(){
		return this.speed + ":" + this.attack + "/" + this.health + ":" + this.reward + ":" + this.bounty;
	}
	
	public Item[] getEquips(){
		return this.equips;
	}
	
	public void changeStats(int deltaSpeed, int deltaAttack, int deltaDefense){
		this.speed += deltaSpeed;
		this.attack += deltaAttack;
		this.defense += deltaDefense;
		this.health += deltaDefense;
	}
}
