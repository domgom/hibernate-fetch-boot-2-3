package com.example.demo;

import com.example.demo.model.Child;
import com.example.demo.model.Parent;
import com.example.demo.model.ParentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	ParentRepository repository;

	@BeforeEach()
	void init() {
		persistEntities();
	}

	@Test
	void lazyLoadTest() throws Exception {
		mockMvc.perform(get("/parent")).andExpect(status().isOk());
	}

	private void persistEntities() {

		var child1 = Child.builder().build();
		var child2 = Child.builder().build();

		var parent = Parent.builder().child(Arrays.asList(child1,child2)).build();

		child1.setParent(parent);
		child2.setParent(parent);

		repository.save(parent);
	}

}
