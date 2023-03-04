package mongo;

import jakarta.annotation.Resource;
import mongo.domain.Circle;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @author Oliver
 * @date 2023年03月04日 20:31
 */
@SpringBootTest(classes = MongoApplication.class)
public class DocumentFindTests {

    @Resource
    private MongoTemplate mongoTemplate;

    /**
     * 条件查询
     */
    @Test
    void find() {
        Circle circle = mongoTemplate.findOne(Query.query(Criteria.where("name").is("圈子102")), Circle.class);
        System.out.println("circle = " + circle);
        List<Circle> circles = mongoTemplate.find(Query.query(Criteria.where("age").gte(3)), Circle.class);
    }

    /**
     * and or 查询
     */
    @Test
    void findAndOr() {
        List<Circle> circles = mongoTemplate.find(Query.query(new Criteria().and("name")
                .orOperator(Criteria.where("name").is("圈子"))), Circle.class);
        circles.forEach(System.out::println);
    }

    /**
     * 排序查询
     */
    @Test
    void order() {
        List<Circle> circles = mongoTemplate.find(new Query().with(Sort.by(Sort.Order.desc("name"))), Circle.class);
        circles.forEach(System.out::println);
    }

    /**
     * 分页查询
     */
    @Test
    void page() {
        List<Circle> circles = mongoTemplate.find(new Query().skip(1L).limit(1), Circle.class);
        circles.forEach(System.out::println);
    }

    /**
     * 总条数查询
     */
    @Test
    void count() {
        long count = mongoTemplate.count(new Query(), Circle.class);
        System.out.println("count = " + count);
    }

    /**
     * 去重查询
     */
    @Test
    void distinct() {
        //参数 1:查询条件 参数 2: 去重字段 参数 3: 操作集合 参数 4: 返回类型
        List<Circle> distinct = mongoTemplate.findDistinct(new Query(), "name", Circle.class, Circle.class);
        distinct.forEach(System.out::println);
    }

    /**
     * 使用原生命令进行查询
     */
    @Test
    void bson() {
        Circle circle = mongoTemplate.findOne(new BasicQuery("{name: '圈子'}"), Circle.class);
        System.out.println("circle = " + circle);
    }
}
