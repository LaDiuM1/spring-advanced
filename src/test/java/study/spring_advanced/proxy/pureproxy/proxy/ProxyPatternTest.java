package study.spring_advanced.proxy.pureproxy.proxy;

import org.junit.jupiter.api.Test;
import study.spring_advanced.proxy.pureproxy.proxy.code.ProxyPatternClient;
import study.spring_advanced.proxy.pureproxy.proxy.code.SubjectCacheProxy;
import study.spring_advanced.proxy.pureproxy.proxy.code.RealSubject;

public class ProxyPatternTest {

    @Test
    void no_proxy_test() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cache_proxy_test() {
        SubjectCacheProxy subjectCacheProxy = new SubjectCacheProxy(new RealSubject());
        ProxyPatternClient client = new ProxyPatternClient(subjectCacheProxy);
        client.execute();
        client.execute();
        client.execute();
    }
}
