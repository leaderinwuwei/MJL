package own;

import io.micrometer.core.instrument.Metrics;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CaptainWang
 * @since 2024/11/15
 */
@SpringBootApplication()
public class WebApplication {

    public static void main(String[] args) {

        Metrics.counter("cashier",
                "bizCode", "1231",
                "statusCode", "13").increment();
        try {
            SpringApplication.run(WebApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
