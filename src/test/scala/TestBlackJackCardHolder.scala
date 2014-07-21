import org.scalatest.FlatSpec

import blackjackcardcount.BlackJackCardHolder
import blackjackcardcount.BlackJackPlayingCard

class TestBlackJackCardHolder extends FlatSpec {
	
	"The Blackjack hand " should "have a score of 21" in {
		val testhand : BlackJackCardHolder = new BlackJackCardHolder();
		testhand.addCard(new BlackJackPlayingCard(0));	//Ace
		testhand.addCard(new BlackJackPlayingCard(1));	//King
		assert(testhand.computeScore()==21, "BlackJackCardHolder.computeScore() failed to compute black jack for ace, king");
	}
	
	"The order of ace appearance" should "not matter" in {
		val testhand : BlackJackCardHolder = new BlackJackCardHolder();
		testhand.addCard(new BlackJackPlayingCard(1));	//King
		testhand.addCard(new BlackJackPlayingCard(0));	//Ace
		assert(testhand.computeScore()==21, "BlackJackCardHolder.computeScore() failed to compute black jack for king, ace");
		val testhand2 : BlackJackCardHolder = new BlackJackCardHolder();
		testhand2.addCard(new BlackJackPlayingCard(0));	//Ace
		testhand2.addCard(new BlackJackPlayingCard(1));	//King
		testhand2.addCard(new BlackJackPlayingCard(5));	//Nine
		assert(testhand2.computeScore()==20, "BlackJackCardHolder.computeScore() failed to compute score for ace, king, nine");
	}
	
	"Aces " should " be correctly counted with value 11 or 1 when more than one exists" in {
		val testhand : BlackJackCardHolder = new BlackJackCardHolder();
		testhand.addCard(new BlackJackPlayingCard(0));	//Ace
		testhand.addCard(new BlackJackPlayingCard(1));	//King
		testhand.addCard(new BlackJackPlayingCard(13));	//Ace
		assert(testhand.computeScore()==12, "BlackJackCardHolder.computeScore() failed to compute score for ace, king, ace");
		
		val testhand2 : BlackJackCardHolder = new BlackJackCardHolder();
		testhand2.addCard(new BlackJackPlayingCard(0));		//Ace
		testhand2.addCard(new BlackJackPlayingCard(13));	//Ace
		assert(testhand2.computeScore()==12, "BlackJackCardHolder.computeScore() failed to compute score for ace, ace");
		
		val testhand3 : BlackJackCardHolder = new BlackJackCardHolder();
		testhand3.addCard(new BlackJackPlayingCard(0));		//Ace
		testhand3.addCard(new BlackJackPlayingCard(13));	//Ace
		testhand3.addCard(new BlackJackPlayingCard(26));	//Ace
		assert(testhand3.computeScore()==13, "BlackJackCardHolder.computeScore() failed to compute score for ace, ace, ace");
	}
}