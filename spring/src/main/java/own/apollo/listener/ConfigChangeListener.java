package own.apollo.listener;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.stereotype.Component;

/**
 * @see com.ctrip.framework.apollo.spring.annotation.ApolloAnnotationProcessor
 * @author CaptainWang
 * @since 2024/12/9
 */
@Component
public class ConfigChangeListener {
    // Inject the config for "someNamespace"
    @ApolloConfig("aaa")
    private Config config;

    // 该方法将被注解标记为配置变更监听器
    @ApolloConfigChangeListener
    public void onApolloConfigChange(ConfigChangeEvent changeEvent) {
        // 处理配置变更
        System.out.println(changeEvent.getChange("timeout"));
        System.out.println(config.getProperty("app.id","aaa"));
    }
}
