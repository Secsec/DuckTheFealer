package component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	private List<Card> cards;
	
	public Deck() {
		this.cards = new ArrayList<Card>();
		for(int i = 0; i < 4 ; i++) {
			for(int j = 1 ; j < 14 ; j++)
				cards.add(new Card(j));
		}
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
	public void shuffle() {
		Collections.shuffle(this.cards);
	}
	
	public int numberOfCardsRemaining() {
		return this.cards.size();
	}
	
	public void removeCard(Card card) {
		for(Card c : this.cards) {
			if(c.equals(card)) {
				System.out.println("card found");
				this.cards.remove(c);
				return;
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("[ ");
		
		for(Card c : this.cards)
			sb.append(c.toString());
		
		sb.append(" ]");
		return sb.toString();
	}
}
