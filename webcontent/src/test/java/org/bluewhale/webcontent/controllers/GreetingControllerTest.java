package org.bluewhale.webcontent.controllers;

import java.net.URL;
import java.util.regex.Pattern;

import org.bluewhale.webcontent.configuration.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class GreetingControllerTest {

	@Value("${local.server.port}")
	private int port;

	private URL base;
	private RestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/greeting");
		template = new TestRestTemplate();
	}

	@Test
	public void getHello() throws Exception {
		final ResponseEntity<String> response = template.getForEntity(
				base.toString(), String.class);
		Assert.assertTrue(Pattern.matches(
				"\\{\"id\":\\d+,\"content\":\"Hello, World!\"\\}",
				response.getBody()));
	}
}
