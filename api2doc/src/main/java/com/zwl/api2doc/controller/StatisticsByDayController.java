package com.zwl.api2doc.controller;

import com.terran4j.commons.api2doc.annotations.Api2Doc;
import com.terran4j.commons.api2doc.annotations.ApiComment;
import com.zwl.mall.entity.StatisticsByDay;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 二师兄超级帅
 * @since 2019-01-26
 */
@Api2Doc(name = "111", order = 1)
@RestController
@RequestMapping("/api/statisticsByDay")
public class StatisticsByDayController {
    @Api2Doc(order = 1)
    @ApiComment("分页查询数据")
    @RequestMapping(name = "分页查询数据", value = "/getPage", method = {RequestMethod.GET})
    public List<StatisticsByDay> getPage(@ApiComment(value = "pageNum") @RequestParam(required = true) Integer pageNum,
                                         @ApiComment(value = "pageSize") @RequestParam(required = true) Integer pageSize) {
        return null;
    }

    @Api2Doc(order = 2)
    @ApiComment("条件查询对象")
    @RequestMapping(name = "条件查询对象", value = "/getByParams", method = {RequestMethod.GET})
    public StatisticsByDay getByParams(@ApiComment(value = "statisticsByDay") StatisticsByDay statisticsByDay) {
        return null;
    }

    @Api2Doc(order = 3)
    @ApiComment("保存")
    @RequestMapping(name = "保存", value = "/save", method = {RequestMethod.GET})
    public StatisticsByDay save(@ApiComment(value = "statisticsByDay") StatisticsByDay statisticsByDay) {
        return null;
    }

    @Api2Doc(order = 4)
    @ApiComment("更新")
    @RequestMapping(name = "更新", value = "/update", method = {RequestMethod.GET})
    public StatisticsByDay update(@ApiComment(value = "statisticsByDay") StatisticsByDay statisticsByDay) {
        return null;
    }

    @Api2Doc(order = 5)
    @ApiComment("保存或更新")
    @RequestMapping(name = "保存或更新", value = "/saveOrUpdate", method = {RequestMethod.GET})
    public StatisticsByDay saveOrUpdate(@ApiComment(value = "statisticsByDay") StatisticsByDay statisticsByDay) {
        return null;
    }


}

