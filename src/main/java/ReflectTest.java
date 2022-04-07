import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author xiwang
 * @date 2022-04-07 09:09
 */
public class ReflectTest {
    @Test
    public void testReflect() throws InvocationTargetException, IllegalAccessException {
        MyReflect reflect = new MyReflect("name");

        Method[] methods = MyReflect.class.getDeclaredMethods();
        Field[] fields = MyReflect.class.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.getName());
        }

        for (Method method : methods) {
            String name = method.getName();
            method.setAccessible(true);
            System.out.println(name);

            if (Objects.equals(name, "speak")) {
                method.invoke(reflect, "hello");
            }

            if (Objects.equals(name, "staticMethod")) {
                method.invoke(null);
            }

        }
    }

    @Test
    public void testPrivateMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MyReflect reflect = new MyReflect("name");
        Method method = MyReflect.class.getDeclaredMethod("speak", String.class);
        method.setAccessible(true);
        method.invoke(reflect, "hello");
    }

    @Test
    public void testStaticMethod() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = MyReflect.class.getDeclaredMethod("staticMethod");
        method.setAccessible(true);
        method.invoke(null);
        // 静态方法的反射不需要传入对象
//        method.invoke(reflect);
    }
}
