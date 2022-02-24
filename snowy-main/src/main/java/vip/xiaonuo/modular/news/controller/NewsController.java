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
package vip.xiaonuo.modular.news.controller;

import vip.xiaonuo.core.annotion.BusinessLog;
import vip.xiaonuo.core.annotion.Permission;
import vip.xiaonuo.core.enums.LogAnnotionOpTypeEnum;
import vip.xiaonuo.core.pojo.page.PageResult;
import vip.xiaonuo.core.pojo.response.ResponseData;
import vip.xiaonuo.core.pojo.response.SuccessResponseData;
import vip.xiaonuo.modular.news.entity.News;
import vip.xiaonuo.modular.news.param.NewsParam;
import vip.xiaonuo.modular.news.service.NewsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

/**
 * 新闻资讯控制器
 *
 * @author ChenZhen
 * @date 2022-02-24 17:32:36
 */
@Controller
public class NewsController {

    private final String PATH_PREFIX = "news/";

    @Resource
    private NewsService newsService;

    /**
     * 新闻资讯页面
     *
     * @author ChenZhen
     * @date 2022-02-24 17:32:36
     */
    @Permission
    @GetMapping("/news/index")
    public String index() {
        return PATH_PREFIX + "index.html";
    }

    /**
     * 新闻资讯表单页面
     *
     * @author ChenZhen
     * @date 2022-02-24 17:32:36
     */
    @GetMapping("/news/form")
    public String form() {
        return PATH_PREFIX + "form.html";
    }

    /**
     * 查询新闻资讯
     *
     * @author ChenZhen
     * @date 2022-02-24 17:32:36
     */
    @Permission
    @ResponseBody
    @GetMapping("/news/page")
    @BusinessLog(title = "新闻资讯_查询", opType = LogAnnotionOpTypeEnum.QUERY)
    public PageResult<News> page(NewsParam newsParam) {
        return newsService.page(newsParam);
    }

    /**
     * 添加新闻资讯
     *
     * @author ChenZhen
     * @date 2022-02-24 17:32:36
     */
    @Permission
    @ResponseBody
    @PostMapping("/news/add")
    @BusinessLog(title = "新闻资讯_增加", opType = LogAnnotionOpTypeEnum.ADD)
    public ResponseData add(@RequestBody @Validated(NewsParam.add.class) NewsParam newsParam) {
        newsService.add(newsParam);
        return new SuccessResponseData();
    }

    /**
     * 删除新闻资讯
     *
     * @author ChenZhen
     * @date 2022-02-24 17:32:36
     */
    @Permission
    @ResponseBody
    @PostMapping("/news/delete")
    @BusinessLog(title = "新闻资讯_删除", opType = LogAnnotionOpTypeEnum.DELETE)
    public ResponseData delete(@RequestBody @Validated(NewsParam.delete.class) List<NewsParam> newsParamList) {
        newsService.delete(newsParamList);
        return new SuccessResponseData();
    }

    /**
     * 编辑新闻资讯
     *
     * @author ChenZhen
     * @date 2022-02-24 17:32:36
     */
    @Permission
    @ResponseBody
    @PostMapping("/news/edit")
    @BusinessLog(title = "新闻资讯_编辑", opType = LogAnnotionOpTypeEnum.EDIT)
    public ResponseData edit(@RequestBody @Validated(NewsParam.edit.class) NewsParam newsParam) {
        newsService.edit(newsParam);
        return new SuccessResponseData();
    }

    /**
     * 查看新闻资讯
     *
     * @author ChenZhen
     * @date 2022-02-24 17:32:36
     */
    @Permission
    @ResponseBody
    @GetMapping("/news/detail")
    @BusinessLog(title = "新闻资讯_查看", opType = LogAnnotionOpTypeEnum.DETAIL)
    public ResponseData detail(@Validated(NewsParam.detail.class) NewsParam newsParam) {
        return new SuccessResponseData(newsService.detail(newsParam));
    }

    /**
     * 新闻资讯列表
     *
     * @author ChenZhen
     * @date 2022-02-24 17:32:36
     */
    @Permission
    @ResponseBody
    @GetMapping("/news/list")
    @BusinessLog(title = "新闻资讯_列表", opType = LogAnnotionOpTypeEnum.QUERY)
    public ResponseData list(NewsParam newsParam) {
        return new SuccessResponseData(newsService.list(newsParam));
    }

}
