package cn.bunk.dao;

import cn.bunk.pojo.CheckGroup;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {

    public Page<CheckGroup> findPage(String queryString);

    public void add(CheckGroup checkGroup);

    public void setCheckGroupAndCheckItem(Map<String, Integer> map);

    public Integer[] findCheckItemIdByCheckGroupId(Integer id);

    public CheckGroup findById(Integer id);

    public void edit(CheckGroup checkGroup);

    public void deleteCheckItemByCheckGroupId(Integer id);

    public void delete(Integer id);

    List<CheckGroup> findAll();
}
