package com.example.demo;

import com.example.demo.model.Child;
import com.example.demo.model.Parent;
import com.example.demo.model.ParentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

    @Autowired
    ParentRepository repository;

    @Autowired
    private MockMvc mockMvc;

	@Before
	public void init() {
		persistEntities();
	}

    @Test
    public void lazyLoadTest() throws Exception {
        mockMvc.perform(get("/parent")).andExpect(status().isOk());
    }

    private void persistEntities() {

        Child child1 = Child.builder().build();
        Child child2 = Child.builder().build();

        Parent parent = Parent.builder().child(Arrays.asList(child1, child2)).build();

        child1.setParent(parent);
        child2.setParent(parent);

        repository.save(parent);
    }
}
