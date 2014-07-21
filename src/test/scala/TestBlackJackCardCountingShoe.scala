import org.scalatest.FlatSpec

import scala.collection.mutable.HashSet

import blackjackcardcount.BlackJackCardCountingShoe
import blackjackcardcount.BlackJackPlayingCard

class TestBlackJackCardHolder extends FlatSpec {
	
	"The shoe" should "continuously return cards when the end is reached" in {
		val testshoe : BlackJackCardCountingShoe = new BlackJackCardCountingShoe(1);
		testshoe.shuffle();
		for(i <- 0 until 54){
			val testcard : BlackJackPlayingCard = testshoe.next()
			assert(testcard.value < 12, "BlackJackCardCountingShoe.next() didn't smoothly reshuffle");
		}
	}
	
	"The set of playing cards in a one deck shoe" should "be complete" in {
		val testshoe : BlackJackCardCountingShoe = new BlackJackCardCountingShoe(1);
		testshoe.shuffle();
		val card_set : HashSet[Int] = HashSet()
		for(i <- 0 until 52){
			val testcard : BlackJackPlayingCard = testshoe.next();
			card_set += testcard.hashCode;
		}
		assert(card_set.size==52, "BlackJackCardCountingShoe(1) didn't hold 52 unique cards");
	}
	
	"The HiLo count" should "start at zero on a new set of decks" in {
		val testshoe : BlackJackCardCountingShoe = new BlackJackCardCountingShoe(6);
		testshoe.shuffle()
		assert(testshoe.getHiLoCount()==0, "BlackJackCardCountingShoe.getHiLoCount() returned "+testshoe.getHiLoCount()+" right after shuffle");
	}
	
	"The Zen count" should "start at zero on a new set of decks" in {
		val testshoe : BlackJackCardCountingShoe = new BlackJackCardCountingShoe(6);
		testshoe.shuffle()
		assert(testshoe.getZenCount()==0, "BlackJackCardCountingShoe.getZenCount() returned "+testshoe.getZenCount()+" right after shuffle");
	}
	
	"The HiLo count" should "end at zero after running a single deck" in {
		val testshoe : BlackJackCardCountingShoe = new BlackJackCardCountingShoe(1);
		testshoe.shuffle()
		for(i <- 0 until 52){
			val testcard : BlackJackPlayingCard = testshoe.next();
		}
		assert(testshoe.getHiLoCount()==0, "BlackJackCardCountingShoe.getHiLoCount() returned "+testshoe.getHiLoCount()+" after running the whole deck");
	}
	
	"The Zen count" should "end at zero after running a single deck" in {
		val testshoe : BlackJackCardCountingShoe = new BlackJackCardCountingShoe(1);
		testshoe.shuffle()
		for(i <- 0 until 52){
			val testcard : BlackJackPlayingCard = testshoe.next();
		}
		assert(testshoe.getZenCount()==0, "BlackJackCardCountingShoe.getZenCount() returned "+testshoe.getZenCount()+" after running the whole deck");
	}
	
	"The HiLo count" should "end at zero after running a two decks" in {
		val testshoe : BlackJackCardCountingShoe = new BlackJackCardCountingShoe(2);
		testshoe.shuffle()
		for(i <- 0 until 52*2){
			val testcard : BlackJackPlayingCard = testshoe.next();
		}
		assert(testshoe.getHiLoCount()==0, "BlackJackCardCountingShoe.getHiLoCount() returned "+testshoe.getHiLoCount()+" after running all decks");
	}
	
	"The Zen count" should "end at zero after running a two decks" in {
		val testshoe : BlackJackCardCountingShoe = new BlackJackCardCountingShoe(2);
		testshoe.shuffle()
		for(i <- 0 until 52*2){
			val testcard : BlackJackPlayingCard = testshoe.next();
		}
		assert(testshoe.getZenCount()==0, "BlackJackCardCountingShoe.getZenCount() returned "+testshoe.getZenCount()+" after running all decks");
	}
	
}