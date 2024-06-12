package com.bugproof.service;

import com.bugproof.model.Bar;
import com.bugproof.model.Foo;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class BugproofTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BugproofTest.class);

    @Inject
    private BusinessService businessService;
    @Inject
    private DatabaseService databaseService;


    @Test
    void shouldHandleConcurrentSaves() throws InterruptedException {
        final int threadsNo = 50;

        final CountDownLatch latch = new CountDownLatch(threadsNo);
        try (final ExecutorService executor = Executors.newFixedThreadPool(threadsNo)) {
            for (int i = 0; i < threadsNo; i++) {
                final long fooId = i + 1;
                executor.execute(() -> {
                    try {
                        businessService.saveBar(new Bar(null, "bar_" + fooId, fooId));
                    } catch (final Exception e) {
                        LOGGER.error(e.getMessage());
                    } finally {
                        latch.countDown();
                    }
                });
            }
            latch.await();
        }

        final List<Foo> foos = databaseService.getFoos();
        final List<Bar> bars = databaseService.getBars();
        assertEquals(threadsNo, bars.size());
        assertEquals(threadsNo, foos.size());
    }

}
