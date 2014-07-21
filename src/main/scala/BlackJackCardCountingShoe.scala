package blackjackcardcount;

import scala.math.ceil

class BlackJackCardCountingShoe(ndecks : Int) extends BlackJackCardShoe(ndecks) {
	
	private var countHiLo : Int = 0;
	private var countZen : Int = 0;
	
	private var suppressPrintOnInit = true;
	
	override def shuffle() = {
		super.shuffle()
		if(!suppressPrintOnInit) {
			println("The dealer has re-shuffled the shoe. Resetting the counts. Reconsider your play!")
		}else{
			suppressPrintOnInit=false;
		}
		countHiLo = 0;
		countZen = 0;
	}
	
	override def next() : BlackJackPlayingCard = {
		val thecard : BlackJackPlayingCard = super.next();
		
		//Update counts
		processHiLo(thecard);
		processZen(thecard);
		
		return thecard;
	}
	
	//processes the input card using the HiLo deck model as defined http://en.wikipedia.org/wiki/Card_counting
	protected def processHiLo(thecard : BlackJackPlayingCard) : Unit = thecard.face_name match {
		case "Ace" => countHiLo -= 1;
		case "King" => countHiLo -= 1;
		case "Queen" => countHiLo -= 1;
		case "Jack" => countHiLo -= 1;
		case "Ten" => countHiLo -= 1;
		case "Nine" => countHiLo += 0;
		case "Eight" => countHiLo += 0;
		case "Seven" => countHiLo += 0;
		case "Six" => countHiLo += 1;
		case "Five" => countHiLo += 1;
		case "Four" => countHiLo += 1;
		case "Three" => countHiLo += 1;
		case "Two" => countHiLo += 1;
		case _ => {
			println("Invalid card passed to processHiLo name: "+thecard.face_name)
			return;
		}
	}
	
	//processes the input card using the Zen deck model as defined http://en.wikipedia.org/wiki/Card_counting
	protected def processZen(thecard : BlackJackPlayingCard) : Unit = thecard.face_name match {
		case "Ace" => countZen -= 1;
		case "King" => countZen -= 2;
		case "Queen" => countZen -= 2;
		case "Jack" => countZen -= 2;
		case "Ten" => countZen -= 2;
		case "Nine" => countZen += 0;
		case "Eight" => countZen += 0;
		case "Seven" => countZen += 1;
		case "Six" => countZen += 2;
		case "Five" => countZen += 2;
		case "Four" => countZen += 2;
		case "Three" => countZen += 1;
		case "Two" => countZen += 1;
		case _ => {
			println("Invalid card passed to processZen name: "+thecard.face_name)
			return;
		}
	}
	
	def getHiLoCount() : Float = {
		var decks_left : Int = ceil(cardqueue.size.toFloat/cards_per_deck.toFloat).toInt
		if(decks_left == 0) decks_left += 1;
		return countHiLo.toFloat/decks_left.toFloat;
	}
	
	def getZenCount() : Float = {
		var decks_left : Int = ceil(cardqueue.size.toFloat/cards_per_deck.toFloat).toInt
		if(decks_left == 0) decks_left += 1;
		return countZen.toFloat/decks_left.toFloat;
	}
	
}