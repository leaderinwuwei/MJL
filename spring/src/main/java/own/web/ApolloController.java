package own.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import own.apollo.ApolloConfig;
import own.apollo.ApolloOtherConfig;

/**
 * @author CaptainWang
 * @since 2024/11/29
 */
@RestController
public class ApolloController {


    @Autowired
    private ApolloConfig apolloConfig;


    @GetMapping("aaa/111")
    public void getConfig() {
        System.out.println(apolloConfig.getTimeout());
        System.out.println(apolloConfig.getTimeout1());
    }
}
