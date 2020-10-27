package tgbot.app.integration.trello.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.cloud.sleuth.instrument.async.TraceableExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nonnull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@RequiredArgsConstructor
public class ExecutionContextConfiguration {

    private final BeanFactory beanFactory;

    @Bean
    public ExecutorService cachedThreadPool() {
        final String name = "blocking-ec";
        final ThreadFactory threadFactory = new ThreadFactory() {

            final ThreadGroup threadGroup;
            final AtomicInteger threadCounter = new AtomicInteger(1);
            final String threadHash = Integer.toUnsignedString(this.hashCode());

            {
                final SecurityManager securityManager = System.getSecurityManager();
                if (securityManager != null) {
                    threadGroup = securityManager.getThreadGroup();
                } else {
                    threadGroup = Thread.currentThread().getThreadGroup();
                }
            }

            @Override
            public Thread newThread(@Nonnull Runnable r) {
                final int newThreadNumber = threadCounter.getAndIncrement();
                final Thread thread = new Thread(threadGroup, r);
                thread.setName(String.format("%s-%d-%s", name, newThreadNumber, threadHash));
                return thread;
            }
        };
        return new TraceableExecutorService(beanFactory, Executors.newCachedThreadPool(threadFactory));
    }
}
