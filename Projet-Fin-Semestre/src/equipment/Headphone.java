package equipment;

import java.util.ArrayList;

import utils.Period;
import config.Model;

/**
 * Class Headphone, defines all common properties to headphones.
 * 
 * initial code by: Marc Karassev; modified by: Marc Karassev
 * 
 * @author Marc Karassev
 * 
 */
public abstract class Headphone extends Equipment {
	// TODO java documentation
	// TODO check the use of field unavailabalityPeriods and re-factor the class
	// if necessary
	// TODO tests

    // Fields

    private String impedance;
    private String frequencyResponse;
    private String soundPressure;

    // Constructors

    public Headphone() {
        this("HP", "unknown", new ArrayList<Period>(), 30, "20-20000", 100,
                Model.UNKWOWN);
    }

    public Headphone(String id, String maker, ArrayList<Period> unavPer,
            int imp, String resp, int pres, Model type) {
        super(id, maker, unavPer, type);
        impedance = imp + " Ohms";
        frequencyResponse = resp + " Hz";
        soundPressure = pres + " dB";
    }

    // Methods

    public String toString() {
        return super.toString() + ", impedance: " + impedance
                + ", frequency response: " + frequencyResponse
                + ", sound pressure: " + soundPressure;
    }

    // Getters and setters

    public String getImpedance() {
        return impedance;
    }

    public String getFrequencyResponse() {
        return frequencyResponse;
    }

    public String getSoundPressure() {
        return soundPressure;
    }
}
