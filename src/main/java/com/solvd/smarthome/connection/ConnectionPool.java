package com.solvd.smarthome.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private static volatile ConnectionPool pool;

    private final BlockingQueue<Connection> connections;

    private ConnectionPool(int size) {
        connections = new ArrayBlockingQueue<>(size);
        for (int i = 1; i <= size; i++) {
            connections.offer(new Connection(i));
        }
        logger.info("ConnectionPool initialised with {} connections.", size);
    }

    public static ConnectionPool getInstance(int size) {
        if (pool == null) {
            synchronized (ConnectionPool.class) {
                if (pool == null) {
                    pool = new ConnectionPool(size);
                }
            }
        }
        return pool;
    }

    public Connection getConnection() throws InterruptedException {
        logger.info("Thread '{}' waiting for com.solvd.smarthome.connection...", Thread.currentThread().getName());
        Connection c = connections.take();
        logger.info("Thread '{}' acquired {}.", Thread.currentThread().getName(), c);
        return c;
    }

    public void releaseConnection(Connection c) {
        connections.offer(c);
        logger.info("Thread '{}' released {}.", Thread.currentThread().getName(), c);
    }

    public int availableConnections() {
        return connections.size();
    }
}
