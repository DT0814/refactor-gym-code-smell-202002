package cc.xpbootcamp.code_smell_kit.$06_mutable_data;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Order {
    private String cName;
    private String addr;
    private List<LineItem> lineItemList;
    private double totalTax;
    private Date date;
    private static final double DISCOUNT = 0.98d;
    private static final int DISCOUNT_DAY = Calendar.WEDNESDAY;

    public Order(String cName, String addr, List<LineItem> lineItemList) {
        this.cName = cName;
        this.addr = addr;
        this.lineItemList = lineItemList;
        this.date = new Date();
    }

    public String getCustomerName() {
        return cName;
    }

    public String getCustomerAddress() {
        return addr;
    }

    public List<LineItem> getLineItems() {
        return lineItemList;
    }

    public Date getDate() {
        return date;
    }

    public double getTotalTax() {
        return totalTax;
    }

    public void totalTax() {
         this.totalTax = getLineItems().stream().mapToDouble(LineItem::salesTax).sum();
    }

    public double totalAmount() {
        return getLineItems().stream().mapToDouble(LineItem::total).sum();
    }

}
