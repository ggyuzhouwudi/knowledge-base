package mongo;

import jakarta.annotation.Resource;
import mongo.domain.Circle;
import mongo.enums.OnlineStatus;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Oliver
 * @date 2023年03月04日 13:52
 */
@SpringBootTest(classes = MongoApplication.class)
public class DocumentSaveTests {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    void save() {
        Circle circle = Circle.builder().id(100L).name("圈子100_update").expose(true).onlineTopicSum(1)
                .status(OnlineStatus.ONLINE).sequence(1).build();
        Circle circle1 = mongoTemplate.save(circle); //根据主键saveOrUpdate
        // Circle circle1 = mongoTemplate.insert(circle); 只是添加，主键重复会报错
        System.out.println("circle1 = " + circle1);
        String desc = circle1.getStatus().getDesc();
        System.out.println(desc);
    }

    @Test
    void insertBath() {

        Circle circle = Circle.builder().id(102L).name("圈子102").expose(true).onlineTopicSum(1)
                .status(OnlineStatus.ONLINE).sequence(1).build();

        Circle circle1 = Circle.builder().id(101L).name("圈子101").expose(true).onlineTopicSum(1)
                .status(OnlineStatus.ONLINE).sequence(1).build();
        List<Circle> circles = new ArrayList<>(2);
        circles.add(circle);
        circles.add(circle1);
        Collection<Circle> insert = mongoTemplate.insert(circles, Circle.class);
        insert.forEach(System.out::println);
    }

}
