package blackjackcardcount;

class Player(starting_chips : Int) extends BlackJackCardHolder {
	
	private var nchips : Int = starting_chips;
	
	def canHit() : Boolean = {
		if(computeScore() >= 21) return false;
		return true;
	}
	
	def betChips(wager : Int) : Boolean = {
		if(wager <= nchips) {
			nchips -= wager;
			return true;
		}else{
			return false;
		}
	}
	
	def addToChips(input : Int) = {
		nchips += input;
	}
	
	def getNumberOfChips() : Int = {
		return nchips;
	}
	
	def getHand() : String = {
		return "Player has: "+computeScore()+"\n"+toString();
	}
	
}