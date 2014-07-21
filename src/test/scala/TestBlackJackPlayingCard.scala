import org.scalatest.FlatSpec

import blackjackcardcount.BlackJackPlayingCard

class TestBlackJackPlayingCard extends FlatSpec {

	"Card id 0" should "represent the ace of spades" in {
		val ace_of_spades : BlackJackPlayingCard = new BlackJackPlayingCard(0);	//0 should be the ace of spades
		assert(ace_of_spades.face_name == "Ace", "BlackJackPlayingCard constructor assigned wrong face_name");
		assert(ace_of_spades.suit_name == "Spades", "BlackJackPlayingCard constructor assigned wrong suit_name");
		assert(ace_of_spades.toString() == "Ace of Spades", "BlackJackPlayingCard.toString() returned wrong value");
	}

	/* Test that the modulii are computing card values correctly.*/
  "The Ace of Clubs" should "have value 11" in {
    val ace : BlackJackPlayingCard = new BlackJackPlayingCard(13);
		assert(ace.value == 11, "BlackJackPlayingCard constructor assigned wrong value, likely bad modulus computation");
  }

	/* Check that the largest id is correct */
	"The largest valid card id (13*4-1)" should "represent the 2 of hearts" in {
		val testcard : BlackJackPlayingCard = new BlackJackPlayingCard(13*4-1);
		assert(testcard.value == 2);
		assert(testcard.face_name == "Two", "BlackJackPlayingCard constructor assigned wrong face_name for max id");
		assert(testcard.suit_name == "Hearts", "BlackJackPlayingCard constructor assigned wrong suit_name for max id");
		assert(testcard.toString() == "Two of Hearts", "BlackJackPlayingCard.toString returned wrong value for max id");
	}

}