package mongo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mongo.enums.OnlineStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 圈子模型
 *
 * @author Oliver
 * @date 2023年03月04日 13:25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("tb_circle")
public class Circle {

    @Id
    private Long id;

    /**
     * 圈子名称
     */
    private String name;

    /**
     * 线上话题数
     */
    private Integer onlineTopicSum;

    /**
     * 是否话题外显
     */
    private Boolean expose;

    /**
     * 状态
     */
    private OnlineStatus status;

    /**
     * 排序值
     */
    private Integer sequence;

}
