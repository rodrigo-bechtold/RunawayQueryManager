package com.rodrigo.RunawayQueryManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RunawayQueryManagerApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testQueryCancel() throws Exception{

		String expected = "Query was cancelled due to timeout";

		this.mockMvc.perform(get("/query").queryParam("t", "6"))
				.andDo(print())
				.andExpect(status().is5xxServerError())
				.andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andExpect(content().string(expected));
	}


	@Test
	void testQueryOk() throws Exception{

		String expected = "{\n" +
				"\t\"value\": 2\n" +
				"}";

		this.mockMvc.perform(get("/query").queryParam("t", "2"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().json(expected));
	}

}
