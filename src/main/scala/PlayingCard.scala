package blackjackcardcount;

import scala.math.floor

class PlayingCard(idnum : Int) {
	
	if(idnum<0 || idnum>=52) {
		println("Invalid BlackJackPlayingCard idnum "+idnum);
	}
	
	val suit_id : Int = floor(idnum.toDouble / 13.).toInt;
	val face_id : Int = idnum % 13;
	val suit_name : String = defineSuit(suit_id)
	val face_name : String = defineFace(face_id)

	override def toString() : String = { return face_name+" of "+suit_name; }
	
	override def hashCode() : Int = { return idnum; }
	
	private def defineSuit(suitid : Int) : String = suitid match {
		case 0 => "Spades";
		case 1 => "Clubs";
		case 2 => "Diamonds";
		case 3 => "Hearts";
		case _ => "Undefined suit id "+suitid; return "badsuitid";
	}
	
	private def defineFace(faceid : Int) : String = faceid match {
		case 0 => "Ace";
		case 1 => "King";
		case 2 => "Queen";
		case 3 => "Jack";
		case 4 => "Ten";
		case 5 => "Nine";
		case 6 => "Eight";
		case 7 => "Seven";
		case 8 => "Six";
		case 9 => "Five";
		case 10 => "Four";
		case 11 => "Three";
		case 12 => "Two";
		case _ => println("Invalid faceid "+faceid); return "badfaceid";
	}
}