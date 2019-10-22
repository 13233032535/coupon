package com.imooc.coupon.filter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 限流过滤器
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class RateLimitFilter extends AbstractPreZuulFilter {

    /** 每秒可以获取到两个令牌 */
    RateLimiter rateLimiter = RateLimiter.create(2.0);

    @Override
    protected Object cRun() {
        HttpServletRequest request = context.getRequest();

        if(rateLimiter.tryAcquire()){
            log.info("get rate token success");
            return success();
        }else{
            log.error("rate limit :{}",request.getRequestURI());
            return fail(402,"error rate limit");
        }
    }

    @Override
    public int filterOrder() {
        return 2;
    }
}
