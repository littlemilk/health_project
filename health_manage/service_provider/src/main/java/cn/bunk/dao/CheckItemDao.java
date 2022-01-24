package cn.bunk.dao;

import cn.bunk.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;

public interface CheckItemDao {

    public void add(CheckItem checkItem);

    public Page<CheckItem> findPage(String queryString);

    public CheckItem findById(Integer id);

    public void edit(CheckItem checkItem);

    public void delete(Integer id);

    public List<CheckItem> findAll();
}
