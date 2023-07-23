package com.prosegur.technicaltest.technicaltest;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SqlGroup({
        @Sql(value = "classpath:sql/schema.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
        @Sql(value = "classpath:sql/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
})
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_respond_ok_with_score() throws Exception {

        Map<String, String> expectedCases = new HashMap<>();
        expectedCases.put("11111111B", "6");
        expectedCases.put("22222222C", "55");
        expectedCases.put("33333333D", "77,5");
        expectedCases.put("44444444E", "35");

        for (Map.Entry<String, String> expectedCase: expectedCases.entrySet()) {
            this.mockMvc.perform(
                            MockMvcRequestBuilders. get("/customer/get-score/{dni}", expectedCase.getKey())
                    )
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.score").value(expectedCase.getValue()));
        }
    }

    @Test
    public void should_respond_ok_with_customer_dni() throws Exception {

        Map<String, String> expectedCases = new HashMap<>();
        expectedCases.put("Banco Santander", "22222222C");
        expectedCases.put("Ibercaja", "33333333D");

        for (Map.Entry<String, String> expectedCase: expectedCases.entrySet()) {
            this.mockMvc.perform(
                            MockMvcRequestBuilders. get("/customer/get-highest-score/{oriEntity}", expectedCase.getKey())
                    )
                    .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.customer_dni").value(expectedCase.getValue()));
        }
    }
}
