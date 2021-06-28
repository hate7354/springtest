package com.base.webapp.common.tray;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

public abstract class TrayFactory {

	protected abstract Tray createTray(ResultSet resultSet);
	protected abstract Tray createTray(HttpServletRequest request);

    public Tray getTray(ResultSet resultSet) {
        return createTray(resultSet);
    }

    public Tray getTray(HttpServletRequest request) {
        return createTray(request);
    }
}
