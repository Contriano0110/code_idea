package com.eat.service;

import com.eat.entity.Menu;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

public interface MenuService {
    boolean insertMenu(Menu menu, String sellname);
    boolean delete(int xgid);
    boolean check(String name, String sellername);
    List getMenu(String name);
    List getsMenu(Map map);
}
