package com.cmdc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 流程:
 * case1:
 * dao层需要实现的方法很少:查询一个用户
 * 查询这个用户极其所有的角色、权限,这次换个方式直接使用map给他查询出来
 * case2:
 * 给出项目的基础配置 一定要简单
 *
 * @author : wuwensheng
 * @date : 9:17 2020/7/1
 */
@SpringBootApplication
//扫描下面的接口生成代理实现类
@MapperScan("com.cmdc.domain.mapper")
public class ShiroApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiroApplication.class,args);
    }
}
