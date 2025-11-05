package study.spring_advanced.aop.pointcut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import study.spring_advanced.aop.app.member.MemberServiceImpl;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * within: 지정한 타입(클래스, 패키지)에 속한 메서드에만 AOP를 적용하는 포인트컷 표현식
 * - 클래스/패키지 기준으로 매칭 (부모 타입 매칭 불가)
 * - execution과 달리 상속 관계는 고려하지 않음
 */
public class WithinTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method helloMethod;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        helloMethod = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    /**
     * 정확한 클래스명 기준 매칭
     */
    @Test
    void withinExact() {
        pointcut.setExpression("within(study.spring_advanced.aop.app.member.MemberServiceImpl)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 클래스명 패턴 매칭 (와일드카드)
     */
    @Test
    void withinStar() {
        pointcut.setExpression("within(study.spring_advanced.aop.app.member.*Service*)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 하위 패키지까지 포함하여 매칭 (..)
     */
    @Test
    void withinSubPackage() {
        pointcut.setExpression("within(study.spring_advanced.aop.app..*)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }

    /**
     * within은 부모 타입(인터페이스) 매칭 불가
     */
    @Test
    void withinSuperTypeFalse() {
        pointcut.setExpression("within(study.spring_advanced.aop.app.member.MemberService)");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isFalse();
    }

    /**
     * execution은 부모 타입의 메서드도 매칭 가능
     */
    @Test
    void executionSuperTypeTrue() {
        pointcut.setExpression("execution(* study.spring_advanced.aop.app.member.MemberService.*(..))");
        assertThat(pointcut.matches(helloMethod, MemberServiceImpl.class)).isTrue();
    }
}
