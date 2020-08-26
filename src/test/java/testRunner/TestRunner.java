package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = ".\\src\\Features\\Test.feature",
        glue = "stepDefinitions",
        dryRun = false,
        plugin = {"pretty","html:test-output","json:test-output/cucumber.json",},
        monochrome = true,
        tags = {"@Test"}
)
public class TestRunner {
}
