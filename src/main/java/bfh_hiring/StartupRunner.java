package bfh_hiring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;

@Component
public class StartupRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        // ---------------- STEP 1: Generate Webhook ----------------

        String generateUrl =
                "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "Vigneswari");
        requestBody.put("regNo", "REG12347");
        requestBody.put("email", "vigneswariramireddy383@email.com");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response =
                restTemplate.postForEntity(generateUrl, entity, Map.class);

        String webhookUrl = (String) response.getBody().get("webhook");
        String accessToken = (String) response.getBody().get("accessToken");

        System.out.println("Webhook URL: " + webhookUrl);
        System.out.println("Access Token: " + accessToken);

        // ---------------- STEP 2: Your Final SQL Query ----------------

        String finalQuery =
                "SELECT d.DEPARTMENT_NAME, " +
                "t.total_salary AS SALARY, " +
                "CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS EMPLOYEE_NAME, " +
                "TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE " +
                "FROM ( " +
                "   SELECT e.EMP_ID, e.DEPARTMENT, SUM(p.AMOUNT) AS total_salary, " +
                "   RANK() OVER (PARTITION BY e.DEPARTMENT ORDER BY SUM(p.AMOUNT) DESC) AS rnk " +
                "   FROM EMPLOYEE e " +
                "   JOIN PAYMENTS p ON e.EMP_ID = p.EMP_ID " +
                "   WHERE DAY(p.PAYMENT_TIME) <> 1 " +
                "   GROUP BY e.EMP_ID, e.DEPARTMENT " +
                ") t " +
                "JOIN EMPLOYEE e ON t.EMP_ID = e.EMP_ID " +
                "JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID " +
                "WHERE t.rnk = 1;";

        // ---------------- STEP 3: Submit Solution ----------------

        HttpHeaders submitHeaders = new HttpHeaders();
        submitHeaders.setContentType(MediaType.APPLICATION_JSON);
        submitHeaders.set("Authorization", accessToken);

        Map<String, String> submitBody = new HashMap<>();
        submitBody.put("finalQuery", finalQuery);

        HttpEntity<Map<String, String>> submitEntity =
                new HttpEntity<>(submitBody, submitHeaders);

        ResponseEntity<String> submitResponse =
                restTemplate.postForEntity(webhookUrl, submitEntity, String.class);

        System.out.println("Solution submitted successfully!");
        System.out.println(submitResponse.getBody());
    }
}
