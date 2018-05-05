package com.crossover.techtrial;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.crossover.techtrial.controller.ArticleController;
import com.crossover.techtrial.controller.CommentController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrossBlogsApplicationTests {


	@InjectMocks
	private CommentController commentController;
	@Test
	public void contextLoads() {
	}

}
