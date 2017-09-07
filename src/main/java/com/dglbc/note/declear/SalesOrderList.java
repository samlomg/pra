package com.dglbc.note.declear;

import com.dglbc.note.entity.SalesOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LbcLT on 2017/5/21.
 */

@Setter
@Getter
public class SalesOrderList {
    List<SalesOrder> salesOrders =new ArrayList<SalesOrder>();

    public SalesOrderList(List<SalesOrder> salesOrders) {
        this.salesOrders = salesOrders;
    }

    public SalesOrderList() {
    }


}
