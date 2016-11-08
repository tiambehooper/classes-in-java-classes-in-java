import net.doughughes.testifier.test.TestifierTest;
import org.junit.Test;

import static org.junit.Assert.fail;

public class AnimalTest extends TestifierTest {

    @Test
    public void canInstantiateAnimalTest() throws ClassNotFoundException {
        /* arrange */

        /* act */
        try {
            Class aClass = Class.forName("Animal");
        } catch (ClassNotFoundException e) {
            fail("Unable to find a class named Animal");
        }

        /* assert */
        /*
            There's no need to assert anything. This test simply confirms that
            the animal class exists and can be instantiated.
        */
    }
}
