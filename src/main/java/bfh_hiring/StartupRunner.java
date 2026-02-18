package bfh_hiring;


	

	import org.springframework.boot.CommandLineRunner;
	import org.springframework.stereotype.Component;
	import org.springframework.web.client.RestTemplate;
	import org.springframework.http.*;

	@Component
	public class StartupRunner implements CommandLineRunner {

	    @Override
	    public void run(String... args) throws Exception {

	        System.out.println("Application Started...");

	        RestTemplate restTemplate = new RestTemplate();

	        String generateUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

	        String requestBody = """
	                {
	                  "name": "Vigneswari",
	                  "regNo": "REG12347",
	                  "email": "vigneswariramireddy383@email.com"
	                }
	                """;

	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);

	        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

	        ResponseEntity<String> response =
	                restTemplate.postForEntity(generateUrl, entity, String.class);

	        System.out.println("Response from generateWebhook API:");
	        System.out.println(response.getBody());
	    }
	}



