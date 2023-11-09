package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CropManager {

    private final ArrayList<Crop> crops = new ArrayList<>();

    private final String[] cropTypes = {"Vegetable","Seed","Fruit","Nut","Grain","Non-Food"};
    File cropsData = new File("Data/cropsData.txt");

    public CropManager(File dataFolder)
    {
        loadCrops(dataFolder);// If there are crops load them else make default crop load.
    }
    public CropManager() // Default
    {

    }
    public void cropMenu()
    {
        boolean menuActive = true;
        while (menuActive) {
            System.out.println("Select an action: ");
            System.out.println("1. View All Crops");
            System.out.println("2. Add Crop");
            System.out.println("3. Remove Crop");
            System.out.println("4. Return");
            int menuInput = InputUtils.getIntInput();
            switch (menuInput){
                case 1:
                    viewCrops();
                    break;
                case 2:
                    addCrop();
                    break;
                case 3:
                    removeCrop();
                    break;
                case 4:
                    menuActive = false;
                    break;
                default:
                    System.out.println("That's not an option!");
            }
        }
    }

    private void viewCrops() {
        System.out.println("These are all your crops: ");
        for (Crop crop: crops) {
            System.out.println(crop.getDescription());
        }
    }

    private void addCrop() {
        System.out.println("Type the Crop you would like to add.");
        String cropName = InputUtils.scanner.nextLine();
        for (Crop crop:crops)
        {
            if(cropName.equalsIgnoreCase(crop.getName())) // Denna getter använder jag för att kolla om redan finns, denna skulle egentligen vara protected men kändes som den behövdes för att göra på detta sättet jga gör.
            {
                crop.addCrop(1);
                System.out.println("Added another: "+cropName);
                return;
            }
        }
        System.out.println("What Crop type is it?");
        for (int i = 0; i < cropTypes.length;i++) // Lists all types of crop types
        {
            System.out.println((i+1) + ". " + cropTypes[i]);
        }
        boolean chooseCropType = true;
        while(chooseCropType) {// Makes sure you input one of the options and adds the crop
            int intInput = InputUtils.getIntInput()-1;
            for (int i = 0; i < cropTypes.length; i++)
            {
                if (i == intInput)
                {
                    // LOOK IF THERE EXISTS A CROP OF THAT NAME
                    crops.add(new Crop(cropName,cropTypes[i]));
                    System.out.println("Added: " + cropName);
                    chooseCropType = false;
                    break;
                }
            }
        }
    }

    private void removeCrop() {
        viewCrops();
        System.out.println("Which one would you like to remove? Type name");
        String cropName = InputUtils.scanner.nextLine();
        for (Crop crop: crops) {
            if(cropName.equalsIgnoreCase(crop.getName())) {
                System.out.println("Removed " + crop.getName());
                crops.remove(crop);
                break;
            }
        }
    }

    public void loadCrops(File dataFolder)
    {
        try {
            if (!dataFolder.exists()) {
                dataFolder.mkdir(); // "Ignored" För att det är en boolean men använder den som skapa mapp samma med andra
                cropsData.createNewFile();
            }else if (!cropsData.exists())
                cropsData.createNewFile();

            FileReader fr = new FileReader(cropsData);
            BufferedReader br = new BufferedReader(fr);

            String nextLine = br.readLine();
            if(nextLine == null)
            {
                System.out.println("No save was done before. Loading crops.");
                crops.add(new Crop("Corn", "Vegetable",2));
                crops.add(new Crop("Tomato", "Vegetable",10));
                crops.add(new Crop("Cucumber", "Vegetable",5));
                crops.add(new Crop("Hazelnut", "Nut",6));
                crops.add(new Crop("Banana", "Fruit",3));
                crops.add(new Crop("Wheat", "Grain",53));
            }
            while (nextLine != null){// Om förra raden inte var null.
                if(!nextLine.isEmpty()) // Om nextLine inte är tom.
                {
                    String[] element = nextLine.split(",");//Split string
                    int id = Integer.parseInt(element[0]);
                    String name = element[1];
                    String cropType = element[2];
                    int quantity = Integer.parseInt(element[3]);
                    Crop.nextCropID = id+1; // makes nextCrop the next id after last highest id saved.

                    crops.add(new Crop(id,name,cropType,quantity));//Makes crop
                }
                nextLine = br.readLine(); // Läser nästa rad innan kolla igenom i nästa loop
            }
            br.close();//Stänger cropData filen så den inte ligger o kör i bakgrunden.
        }catch (IOException ignored){

        }
    }

    public ArrayList<Crop> getCrops() {
        return crops;
    }

    public String[] getCropTypes() {
        return cropTypes;
    }
}
