 package Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.io.FileUtils;

import Runner.TestRunner;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.presentation.PresentationMode;


public class ReportGenerate {
	public static void generateCucumberReport() {
		File reportOutputDirectory = new File("target");
		Collection<File> jsonFiles = FileUtils.listFiles(new File("target/cucumber-reports/"), new String[] {"json"}, true);
		List<String> jsonPaths = new ArrayList<String>(jsonFiles.size());
		jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
		String projectName = "UI Engne Automation";
		Configuration conf = new Configuration(reportOutputDirectory, projectName);
		conf.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
		conf.addPresentationModes(PresentationMode.PARALLEL_TESTING);
		conf.addClassifications("os", System.getProperty("os.name"));
		conf.addClassifications("Browser", "Chrome");
		conf.addClassifications("Environment", TestRunner.envConfig.env().toUpperCase());
		
		ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, conf);
		reportBuilder.generateReports();
	}
}
