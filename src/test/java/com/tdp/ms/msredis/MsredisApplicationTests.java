package com.tdp.ms.msredis;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MsredisApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void main() {
        MsredisApplication.main(new String[] {});
    }

}
