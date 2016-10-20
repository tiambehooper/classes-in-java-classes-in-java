import com.github.javaparser.ParseException;
import net.doughughes.testifier.annotation.Testifier;
import net.doughughes.testifier.matcher.RegexMatcher;
import net.doughughes.testifier.output.OutputStreamInterceptor;
import net.doughughes.testifier.util.SourceCodeExtractor;
import net.doughughes.testifier.util.TestifierAnnotationReader;
import net.doughughes.testifier.watcher.NotifyingWatcher;
import net.doughughes.testifier.watcher.OutputWatcher;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertTrue;

@Testifier(sourcePath = "./src/main/java/Main.java", clazz = Main.class)
public class MainTest {

    @Rule
    public NotifyingWatcher notifyingWatcher = new NotifyingWatcher("https://tiy-testifier-webapp.herokuapp.com/notify");

    @Rule
    public OutputWatcher outputWatcher = new OutputWatcher();

    @Test
    @Testifier(method = "main", args = {String[].class})
    public void mainMethodPrintsAnimalToConsoleTest() {
        /* arrange */

        /* act */
        Main.main(new String[]{});

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The main method should have printed something to the console",
                printed.size(), greaterThan(0));
        assertThat("The main method should have printed an Animal to the console",
                printed.get(0), instanceOf(Animal.class));
    }

    @Test
    @Testifier(method = "main", args = {String[].class})
    public void mainMethodCreatesNewAnimalTest() throws NoSuchMethodException, IOException, ParseException {
        /* arrange */

        /* act */

        /* assert */
        // read this test's annotations
        TestifierAnnotationReader reader = new TestifierAnnotationReader(
                this.getClass().getAnnotation(Testifier.class),
                this.getClass().getMethod("mainMethodCreatesNewAnimalTest").getAnnotation(Testifier.class)
        );

        // get the structure of the main method
        String extractor = new SourceCodeExtractor(reader.getSourcePath()).getMethodDescription(reader.getMethod(), reader.getArgs());
        Assert.assertThat("The main method should be declare an Animal variable and set it to a new instance of the Animal class.",
                extractor, RegexMatcher.matches("^.*?VariableDeclarator VariableDeclaratorId\\[\\S*?\\] ObjectCreationExpr ClassOrInterfaceType\\[Animal\\].*?$"));
    }
}