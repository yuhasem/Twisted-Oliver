Twisted-Oliver
==============

A simple Card Game and Card Game Engine

Rules:

This card game draws a lot of inspiration from MTG, Pokemon TCG, and Yu-Gi-Oh, so if you have played any of those it should be rather easy to follow.

Phases:

There is a simple phase system that controls what actions you can take at which time.

	First there is a Start step where you energy pool is reset.

	Second there is the Uncall step in which all cards are uncalled.
	
	Third there is the Draw Step in which you draw a card. If you cannot draw a card because your deck is empty, you lose.

	Next there is the Main Step. During this step you may play a card, call a card, or sack a card. You may take as many of these actions as many times as you so choose. Cards that do not have call or sack effects can still be called or sacked, but there is rarely, if any, benefit to this. When playing a card you must have the correct amount of energy available to fulfill its cost. Only one Orb may be played per turn. Only one Valuable Item may be played per turn. Only one Legendary Battler may be played per turn. Only one Legendary Spell may be used per turn.

	Next there is the Attack Step. During the Attack step you may call or sack cards just as during the Main Step. If you have any Battlers, you may also attack with them. Each battler may only attack once per turn. A Battler cannot attack and use a call effect in the same turn. Battlers can attack other battlers or they can attack players directly, if there are no Battlers on the opposing field.

	Lastly there is the End Step in which Battlers heal.

Energy:

There are 9 types of Energy: Red, Orange, Yellow, Green, Blue, Purple, White, Black, and Clear.

When you provide energy of Red, Orange, Yellow, Green, Blue, or Purple colors, you can use their respective colored energy, white energy, or clear energy. White energy can only be provided by White and Clear energy, while Black can be provided by any color except for White. Clear energy can be provided by any color and can provide any color energy.

Types of Cards:

There are 3 types of cards, each of which can be further classified based on its properties.

	Items:

	Items are cards which remain in the field after being played. Items can be Orbs, Equips, Baubles, or Valuables.
	
	An orb is an item that is called to provide energy. Most orbs can be sacked to provide more energy, however sacking sends cards to the discard so it will no longer be available for your use afterwards.
	
	An equip must be attached to a Battler upon entering the field. An equip can augment a Battlers stats, or give it extra powers such as additional call effects, sack effects, or boosts on actions such as attacking and defending. Each Battler has a limit to the amount of equips it can hold. Usually this is one equips for Cratures and two equips for Warriors.
	
	A Bauble is an Item which simply remains in play. There is no limit to the number of Baubles you can have in play at any given time. Baubles normally provide interesting effects that modify play to give you an advantage. Occasionally, Baubles will provide additional effects when called or sacked.
	
	A Valuable is an extremely powerful Item which functions similar to a Bauble. Because of their immense power and usefulness, only one Valuable may be played per turn, and only three can be kept in play at a given time.
	
	Battlers:
	
	Battlers are cards which remain in the field after being played. Battlers attack opposing Battlers, and other targetable Cards, with the ultimate goal of earning collection points which are used to win the game. Battlers are the only way of earning collection points, and it is therefore necessary to include some in your deck. Battlers can be Creatures, Warriors, Walls, or Legendaries.
	
	Any number of Creatures, Warriors, or Walls can be in play at given time. Creatures and Warriors can attack, where as Walls cannot. Walls generally have very high defense, however and are difficult to take down. This prevents the opponent from attacking you directly and earning easy collection points. Legendary Battlers are extremely powerful and unique Cards that can easily sway any battle in your favor. However, only one Legendary can played per turn and if two Legendaries with the same title are ever in play (this includes both your's and opponents's cards) both will be instatnly removed from play.
	
	Spells:
	
	Spells are enchantments which could either remain in play or be resolve immediately after affecting the field.
	
	Spells can either be Mundane or Legendary. Mundane spells are simple and any number can be played per turn. Legendary Spells are powerful and only one can be played per turn.
	
	Spells can vary in scope as either Field or Augments. Field Spells affect many Cards at once while Augments affect only one card.
	
	Spells can either be instants or infinities. Instants resolve immediately and after their effect has been casted, they are sent to the discard. Infinity Spells enter the field and remain there until they are sent to the discard (either by the cards effect, or by being targeted).
	
Winning the Game:

The Game is won when one of the players reaches 30 Collection Points. Collection points are obtainable by either destroying an opponent's Battler or by attacking the opponent directly. When a Battler is defeated it gives a number of collection points equal to its reward plus its bounty. Reward is a passive statistic, while Bounty changes as a card gains fame in battle.

Battling:

When battling there are three important statistics: Speed, Attack, and Health. In Game the statistics are shown as Spedd:Attack/Health:Reward:Bounty (e.g. for a Wolverine who has just entered the field it would look like 60:2/1:2:0).

The faster Battler always strikes first and does damage equal to its Attack to the opponent's Health. If the slower Battler was not slain, It then counter-attacks dealing its attack in damage to the opponent's health. If a Battler kills an opponent in battle, its bounty increases by one. If it kills a Legendary, its bounty increases by 2. If it survives in battle against a Legendary, its bounty increases by 1.

At the end of every turn all Battler's health's are reset to the Battler's defense. When defense increases or decreases, health usually increases or decreases by the same amount. Spells that alter an opponent's defense and subsequently alter its helath to be at or below zero do not necessarily defeat that Battler or remove it from play. In this case it will still have to be attacked (but even an attack of zero can beat it). Attack can never drop below zero (Because we don't want you healing the opponent).
