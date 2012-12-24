package com;

import com.context.MongoConfig;
import com.context.model.Doc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        MongoConfig.class
})
public class MongoDbTest {
    @Autowired
    private MongoOperations mongo;

    @Test
    public void performTest() {
        int counter = 0;
        while (true) {
            Doc doc = new Doc();
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < 100; i++) {
                sb.append(UUID.randomUUID().toString());
            }
            doc.setContent(sb.toString());
            mongo.save(doc);
            mongo.find(Query.query(Criteria.where("content").is(sb.toString())), Doc.class);
            if (++counter % 1000 == 0) {
                System.out.println(counter);
            }
        }
    }
}
