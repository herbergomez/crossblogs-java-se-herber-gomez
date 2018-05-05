package com.crossover.techtrial.controller;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.crossover.techtrial.model.Article;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ArticleControllerTest {
	private final static Logger logger = LoggerFactory
			.getLogger(ArticleControllerTest.class);
	@Autowired
	private TestRestTemplate template;

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@InjectMocks
	private ArticleController articleControllerTest;
	
	@Before
	public void setup() throws Exception {

	}

	@Test
	public void testArticleShouldBeCreated() throws Exception {
		HttpEntity<Object> article = getHttpEntity("{\"email\": \"user1@gmail.com\", \"title\": \"hello\" }");
		ResponseEntity<Article> resultAsset = template.postForEntity("/articles", article, Article.class);
		Assert.assertNotNull(resultAsset.getBody().getId());
	}

	private HttpEntity<Object> getHttpEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new HttpEntity<Object>(body, headers);
	}
	

	@Test
	public void getAllByMockingRequest() throws Exception {
		MvcResult result = this.mockMvc.perform(
				MockMvcRequestBuilders.get("/articles/").accept(
						MediaType.APPLICATION_JSON)).andReturn();
		logger.info(result.getResponse().getContentAsString());

	}

	@Test
	public void getArticleByMockingRequest() throws Exception {
		String articleId = "1";
		MvcResult result = this.mockMvc.perform(
				MockMvcRequestBuilders.get("/articles/" + articleId).accept(
						MediaType.APPLICATION_JSON)).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	public void putArticleByMockingRequest() throws Exception {
		String articleId = "1";
		Article article = new Article();
		article.setId(1L);
		article.setTitle("Test");
		article.setContent("Sample Article");
		article.setEmail("email@com.sv");
		article.setPublished(true);
	
		ObjectMapper mapper = new ObjectMapper();

		byte[] content = mapper.writeValueAsBytes(article);
		MvcResult result = this.mockMvc.perform(
				MockMvcRequestBuilders.put("/articles/" + articleId)
						.accept(MediaType.APPLICATION_JSON).content(content))
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	public void postArticleByMockingRequest() throws Exception {
		Article article = new Article();
		article.setId(1L);
		article.setTitle("Test");
		article.setContent("Sample Article");
		article.setEmail("email@com.sv");
		article.setPublished(true);
		ObjectMapper mapper = new ObjectMapper();

		byte[] content = mapper.writeValueAsBytes(article);
		MvcResult result = this.mockMvc.perform(
				MockMvcRequestBuilders.post("/articles/")
						.accept(MediaType.APPLICATION_JSON).content(content))
				.andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	public void deleteArticleByMockingRequest() throws Exception {
		String articleId = "1";
		MvcResult result = this.mockMvc.perform(
				MockMvcRequestBuilders.delete("/articles/" + articleId).accept(
						MediaType.APPLICATION_JSON)).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}
}
