package cn.bunk.service.impl;

import cn.bunk.dao.OrderSettingDao;
import cn.bunk.pojo.OrderSetting;
import cn.bunk.service.OrderSettingService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> list) {
        if(null != list && list.size() > 0){
            for (OrderSetting orderSetting : list) {
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if(count > 0){
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                } else {
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }
}
