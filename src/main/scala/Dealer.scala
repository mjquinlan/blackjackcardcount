package blackjackcardcount;

class Dealer extends BlackJackCardHolder {
	
	def canHit() : Boolean = {
		if(computeScore() >= 17) return false;
		return true;
	}
	
	def getHiddenHand() : String = {
		return "Dealer has:\n"+baseGetHiddenHand();
	}
	
	def getHand() : String = {
		return "Dealer has: "+computeScore()+"\n"+toString();
	}
	
}