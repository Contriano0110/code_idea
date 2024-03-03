package com.eat.service;

import java.util.List;

public interface CartService {
    boolean insert(String memid, int menuid);
    boolean delete(String memid, int menuid);
    List select(String name, String memid);
}
