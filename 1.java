package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.bo.AdminConfigBO;
import com.cskaoyan.bean.vo.AdminConfigVO;
import com.cskaoyan.handler.AdminRequestBodyException;
import com.cskaoyan.handler.LogAnnotation;
import com.cskaoyan.handler.ValidationUtils;
import com.cskaoyan.service.AdminConfigService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @Author: 于艳帆
 * @createTime: 2022年06月26日 10:00:06
 * @version: v0.1
 * @Description: 处理配置管理业务
 */
@RestController
@RequestMapping("admin/config")
public class AdminConfigController {

    @Autowired
    AdminConfigService adminConfigService;




    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 10:07:25
     * @description: 根据AdminConfigVO的成员变量，拿到market_system中key_name的对应字段，并封装
     * @param:
     * @return: com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.vo.AdminConfigVO>
     */
    @GetMapping({"mall", "express", "order", "wx"})
    @RequiresPermissions(value = {"admin:config:mall","admin:config:express","admin:config:order","admin:config:wx","*"},logical = Logical.OR)
    public BaseRespVo<AdminConfigVO> getInfo() {
        BaseRespVo<AdminConfigVO> resp = new BaseRespVo<>();

        AdminConfigVO adminConfigVO = null;
        try {
            adminConfigVO = adminConfigService.getInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.setData(adminConfigVO);
        return resp;
    }

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 15:17:24
     * @description: 根据AdminConfigBO的成员变量，更新system表
     * @param: adminConfigBO - [AdminConfigBO]
     * @return: com.cskaoyan.bean.BaseRespVo<java.lang.String>
     */

    @PostMapping({"mall", "express", "order", "wx"})
    @LogAnnotation(value = "update admin config")
    @RequiresPermissions(value = {"admin:config:mall","admin:config:express","admin:config:order","admin:config:wx","*"},logical = Logical.OR)
    public BaseRespVo<String> updateInfo(@RequestBody@Validated AdminConfigBO adminConfigBO, BindingResult bindingResult) throws AdminRequestBodyException {

        try {
            adminConfigService.updateInfo(adminConfigBO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return BaseRespVo.ok();
    }

}
