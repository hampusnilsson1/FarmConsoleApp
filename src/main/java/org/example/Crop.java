package org.example;

public class Crop extends Entity{
    static int nextCropID = 1;
    private final String cropType;
    private int quantity = 0;

    public Crop(String name,String cropType) { // add a new one with one quantity
        super(name);
        this.cropType = cropType;
        setId(nextCropID);
        addCrop(1);
        nextCropID++;
    }
    public Crop(String name,String cropType,int quantity) { // Add with quantity first start with no map
        super(name);
        this.cropType = cropType;
        this.quantity = quantity;
        setId(nextCropID);
        nextCropID++;
    }
    public Crop(int id,String name,String cropType,int quantity) { // Add with quantity at load from file
        super(name);
        this.setId(id);
        this.cropType = cropType;
        this.quantity = quantity;
    }
    @Override
    public String getDescription(){
        return (getId() + ". Name: " + name + ", Type: " + cropType + ", Amount: "+quantity);
    }
    @Override
    public String getCSV()
    {
        return (getId()+","+name+","+cropType+","+quantity);
    }

    public void addCrop(int amount)
    {
        quantity= quantity+amount;
    }

    public boolean takeCrop(int amount){
        if(amount <= quantity && amount > 0){
            quantity = quantity-amount;
            return true;
        }
        else{
            System.out.println("Not enough "+name+"s");
            return false;
        }
    }

    public String getCropType() {
        return cropType;
    }
}
