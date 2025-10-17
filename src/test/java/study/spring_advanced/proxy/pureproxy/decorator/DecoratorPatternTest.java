package study.spring_advanced.proxy.pureproxy.decorator;

import org.junit.jupiter.api.Test;
import study.spring_advanced.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import study.spring_advanced.proxy.pureproxy.decorator.code.MessageDecorator;
import study.spring_advanced.proxy.pureproxy.decorator.code.RealComponent;
import study.spring_advanced.proxy.pureproxy.decorator.code.TimeDecorator;

public class DecoratorPatternTest {

    @Test
    void no_decorator() {
        DecoratorPatternClient client = new DecoratorPatternClient(new RealComponent());
        client.execute();
    }

    @Test
    void decorator() {
        MessageDecorator messageDecorator = new MessageDecorator(new RealComponent());
        TimeDecorator timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);
        client.execute();
    }
}
