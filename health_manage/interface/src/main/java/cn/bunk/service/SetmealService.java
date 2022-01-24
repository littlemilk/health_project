package cn.bunk.service;

import cn.bunk.entity.PageResult;
import cn.bunk.entity.QueryPageBean;
import cn.bunk.pojo.Setmeal;

import java.util.List;

public interface SetmealService {
    PageResult findPage(QueryPageBean queryPageBean);

    void add(Integer[] checkgroupIds, Setmeal setmeal);

    Setmeal findById(Integer id);

    List<Integer> findGroupIdsBySetmealId(Integer id);

    void edit(Setmeal setmeal, Integer[] checkgroupIds);

    void delete(Integer id);
}
