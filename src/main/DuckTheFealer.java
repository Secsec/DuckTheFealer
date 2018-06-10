package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import component.Card;
import component.Deck;
import logic.Logic;

public class DuckTheFealer {
	public static void main (String[] args) {
		Deck deck = new Deck();
		System.out.println(deck.toString());
		
		deck.shuffle();
		deck.shuffle();
		deck.shuffle();
		
		System.out.println(deck.toString());
		System.out.println(deck.numberOfCardsRemaining());
		
		Logic logic = new Logic(deck);
		
		System.out.println();
		
		List<Float> a = new ArrayList<Float>();
		Map<Card,Float> b = new HashMap<Card,Float>();
		a = logic.probabilitiesFromCard(new Card(10));
		
		System.out.println(deck.numberOfCardsRemaining());
		
		deck.removeCard(new Card(1));
		deck.removeCard(new Card(1));
		deck.removeCard(new Card(1));
		deck.removeCard(new Card(1));
		
		deck.removeCard(new Card(2));
		deck.removeCard(new Card(3));
		
		
		
		System.out.println(deck.numberOfCardsRemaining());
		
		a = logic.probabilitiesFromCard(new Card(10));
		
		System.out.println("--------------------------");
		
		for(Float f : a)
			System.out.println(f);
		
		b = logic.bestTriplets(logic.probabilitiesFromCard(new Card(10)), new Card(10));
		
		System.out.println("--------------------------");
		
		for(Float f : a)
			System.out.println(f);
		
		System.out.println("*--------------------------*");
		
		for (Map.Entry<Card, Float> entry : b.entrySet()) {
			System.out.println(entry.getKey().toString());
			System.out.println(entry.getValue().toString());
		}
		
	}
}
