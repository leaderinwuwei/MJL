package own.apollo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author CaptainWang
 * @since 2024/11/29
 */
@Component
public class ApolloConfig {

    @Value("${timeout:}")
    private String timeout;

    public String getTimeout() {
        return timeout;
    }

    /**
     * 顺序
     *
     * 1、jvm参数
     *
     * 2、apollo
     *
     * 3、环境变量
     *
     * 4、应用参数
     *
     * 5、带profile的配置文件
     *
     * 6、不带profile的配置文件
     *
     * 7、@PropertySource指定的文件
     */
    @Value("${timeout1:}")
    private String timeout1;

    public String getTimeout1() {
        return timeout1;
    }

}
