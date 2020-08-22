package domain;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DocumentTest {

    private final String en36197495 = "Tu Mera 22 Main Tera 22\tThe story unfolds in Melbourne, Australia with two spoilt rich brothers and best friends Robby (Amrinder Gill), Rolly (Honey Singh). Their businessman father is worried about the future of his irresponsible sons so he strikes a deal with them, by throwing the brats out of his house sending them to Punjab so that they can understand the realities of life and importance of their roots and heritage. The film is about how these two spoilt brothers arrive in Punjab and learn to live with the struggle, whilst being challenged by their father to come up with Rs.30 lakhs in 30 days in order to inherit his wealth.Otherwise the wealth would be transferred to charity which would be maintained by their father's secretary.\ten";

    @Test
    public void shouldReturnTheCorrectFrequencies() {
        Map<String, Integer> frequencies = new Document(en36197495).getFrequencies();
        assertEquals(frequencies.get("transferred"), new Integer(1));
        assertEquals(frequencies.get("brothers"), new Integer(2));
        assertEquals(frequencies.get("wealthotherwise"), new Integer(1));
        assertEquals(frequencies.get("about"), new Integer(2));
        assertEquals(frequencies.get("these"), new Integer(1));
        assertEquals(frequencies.get("that"), new Integer(1));
        assertEquals(frequencies.get("his"), new Integer(3));
        assertEquals(frequencies.get("would"), new Integer(2));
        assertEquals(frequencies.get("sons"), new Integer(1));
        assertEquals(frequencies.get("charity"), new Integer(1));
        assertEquals(frequencies.get("amrinder"), new Integer(1));
        assertEquals(frequencies.get("up"), new Integer(1));
        assertEquals(frequencies.get("maintained"), new Integer(1));
        assertEquals(frequencies.get("businessman"), new Integer(1));
        assertEquals(frequencies.get("melbourne"), new Integer(1));
        assertEquals(frequencies.get("struggle"), new Integer(1));
        assertEquals(frequencies.get("they"), new Integer(1));
        assertEquals(frequencies.get("order"), new Integer(1));
        assertEquals(frequencies.get("which"), new Integer(1));
        assertEquals(frequencies.get("punjab"), new Integer(2));
        assertEquals(frequencies.get("spoilt"), new Integer(2));
        assertEquals(frequencies.get("in"), new Integer(4));
        assertEquals(frequencies.get("robby"), new Integer(1));
        assertEquals(frequencies.get("unfolds"), new Integer(1));
        assertEquals(frequencies.get("understand"), new Integer(1));
        assertEquals(frequencies.get("come"), new Integer(1));
        assertEquals(frequencies.get("heritage"), new Integer(1));
        assertEquals(frequencies.get("them"), new Integer(2));
        assertEquals(frequencies.get("is"), new Integer(2));
        assertEquals(frequencies.get("australia"), new Integer(1));
        assertEquals(frequencies.get("being"), new Integer(1));
        assertEquals(frequencies.get("film"), new Integer(1));
        assertEquals(frequencies.get("roots"), new Integer(1));
        assertEquals(frequencies.get("friends"), new Integer(1));
        assertEquals(frequencies.get("realities"), new Integer(1));
        assertEquals(frequencies.get("singh"), new Integer(1));
        assertEquals(frequencies.get("lakhs"), new Integer(1));
        assertEquals(frequencies.get("fathers"), new Integer(1));
        assertEquals(frequencies.get("deal"), new Integer(1));
        assertEquals(frequencies.get("be"), new Integer(2));
        assertEquals(frequencies.get("importance"), new Integer(1));
        assertEquals(frequencies.get("father"), new Integer(2));
        assertEquals(frequencies.get("their"), new Integer(4));
        assertEquals(frequencies.get("brats"), new Integer(1));
        assertEquals(frequencies.get("best"), new Integer(1));
        assertEquals(frequencies.get("house"), new Integer(1));
        assertEquals(frequencies.get("two"), new Integer(2));
        assertEquals(frequencies.get("life"), new Integer(1));
        assertEquals(frequencies.get("out"), new Integer(1));
        assertEquals(frequencies.get("throwing"), new Integer(1));
        assertEquals(frequencies.get("how"), new Integer(1));
        assertEquals(frequencies.get("can"), new Integer(1));
        assertEquals(frequencies.get("gill"), new Integer(1));
        assertEquals(frequencies.get("secretary"), new Integer(1));
        assertEquals(frequencies.get("challenged"), new Integer(1));
        assertEquals(frequencies.get("arrive"), new Integer(1));
        assertEquals(frequencies.get("honey"), new Integer(1));
        assertEquals(frequencies.get("and"), new Integer(4));
        assertEquals(frequencies.get("by"), new Integer(3));
        assertEquals(frequencies.get("strikes"), new Integer(1));
        assertEquals(frequencies.get("irresponsible"), new Integer(1));
        assertEquals(frequencies.get("of"), new Integer(4));
        assertEquals(frequencies.get("sending"), new Integer(1));
        assertEquals(frequencies.get("worried"), new Integer(1));
        assertEquals(frequencies.get("so"), new Integer(2));
        assertEquals(frequencies.get("30"), new Integer(1));
        assertEquals(frequencies.get("live"), new Integer(1));
        assertEquals(frequencies.get("wealth"), new Integer(1));
        assertEquals(frequencies.get("a"), new Integer(1));
        assertEquals(frequencies.get("learn"), new Integer(1));
        assertEquals(frequencies.get("rolly"), new Integer(1));
        assertEquals(frequencies.get("rich"), new Integer(1));
        assertEquals(frequencies.get("rs30"), new Integer(1));
        assertEquals(frequencies.get("whilst"), new Integer(1));
        assertEquals(frequencies.get("the"), new Integer(7));
        assertEquals(frequencies.get("with"), new Integer(4));
        assertEquals(frequencies.get("future"), new Integer(1));
        assertEquals(frequencies.get("inherit"), new Integer(1));
        assertEquals(frequencies.get("days"), new Integer(1));
        assertEquals(frequencies.get("to"), new Integer(5));
        assertEquals(frequencies.get("he"), new Integer(1));
        assertEquals(frequencies.get("story"), new Integer(1));
    }


}
