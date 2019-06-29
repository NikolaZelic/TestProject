package com.zelic.TestProject;

import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.zelic.TestProject.controllers.AuthenticationController;
import com.zelic.TestProject.controllers.AuthenticationController.LoginDetails;
import com.zelic.TestProject.entities.Account;
import com.zelic.TestProject.entities.Farm;
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
		
		// CREATING USERS
		User user1 = new User("Test1", "Lastname", "test1@gmail.com", "123", null);
		ResponseEntity<Long> response1 = restTemplate.postForEntity("/api/register", user1, Long.class);
		Long user1Id = response1.getBody();
		assertThat(user1Id, notNullValue());
		
		User user2 = new User("Test2", "Lastname", "test2@gmail.com", "123", null);
		ResponseEntity<Long> response2 = restTemplate.postForEntity("/api/register", user2, Long.class);
		Long user2Id = response2.getBody();
		assertThat(user2Id, notNullValue());
		
		User user3 = new User("Test3", "Lastname", "test3@gmail.com", "123", null);
		ResponseEntity<Long> response3 = restTemplate.postForEntity("/api/register", user3, Long.class);
		Long user3Id = response3.getBody();
		assertThat(user1Id, notNullValue());
		
		
		// LOGIN 
		LoginDetails loginDetails = new LoginDetails(user1.getEmail(), user1.getPassword());
		ResponseEntity<String> loginResponse = restTemplate.postForEntity("/api/login", loginDetails, String.class);
//		loginResponse.getHeaders().forEach( (k,v) -> System.out.println(k+" " +v) );
		
		assertThat(loginResponse.getBody(), is("Successful login"));
		
		// CREATE ACCOUNT
		// Maintain session
		String cookie = loginResponse.getHeaders().get("Set-Cookie").get(0);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cookie", cookie);
		
		Account account = new Account("TestAccount", "Some description");
		HttpEntity<Account> accountEntity = new HttpEntity<Account>(account, headers);
		
		ResponseEntity<Long> accountResponse = restTemplate.postForEntity("/api/accounts", accountEntity, Long.class);
		Long accountId = accountResponse.getBody();
		assertThat(accountId, notNullValue());
		
		// Add user to account
		HttpEntity<Void> addUserEntity = new HttpEntity<>(headers);
		String url = "/api/accounts/"+accountId+"/users?userId="+user2Id;
		ResponseEntity<String> addUserResponse = restTemplate.postForEntity(url, addUserEntity, String.class);
		assertThat(addUserResponse.getBody(), is("User added"));
		
		// Create farm
		Farm farm = new Farm(null, "MyFarm", "Besta farm ever", "Somwhere");
		ResponseEntity<Long> createFarmResponse = 
				restTemplate.postForEntity("/api/farms?accountId="+accountId, farm, Long.class);
		Long farmId = createFarmResponse.getBody();
		assertThat(farmId, notNullValue());
		
		// Select users
		ResponseEntity<Object> getUsersResponse = restTemplate.getForEntity("/api/users", Object.class);
		List<User> users = (List<User>) getUsersResponse.getBody();
		assertThat(users.size(), is(3));
		
		// Select customers
		ResponseEntity<Object> getCustomersResponse = restTemplate.getForEntity("/api/customers", Object.class);
		List<User> customers = (List<User>) getCustomersResponse.getBody();
		assertThat(customers.size(), is(1));	// User one has created account so he has become customer
		System.out.println(customers.get(0));
	}

}

