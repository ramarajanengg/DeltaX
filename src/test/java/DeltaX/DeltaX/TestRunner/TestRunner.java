package DeltaX.DeltaX.TestRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Resources/Features/",
		glue = "DeltaX.DeltaX.stepDefinitionLibrary",
		//tags = {"@ErrorValidations"},
		monochrome = true
		)

public class TestRunner {
}
