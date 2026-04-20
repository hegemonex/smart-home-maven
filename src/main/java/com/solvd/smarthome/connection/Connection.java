package com.solvd.smarthome.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {

    private static final Logger logger = LogManager.getLogger(Connection.class);

    private final int id;

    public Connection(int id) {
        this.id = id;
        logger.info("Connection {} created.", id);
    }

    public int getId() {
        return id;
    }

    public void createAccount(String name) {
        logger.info("[Connection {}] CREATE account: {}", id, name);
    }

    public String getAccount(int accountId) {
        String fake = "Account{id=" + accountId + ", name='FakeUser" + accountId + "'}";
        logger.info("[Connection {}] GET account: {}", id, fake);
        return fake;
    }

    public void updateAccount(int accountId, String newName) {
        logger.info("[Connection {}] UPDATE account {} → name='{}'", id, accountId, newName);
    }

    public void deleteAccount(int accountId) {
        logger.info("[Connection {}] DELETE account {}", id, accountId);
    }

    @Override
    public String toString() {
        return "Connection{id=" + id + "}";
    }
}