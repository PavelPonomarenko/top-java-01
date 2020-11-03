package com.gmail.ponomarenko.model;






@MappedSuperclass
@Access(AccessType.FIELD)
public class NamedEntity extends BaseEntity {
    @NotEmpty
    @Column(name="name",nullable="false")
//    -----------------------------
    protected String name;

    public NamedEntity() {
    }

    public NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public NamedEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
