package study.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component {

    private final Component target;

    public MessageDecorator(Component target) {
        this.target = target;
    }

    public String operation() {
        return decorate(target.operation());
    }

    private String decorate(String message) {
        return String.format("decorator 적용 [%s]", message);
    }
}
