package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        tests.firstAssignment.class,
        tests.secondAssignment.class,
        tests.thirdAssignment.class
})

public class runAllTests {

}
