package org.bluewhale.webcontent.controllers;

import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class GreetingControllerTest extends BlueWhaleIntegrationTest {

	@Override
	protected String nameOfService() {
		return "greeting";
	}

	@Test
	public void getHello() throws Exception {
		final ResponseEntity<String> response = template().getForEntity(
				base().toString(), String.class);

		assertThat(response.getBody()).matches(
				"\\{\"id\":\\d+,\"content\":\"Hello, World!\"\\}");
	}
}
