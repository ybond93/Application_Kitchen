package miun.dt170g.application_kitchen.entities;

public class Orders {

    private int orderId;

    private Boolean statusOrder;

    private Boolean statusMains;

    private Boolean statusStart;

    public Orders(){};
    public Orders(int orderId, Boolean statusOrder, Boolean statusMains, Boolean statusStart) {
        this.orderId = orderId;
        this.statusOrder = statusOrder;
        this.statusMains = statusMains;
        this.statusStart = statusStart;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
}

