package study.spring_advanced.aop.app;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import study.spring_advanced.aop.app.member.MemberServiceImpl;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * execution: 메서드의 실행 시점을 기준으로 AOP 적용 대상을 지정하는 포인트컷 표현식
 * - 메서드 시그니처(접근제어자, 반환타입, 클래스, 메서드명, 파라미터 등)를 기준으로 매칭
 * - 메서드 단위로 매우 정밀한 제어가 가능하며, 상속 및 오버라이드 관계도 고려함
 */
@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method method;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        // MemberServiceImpl.hello(String) 메서드 정보를 리플렉션으로 가져옴
        method = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    void printMethod() {
        // 메서드 시그니처 확인용
        // 출력 예: public java.lang.String study.spring_advanced.aop.app.member.MemberServiceImpl.hello(java.lang.String)
        log.info("method={}", method);
    }

    /**
     * 정확한 메서드 시그니처 매칭
     * 접근제어자, 반환타입, 패키지, 클래스, 메서드명, 파라미터 모두 일치해야 매칭됨
     */
    @Test
    void exactMatch() {
        pointcut.setExpression("execution(public String study.spring_advanced.aop.app.member.MemberServiceImpl.hello(String))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 모든 메서드(*)와 모든 파라미터(..)에 매칭
     * 즉, 어떤 메서드든 무조건 true
     */
    @Test
    void allMatch() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 메서드 이름만 지정하여 매칭
     * hello라는 이름을 가진 모든 메서드에 매칭
     */
    @Test
    void nameMatch() {
        pointcut.setExpression("execution(* hello(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 메서드 이름 패턴 매칭 (와일드카드 사용)
     * hel로 시작하는 모든 메서드에 매칭
     */
    @Test
    void namePatternMatch() {
        pointcut.setExpression("execution(* hel*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 존재하지 않는 메서드명은 매칭되지 않음
     */
    @Test
    void nameMatchFalse() {
        pointcut.setExpression("execution(* noExistName(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isFalse();
    }

    /**
     * 패키지까지 정확히 지정하여 매칭
     */
    @Test
    void packageExactMatch() {
        pointcut.setExpression("execution(* study.spring_advanced.aop.app.member.MemberServiceImpl.hello(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 패키지 내부 클래스의 모든 메서드 매칭
     * study.spring_advanced.aop.app.member 패키지 하위의 모든 클래스/메서드 허용
     */
    @Test
    void packageExactPatternMatch() {
        pointcut.setExpression("execution(* study.spring_advanced.aop.app.member.*.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 하위 패키지를 포함하지 않기 때문에 매칭 실패
     */
    @Test
    void packageExactMatchFalse() {
        pointcut.setExpression("execution(* study.spring_advanced.aop.*.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isFalse();
    }

    /**
     * 하위 패키지까지 포함하여 매칭 (..)
     * app 하위의 모든 패키지까지 포함됨
     */
    @Test
    void packageExactSubPackageMatch() {
        pointcut.setExpression("execution(* study.spring_advanced.aop.app..*.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 정확한 타입(클래스)에 대한 매칭
     */
    @Test
    void typeExactMatch() {
        pointcut.setExpression("execution(* study.spring_advanced.aop.app.member.MemberServiceImpl.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 부모 타입(인터페이스)에도 매칭 가능
     * 즉, 부모 타입의 메서드를 오버라이드한 경우 true
     */
    @Test
    void typeMatchSuperType() {
        pointcut.setExpression("execution(* study.spring_advanced.aop.app.member.MemberService.*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 부모 타입에는 없는 내부 메서드는 매칭되지 않음
     */
    @Test
    void typeMatchInternalFalse() throws NoSuchMethodException {
        pointcut.setExpression("execution(* study.spring_advanced.aop.app.member.MemberService.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
    }

    // ==============================
    // 파라미터 매칭 테스트
    // ==============================

    /**
     * 정확히 String 타입 하나를 매개변수로 가지는 메서드 매칭
     */
    @Test
    void argsMatch() {
        pointcut.setExpression("execution(* *(String))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 파라미터가 없어야 함
     * hello(String) 은 파라미터가 있으므로 false
     */
    @Test
    void argsMatchNoArgs() {
        pointcut.setExpression("execution(* *())");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isFalse();
    }

    /**
     * 파라미터 타입 제한 없이 1개만 있는 경우 매칭
     */
    @Test
    void argsMatchStar() {
        pointcut.setExpression("execution(* *(*))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 모든 형태의 파라미터를 허용
     * 0개 이상 모든 경우 매칭됨
     */
    @Test
    void argsMatchAll() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    /**
     * 첫 번째 파라미터가 String 타입이고,
     * 그 뒤는 아무 파라미터나 여러 개 허용
     * ex) (String), (String, int), (String, Object, ...)
     */
    @Test
    void argsMatchComplex() {
        pointcut.setExpression("execution(* *(String, ..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

}
