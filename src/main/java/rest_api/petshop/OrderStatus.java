package rest_api.petshop;

public enum OrderStatus {
    PLACED("placed"), APPROVED("approved"), DELIVERED("delivered");
    private String title;

    OrderStatus(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
}
