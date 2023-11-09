package org.example;

public class Entity {
    private int id;

    protected String name;


    public Entity(String name)
    {
        this.name = name;
    }
    public String getDescription()
    {
        return ("ID: " + id + "Name: " + name);
    }
    public String getCSV()
    {
        return (id+","+name);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() { // Gjorde denna gettern för att få tillgång i en If sats som kollar om denna crop redan finns
        return name;
    }
}
