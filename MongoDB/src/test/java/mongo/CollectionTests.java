package mongo;

import com.mongodb.client.MongoCollection;
import jakarta.annotation.Resource;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Set;

/**
 * @author Oliver
 * @date 2023年03月04日 11:32
 */
@SpringBootTest(classes = MongoApplication.class)
public class CollectionTests {

    // 自动在工厂创建，直接注入使用
    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 创建集合
     */
    @Test
    void create() {
        boolean exists = mongoTemplate.collectionExists("users");
        if (!exists) {
            MongoCollection<Document> users = mongoTemplate.createCollection("users");
            System.out.println("users = " + users);
        }
    }

    /**
     * 查询集合
     */
    @Test
    void query() {
        MongoCollection<Document> temp = mongoTemplate.getCollection("temp");
        System.out.println("temp = " + temp);
        Set<String> collectionNames = mongoTemplate.getCollectionNames();
        collectionNames.forEach(System.out::println);
    }

    /**
     * 删除集合
     */
    @Test
    void drop() {
        boolean exists = mongoTemplate.collectionExists("users");
        if (exists) {
            mongoTemplate.dropCollection("users");
        }
    }

}
