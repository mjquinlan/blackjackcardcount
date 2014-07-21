package blackjackcardcount;

import java.util.ArrayDeque
import scala.collection.JavaConversions._

class BlackJackCardHolder {
	
	//Using an ArrayDeque so that Aces can be placed on the end, making score computations easier
	private val hand : ArrayDeque[BlackJackPlayingCard] = new ArrayDeque()
	
	def computeScore() : Int = {
		var score : Int = 0;
		
		//Needed to handle hands with multiple aces.
		var naces : Int = 0;
		for(card <- hand) {
			if(card.value == 11) naces+=1;
		}
		
		var aces_processed : Int = 0;
		for(card <- hand) {
			if(card.value == 11) {	//card is an Ace
				val aces_remaining : Int = naces-aces_processed-1;
				if(score+card.value>21 || (aces_remaining>0 && score+card.value+aces_remaining>21)) {
					score += 1;
				}else{
					score += card.value;
				}
				aces_processed += 1;
			}else{
				score += card.value;
			}
		}
		return score;
	}
	
	def addCard(card : BlackJackPlayingCard) = {
		if(card.value==11){		//card is an Ace -- put it at the far end of the deque
			hand.addLast(card);
		}else{
			hand.addFirst(card);
		}
	}
	
	def flushCards() = {
		hand.clear()
	}
	
	//produce a string describing the holder's hand
	override def toString() : String = {
		var result : String = "";
		for(card <- hand) {
			result += "\t" + card.toString + "\n";
		}
		return result;
	}
	
	//produce a string with the first card hidden
	def baseGetHiddenHand() : String = {
		var result : String = "\t" + "(hidden)\n";
		var toggle : Boolean = false;			//used to skip the first card...
		for(card <- hand) {
			if(!toggle){
				toggle = true;
			}else{
				result += "\t" + card.toString + "\n";
			}
		}
		return result;
	}
	
}