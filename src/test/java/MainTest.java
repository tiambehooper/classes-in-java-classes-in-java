import com.github.javaparser.ParseException;
import net.doughughes.testifier.exception.CannotFindMethodException;
import net.doughughes.testifier.matcher.RegexMatcher;
import net.doughughes.testifier.output.OutputStreamInterceptor;
import net.doughughes.testifier.test.TestifierTest;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.fail;

public class MainTest extends TestifierTest {

    @Test
    public void mainMethodPrintsAnimalToConsoleTest() {
        /* arrange */

        /* act */
        Main.main(new String[]{});

        /* assert */
        ArrayList printed = ((OutputStreamInterceptor) System.out).getPrinted();
        assertThat("The main method should have printed something to the console",
                printed.size(), greaterThan(0));

        // get the Animal class
        Class aClass = null;
        try {
            aClass = Class.forName("Animal");
        } catch (ClassNotFoundException e) {
            fail("Unable to find a class named Animal");
        }

        assertThat("The main method should have printed an Animal to the console",
                printed.get(0), instanceOf(aClass));
    }

    @Test
    public void mainMethodCreatesNewAnimalTest(){
        /* arrange */

        /* act */

        /* assert */
        String source = null;
        try {
            source = codeWatcher.getMainSourceCodeService().getDescriptionOfMethod("main", String[].class);
        } catch (CannotFindMethodException e) {
            fail(e.getMessage());
        }

        Assert.assertThat("The main method should be declare an Animal variable and set it to a new instance of the Animal class.",
                source, RegexMatcher.matches("^.*?VariableDeclarator VariableDeclaratorId\\[\\S*?\\] ObjectCreationExpr ClassOrInterfaceType\\[Animal\\].*?$"));
    }
}