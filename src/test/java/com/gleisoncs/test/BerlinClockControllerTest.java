package com.gleisoncs.test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.gleisoncs.berlinclock.BerlinClockApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BerlinClockApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class BerlinClockControllerTest {

	@Value("${local.server.port}")
	private int port;

	private RestTemplate restTemplate = new RestTemplate();

	private String getUrl() {
		StringBuilder url = new StringBuilder("http://localhost:");
		url.append(port).append("/berlinclock");
		return url.toString();
	}

	@Test
	public void testMainScreen() throws Exception {
		String body = this.restTemplate.getForObject(getUrl() + "/", String.class);
		assertThat(body, equalTo("Welcome to Berlin Clock Service"));
	}

	@Test
	public void testBerlinClockControllerMidNight() {
		HttpEntity<String> response = getResponse(0, 0, 0);
		String midNigth = "O\n" + "OOOO\n" + "OOOO\n" + "OOOOOOOOOOO\n" + "OOOO\n";

		Assert.assertEquals(response.getBody(), midNigth);
	}

	@Test
	public void testBerlinClockControllerAnteMeridian() {
		HttpEntity<String> response = getResponse(9, 24, 54);
		String anteMeridian = "Y\n" + "ROOO\n" + "RRRR\n" + "YYRYOOOOOOO\n" + "YYYY\n";

		Assert.assertEquals(response.getBody(), anteMeridian);
	}

	@Test
	public void testBerlinClockControllerPostMeridian() {
		HttpEntity<String> response = getResponse(14, 12, 23);
		String postMeridian = "Y\n" + "RROO\n" + "RRRR\n" + "YYOOOOOOOOO\n" + "YYOO\n";
		
		Assert.assertEquals(response.getBody(), postMeridian);
	}

	@Test
	public void testBerlinClockControllerSecondsOn() {
		HttpEntity<String> response = getResponse(0, 0, 2);
		String secondsOn = "Y\n" + "OOOO\n" + "OOOO\n" + "OOOOOOOOOOO\n" + "OOOO\n";
		
		Assert.assertEquals(response.getBody(), secondsOn);
	}

	@Test
	public void testBerlinClockControllerSecondsOff() {
		HttpEntity<String> response = getResponse(0, 0, 1);
		String secondsOff = "O\n" + "OOOO\n" + "OOOO\n" + "OOOOOOOOOOO\n" + "OOOO\n";
		
		Assert.assertEquals(response.getBody(), secondsOff);
	}

	private HttpEntity<String> getResponse(int hour, int minute, int second) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(getUrl()).queryParam("hour", hour)
				.queryParam("minute", minute).queryParam("second", second);

		HttpEntity<?> entity = new HttpEntity<>(headers);

		HttpEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity,
				String.class);

		return response;
	}
}