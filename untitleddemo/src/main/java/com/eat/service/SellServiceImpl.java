package com.eat.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class SellServiceImpl implements SellService{
    Logger logger = Logger.getLogger(SellService.class);
    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public boolean check(String xgname){
//        String sql = "select * from seller where xgname='"+xgname+"'";
//        logger.debug(sql);
//        return dao.isExist(sql);
        logger.debug(xgname);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Map m = session.selectOne("com.xg.mapper.SellerMapper.check", xgname);
            logger.debug(m);
            return m!=null;
            // 如果有需要，可以提交事务
//            session.commit();
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean sregister(Map map) {
        logger.debug(map);
//        String sql="insert into seller(xgid,xgname,xgpass,xgtel,xgaddr)"+"values(seq_sellid.nextval,'"
//                +map.get("xgname")+"','"+map.get("xgpass")+"','"
//                +map.get("xgtel")+"','"+map.get("xgaddr")+"')";
        if (map.get("xgpass").equals(map.get("cfpass"))){
            boolean b = sqlSessionFactory.openSession().insert("com.xg.mapper.SellerMapper.register", map)>0;
            logger.debug(b);
            return b;
        } else {
            return false;
        }
    }

    @Override
    public boolean slogin(Map map) {
//        logger.debug(map);
//        String sql="select * from seller where xgname = '"+map.get("xgname")+"' and xgpass='"+map.get("xgpass")+"'";
        logger.debug(map);
        Map m = sqlSessionFactory.openSession().selectOne("com.xg.mapper.SellerMapper.login", map);
        logger.debug(m);
        return m!=null;
    }
}
