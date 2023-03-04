package mongo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 上线状态枚举
 * @author Oliver
 * @date 2023年03月04日 13:35
 */
@Getter
@AllArgsConstructor
public enum OnlineStatus {

    OFFLINE(0, "未上线"),
    ONLINE(1, "已上线");

    private final Integer code;

    private final String desc;

    public static String getName(int code) {
        for (OnlineStatus c : OnlineStatus.values()) {
            if (c.getCode() == code) {
                return c.desc;
            }
        }
        return null;
    }
}
