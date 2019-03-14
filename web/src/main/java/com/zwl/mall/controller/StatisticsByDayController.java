package com.zwl.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwl.mall.api.IStatisticsByDayService;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.mall.dao.model.StatisticsByDay;
import com.zwl.mall.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 二师兄超级帅
 * @Title:
 * @ProjectName mall
 * @Description: TODO
 * @date 2019/1/2614:05
 */

@RestController
@RequestMapping("/api/statisticsByDay")
public class StatisticsByDayController {
    @Autowired
    private IStatisticsByDayService statisticsByDayService;

    /**
     * 分页查询数据
     *
     * @param statisticsByDay 查询条件
     * @return
     */
    @GetMapping("/getPage")
    public Result getPage(StatisticsByDay statisticsByDay, int pageNum, int pageSize) throws Exception {
        return ResultUtil.ok(new StatisticsByDay().selectPage(new Page<>(pageNum, pageSize),
                new QueryWrapper<StatisticsByDay>().allEq(MapUtil.objectToUnderlineMap(statisticsByDay), false)));
    }

    /**
     * 根据条件获取对象
     *
     * @param statisticsByDay
     * @return
     */
    @GetMapping("/getByParams")
    public Result getByParams(StatisticsByDay statisticsByDay) throws Exception {
        return ResultUtil.ok(new StatisticsByDay().selectOne(new QueryWrapper<StatisticsByDay>()
                .allEq(MapUtil.objectToUnderlineMap(statisticsByDay), false)));
    }

    /**
     * 根据id获取对象
     *
     * @param id
     * @return
     */
    @GetMapping("/getById")
    public Result getById(Long id) {
        return ResultUtil.ok(statisticsByDayService.test(id));
    }

    /**
     * 保存
     *
     * @param statisticsByDay 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/save")
    public Result save(StatisticsByDay statisticsByDay) {
        return ResultUtil.ok(statisticsByDay.insert());
    }

    /**
     * 更新
     *
     * @param statisticsByDay 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/update")
    public Result update(StatisticsByDay statisticsByDay) throws Exception {
        return ResultUtil.ok(statisticsByDay.updateById());
    }

    /**
     * 保存或更新
     *
     * @param statisticsByDay 传递的实体
     * @return 0 失败  1 成功
     */
    @GetMapping("/saveOrUpdate")
    public Result saveOrUpdate(StatisticsByDay statisticsByDay) throws Exception {
        return ResultUtil.ok(statisticsByDay.insertOrUpdate());
    }


}
