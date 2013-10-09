package tomain;

/**
 * TODO Put here a description of what this class does.
 *
 * @author yuhasem.
 *         Created Jun 6, 2013.
 */
public interface Targetable {

	public void onTargetedEffect(Spell targeter, Field a, Field b);
	
	public void onRemoveEffect(Spell remover, Field a, Field b);
}
