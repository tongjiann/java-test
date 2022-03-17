import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author xiwang
 * @date 2022-03-17 16:09
 */
public class JacksonXmlTest {
    @Test
    public void testJacksonXml(){
        Students students = new Students();
        students.setId(1);
        students.setName(Arrays.asList("1","2"));
        XmlMapper xmlMapper = new XmlMapper();
        String s = null;
        try {
            s = xmlMapper.writeValueAsString(students);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }
}
