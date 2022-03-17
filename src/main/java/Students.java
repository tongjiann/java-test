import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

import java.util.List;

/**
 * @author xiwang
 * @date 2022-03-17 16:09
 */
@Data
public class Students {
    @JacksonXmlProperty(localName = "id")
    private int id;

    @JacksonXmlElementWrapper(localName="name",useWrapping = false)
    private List<String> name;
}
