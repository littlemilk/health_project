package cn.bunk.dao;

import cn.bunk.pojo.Setmeal;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    Page findPage(String queryString);

    void setSetmealIdAndCheckgroupId(Map map);

    void add(Setmeal setmeal);

    Setmeal findById(Integer id);

    List<Integer> findGroupIdsBySetmealId(Integer id);

    void deleteCheckgroupIds(int id);

    void updateSetmeal(Setmeal setmeal);

    void delete(Integer id);
}
