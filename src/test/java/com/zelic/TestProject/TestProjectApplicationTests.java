package com.zelic.TestProject;

import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.zelic.TestProject.entities.User;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestProjectApplication.class)
public class TestProjectApplicationTests {

	@Autowired
    private TestRestTemplate restTemplate;
	
	@Test
	public void createUserTest() {
		System.out.println("--------------------------THIS IS TEST---------------------");
		
		User user1 = new User("Test1", "Lastname", "test1@gmail.com", "123", null);
		ResponseEntity<Long> response1 = restTemplate.postForEntity("/api/register", user1, Long.class);
		Long user1Id = response1.getBody();
		assertThat(user1Id, notNullValue());
		
		User user2 = new User("Test2", "Lastname", "test2@gmail.com", "123", null);
		ResponseEntity<Long> response2 = restTemplate.postForEntity("/api/register", user2, Long.class);
		Long user2Id = response2.getBody();
		assertThat(user2Id, notNullValue());
		
		User user3 = new User("Test3", "Lastname", "test3@gmail.com", "123", null);
		ResponseEntity<Long> response = restTemplate.postForEntity("/api/register", user3, Long.class);
		Long user3Id = response.getBody();
		assertThat(user1Id, notNullValue());
		
		
	}

}
