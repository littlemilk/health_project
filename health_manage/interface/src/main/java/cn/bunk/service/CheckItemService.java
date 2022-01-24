package cn.bunk.service;


import cn.bunk.entity.PageResult;
import cn.bunk.entity.QueryPageBean;
import cn.bunk.pojo.CheckItem;

import java.util.List;


public interface CheckItemService {
    void add(CheckItem checkItem);
    PageResult findPage(QueryPageBean queryPageBean);
    CheckItem findById(Integer id);
    void edit(CheckItem checkItem);
    void delete(Integer id);
    List<CheckItem> findAll();
}
