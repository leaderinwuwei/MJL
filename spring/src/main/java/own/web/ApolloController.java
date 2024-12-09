package own.web;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import own.apollo.MJLApolloConfig;

/**
 * @author CaptainWang
 * @since 2024/11/29
 */
@RestController
public class ApolloController {



    @Autowired
    private MJLApolloConfig apolloConfig;


    @GetMapping("aaa/111")
    public void getConfig() {
        System.out.println(apolloConfig.getTimeout());
        System.out.println(apolloConfig.getTimeout1());
    }
}
