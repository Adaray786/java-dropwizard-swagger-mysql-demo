package org.kainos.ea.client;

import java.sql.SQLException;

public class FailedToGetOrdersException extends Throwable {
    @Override
    public String getMessage() {
        return "Failed to get Orders";
    }
}
