package miun.dt170g.application_kitchen.entities;

import java.util.List;

public class KitchenView {

    private int orderId;
    private int tableNum;
    private Boolean statusOrder;
    private Boolean statusMains;
    private Boolean statusStart;

    List<OrderDetails> orderDetails;

    public static class OrderDetails {

        private int menuItemId;
        private String name;
        private Integer amount;
        private String category;

        public OrderDetails(){};

        public OrderDetails(int menuItemId, String name, Integer amount, String category) {
            this.menuItemId = menuItemId;
            this.name = name;
            this.amount = amount;
            this.category = category;
        }

        public int getMenuItemId() {
            return menuItemId;
        }

        public void setMenuItemId(int menuItemId) {
            this.menuItemId = menuItemId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAmount() {
            return amount;
        }

        public void setAmount(Integer amount) {
            this.amount = amount;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }
    public KitchenView(){};

    public KitchenView(int orderId, int tableNum, Boolean statusOrder, Boolean statusMains, Boolean statusStart, List<OrderDetails> orderDetails) {
        this.orderId = orderId;
        this.tableNum = tableNum;
        this.statusOrder = statusOrder;
        this.statusMains = statusMains;
        this.statusStart = statusStart;
        this.orderDetails = orderDetails;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTableNum() {
        return tableNum;
    }

    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }

    public Boolean getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(Boolean statusOrder) {
        this.statusOrder = statusOrder;
    }

    public Boolean getStatusMains() {
        return statusMains;
    }

    public void setStatusMains(Boolean statusMains) {
        this.statusMains = statusMains;
    }

    public Boolean getStatusStart() {
        return statusStart;
    }

    public void setStatusStart(Boolean statusStart) {
        this.statusStart = statusStart;
    }

    public List<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
