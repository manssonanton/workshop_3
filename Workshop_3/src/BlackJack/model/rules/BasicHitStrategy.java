package BlackJack.model.rules;

import BlackJack.model.Card;
import BlackJack.model.Player;

class BasicHitStrategy implements IHitStrategy {

	public boolean DoHit(Player a_dealer) {
		return a_dealer.CalcScore() < hitLimit(a_dealer);  
	}
	private int hitLimit(Player a_dealer){
		Iterable<Card> temp_hand = a_dealer.GetHand();
		int score=0;
		int cardScores[] = {
				2, 3, 4, 5, 6, 7, 8, 9, 10, 10 ,10 ,10, 11
		};
		boolean ace = false;
		for(Card c : temp_hand) {
			if(c.GetValue() == Card.Value.Ace){
				ace = true;
			}
		}
		if(a_dealer.CalcScore() == 17 && ace){	
			for(Card c : temp_hand) {
				if (c.GetValue() != Card.Value.Hidden && c.GetValue() != Card.Value.Ace) {
					score += cardScores[c.GetValue().ordinal()];
				}
			}
			if(score == 6){
				return 21;
			}
		}
		return 17;
	}

}