package blackjackcardcount;

class BlackJackPlayingCard(idnum : Int) extends PlayingCard(idnum) {

	val value : Int = defineValue(face_name);

	private def defineValue(inname : String) : Int = inname match {
		case "Ace" => 11;
		case "King" => 10;
		case "Queen" => 10;
		case "Jack" => 10;
		case "Ten" => 10;
		case "Nine" => 9;
		case "Eight" => 8;
		case "Seven" => 7;
		case "Six" => 6;
		case "Five" => 5;
		case "Four" => 4;
		case "Three" => 3;
		case "Two" => 2;
		case _ => println("Invalid card name: "+inname); println("value will be 0."); return 0;
	}
	
}