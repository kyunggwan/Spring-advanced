package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

    private final OrderRepositoryV2 orderRepository;
    private final HelloTraceV2 trace;

    public void orderItem(TraceId beforeTraceId, String itemId){

        TraceStatus status = null;
        try {
            status = trace.beginSync(beforeTraceId,"OrderService.orderItem()");
            orderRepository.save(status.getTraceId(), itemId);
            trace.end(status);
        } catch (Exception ex) {
            trace.exception(status, ex);
            throw ex; // 로그 때문에 정상 흐름이 방해되면 안되기 때문에 예외를 다시 던짐
        }

    }
}
