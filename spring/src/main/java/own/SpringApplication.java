package own;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import own.apollo.ApolloConfig;

/**
 * @author CaptainWang
 * @since 2024/11/15
 */
@SpringBootApplication
public class SpringApplication {



    public static void main(String[] args) {

        try {
            org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
