/*
Copyright [2020] [https://www.xiaonuo.vip]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Snowy采用APACHE LICENSE 2.0开源协议，您在使用过程中，需要注意以下几点：

1.请不要删除和修改根目录下的LICENSE文件。
2.请不要删除和修改Snowy源码头部的版权声明。
3.请保留源码和相关描述文件的项目出处，作者声明等。
4.分发源码时候，请注明软件出处 https://gitee.com/xiaonuobase/snowy-layui
5.在修改包名，模块名称，项目代码等时，请注明软件出处 https://gitee.com/xiaonuobase/snowy-layui
6.若您的项目无法满足以上几点，可申请商业授权，获取Snowy商业授权许可，请在官网购买授权，地址为 https://www.xiaonuo.vip
 */
package vip.xiaonuo.sys.modular.consts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xiaonuo.core.annotion.BusinessLog;
import vip.xiaonuo.core.annotion.Permission;
import vip.xiaonuo.core.enums.LogAnnotionOpTypeEnum;
import vip.xiaonuo.core.pojo.page.PageResult;
import vip.xiaonuo.core.pojo.response.ResponseData;
import vip.xiaonuo.core.pojo.response.SuccessResponseData;
import vip.xiaonuo.sys.modular.consts.entity.SysConfig;
import vip.xiaonuo.sys.modular.consts.param.SysConfigParam;
import vip.xiaonuo.sys.modular.consts.service.SysConfigService;

import javax.annotation.Resource;
import java.util.List;


/**
 * 参数配置控制器
 *
 * @author yubaoshan
 * @date 2020/4/13 22:46
 */
@Controller
public class SysConfigController {

    @Resource
    private SysConfigService sysConfigService;

    /**
     * 系统配置页面
     *
     * @author xuyuxiang
     * @date 2020/11/17 16:40
     */
    @Permission
    @GetMapping("/sysConfig/index")
    public String index() {
        return "system/sysConfig/index.html";
    }

    /**
     * 系统配置表单页面
     *
     * @author xuyuxiang
     * @date 2020/11/17 16:40
     */
    @GetMapping("/sysConfig/form")
    public String form() {
        return "system/sysConfig/form.html";
    }

    /**
     * 分页查询配置列表
     *
     * @author xuyuxiang
     * @date 2020/4/14 11:10
     */
    @Permission
    @ResponseBody
    @GetMapping("/sysConfig/page")
    @BusinessLog(title = "系统参数配置_查询", opType = LogAnnotionOpTypeEnum.QUERY)
    public PageResult<SysConfig> page(SysConfigParam sysConfigParam) {
        return sysConfigService.page(sysConfigParam);
    }

    /**
     * 系统参数配置列表
     *
     * @author xuyuxiang
     * @date 2020/4/14 11:10
     */
    @Permission
    @ResponseBody
    @GetMapping("/sysConfig/list")
    @BusinessLog(title = "系统参数配置_列表", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData list(SysConfigParam sysConfigParam) {
        return new SuccessResponseData(sysConfigService.list(sysConfigParam));
    }

    /**
     * 查看系统参数配置
     *
     * @author xuyuxiang
     * @date 2020/4/14 11:12
     */
    @Permission
    @ResponseBody
    @GetMapping("/sysConfig/detail")
    @BusinessLog(title = "系统参数配置_详情", opType = LogAnnotionOpTypeEnum.DETAIL)
    public ResponseData detail(@Validated(SysConfigParam.detail.class) SysConfigParam sysConfigParam) {
        return new SuccessResponseData(sysConfigService.detail(sysConfigParam));
    }

    /**
     * 添加系统参数配置
     *
     * @author xuyuxiang
     * @date 2020/4/14 11:11
     */
    @Permission
    @ResponseBody
    @PostMapping("/sysConfig/add")
    @BusinessLog(title = "系统参数配置_增加", opType = LogAnnotionOpTypeEnum.ADD)
    public ResponseData add(@RequestBody @Validated(SysConfigParam.add.class) SysConfigParam sysConfigParam) {
        sysConfigService.add(sysConfigParam);
        return new SuccessResponseData();
    }

    /**
     * 删除系统参数配置
     *
     * @author xuyuxiang
     * @date 2020/4/14 11:11
     */
    @Permission
    @ResponseBody
    @PostMapping("/sysConfig/delete")
    @BusinessLog(title = "系统参数配置_删除", opType = LogAnnotionOpTypeEnum.DELETE)
    public ResponseData delete(@RequestBody @Validated(SysConfigParam.delete.class) List<SysConfigParam> sysConfigParamList) {
        sysConfigService.delete(sysConfigParamList);
        return new SuccessResponseData();
    }

    /**
     * 编辑系统参数配置
     *
     * @author xuyuxiang
     * @date 2020/4/14 11:11
     */
    @Permission
    @ResponseBody
    @PostMapping("/sysConfig/edit")
    @BusinessLog(title = "系统参数配置_编辑", opType = LogAnnotionOpTypeEnum.EDIT)
    public ResponseData edit(@RequestBody @Validated(SysConfigParam.edit.class) SysConfigParam sysConfigParam) {
        sysConfigService.edit(sysConfigParam);
        return new SuccessResponseData();
    }

}


