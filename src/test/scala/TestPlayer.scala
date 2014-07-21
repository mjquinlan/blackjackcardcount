import org.scalatest.FlatSpec

import blackjackcardcount.Player
import blackjackcardcount.BlackJackPlayingCard

class TestPlayer extends FlatSpec {
	
	"A player" should "be allowed to hit when his hand is under 21" in {
		val testplayer : Player = new Player(100);
		testplayer.addCard(new BlackJackPlayingCard(1));	//King
		testplayer.addCard(new BlackJackPlayingCard(2));	//Queen
		assert(testplayer.computeScore()==20, "Player's score isn't computed correctly for king, queen");
		assert(testplayer.canHit(), "Player.canHit() method failed to return the correct boolean when score == 15");
	}
	
	"A player" should "NOT hit when his hand is >= 21" in {
		val testplayer : Player = new Player(100);
		testplayer.addCard(new BlackJackPlayingCard(0));	//Ace
		testplayer.addCard(new BlackJackPlayingCard(3));	//Jack
		assert(testplayer.computeScore()==21, "Player's score isn't computed correctly for ace, jack");
		assert(!testplayer.canHit(), "Player.canHit() method failed to return the correct boolean when score == 21");
	}
	
	"A player" should "have 80 chips after wagering 20" in {
		val testplayer : Player = new Player(100);
		assert(testplayer.betChips(20), "Player.betChips returned false when 20 were bet from the pool of 100");
		assert(testplayer.getNumberOfChips()==80, "Player.getNumberOfChips() didn't retun 80 after 20 were bet from a pool of 100");
	}
	
	"A player" should "not be able to wager more chips than he/she has" in {
		val testplayer : Player = new Player(100);
		assert(!testplayer.betChips(200), "Player was able to bet more chips than they had");
	}
	
	"A player" should "have 120 chips after wining 20" in {
		val testplayer : Player = new Player(100);
		assert(testplayer.betChips(20), "Player.betChips returned false when 20 were bet from the pool of 100");
		testplayer.addToChips(40);
		assert(testplayer.getNumberOfChips()==120, "Player.getNumberOfChips() didn't retun 120 after 20 were bet they won a hand");
	}
	
}