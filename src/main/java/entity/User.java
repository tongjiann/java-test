package entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-06-27 16:57
 */
@Data
public class User implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;
    private String id;
    private List<String> s;

    private Map<String ,String > map;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", s=" + s +
                ", map=" + map +
                '}';
    }
}
