import entity.User;
import org.junit.Test;

import java.io.*;
import java.util.UUID;

/**
 * @author xiwang
 * @apiNote
 * @since 2022-08-10 18:29
 */
public class InputStreamTest {

    @Test
    public void InputStreamTest() throws IOException, ClassNotFoundException {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        new ObjectOutputStream(new FileOutputStream("user.dat")).writeObject(user);
        Object o = new ObjectInputStream(new FileInputStream("user.dat")).readObject();
        System.out.println(o);
    }
}
