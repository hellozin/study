package me.hellozin.study;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostRepository postRepository;

    @BeforeAll
    public void 데이터_추가() {
        for (int i = 0; i < 10; i++) {
            postRepository.save(new Post("title"+i, "study"+(i%3), i));
        }
    }

    @Test
    public void 포스트_전체조회() throws Exception {
        mockMvc.perform(get("/post/list"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    public void 포스트_제목으로_조회() throws Exception {
        mockMvc.perform(get("/post/list")
                .param("title", "title0")
        )
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void 포스트_태그로_조회() throws Exception {
        mockMvc.perform(get("/post/list")
                .param("tag", "study0")
        )
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    public void 포스트_좋아요수로_조회() throws Exception {
        mockMvc.perform(get("/post/list")
                .param("likesGreaterThan", "6")
        )
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(3)));
    }

}
