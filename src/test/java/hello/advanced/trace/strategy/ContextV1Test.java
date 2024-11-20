package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {


    @Test
    void strategyV0() {
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
     * 전략 패턴 사용
     */
    @Test
    void strategyV1() {
        StrategyLogic1 logic1 = new StrategyLogic1();
        ContextV1 contextV1 = new ContextV1(logic1);

        contextV1.execute();

        StrategyLogic2 logic2 = new StrategyLogic2();
        ContextV1 contextV2 = new ContextV1(logic2);

        contextV2.execute();
    }


}
