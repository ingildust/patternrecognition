package it.ingildust.recognition;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import it.ingildust.recognition.model.Response;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecognitionApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testEmpySet() {
		Response body = (Response) this.restTemplate.getForObject("/space", Response.class);
		assertThat(body.getResult()).isEmpty();
	}

}
