package eu.sos.ttc.cg;


import org.junit.Test;


/**
 * @author BauerMitFackel
 */
public class Tryout {


	@Test
	public void execute () {


		float fatigue = 0F;
		float fatigueDefault = 0F;
		float fatigueIncrease = 0.01F;

		float fatigueModification = 0.995F;


		while (fatigue < 1.0F) {

			fatigueDefault += fatigueIncrease;
			fatigue = (fatigue + fatigueIncrease) * fatigueModification;

			System.out.println("Fatigue: (Default) " + fatigueDefault + " (Modified) " + fatigue);
		}
	}
}
