package com.eat.service;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public interface SellService {
    boolean check(String xgname) throws SQLException;
    boolean sregister(Map map);
    boolean slogin(Map map);
}
