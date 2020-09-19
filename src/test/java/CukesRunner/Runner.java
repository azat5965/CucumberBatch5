package CukesRunner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-html-report",
                "json:target/cucumber.json",
                "junit:target/cucumber.xml",
                "rerun:target/rerun.txt"},
        features = "src/test/resources",
        glue = "StepDefinition",
        tags = "@TEC-2015 or @TEC-2003",
        dryRun = false
)
public class Runner {

}
