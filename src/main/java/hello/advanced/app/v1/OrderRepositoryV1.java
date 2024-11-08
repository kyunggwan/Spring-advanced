package hello.advanced.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 {

    private final HelloTraceV1 trace;

    public void save(String itemId) {


        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");
            // 저장 로직
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            trace.end(status);

        } catch (Exception ex) {
            trace.exception(status, ex);
            throw ex; // 로그 때문에 정상 흐름이 방해되면 안되기 때문에 예외를 다시 던짐
        }

    }

    private void sleep(int millis) {
         try {
             Thread.sleep(millis);
         }catch(InterruptedException e){
             e.printStackTrace();
        }

    }
}
