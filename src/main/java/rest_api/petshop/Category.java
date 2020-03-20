package rest_api.petshop;

public class Category {
    int id;
    String name;

    public int getId() {
        return id;
    }

    public Category setId(int id) {
        this.id = id;
        return new Category();
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return new Category();
    }
}
