package com.zwl.mall.controller;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.zwl.common.utils.excel.EmptyUtils;
import com.zwl.common.utils.excel.ExcelUtil;
import com.zwl.common.utils.excel.model.BatchCreateOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 二师兄超级帅
 * @Title: AdminCreatOrder
 * @ProjectName mall-parent
 * @Description: TODO
 * @date 2019/4/315:18
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class AdminCreateOrderController {

    /**
     * @Author 二师兄
     * @Version 1.0
     * @Description 批量订单导入
     * @Param file
     * @Param httpServletResponse
     * @Return void
     * @CreateTime 2019/4/10 11:22
     * @修改人
     * @修改时间
     */
    @RequestMapping(value = "/pub/order/batch/import", method = RequestMethod.POST)
    public void importExcel(@RequestParam("file") MultipartFile file, HttpServletResponse httpServletResponse) throws IOException {
        ImportParams params = new ImportParams();
        params.setHeadRows(1);
        List<BatchCreateOrder> list = ExcelUtil.importExcel(
                file,
                1, 1,
                BatchCreateOrder.class);
        if (EmptyUtils.isNotNull(list)) {
            for (BatchCreateOrder batchCreateOrder : list) {
                batchCreateOrder.setRemark("成功");
            }

            log.info("测试");
            ExcelUtil.exportExcel(list, "订单数据", "订单数据", BatchCreateOrder.class, "导入模版", httpServletResponse);

        }
    }

    /**
     * @Author 二师兄
     * @Version 1.0
     * @Description 模版下载
     * @Param httpServletResponse
     * @Return void
     * @CreateTime 2019/4/10 11:21
     * @修改人
     * @修改时间
     */
    @RequestMapping(value = "/pub/order/batch/download", method = {RequestMethod.GET, RequestMethod.POST})
    public void downloadExcel(HttpServletResponse httpServletResponse) throws IOException {

        List<BatchCreateOrder> batchCreateOrders = new ArrayList<>();
        BatchCreateOrder batchCreateOrder = new BatchCreateOrder();
        batchCreateOrder.setPhone("13000000000");
        batchCreateOrder.setName("张三");
        batchCreateOrder.setGoodsName("小创商学院");
        batchCreateOrder.setGid(147L);
        batchCreateOrder.setProductQuantity(20);
        batchCreateOrder.setTotalFee(0);
        batchCreateOrders.add(batchCreateOrder);
        ExcelUtil.exportExcel(batchCreateOrders, "订单数据", "订单数据", BatchCreateOrder.class, "导入模版", httpServletResponse);
    }

}
