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
package vip.xiaonuo.sys.modular.menu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.xiaonuo.sys.modular.role.service.SysRoleMenuService;
import vip.xiaonuo.sys.modular.user.service.SysUserRoleService;
import vip.xiaonuo.core.annotion.BusinessLog;
import vip.xiaonuo.core.annotion.Permission;
import vip.xiaonuo.core.context.login.LoginContextHolder;
import vip.xiaonuo.core.enums.LogAnnotionOpTypeEnum;
import vip.xiaonuo.core.pojo.node.LoginMenuTreeNode;
import vip.xiaonuo.core.pojo.page.PageResult;
import vip.xiaonuo.core.pojo.response.ResponseData;
import vip.xiaonuo.core.pojo.response.SuccessResponseData;
import vip.xiaonuo.sys.modular.menu.entity.SysMenu;
import vip.xiaonuo.sys.modular.menu.param.SysMenuParam;
import vip.xiaonuo.sys.modular.menu.service.SysMenuService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统菜单控制器
 *
 * @author xuyuxiang
 * @date 2020/3/20 18:54
 */
@Controller
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Resource
    private SysUserRoleService sysUserRoleService;

    /**
     * 系统菜单页面
     *
     * @author xuyuxiang
     * @date 2020/11/17 16:40
     */
    @Permission
    @GetMapping("/sysMenu/index")
    public String index() {
        return "system/sysMenu/index.html";
    }

    /**
     * 系统菜单表单页面
     *
     * @author xuyuxiang
     * @date 2020/11/17 16:40
     */
    @GetMapping("/sysMenu/form")
    public String form() {
        return "system/sysMenu/form.html";
    }

    /**
     * 系统菜单列表（树）
     *
     * @author xuyuxiang
     * @date 2020/3/20 21:23
     */
    @Permission
    @ResponseBody
    @GetMapping("/sysMenu/page")
    @BusinessLog(title = "系统菜单_列表", opType = LogAnnotionOpTypeEnum.QUERY)
    public PageResult<SysMenu> page() {
        return sysMenuService.page();
    }

    /**
     * 添加系统菜单
     *
     * @author xuyuxiang
     * @date 2020/3/27 8:57
     */
    @Permission
    @ResponseBody
    @PostMapping("/sysMenu/add")
    @BusinessLog(title = "系统菜单_增加", opType = LogAnnotionOpTypeEnum.ADD)
    public ResponseData add(@RequestBody @Validated(SysMenuParam.add.class) SysMenuParam sysMenuParam) {
        sysMenuService.add(sysMenuParam);
        return new SuccessResponseData();
    }

    /**
     * 删除系统菜单
     *
     * @author xuyuxiang
     * @date 2020/3/27 8:58
     */
    @Permission
    @ResponseBody
    @PostMapping("/sysMenu/delete")
    @BusinessLog(title = "系统菜单_删除", opType = LogAnnotionOpTypeEnum.DELETE)
    public ResponseData delete(@RequestBody @Validated(SysMenuParam.delete.class) List<SysMenuParam> sysMenuParamList) {
        sysMenuService.delete(sysMenuParamList);
        return new SuccessResponseData();
    }

    /**
     * 编辑系统菜单
     *
     * @author xuyuxiang
     * @date 2020/3/27 8:59
     */
    @Permission
    @ResponseBody
    @PostMapping("/sysMenu/edit")
    @BusinessLog(title = "系统菜单_编辑", opType = LogAnnotionOpTypeEnum.EDIT)
    public ResponseData edit(@RequestBody @Validated(SysMenuParam.edit.class) SysMenuParam sysMenuParam) {
        sysMenuService.edit(sysMenuParam);
        return new SuccessResponseData();
    }

    /**
     * 查看系统菜单
     *
     * @author xuyuxiang
     * @date 2020/3/27 9:01
     */
    @Permission
    @ResponseBody
    @PostMapping("/sysMenu/detail")
    @BusinessLog(title = "系统菜单_查看", opType = LogAnnotionOpTypeEnum.DETAIL)
    public ResponseData detail(@Validated(SysMenuParam.detail.class) SysMenuParam sysMenuParam) {
        return new SuccessResponseData(sysMenuService.detail(sysMenuParam));
    }

    /**
     * 获取系统菜单树，用于新增，编辑时选择上级节点
     *
     * @author xuyuxiang
     * @date 2020/3/27 15:55
     */
    @Permission
    @ResponseBody
    @GetMapping("/sysMenu/tree")
    @BusinessLog(title = "系统菜单_树", opType = LogAnnotionOpTypeEnum.TREE)
    public ResponseData tree(SysMenuParam sysMenuParam) {
        return new SuccessResponseData(sysMenuService.tree(sysMenuParam));
    }

    /**
     * 获取系统菜单树，用于给角色授权时选择
     *
     * @author xuyuxiang
     * @date 2020/4/5 15:00
     */
    @Permission
    @ResponseBody
    @GetMapping("/sysMenu/treeForGrant")
    @BusinessLog(title = "系统菜单_授权树", opType = LogAnnotionOpTypeEnum.TREE)
    public ResponseData treeForGrant(SysMenuParam sysMenuParam) {
        return new SuccessResponseData(sysMenuService.treeForGrant(sysMenuParam));
    }

    /**
     * 根据系统切换菜单
     *
     * @author xuyuxiang
     * @date 2020/4/19 15:50
     */
    @ResponseBody
    @PostMapping("/sysMenu/change")
    @BusinessLog(title = "系统菜单_切换", opType = LogAnnotionOpTypeEnum.TREE)
    public ResponseData change(@RequestBody @Validated(SysMenuParam.change.class) SysMenuParam sysMenuParam) {
        Long sysLoginUserId = LoginContextHolder.me().getSysLoginUserId();
        List<Long> userRoleIdList = sysUserRoleService.getUserRoleIdList(sysLoginUserId);
        List<Long> menuIdList = sysRoleMenuService.getRoleMenuIdList(userRoleIdList);
        //转换成登录菜单
        List<SysMenu> sysMenuList = sysMenuService.getLoginMenus(sysLoginUserId, sysMenuParam.getApplication(), menuIdList);
        List<LoginMenuTreeNode> menuTreeNodeList = sysMenuService.convertSysMenuToLoginMenu(sysMenuList);
        return new SuccessResponseData(menuTreeNodeList);
    }
}
