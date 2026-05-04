package com.solvd.smarthome.threads;

import com.solvd.smarthome.connection.Connection;
import com.solvd.smarthome.connection.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadDemo {

    private static final Logger logger = LogManager.getLogger(ThreadDemo.class);

    static class DeviceMonitorRunnable implements Runnable {
        private final String deviceName;

        DeviceMonitorRunnable(String deviceName) {
            this.deviceName = deviceName;
        }

        @Override
        public void run() {
            logger.info("[Runnable] Monitoring device: {} on thread '{}'",
                    deviceName, Thread.currentThread().getName());
        }
    }

    static class AlertThread extends Thread {
        private final String message;

        AlertThread(String message) {
            super("AlertThread");
            this.message = message;
        }

        @Override
        public void run() {
            logger.info("[Thread] Alert fired: '{}' on thread '{}'",
                    message, Thread.currentThread().getName());
        }
    }

    public static void runConnectionPoolDemo() throws InterruptedException {

        final int POOL_SIZE = 5;
        final int THREAD_COUNT = 7;

        ConnectionPool pool = ConnectionPool.getInstance(POOL_SIZE);

        Runnable connectionTask = () -> {
            Connection c = null;
            try {
                c = pool.getConnection();
                c.getAccount(1);
                c.updateAccount(1, "SmartHomeUser");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Thread interrupted while waiting for com.solvd.smarthome.connection.", e);
            } finally {
                if (c != null) {
                    pool.releaseConnection(c);
                }
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(connectionTask);
        }

        executor.shutdown();
        boolean finished = executor.awaitTermination(30, TimeUnit.SECONDS);

        if (finished) {
            logger.info("All com.solvd.smarthome.threads finished. Available connections: {}",
                    pool.availableConnections());
        } else {
            logger.warn("Timeout reached before all com.solvd.smarthome.threads finished.");
        }
    }

    public static void runThreadExamples() throws InterruptedException {

        Thread t1 = new Thread(new DeviceMonitorRunnable("Ceiling Light"), "MonitorThread");
        AlertThread t2 = new AlertThread("Motion detected at front door");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        logger.info("Both manual com.solvd.smarthome.threads finished.");
    }
}