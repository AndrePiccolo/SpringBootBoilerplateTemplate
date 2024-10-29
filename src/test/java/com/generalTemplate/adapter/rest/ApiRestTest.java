package com.generalTemplate.adapter.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generalTemplate.CucumberIntegrationTest;
import com.generalTemplate.adapter.rest.entity.pdf.PdfContent;
import com.generalTemplate.adapter.util.PdfCreator;
import com.itextpdf.text.DocumentException;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.http.ResponseEntity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class ApiRestTest extends CucumberIntegrationTest {

    @InjectMocks
    ApiRest apiRest;

    @Mock
    PdfCreator pdfCreatorMock;

    private PdfContent pdfContent;
    ResponseEntity<byte[]> response;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void teardown() throws Exception{
        MockitoAnnotations.openMocks(this).close();
    }

    @Given("the follow request:")
    public void uploadInformation(String request) throws IOException {
        String requestPath = Paths.get("").toAbsolutePath() + "/src/test/resources/testdata/" + request;
        pdfContent = new ObjectMapper().readValue(new BufferedReader(new FileReader(requestPath)), PdfContent.class);
    }

    @When("get endpoint is called {string} environment")
    public void startRequest(String environment) throws DocumentException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(pdfContent.getMainContent().getBytes(StandardCharsets.UTF_8));
        PowerMockito.when(pdfCreatorMock.createPdf(Mockito.any())).thenReturn(out);

        response = apiRest.getPDFExample(pdfContent);
    }

    @Then("server will send status code of {int}")
    public void sendRequestResult(int requestResult){
        Assert.assertEquals(requestResult, response.getStatusCode().value());
    }

}
