package com.eat.service;

import com.eat.util.MyUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService{
    Logger logger = Logger.getLogger(CartServiceImpl.class);
    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Override
    public boolean insert(String memid, int menuid) {
        Map map = new HashMap();
        map.put("memid",memid);
        map.put("menuid",menuid);
        boolean b = sqlSessionFactory.openSession().insert("com.xg.mapper.CartMapper.insert",map)>0;
        logger.debug(b);
        return b;
//        String sql = "insert into cart(xgmenuid,xgmemid) values('" + menuid + "', '" + memid + "')";
//        return dao.exeUpdate(sql);
    }

    @Override
    public boolean delete(String memid, int menuid) {
        Map map = new HashMap();
        map.put("memid",memid);
        map.put("menuid",menuid);
        boolean b = sqlSessionFactory.openSession().delete("com.xg.mapper.CartMapper.delete",map)>0;
        return b;
//        String sql = "delete from cart where xgmemid='"+memid +"' and xgmenuid='"+menuid+"'";
//        return dao.exeUpdate(sql);
    }

    @Override
    public List select(String name, String memid) {
//        String sql = "select menu.xgid,xgpic,xgname,xgprice,xgremark " + " from menu,cart" + " where menu.xgid=cart.xgmenuid"
//					+ " and xgmemid='" + memid + "'" + " and xgname like '%" + name + "%'";
//        return dao.getData(sql);
        Map map=new HashMap();
        map.put("name",name);
        map.put("memid",memid);
        List lst = sqlSessionFactory.openSession().selectList("com.xg.mapper.CartMapper.discart",map);
        logger.debug(lst);
        return lst;
    }
}
