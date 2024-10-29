package com.generalTemplate;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = GeneralTemplateApplication.class)
public class GeneralTemplateApplicationTests {

	@Test
	void contextLoads() {
	}

}
