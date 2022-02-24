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
package vip.xiaonuo.modular.news.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.xiaonuo.core.consts.CommonConstant;
import vip.xiaonuo.core.enums.CommonStatusEnum;
import vip.xiaonuo.core.exception.ServiceException;
import vip.xiaonuo.core.factory.PageFactory;
import vip.xiaonuo.core.pojo.page.PageResult;
import vip.xiaonuo.modular.news.entity.News;
import vip.xiaonuo.modular.news.enums.NewsExceptionEnum;
import vip.xiaonuo.modular.news.mapper.NewsMapper;
import vip.xiaonuo.modular.news.param.NewsParam;
import vip.xiaonuo.modular.news.service.NewsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

/**
 * 新闻资讯service接口实现类
 *
 * @author ChenZhen
 * @date 2022-02-24 17:32:36
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Override
    public PageResult<News> page(NewsParam newsParam) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(newsParam)) {

            // 根据标题 查询
            if (ObjectUtil.isNotEmpty(newsParam.getTitle())) {
                queryWrapper.lambda().like(News::getTitle, newsParam.getTitle());
            }
            // 根据作者 查询
            if (ObjectUtil.isNotEmpty(newsParam.getAuthor())) {
                queryWrapper.lambda().like(News::getAuthor, newsParam.getAuthor());
            }
            // 根据状态（字典 0正常 1冻结 2删除） 查询
            if (ObjectUtil.isNotEmpty(newsParam.getStatus())) {
                queryWrapper.lambda().eq(News::getStatus, newsParam.getStatus());
            }
        }
        return new PageResult<>(this.page(PageFactory.defaultPage(), queryWrapper));
    }

    @Override
    public List<News> list(NewsParam newsParam) {
        return this.list();
    }

    @Override
    public void add(NewsParam newsParam) {
        News news = new News();
        BeanUtil.copyProperties(newsParam, news);
        this.save(news);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<NewsParam> newsParamList) {
        newsParamList.forEach(newsParam -> {
        News news = this.queryNews(newsParam);
            this.removeById(news.getId());
        });
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(NewsParam newsParam) {
        News news = this.queryNews(newsParam);
        BeanUtil.copyProperties(newsParam, news);
        this.updateById(news);
    }

    @Override
    public News detail(NewsParam newsParam) {
        return this.queryNews(newsParam);
    }

    /**
     * 获取新闻资讯
     *
     * @author ChenZhen
     * @date 2022-02-24 17:32:36
     */
    private News queryNews(NewsParam newsParam) {
        News news = this.getById(newsParam.getId());
        if (ObjectUtil.isNull(news)) {
            throw new ServiceException(NewsExceptionEnum.NOT_EXIST);
        }
        return news;
    }
}
