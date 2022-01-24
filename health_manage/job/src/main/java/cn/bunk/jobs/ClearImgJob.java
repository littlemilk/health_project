package cn.bunk.jobs;

import cn.bunk.constant.RedisConstant;
import cn.bunk.util.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.Set;

public class ClearImgJob {

    @Autowired
    private JedisPool jedisPool;

    public void clearImg(){
        Set<String> delImg = jedisPool.getResource().sdiff(RedisConstant.SETMEAL_PIC_RESOURCES, RedisConstant.SETMEAL_PIC_DB_RESOURCES);
        if(delImg != null){
            for(String img : delImg){
                QiniuUtil.deleteFileFromQiniu(img);
                jedisPool.getResource().srem(RedisConstant.SETMEAL_PIC_RESOURCES, img);
            }
        }
    }

}
