package logic;

import java.security.cert.CertificateRevokedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import component.Card;
import component.Deck;

public class Logic {
	
	private Deck deck;
	
	public Logic(Deck deck) {
		this.deck = deck;
	}
	
	public float probabilityDrawing(Card card) {
		
		int occurence=0;
		
		for(Card c : this.deck.getCards()) {
			if(card.equals(c))
				occurence++;
		}
		return (float)occurence/this.deck.numberOfCardsRemaining();
	}
	
	public float probabilityDrawing(Card card, int deckSize) {
		int occurence=0;
		
		for(Card c : this.deck.getCards()) {
			if(card.equals(c))
				occurence++;
		}
		
		return (float)occurence/deckSize;
	}
	
	public float probabilityDrawingSuperiorTo(Card card) {
	int occurence=0;
		
		for(Card c : this.deck.getCards()) {
			if(card.getValue() < c.getValue())
				occurence++;
		}
		
		return (float)occurence/this.deck.numberOfCardsRemaining();
	}
	
	public float probabilityDrawingInferiorTo(Card card) {
		int occurence=0;
			
			for(Card c : this.deck.getCards()) {
				if(card.getValue() > c.getValue())
					occurence++;
			}
			
			return (float)occurence/this.deck.numberOfCardsRemaining();
		}
	
	public int remainingCardsInferiorTo(Card card) {
		int occurence=0;
		
		for(Card c : this.deck.getCards()) {
			if(card.getValue() > c.getValue())
				occurence++;
		}
		
		return occurence;
	}
	
	public int remainingCardsSuperiorTo(Card card) {
		int occurence=0;
		
		for(Card c : this.deck.getCards()) {
			if(card.getValue() < c.getValue())
				occurence++;
		}
		
		return occurence;
	}
	
	public List<Float> probabilitiesFromCard(Card card) {
		ArrayList<Float> probs = new ArrayList<Float>();
		Card cardTest = new Card(1);
		
		if(card.getValue() == Card.MIN_VALUE) {
			probs.add(new Float(0));
			for(int i = 2 ; i <= Card.MAX_VALUE ; i++) {
				cardTest.setValue(i);
				probs.add(probabilityDrawing(cardTest,remainingCardsSuperiorTo(new Card(Card.MIN_VALUE))));
			}
			return probs;
		}
		
		else if(card.getValue() == Card.MAX_VALUE) {
			for(int i = 1 ; i < Card.MAX_VALUE ; i++) {
				cardTest.setValue(i);
				probs.add(probabilityDrawing(cardTest,remainingCardsInferiorTo(new Card(Card.MAX_VALUE))));
				}	
			probs.add(new Float(0));
			return probs;
			}
		
		else {
			for(int i = 1 ; i<card.getValue();i++) {
				cardTest.setValue(i);
				probs.add(probabilityDrawing(cardTest,remainingCardsInferiorTo(new Card(card.getValue()))));
			}
			probs.add(new Float(0));
			for(int i = card.getValue()+1 ; i < Card.MAX_VALUE+1;i++) {
				cardTest.setValue(i);
				probs.add(probabilityDrawing(cardTest,remainingCardsSuperiorTo(new Card(card.getValue()))));
			}
			return probs;
		}	
	}
	
	public Map<Card,Float> bestTriplets(List<Float> probs, Card card) {
		
		Map<Card,Float> bestTriplet = new HashMap<Card,Float>();
		ArrayList<Float> plays = new ArrayList<Float>();
		int it=0;
		
		float maxValue = 0;
		
		for(int i = 1 ; i < card.getValue();i++) {
			plays.add(probs.get(i-1)*(card.getValue()-i));
		}
		
		plays.add(new Float(0));
		
		for(int i = card.getValue()+1 ; i <= Card.MAX_VALUE ; i++) {
			plays.add(probs.get((card.getValue()))*(i-card.getValue()));
		}
		
		System.out.println("Probabilité*gorgée pour chaques cartes");
		for(Float f : plays)
			System.out.println(f.toString());
		
		bestTriplet.put(card, new Float(0));
		
		for(int i = 1 ; i < card.getValue();i++) {
			if(plays.get(i-1) > maxValue) {
				maxValue = plays.get(i-1);
				it = i;
			}
		}
		
		bestTriplet.put(new Card(it), maxValue);
		maxValue = 0;
		
		for(int i = card.getValue()+1 ; i <= Card.MAX_VALUE ; i++) {
			if(plays.get(i-1) > maxValue) {
				maxValue = plays.get(i-1);
				it = i;
			}
		}
		
		bestTriplet.put(new Card(it), maxValue);
		
		return bestTriplet;
	}
}
