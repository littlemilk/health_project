package cn.bunk.service.impl;

import cn.bunk.dao.CheckGroupDao;
import cn.bunk.entity.PageResult;
import cn.bunk.entity.QueryPageBean;
import cn.bunk.pojo.CheckGroup;
import cn.bunk.service.CheckGroupService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page = checkGroupDao.findPage(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        checkGroupDao.add(checkGroup);
        this.setCheckGroupAndCheckItem(checkGroup.getId(), checkitemIds);
    }

    @Override
    public Integer[] findCheckItemIdByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdByCheckGroupId(id);
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkItemIds) {
        checkGroupDao.edit(checkGroup);
        checkGroupDao.deleteCheckItemByCheckGroupId(checkGroup.getId());
        this.setCheckGroupAndCheckItem(checkGroup.getId(), checkItemIds);
    }

    @Override
    public void delete(Integer id) {
        checkGroupDao.deleteCheckItemByCheckGroupId(id);
        checkGroupDao.delete(id);
    }

    @Override
    public List<CheckGroup> findAll() {
        return checkGroupDao.findAll();
    }

    public void setCheckGroupAndCheckItem(Integer checkGroupId, Integer[] checkitemIds){
        Map<String, Integer> map = new HashMap<>();
        for(int checkitemId : checkitemIds){
            map.put("checkgroupId", checkGroupId);
            map.put("checkitemId", checkitemId);
            checkGroupDao.setCheckGroupAndCheckItem(map);
        }
    }
}
