import net.doughughes.testifier.annotation.Testifier;
import net.doughughes.testifier.watcher.NotifyingWatcher;
import net.doughughes.testifier.watcher.OutputWatcher;
import org.junit.Rule;
import org.junit.Test;

@Testifier(sourcePath = "./src/main/java/Animal.java", clazz = Animal.class)
public class AnimalTest {

    @Rule
    public NotifyingWatcher notifyingWatcher = new NotifyingWatcher("https://tiy-testifier-webapp.herokuapp.com/notify");

    @Rule
    public OutputWatcher outputWatcher = new OutputWatcher();

    @Test
    @Testifier(constructor = true)
    public void canInstantiateAnimalTest() {
        /* arrange */

        /* act */
        Animal animal = new Animal();

        /* assert */
        /*
            There's no need to assert anything. This test simply confirms that
            the animal class exists and can be instantiated.
        */
    }
}
