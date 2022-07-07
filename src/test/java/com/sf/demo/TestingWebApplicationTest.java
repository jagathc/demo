package com.sf.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TestingWebApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllShouldReturnEmployees() throws Exception {
        this.mockMvc.perform(get("/getAll")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("[150] Jamie\n" +
                        "        [100] Allan\n" +
                        "            [220] Martin\n" +
                        "            [275] Alex\n" +
                        "        [400] Steve\n" +
                        "            [190] David")));
    }
}
