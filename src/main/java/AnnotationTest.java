import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String value() default "default value";
}

public class AnnotationTest {
    public static void main(String[] args) throws NoSuchMethodException {
        Class<MyAnnotation> annotationClass = MyAnnotation.class;
        MyAnnotation annotation = annotationClass.getAnnotation(MyAnnotation.class);
        String defaultValue = annotation.value();
        System.out.println("Default value: " + defaultValue);
    }
}
