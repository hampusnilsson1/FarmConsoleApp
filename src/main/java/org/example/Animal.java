package org.example;

import java.util.ArrayList;

public class Animal extends Entity{
    static int nextCropID = 1;
    private final String species;
    private final ArrayList<String> acceptableCropTypes;



    public Animal(String name,String species,ArrayList<String> acceptableCropTypes) {
        super(name);
        setId(nextCropID);
        nextCropID++;
        this.species = species;
        this.acceptableCropTypes = acceptableCropTypes;
    }
    public Animal(int id,String name,String species,ArrayList<String> acceptableCropTypes) {
        super(name);
        setId(id);
        this.species = species;
        this.acceptableCropTypes = acceptableCropTypes;
    }
    @Override
    public String getCSV()
    {
        return (getId()+","+name+","+species+","+acceptableCropTypes);
    }

    @Override
    public String getDescription() {
        return ("ID: "+ getId() + ". Name: " + name + ", Species: " + species + ", Edible crop types: "+acceptableCropTypes);
    }

    public void feed(Crop crop){
        boolean eatableCrop = false;
        for (String croptypeItEats:acceptableCropTypes) {
            if(croptypeItEats.equals(crop.getCropType()))
            {
                System.out.println("How many "+crop.getName()+"s would you like to feed "+ name+"?");
                int amountInput = InputUtils.getIntInput();
                boolean feedHappened = crop.takeCrop(amountInput);

                if(feedHappened && amountInput > 1) { // Om ätit flera (Plural)
                    System.out.println(name + " was successfully fed " + amountInput +" "+ crop.getName() + "s");
                }
                else if (feedHappened) { // Om ätit en (Singular)
                    System.out.println(name + " was successfully fed " + amountInput +" "+ crop.getName());
                }
                else {
                    System.out.println("Well i guess you're gonna starve the "+species+", Or just get to work and start farming..");
                }
                eatableCrop = true;
                break;
            }
        }
        if(!eatableCrop)
            System.out.println("The "+name+" doesn't eat that >:C");
    }
}
