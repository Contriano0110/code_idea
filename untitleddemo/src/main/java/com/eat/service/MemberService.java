package com.eat.service;

import org.springframework.stereotype.Service;

import java.util.Map;

public interface MemberService {
    boolean check(String name);
    boolean register(Map map);
    boolean login(Map map);
}
