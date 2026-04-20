package com.solvd.smarthome.threads;

import com.solvd.smarthome.connection.Connection;
import com.solvd.smarthome.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureDemo {

    private static final Logger logger = LogManager.getLogger(CompletableFutureDemo.class);

    public static void runAll() throws Exception {

        ConnectionPool pool = ConnectionPool.getInstance(5);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            logger.info("[CF1] Fetching device name asynchronously...");
            return "Living Room TV";
        }, executor);

        logger.info("[CF1] Result: {}", cf1.get());

        CompletableFuture<String> cf2 = CompletableFuture
                .supplyAsync(() -> {
                    logger.info("[CF2] Getting device price...");
                    return 799.99;
                }, executor)
                .thenApply(price -> {
                    logger.info("[CF2] Formatting price: {}", price);
                    return "Price: $" + price;
                });

        logger.info("[CF2] Result: {}", cf2.get());

        CompletableFuture<String> cf3 = CompletableFuture
                .supplyAsync(() -> {
                    logger.info("[CF3] Resolving account ID...");
                    return 42;
                }, executor)
                .thenCompose(accountId -> CompletableFuture.supplyAsync(() -> {
                    logger.info("[CF3] Fetching account for id {}...", accountId);
                    Connection c = null;
                    try {
                        c = pool.getConnection();
                        return c.getAccount(accountId);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return "error";
                    } finally {
                        if (c != null) pool.releaseConnection(c);
                    }
                }, executor));

        logger.info("[CF3] Result: {}", cf3.get());

        CompletableFuture<String> deviceFuture = CompletableFuture.supplyAsync(() -> {
            logger.info("[CF4] Fetching device name...");
            return "Front Door Camera";
        }, executor);

        CompletableFuture<String> ownerFuture = CompletableFuture.supplyAsync(() -> {
            logger.info("[CF4] Fetching owner name...");
            return "John Smith";
        }, executor);

        CompletableFuture<String> cf4 = deviceFuture.thenCombine(ownerFuture,
                (device, owner) -> owner + " owns " + device);

        logger.info("[CF4] Result: {}", cf4.get());

        CompletableFuture<String> cf5 = CompletableFuture
                .supplyAsync(() -> {
                    logger.info("[CF5] Reading sensor value...");
                    if (true) throw new RuntimeException("Sensor offline");
                    return "67.8";
                }, executor)
                .exceptionally(ex -> {
                    logger.warn("[CF5] Sensor read failed: {}. Using default.", ex.getMessage());
                    return "0.0 (default)";
                });

        logger.info("[CF5] Result: {}", cf5.get());

        executor.shutdown();
        logger.info("All CompletableFuture demos complete.");
    }
}