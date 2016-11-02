package BlackJack.model;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Player implements ObserverManager{

	private List<Card> m_hand;
	protected final int g_maxScore = 21;
	private List<Observer> m_observers;
	public Player()
	{
		m_hand = new LinkedList<Card>();
		m_observers = new ArrayList<Observer>();
	}

	public void DealCard(Card a_addToHand)
	{
		m_hand.add(a_addToHand);
		notifyObservers();
	}
	public Iterable<Card> GetHand()
	{
		return m_hand;
	}

	public void ClearHand()
	{
		m_hand.clear();
	}

	public void ShowHand()
	{
		for(Card c : m_hand)
		{
			c.Show(true);
		}
	}

	public int CalcScore()
	{
		// the number of scores is dependant on the number of scorable values
		// as it seems there is no way to do this check at compile time in java ?!
		// cardScores[13] = {...};
		int cardScores[] = {
				2, 3, 4, 5, 6, 7, 8, 9, 10, 10 ,10 ,10, 11
		};
		assert (cardScores.length == Card.Value.Count.ordinal()) : "Card Scores array size does not match number of card values";


		int score = 0;

		for(Card c : GetHand()) {
			if (c.GetValue() != Card.Value.Hidden)
			{
				score += cardScores[c.GetValue().ordinal()];
			}
		}

		if (score > g_maxScore)
		{
			for(Card c : GetHand())
			{
				if (c.GetValue() == Card.Value.Ace && score > g_maxScore)
				{
					score -= 10;
				}
			}
		}
		return score;
	}

	@Override
	public void addObserver(Observer o) {
		m_observers.add(o);
	}

	@Override
	public void notifyObservers() {
		for (int i = 0; i < m_observers.size(); i++) {
			m_observers.get(i).Update();
		}
	}
}