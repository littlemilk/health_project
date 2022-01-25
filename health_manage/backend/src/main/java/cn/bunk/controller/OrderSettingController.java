package cn.bunk.controller;

import cn.bunk.constant.MessageConstant;
import cn.bunk.entity.Result;
import cn.bunk.pojo.OrderSetting;
import cn.bunk.service.OrderSettingService;
import cn.bunk.util.POIUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile){
        try {
            List<String[]> strings = POIUtils.readExcel(excelFile);
            List<OrderSetting> orders = new ArrayList<OrderSetting>();
            for (String[] s : strings){
                String orderDate = s[0];
                String number = s[1];
                OrderSetting orderSetting = new OrderSetting(new Date(orderDate), Integer.parseInt(number));
                orders.add(orderSetting);
            }
            orderSettingService.add(orders);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }

}
