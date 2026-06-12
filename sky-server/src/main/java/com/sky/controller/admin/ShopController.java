package com.sky.controller.admin;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController("adminShopController")
@Api(tags = "营业状态相关接口...")
@RequestMapping("/admin/shop")
public class ShopController {

    private static final String Key = "SHOP_STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置店铺的营业状态
     */
    @PutMapping("/{status}")
    @ApiOperation("设置店铺的营业状态")
    public Result setStart(@PathVariable Integer status){
        log.info("设置店铺的营业状态：{}",status==1?"营业中":"打样中");
        redisTemplate.opsForValue().set(Key,status);
        return Result.success();
    }

    /**
     * 获取店铺营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取店铺营业状态...")
    public Result<Integer> getStatus(){
        Integer status =(Integer) redisTemplate.opsForValue().get(Key);
        log.info("获取店铺的营业状态：{}",status==1?"营业中":"打样中");
        return Result.success(status);
    }
}
