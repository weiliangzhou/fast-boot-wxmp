package com.zwl.mall.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zwl.mall.api.ISysRoleService;
import com.zwl.common.base.Result;
import com.zwl.common.base.ResultUtil;
import com.zwl.mall.dao.model.SysRole;
import com.zwl.mall.utils.MapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 二师兄超级帅
 * @since 2019-03-14
 */

@RestController
@RequestMapping("/api/sysRole")
public class SysRoleController {


@Autowired
public ISysRoleService iSysRoleService;

/**
 * 分页查询数据
 *
 * @param sysRole 查询条件
 * @return
 */
@GetMapping("/getPage")
public Result getPage(SysRole sysRole,int pageNum,int pageSize)throws Exception{
        return ResultUtil.ok(new SysRole().selectPage(new Page<>(pageNum,pageSize),
        new QueryWrapper<SysRole>().allEq(MapUtil.objectToUnderlineMap(sysRole),false)));}

/**
 * 根据id获取对象
 *
 * @param sysRole 传递的实体
 * @return
 */
@GetMapping("/getByParams")
public Result getByParams(SysRole sysRole)throws Exception{
        return ResultUtil.ok(new SysRole().selectOne(new QueryWrapper<SysRole>().allEq(MapUtil.objectToUnderlineMap(sysRole),false)));
        }






/**
* 保存
*
* @param sysRole 传递的实体
* @return 0 失败  1 成功
*/
@GetMapping("/save")
public Result save(SysRole sysRole)throws Exception{
        return ResultUtil.ok(sysRole.insert());
        }

/**
 * 更新
 *
 * @param sysRole 传递的实体
 * @return 0 失败  1 成功
 */
@GetMapping("/update")
public Result update(SysRole sysRole)throws Exception{
        return ResultUtil.ok(sysRole.updateById());
        }

/**
 * 保存或更新
 *
 * @param sysRole 传递的实体
 * @return 0 失败  1 成功
 */
@GetMapping("/saveOrUpdate")
public Result saveOrUpdate(SysRole sysRole)throws Exception{
        return ResultUtil.ok(sysRole.insertOrUpdate());
        }
          }
