package com.eat.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    Logger logger = Logger.getLogger(MemberService.class);
    @Override
    public boolean check(String memid) {//注册检查memid是否被注册
//        String sql = "select * from member where xgmemid='"+memid+"'";
//        return dao.isExist(sql);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Map m = session.selectOne("com.xg.mapper.MemberMapper.check",memid);
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
    public boolean register(Map map) {
        logger.debug(map);
        boolean b = false;
//        String sql="insert into member(xgmemid,xgmempass,xgmemalias)"+"values('"
//                +map.get("xgmemid")+"','"+map.get("xgmempass")+"','"
//                +map.get("xgmemalias")+"')";
//        logger.debug(sql);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            b = session.insert("com.xg.mapper.MemberMapper.register", map)>0;
            // 如果有需要，可以提交事务
//            session.commit();
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        logger.debug(b);
        return b;
    }

    @Override
    public boolean login(Map map) {
        logger.debug(map);
//        String sql="select * from member where xgmemid = '"
//                +map.get("xgmemid")+"' and xgmempass='"
//                +map.get("xgmempass")+"'";
//        logger.debug(sql);
        Map m = sqlSessionFactory.openSession().selectOne("com.xg.mapper.MemberMapper.login",map);
        logger.debug(m);
        return m!=null;
    }
}
