package study.spring_advanced.proxy.postprocessor;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class BeanPostProcessorTest {

    @Test
    void basicConfig() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BasicConfig.class);

        // beanA 호출 시 빈 후처리기에 의해 B 클래스로 교체되었는지 확인
        Assertions.assertThat(ac.getBean("beanA")).isInstanceOf(B.class);
    }

    @Slf4j
    static class BasicConfig {
        @Bean(name = "beanA")
        public A a() {
            return new A();
        }

        @Bean
        public AToBPostProcessor aToBPostProcessor() {
            return new AToBPostProcessor();
        }
    }

    @Slf4j
    static class A {
        public void helloA() {
            log.info("hello A");
        }
    }

    @Slf4j
    static class B {
        public void helloB() {
            log.info("hello B");
        }
    }

    @Slf4j
    static class AToBPostProcessor implements BeanPostProcessor {
        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            log.info("beanName={}, bean={}", beanName, bean);
            if(bean instanceof A) {
                return new B();
            }

            return bean;
        }
    }
}
