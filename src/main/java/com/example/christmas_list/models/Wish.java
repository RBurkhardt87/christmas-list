package com.example.christmas_list.models;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


import java.util.Objects;

//TODO: Setup the class first ...
//@Entity
public class Wish {



    //TODO: declare the fields
    //We add this above the id field
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int nextId;

    @NotNull(message = "You must call out your Wish!")
    private String name;

    @Size(max = 500, message = "Wow! That's a loooong description of your Wish. Wanna shorten it for me?!!!")
    private String description;

    @Size(max = 200, message = "The store/site you entered is too long!")
    private String store;


    //TODO: generate default and loaded constructor:
    //We need an empty/default constructor inside the Entity Class
    public Wish() {
    this.id = nextId;
    nextId++;
    }

    //We need a constructor that takes args for the fields
    public Wish(String name, String description, String store) {
        this();
        this.name = name;
        this.description = description;
        this.store = store;
    }


    //TODO: generate getters/setters
    //Leave out the id field because it will be set by the database (but we do want a getter for it)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public int getId() {
        return id;
    }

    //TODO: Generate equals and hashcode methods:
    //We are only checking if the id is the same. It is possible the other fields could match, but not be equal

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wish wish = (Wish) o;
        return id == wish.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


}
