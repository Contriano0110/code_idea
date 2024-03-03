package com.eat.service;

import com.eat.entity.Menu;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl implements MenuService {
    Logger logger = Logger.getLogger(MenuServiceImpl.class);

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public boolean insertMenu(Menu menu, String sellname) {
//        logger.debug(sellname);
//        int sellid = dao.count("select xgid from seller where xgname='" + sellname + "'");
//        logger.debug(sellid);
        int sellid=sqlSessionFactory.openSession().selectOne("com.xg.mapper.SellerMapper.getid",sellname);
        logger.debug(sellid);
//        String pic = menu.getXgname() + "_.png";
//        logger.debug(pic);
//        logger.debug(menu);
//        //insert into menu values(seq_id.nextval,'糖醋里脊',56,'tclj','精选上等里脊','1');
//        String sql = "insert into menu values(seq_id.nextval,'"
//                + menu.getXgname() + "','" + menu.getXgprice() + "','" + pic + "','" + menu.getXgremark() + "','" + sellid + "')";
//        logger.debug(sql);
//        //return dao.exeUpdate(sql);
//        boolean b = dao.exeUpdate(sql);
//        logger.debug(b);
        menu.setXgpic(menu.getXgname()+"_.png");
        menu.setXgsellid(sellid);
        logger.debug(menu);
        boolean b = sqlSessionFactory.openSession().insert("com.xg.mapper.MenuMapper.insert", menu) > 0;
        logger.debug(b);
        return b;

    }

    @Override
    public boolean delete(int xgid) {
//        String sql = "delete from menu where xgid=" + xgid;
//        logger.debug(sql);
        boolean b= sqlSessionFactory.openSession().delete("com.xg.mapper.MenuMapper.delete",xgid)>0;
        return b;
    }

    @Override
    public boolean check(String name, String sellername) {//判断商家新增菜是否重复
        //String sql="select a.*,b.xgname from menu a left join seller b " +
        //                "on a.xgsellid=b.xgid where b.xgname='"+sellname+"' and a.xgname like '%"+name+"%'";
        //String sql = "select * from menu where xgname='"+name+"'";
//        String sql = "select a.*,b.xgname from menu a left join seller b " +
//                "on a.xgsellid=b.xgid where b.xgname='" + sellername + "' and a.xgname = '" + name + "'";
//        logger.debug(sql);
//        return dao.isExist(sql);
        Map map = new HashMap();
        map.put("name", name);
        map.put("sellername", sellername);
        logger.debug(map);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            Map m = session.selectOne("com.xg.mapper.MenuMapper.check",map);
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
    public List getMenu(String name) {
//        String sql = "select * from menu where xgname like '%" + name + "%'";
//        logger.debug(sql);
        List l = sqlSessionFactory.openSession().selectList("com.xg.mapper.MenuMapper.memberselect",name);
        logger.debug(l);
        return l;
    }

    @Override
    public List getsMenu(Map map) {
        //select a.*,b.xgname from menu a left join seller b on a.xgsellid=b.xgid where b.xgname='s1' and a.xgname like '%%';
//        String sql = "select a.* from menu a left join seller b " +
//                "on a.xgsellid=b.xgid where b.xgname='" + sellname + "' and a.xgname like '%" + name + "%'";
//        logger.debug(sql);
//        List lst = dao.getData(sql);
//        logger.debug(lst);
        logger.debug(map);
        List l = sqlSessionFactory.openSession().selectList("com.xg.mapper.MenuMapper.sellerselect",map);
        logger.debug(l);
        return l;
    }
}
