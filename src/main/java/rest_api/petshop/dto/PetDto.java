package rest_api.petshop.dto;

import rest_api.petshop.Category;
import rest_api.petshop.PetStatus;
import rest_api.petshop.Tags;

import java.util.Objects;

public class PetDto {
    long id;
    Category category;
    String name;
    String[] photoUrls;
    Tags[] tags;
    PetStatus status;

    public long getId() {
        return id;
    }

    public PetDto setId(long id) {
        this.id = id;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public PetDto setCategory(Category category) {
        this.category = category;
        return this;
    }

    public String getName() {
        return name;
    }

    public PetDto setName(String name) {
        this.name = name;
        return this;
    }

    public String[] getPhotoUrls() {
        return photoUrls;
    }

    public PetDto setPhotoUrls(String[] photoUrls) {
        this.photoUrls = photoUrls;
        return this;
    }

    public Tags[] getTags() {
        return tags;
    }

    public PetDto setTags(Tags[] tags) {
        this.tags = tags;
        return this;
    }

    public String getStatus() {
        return status.getTitle();
    }

    public PetDto setStatus(PetStatus status) {
        this.status = status;
        return this;
    }

    public PetDto setStatus(String status) {
        this.status = PetStatus.valueOf(status.toUpperCase());
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetDto petDto = (PetDto) o;
        return getId() == petDto.getId() &&
                Objects.equals(getCategory(), petDto.getCategory()) &&
                Objects.equals(getName(), petDto.getName()) &&
                Objects.equals(getStatus(), petDto.getStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
