package com.qdbank.mall.web;

import com.qdbank.mall.MallMainApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = MallMainApplication.class)
@RunWith(SpringRunner.class)
@ActiveProfiles("sit2")
public class BaseSpringTest {
}
