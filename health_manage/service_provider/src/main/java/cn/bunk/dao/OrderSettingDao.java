package cn.bunk.dao;

import cn.bunk.pojo.OrderSetting;

import java.util.Date;
import java.util.List;

public interface OrderSettingDao {
    void add(OrderSetting orderSetting);
    long findCountByOrderDate(Date orderDate);
    void editNumberByOrderDate(OrderSetting orderSetting);
}
