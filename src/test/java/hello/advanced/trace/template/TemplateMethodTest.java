package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    @Test
    void templateMothodV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        // 비지니스 로직 실행
        log.info("비지니스 로직1 실행");
        // 비지니스 로직 종료
        long endTime = System.currentTimeMillis();

        long resultTimne = endTime - startTime;
        log.info("resultTime={}", resultTimne);
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        // 비지니스 로직 실행
        log.info("비지니스 로직2 실행");
        // 비지니스 로직 종료
        long endTime = System.currentTimeMillis();

        long resultTimne = endTime - startTime;
        log.info("resultTime={}", resultTimne);
    }

    /**
     * 템플릿 메서드 패턴 적용
     */
    @Test
    void templateMothodV1() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();
        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();

    }

    @Test
    void templateMothodV2() {
        AbstractTemplate template1 = new AbstractTemplate() { // 함수를 선언하면서 바로 구현 가능

            @Override
            protected void call() {
                log.info("비지니스 로직1 실행");
            }
        };
        template1.execute();
        log.info("익명 내부 클래스 이름1={}", template1.getClass());

        AbstractTemplate template2 = new AbstractTemplate() { // 함수를 선언하면서 바로 구현 가능

            @Override
            protected void call() {
                log.info("비지니스 로직2 실행");
            }
        };
        template2.execute();
        log.info("익명 내부 클래스 이름2={}", template2.getClass());
    }


}
