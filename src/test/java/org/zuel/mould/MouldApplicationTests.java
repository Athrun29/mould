package org.zuel.mould;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zuel.mould.task.impl.NcJobExecutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MouldApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void ncJobExecute() {
        try {
            long startTime = System.currentTimeMillis();
            new NcJobExecutor().execute("/Users/athrun/Work/Docs/mould/ak7-23");
            long endTime = System.currentTimeMillis();
            System.out.println("NcJob cost: " + (endTime - startTime));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
