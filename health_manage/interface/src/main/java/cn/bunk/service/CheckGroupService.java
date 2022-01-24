package cn.bunk.service;

import cn.bunk.entity.PageResult;
import cn.bunk.entity.QueryPageBean;
import cn.bunk.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    public PageResult findPage(QueryPageBean queryPageBean);
    void add(CheckGroup checkGroup, Integer[] checkitemIds);
    Integer[] findCheckItemIdByCheckGroupId(Integer id);
    CheckGroup findById(Integer id);
    void edit(CheckGroup checkGroup, Integer[] checkItemIds);
    void delete(Integer id);
    List<CheckGroup> findAll();
}
