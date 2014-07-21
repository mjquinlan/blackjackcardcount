package blackjackcardcount;

import scala.collection.mutable.{Queue, ArrayBuffer, HashSet}
import scala.util.Random

class BlackJackCardShoe(ndecks : Int) {
	
	protected val cardqueue : Queue[BlackJackPlayingCard] = Queue();
	
	private val rgenerator : Random = new Random();
	
	protected val cards_per_deck : Int = 52;
	
	def shuffle() = {
		cardqueue.dequeueAll(_ => true);
		
		val exclude_set : HashSet[Int] = HashSet[Int]();
		for(index <- 0 until ndecks*cards_per_deck){
			val rint : Int = getRandomIntegerWithExcludes(ndecks*cards_per_deck,exclude_set)
			val modulated_card_id : Int = rint % cards_per_deck;
			cardqueue.enqueue( new BlackJackPlayingCard( modulated_card_id ) )
			exclude_set += rint;
		}
	}
	
	def getRandomIntegerWithExcludes(max_range : Int, excludes : HashSet[Int]) : Int = {
		val choices : ArrayBuffer[Int] = ArrayBuffer();
		for(index <- 0 until max_range) {
			if(!excludes.contains(index)) {
				choices += index
			}
		}
		if(choices.length<1) {
			println("No possible return values in BlackJackCardShoe::getRandomIntegerWithExcludes") 
			return -1;
		}		
		val returnval : Int = choices( rgenerator.nextInt(choices.length) )
		return returnval;
	}
	
	def next() : BlackJackPlayingCard = {
		if(cardqueue.isEmpty) shuffle();
		return cardqueue.dequeue();
	}
	
}