import org.scalatest.FlatSpec

import scala.collection.mutable.HashSet

import blackjackcardcount.BlackJackCardShoe
import blackjackcardcount.BlackJackPlayingCard

class TestBlackCardShoe extends FlatSpec {
	
	"Random integer generation" should "respect the exclude list" in {
		val testshoe : BlackJackCardShoe = new BlackJackCardShoe(1);
		val excludes : HashSet[Int] = HashSet(1);
		assert(testshoe.getRandomIntegerWithExcludes(1, excludes)==0, "BlackJackCardShoe.getRandomIntegerWithExcludes() didn't get zero when [0,1) range provided");
		excludes += 0;
		assert(testshoe.getRandomIntegerWithExcludes(3, excludes)==2, "BlackJackCardShoe.getRandomIntegerWithExcludes() didn't get 2 when [2,3) range provided");
	}
	
	"Shuffling " should "suceed and return a card with value less than 12" in {
		val testshoe : BlackJackCardShoe = new BlackJackCardShoe(2);
		testshoe.shuffle();
		val testcard : BlackJackPlayingCard = testshoe.next()
		assert( testcard.value < 12, "BlackJackCardShoe.next() pulled card that shouldn't exist" );
	}
	
	"The shoe" should "continuously return cards when the end is reached" in {
		val testshoe : BlackJackCardShoe = new BlackJackCardShoe(1);
		testshoe.shuffle();
		for(i <- 0 until 54){
			val testcard : BlackJackPlayingCard = testshoe.next()
			assert(testcard.value < 12, "BlackJackCardShoe.next() didn't smoothly reshuffle");
		}
	}
	
	"The set of playing cards in a one deck shoe" should "be complete" in {
		val testshoe : BlackJackCardShoe = new BlackJackCardShoe(1);
		testshoe.shuffle();
		val card_set : HashSet[Int] = HashSet()
		for(i <- 0 until 52){
			val testcard : BlackJackPlayingCard = testshoe.next();
			card_set += testcard.hashCode;
		}
		assert(card_set.size==52, "BlackJackCardShoe(1) didn't hold 52 unique cards");
	}
	
}