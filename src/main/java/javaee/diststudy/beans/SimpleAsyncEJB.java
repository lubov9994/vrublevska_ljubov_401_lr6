package javaee.diststudy.beans;


import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import java.util.concurrent.Future;

@Stateless
@Asynchronous
public class SimpleAsyncEJB {

    @Resource
    SessionContext ctx;
    public Future<Integer> sum(int n1, int n2) {
        Integer result;

        int sum = 0;
        for (int i = n1; i < n2; i++) {
            sum += i;
        }
        try {
            // имитации запросов JPA
            Thread.sleep(2000);
            if (ctx.wasCancelCalled()) {
                return new AsyncResult<>(2);
            }
            // обработка
            return new AsyncResult<>(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return new AsyncResult(sum);
    }
}
