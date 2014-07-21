package blackjackcardcount;

object BlackJackUI {
	
	val starting_chips : Int = 100;
	var ndecks : Int = 1;
	val dealer : Dealer = new Dealer
	val player : Player = new Player(starting_chips)
	var shoe : BlackJackCardCountingShoe = null;
	
	def main(args : Array[String]) = {
		println("Welcome to the game!")
		println(" You start with "+starting_chips+" chips and must bet at least one each round.")
		println(" The dealer will stay at 17. Card counting is supported and encouraged!")
		println(" Please provide input as suggested in [], followed by a return key.")
		println(" See http://en.wikipedia.org/wiki/Card_counting for a description of the HiLo and Zen models.")
		println(" ")
		
		//Request user input on the number of decks
		ndecks = processNumberRequest("How many decks should the house play with [1-6]?",1,6);
		//Initalize the shoe
		shoe = new BlackJackCardCountingShoe(ndecks)
		
		//Begin game loop
		var ok = true
		while (ok) {
			//Begin a round
			runBlackJackRound()
			
			//Ask if the user wishes to leave the table
			if(player.getNumberOfChips()>1){
				val cont : Boolean = processYesNo("Play another round? [y/n]")
				if(!cont) ok = false
			}else{
				ok=false
			}
		}
		
		if(player.getNumberOfChips()>starting_chips){
			println("Sorry to see you go. Lucky for you, you're richer than when you came.")
		}else{
			println("Sorry to see you go. Better luck next time.")
		}
			
	}
	
	//Method runs one round of blackjack
	// ensures that the chips are awarded upon completion of the hand
	// ensures that the cards are flushed from each players hand
	def runBlackJackRound() {
		//Report the counts
		println("\nThe counts are");
		if(shoe.getHiLoCount()>0){
			println("\tHiLo: "+shoe.getHiLoCount()+" (player advantage)");
		}else{
			println("\tHiLo: "+shoe.getHiLoCount()+" (dealer advantage)");
		}
		if(shoe.getZenCount()>0) {
			println("\tand Zen: "+shoe.getZenCount()+" (player advantage)");
		}else{
			println("\tand Zen: "+shoe.getZenCount()+" (dealer advantage)");
		}
		
		//Take bet amount
		val nchips_wagered = processNumberRequest("How many chips do you wager? [1-"+player.getNumberOfChips()+"]",1,player.getNumberOfChips())
		player.betChips(nchips_wagered)
		
		//Deal 2 cards each - reporting hands
		player.addCard(shoe.next())
		dealer.addCard(shoe.next())
		player.addCard(shoe.next())
		dealer.addCard(shoe.next())
		
		//Check the dealer for blackjack
		if(dealer.computeScore()==21) {
			println("\nDealer has blackjack.\n")
			player.flushCards()
			dealer.flushCards()
			return
		}
		println("\n"+dealer.getHiddenHand())
		
		//Ask user for hit/stand
		println(player.getHand())
		var ok = player.canHit()
		while(ok){
			if(processHitRequest){
				player.addCard(shoe.next())
				ok=player.canHit()
			}else{
				ok=false
			}
			if(ok || player.computeScore()>21) println("\n"+player.getHand())
		}
		if(player.computeScore()>21){
			println("Bust!")
			player.flushCards()
			dealer.flushCards()
			return
		}
		
		//Run dealer hit/stand
		println(dealer.getHand())
		ok = dealer.canHit()
		while(ok){
			println("Dealer hits\n")
			dealer.addCard(shoe.next())
			println(dealer.getHand())
			ok = dealer.canHit()
		}
		
		//Decide winner and award chips
		if(dealer.computeScore()<=21 && dealer.computeScore()>=player.computeScore()){
			println("The house wins.");
		}else{
			println("Congrats you win "+nchips_wagered+" chips!")
			player.addToChips(2*nchips_wagered)
		}
		
		player.flushCards()
		dealer.flushCards()
	}
	
	//Method requests a number from the user within the range specified by 
	//  lower bound a and upper bound b
	def processNumberRequest(question : String, a : Int, b : Int) : Int = {
		println(question)
		//Read the number of decks
		val ln : String = readLine()
		try{
			val user_number : Int = ln.toInt
			if(user_number<a || user_number>b) return processNumberRequest(question, a, b)
			return user_number
		}catch{
			case _ : Throwable => return processNumberRequest(question, a, b)
		}
	}
	
	//Method requests a y/n character from user and returns boolean answer to question
	def processYesNo(question : String) : Boolean = {
		return processBooleanQuestion(question, "y", "n")
	}
	
	//Method requests hit or stand response from user and returns boolean true if hit requested
	def processHitRequest() : Boolean = {
		return processBooleanQuestion("Hit or Stand [h/s]?", "h", "s")
	}
	
	//General boolean question response processor
	def processBooleanQuestion(question : String, true_char : String, false_char : String) : Boolean = {
		println(question)
		val ln : String = readLine()
		if(ln.toLowerCase().startsWith(true_char)){
			return true
		}else if(ln.toLowerCase().startsWith(false_char)){
			return false
		}
		return processBooleanQuestion(question, true_char, false_char);
	}
	
}