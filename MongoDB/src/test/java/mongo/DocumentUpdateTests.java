package mongo;

import com.mongodb.client.result.UpdateResult;
import jakarta.annotation.Resource;
import mongo.domain.Circle;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * @author Oliver
 * @date 2023年03月04日 21:08
 */
@SpringBootTest(classes = MongoApplication.class)
public class DocumentUpdateTests {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 条件更新
     */
    @Test
    void updateQuery() {
        // 单条更新
        /*UpdateResult updateResult = mongoTemplate.updateFirst(Query.query(Criteria.where("name").is("圈子101"))
                , new Update().set("name", "圈子2"), Circle.class);
        System.out.println("updateResult = " + updateResult);

        // 多条更新
        UpdateResult updateResult1 = mongoTemplate.updateMulti(Query.query(Criteria.where("name").is("圈子2"))
                , new Update().set("name", "圈子3"), Criteria.class);
        System.out.println("updateResult1 = " + updateResult1);*/

        // 更新插入
        UpdateResult upsert = mongoTemplate.upsert(Query.query(Criteria.where("name").is("upsert"))
                , new Update().set("name", "upsert").setOnInsert("_id", 1000), Circle.class);
        System.out.println("upsert = " + upsert);
    }
}
