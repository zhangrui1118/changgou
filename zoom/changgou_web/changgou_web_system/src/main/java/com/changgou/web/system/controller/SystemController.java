package com.changgou.web.system.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.order.pojo.Order;
import com.changgou.web.system.service.SystemService;
import com.github.pagehelper.Page;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 江鑫
 */
@Controller
@RequestMapping("/system")
public class SystemController {
@Autowired
private SystemService systemService;
@Value("${urlExample}")
private String urlExcel;
    @GetMapping(value = "/orderlist" )
    public String orderlist (){
        return "theorderlist";
    }
    /***
     * 根据ID删除品牌数据
     * @param orderList
     * @return
     */
    @PostMapping("/deleteOrder")
    public Result deleteOrder(@RequestBody List<Map> orderList){
        for (Map map:orderList){
            String id = (String) map.get("id");
            systemService.delectOrder(id);
        }
       return new Result(true,StatusCode.OK,"删除成功");
    }
    /***
     * 首页分页实现
     * @return
     */
    @GetMapping("/search")
    @ResponseBody
    public Result findList (){
        Map<String, Object> map=new HashMap();
        Page<Order> page = systemService.findPage(1, 10);
        List<Order> parameters = parameters(page.getResult());
        map.put("result", parameters);
        map.put("total",page.getTotal());
        map.put("pageSize",page.getPageSize());
        map.put("currentPage","1");

        return new Result(true, StatusCode.OK,"分页查询成功",map);
    }
    /***
     * 首页分页条件查询
     * 订单发货
     * @return
     */
    @PostMapping("/condition")
    @ResponseBody
    public Result conditionOrder(@RequestBody Map param,@RequestParam Integer pageSize,@RequestParam Integer currentPage){
        Map<String, Object> map=new HashMap();
        Page<Order> pageList =systemService.findList(param,pageSize,currentPage);
        List<Order> parameters = parameters(pageList.getResult());
        map.put("result", parameters);
        map.put("total",pageList.getTotal());
        map.put("pageSize",pageList.getPageSize());
        map.put("currentPage",currentPage);
        return new Result(true,StatusCode.OK,"查询成功",map);
    }

    /***
     * 订单发货
     * @return
     */
//    @GetMapping("/{id}")
//    public Result<Order> findById(@PathVariable("id") String id){
//      /*  return orderFeign.findById(id);*/
//    }
    @GetMapping("/deliver")
    @ResponseBody
    public Result deliver(@RequestParam Integer id){
        systemService.batchSend(id);
        return new Result(true,StatusCode.OK,"发货成功");
    }

    /***
     * 导出Excel表格
     */
    @PostMapping("/POIExcel")
    @ResponseBody
    public Result POIExcel(@RequestBody List<Order> orders) throws IOException {
        XSSFWorkbook excel =new XSSFWorkbook();
        XSSFSheet sheet = excel.createSheet("畅购商城");
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("订单编号");
        row.createCell(1).setCellValue("用户账号");
        row.createCell(2).setCellValue("收货人");
        row.createCell(3).setCellValue("手机号码");
        row.createCell(4).setCellValue("订单金额");
        row.createCell(5).setCellValue("支付方式");
        row.createCell(6).setCellValue("订单来源");
        row.createCell(7).setCellValue("订单状态");
        int i=1;
        for (Order order:orders){
            XSSFRow row1 = sheet.createRow(i++);
            row1.createCell(0).setCellValue((order.getId()!=null)?order.getId():"空");
            row1.createCell(1).setCellValue((order.getUsername()!=null)?order.getUsername():"空");
            row1.createCell(2).setCellValue((order.getReceiverContact()!=null)?order.getReceiverContact():"空");
            row1.createCell(3).setCellValue((order.getReceiverMobile()!=null)?order.getReceiverMobile():"空");
            row1.createCell(4).setCellValue((order.getPayMoney()!=null)? order.getPayMoney().toString() :"空");
            row1.createCell(5).setCellValue((order.getPayType()!=null)?order.getPayType():"空");
            row1.createCell(6).setCellValue((order.getSourceType()!=null)?order.getSourceType():"空");
            row1.createCell(7).setCellValue((order.getConsignStatus()!=null)?order.getConsignStatus():"空");
        }
        //创建一个输出流,通过输出流将内存中文件写入磁盘
        FileOutputStream out =new FileOutputStream(new File(urlExcel));
        excel.write(out);
        out.flush();
        excel.close();
        return new Result(true,StatusCode.OK,"导出成功"+urlExcel);
    }
    /***
     * 字符转换
     */
    public List<Order>  parameters(List<Order> list){
        List<Order> ok=new ArrayList<>();
        for (Order order:list){
            String payType = order.getPayType();
            String sourceType = order.getSourceType();
            String consignStatus = order.getConsignStatus();
            //修改发货状态
            if ("0".equals(payType)){
                order.setPayType("微信支付");
            }else if ("1".equals(payType)){
                order.setPayType("货到付款");
            }else if ("2".equals(payType)){
                order.setPayType("支付宝支付");
            }
            //订单来源
            if ("1".equals(sourceType)){
                order.setSourceType("PC");
            }else if ("2".equals(sourceType)){
                order.setSourceType("app");
            }else if ("3".equals(sourceType)){
                order.setSourceType("微信公众号");
            }
            if ("4".equals(sourceType)){
                order.setSourceType("微信小程序");
            }
            if ("5".equals(sourceType)){
                order.setSourceType("H5手机页面");
            }
            //修改支付方式
            if ("0".equals(consignStatus)){
                order.setConsignStatus("未发货");
            }else if ("1".equals(consignStatus)){
                order.setConsignStatus("已发货");
            }else if ("2".equals(consignStatus)){
                order.setConsignStatus("已收货");
            }
            ok.add(order);
        }
        return ok;
    }

}
