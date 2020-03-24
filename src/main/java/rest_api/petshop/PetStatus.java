package rest_api.petshop;

public enum PetStatus {
    AVAILABLE("available"), PENDING("pending"), SOLD("sold");
    private String title;

    PetStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
