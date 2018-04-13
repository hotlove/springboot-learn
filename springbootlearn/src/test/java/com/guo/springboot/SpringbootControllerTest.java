package com.guo.springboot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class SpringbootControllerTest {

    @Resource
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testAddUser() throws Exception
    {
        //构造添加的用户信息

        //调用接口，传入添加的用户参数
        mockMvc.perform(post("/profile/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(""));
                //判断返回值，是否达到预期，测试示例中的返回值的结构如下{"errcode":0,"errmsg":"OK","p2pdata":null}
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
                //使用jsonPath解析返回值，判断具体的内容
//                .andExpect(jsonPath("$.errcode", is(0)))
//                .andExpect(jsonPath("$.p2pdata", notNullValue()))
//                .andExpect(jsonPath("$.p2pdata.id", not(0)))
//                .andExpect(jsonPath("$.p2pdata.name", is("testuser2")));
    }
}
