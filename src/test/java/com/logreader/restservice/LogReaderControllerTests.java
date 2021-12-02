/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.logreader.restservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class LogReaderControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void noFileExistMessage() throws Exception {
		this.mockMvc.perform(post("/logreader")
				.content(asJsonString(new LogInput("system1.log", 5, "error", null)))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void noOccuranceMessage() throws Exception {
		this.mockMvc.perform(post("/logreader")
						.content(asJsonString(new LogInput("system.log", 0, "error", null)))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void noEventMessage() throws Exception {
		this.mockMvc.perform(post("/logreader")
						.content(asJsonString(new LogInput("system.log", 5, null, null)))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void successMessage() throws Exception {
		this.mockMvc.perform(post("/logreader")
						.content(asJsonString(new LogInput("system.log", 5, "error", null)))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

//	@Test
//	public void noParamGreetingShouldReturnDefaultMessage() throws Exception {
//
//		this.mockMvc.perform(get("/logreader")).andDo(print()).andExpect(status().isOk())
//				.andExpect(jsonPath("$.content").value("Hello, World!"));
//	}
//
//	@Test
//	public void paramGreetingShouldReturnTailoredMessage() throws Exception {
//
//		this.mockMvc.perform(get("/logreader").param("fileName", "Spring Community"))
//				.andDo(print()).andExpect(status().isOk())
//				.andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
//	}

}
