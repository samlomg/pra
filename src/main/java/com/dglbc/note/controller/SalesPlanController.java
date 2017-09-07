package com.dglbc.note.controller;

import com.dglbc.conf.security.UserDetailsCustom;
import com.dglbc.login.repository.UserRepository;
import com.dglbc.note.declear.SalesOrderList;
import com.dglbc.note.entity.SalesPlan;
import com.dglbc.note.service.SalesPlanService;
import com.dglbc.untils.TimeUntil;
import com.dglbc.untils.declear.FlagCom;
import com.dglbc.untils.declear.ReturnResult;
import com.github.wenhao.jpa.Specifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by LbcLT on 2017/5/17.
 */
@Controller
public class SalesPlanController {

    @Autowired
    SalesPlanService salesPlanService;

    @Autowired
    UserRepository userRepository;

    //查询所有单据
    @RequestMapping(value = "listSalesPlan", method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @PreAuthorize("hasAuthority('access_all')")
    public Page listSalesInvoices(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10") Integer size,
                                  @RequestParam(value = "beginDate") String beginDate,
                                  @RequestParam(value = "endDate") String endDate,
                                  @RequestParam(value = "flag") Integer flag,
                                  @RequestParam(value = "salesPlanId") Long salesPlanId
    ) throws ParseException {
        Sort sort = new Sort(Sort.Direction.DESC, "salesInvoiceId");
        Pageable pageable = new PageRequest(page, size, sort);

        //条件条件开始

        //配货时间处理
        Date sdate;
        Date ddate;
        System.out.println("endDate"+endDate);
        if (StringUtils.isEmpty(endDate)){
            sdate=new Date();
        }else {
            sdate= TimeUntil.stringToDate(endDate,"yyyy-MM-dd HH:mm:ss");
        }

        if (StringUtils.isEmpty(beginDate)){
            ddate= TimeUntil.timeOperationDate(0,0,-60);
        }else {
            ddate= TimeUntil.stringToDate(beginDate,"yyyy-MM-dd HH:mm:ss");
        }
        System.err.println("sdate:"+sdate);
        System.err.println("ddate:"+ddate);
        Specifications.Builder<SalesPlan> SalesInvoiceBuilder=Specifications.builder();
        SalesInvoiceBuilder.between("createTime", new Range<>(ddate,sdate));
        if (null != salesPlanId) SalesInvoiceBuilder.eq("salesPlanId",Long.valueOf(salesPlanId));
        if (null != flag) SalesInvoiceBuilder.eq("flag",flag);

        Specification<SalesPlan> specification = SalesInvoiceBuilder.build();
        Page<SalesPlan> salesPlan= salesPlanService.findAll(specification,pageable);
//        System.err.println("Page:"+salesPlan.getContent().toString());
        return salesPlan;
    }

    //查询所有单据
    @RequestMapping(value = "saveSalesPlan", method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ReturnResult saveSalesInvoice(SalesPlan salesPlan){
        ReturnResult returnResult = null;
        System.err.println("flag"+ salesPlan.getFlag());
        System.err.println("salesInvoiceId"+ salesPlan.getSalesPlanId());
        String userName=null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsCustom) {
            userName = ((UserDetailsCustom) principal).getName();
        }
        String status=null;
        List<SalesPlan> salesPlans = new ArrayList<>();
        //如果返回salesInvoice里面只有flag和salesInvoiceId证明是收款的
        if (null != salesPlan.getSalesPlanId() && null != salesPlan.getFlag()   && FlagCom.statusFlag.contains(salesPlan.getFlag())){
            Integer flag= salesPlan.getFlag();
            salesPlan = salesPlanService.findBySalesPlanId(salesPlan.getSalesPlanId());
            if (null != salesPlan){
                salesPlan.setFlag(flag);
//                salesPlan.setReceiveTime(new Date());
//                salesPlan.setReceiveUser(userName);
                salesPlanService.save(salesPlan);
                status = "success";
            }else {
                status = "error,salesInvoiceId is null";
            }
        }else if (null == salesPlan.getSalesPlanId() || salesPlan.getSalesPlanId() == 0){
            salesPlan.setCreateTime(new Date());
            salesPlan.setCreateUser(userName);
            salesPlanService.save(salesPlan);
            status = "success";
        }
        salesPlans.add(salesPlan);
        returnResult=new ReturnResult<String,List>(status, salesPlans);

        return returnResult;
    }


    //保存单据
    @RequestMapping(value = "saveSalesPlans", method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ReturnResult saveSalesInvoices( SalesOrderList salesOrderList){
        ReturnResult returnResult = null;
        System.err.println("LIST:"+ salesOrderList.toString());

        String userName=null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsCustom) {
            userName = ((UserDetailsCustom) principal).getName();
        }

        //返回的list context
        List<SalesPlan> salesPlans = new ArrayList<>();


        //保存，这个只用作批量保存
//        for (SalesPlan salesPlan:salesOrderList.getSalesInvoices()){
//            salesPlan.setCreateTime(new Date());
//            salesPlan.setCreateUser(userName);
//            salesPlanService.save(salesPlan);
//            salesPlans.add(salesPlan);
//        }
        if (salesPlans.size() >0){
            returnResult = new ReturnResult<String,List>("success", salesPlans);
        }else {
            throw new NullPointerException("请输入记录！");
        }
        return returnResult;
    }

}
