package study.spring_advanced.aop.app.member;

import org.springframework.stereotype.Component;
import study.spring_advanced.aop.config.annotation.ClassAop;
import study.spring_advanced.aop.config.annotation.MethodAop;

@ClassAop
@Component
public class MemberServiceImpl implements MemberService {

    @Override
    @MethodAop("test value")
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "ok";
    }

}
