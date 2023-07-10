import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import org.junit.Test;

public class DockerTest {

    @Test
    public void start(){
        DockerClient localhost = DockerClientBuilder.getInstance("tcp://localhost:2375").build();
        System.out.println(localhost.listContainersCmd());

    }
}
