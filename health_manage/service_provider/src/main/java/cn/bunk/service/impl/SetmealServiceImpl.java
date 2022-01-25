package cn.bunk.service.impl;

import cn.bunk.constant.RedisConstant;
import cn.bunk.dao.SetmealDao;
import cn.bunk.entity.PageResult;
import cn.bunk.entity.QueryPageBean;
import cn.bunk.pojo.Setmeal;
import cn.bunk.service.SetmealService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    @Autowired
    private JedisPool jedisPool;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        int currentPage = queryPageBean.getCurrentPage();
        int pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);
        Page page = setmealDao.findPage(queryString);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void add(Integer[] checkgroupIds, Setmeal setmeal) {
        setmealDao.add(setmeal);
        this.setSetmealIdAndCheckgroupId(setmeal.getId(), checkgroupIds);
        String fileName = setmeal.getImg();
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, fileName);
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealDao.findById(id);
    }

    @Override
    public List<Integer> findGroupIdsBySetmealId(Integer id) {
        return setmealDao.findGroupIdsBySetmealId(id);
    }

    @Override
    public void edit(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.updateSetmeal(setmeal);
        this.updateCheckgroupIds(setmeal.getId(), checkgroupIds);
    }

    @Override
    public void delete(Integer id) {
        setmealDao.delete(id);
    }

    public void setSetmealIdAndCheckgroupId(int setmealId, Integer[] checkgroupIds){
        for (int checkgroupId : checkgroupIds){
            Map map = new HashMap<Integer, Integer>();
            map.put("setmealId",setmealId);
            map.put("checkgroupId",checkgroupId);
            setmealDao.setSetmealIdAndCheckgroupId(map);
        }
    }

    public void updateCheckgroupIds(int id, Integer[] checkgroupIds){
        setmealDao.deleteCheckgroupIds(id);
        this.setSetmealIdAndCheckgroupId(id, checkgroupIds);
    }
}
