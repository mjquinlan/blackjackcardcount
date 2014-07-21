import org.scalatest.FlatSpec

import blackjackcardcount.Dealer
import blackjackcardcount.BlackJackPlayingCard

class TestDealer extends FlatSpec {
	
	"The dealer" should "be allowed to hit when his hand is under 17" in {
		val testdealer : Dealer = new Dealer();
		testdealer.addCard(new BlackJackPlayingCard(1));	//King
		testdealer.addCard(new BlackJackPlayingCard(9));	//Five
		assert(testdealer.computeScore()==15, "Dealer's score isn't computed correctly for king, five");
		assert(testdealer.canHit(), "Dealer.canHit() method failed to return the correct boolean when score == 15");
	}
	
	"The dealer" should "NOT hit when his hand is >= 17" in {
		val testdealer : Dealer = new Dealer();
		testdealer.addCard(new BlackJackPlayingCard(1));	//King
		testdealer.addCard(new BlackJackPlayingCard(7));	//Seven
		assert(testdealer.computeScore()==17, "Dealer's score isn't computed correctly for king, seven");
		assert(!testdealer.canHit(), "Dealer.canHit() method failed to return the correct boolean when score == 17");
		
		val testdealer2 : Dealer = new Dealer();
		testdealer2.addCard(new BlackJackPlayingCard(1));	//King
		testdealer2.addCard(new BlackJackPlayingCard(2));	//Queen
		assert(testdealer2.computeScore()==20, "Dealer's score isn't computed correctly for king, queen");
		assert(!testdealer2.canHit(), "Dealer.canHit() method failed to return the correct boolean when score == 20");
	}
	
}