package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AnimalManager {
    private final ArrayList<Animal> animals = new ArrayList<>();
    File animalData = new File("Data/animalData.txt");

    public AnimalManager(File dataFolder)
    {
        loadAnimals(dataFolder);
    }
    public void animalMenu(ArrayList<Crop> crops) {
        boolean menuActive = true;
        while (menuActive) {
            System.out.println("Select an action: ");
            System.out.println("1. View All Animals");
            System.out.println("2. Add Animal");
            System.out.println("3. Remove Animal");
            System.out.println("4. Feed Animals");
            System.out.println("5. Return");
            int menuInput = InputUtils.getIntInput();
            switch (menuInput){
                case 1:
                    viewAnimals();
                    break;
                case 2:
                    addAnimal();
                    break;
                case 3:
                    removeAnimal();
                    break;
                case 4:
                    feedAnimals(crops);
                    break;
                case 5:
                    menuActive = false;
                    break;
                default:
                    System.out.println("That's not an option!");
            }
        }
    }
    private void viewAnimals() {
        System.out.println("These are the Animals on the Farm:");
        for (Animal animal: animals) {
            System.out.println(animal.getDescription());
        }
    }
    private void addAnimal(){
        System.out.println("What species is the animal?");
        String speciesName = InputUtils.scanner.nextLine();
        System.out.println("What the "+speciesName+"´s name?");
        String animalName = InputUtils.scanner.nextLine();


        System.out.println("What crop types does "+animalName+" eat?1,2,3...");
        String[] cropTypes = new CropManager().getCropTypes();
        ArrayList<String> cropTypesToAdd = new ArrayList<>();
        boolean chooseCropType = true;
        while(chooseCropType) {
            for(int i = 0; i < cropTypes.length;i++)
            {
                System.out.println(i+1+". "+cropTypes[i]);
            }
            int input = InputUtils.getIntInput()-1;
            if(input < cropTypes.length)
                cropTypesToAdd.add(cropTypes[input]);
            System.out.println("Does he eat any other type of food? Yes/No");
            String otherFoodAsk = InputUtils.scanner.nextLine();
            if(!otherFoodAsk.equalsIgnoreCase("Yes"))
                chooseCropType = false;
        }
        animals.add(new Animal(animalName,speciesName,cropTypesToAdd));
    }
    private void removeAnimal(){
        viewAnimals();
        System.out.println("Which one would you like to remove? Type name");
        String animalName = InputUtils.scanner.nextLine();
        for (Animal animal: animals) {
            if(animalName.equalsIgnoreCase(animal.getName())) {
                System.out.println("Removed " + animal.getName());
                animals.remove(animal);
                break;
            }
        }
    }
    private void feedAnimals(ArrayList<Crop> crops){
        viewAnimals();
        System.out.println("Which animal would you like to feed? Type their ID.");
        int animalInputId = InputUtils.getIntInput()-1;
        Animal theAnimalToFeed = animals.get(animalInputId);
        for(Crop crop: crops){
            System.out.println(crop.getDescription());
        }
        System.out.println("Which Crop would you like to feed "+theAnimalToFeed.getName()+"? Type their ID.");
        int cropInputId = InputUtils.getIntInput()-1;
        Crop theCropToFeed = crops.get(cropInputId);
        theAnimalToFeed.feed(theCropToFeed);
    }

    public void loadAnimals(File dataFolder)
    {
        try {
            if (!dataFolder.exists()) {
                dataFolder.mkdir(); // "Ignored" För att det är en boolean men använder den som skapa mapp samma med andra
                animalData.createNewFile();
            }else if (!animalData.exists())
                animalData.createNewFile();

            FileReader fr = new FileReader(animalData);
            BufferedReader br = new BufferedReader(fr);

            String nextLine = br.readLine();
            if(nextLine == null)
            {
                System.out.println("No save was done before. Loading animals.");
                animals.add(new Animal("Hampus", "Panda", new ArrayList<>(Arrays.asList("Vegetable", "Fruit"))));
                animals.add(new Animal("Erik", "Sheep",new ArrayList<>(Arrays.asList("Vegetable","Grain"))));
                animals.add(new Animal("Greg", "Cow",new ArrayList<>(Arrays.asList("Vegetable","Grain"))));
                animals.add(new Animal("Cheeky", "Chicken",new ArrayList<>(List.of("Seed"))));
                animals.add(new Animal("Henry", "Bull",new ArrayList<>(Arrays.asList("Vegetable","Nut","Grain","Non-Food"))));
                animals.add(new Animal("Yenny", "Sheep",new ArrayList<>(Arrays.asList("Vegetable","Grain"))));
            }
            while (nextLine != null){// Om förra raden inte var null.
                if(!nextLine.isEmpty()) // Om nextLine inte är tom.
                {
                    String[] element = nextLine.split(",");//Split string
                    int id = Integer.parseInt(element[0]);
                    String name = element[1];
                    String species = element[2];
                    for (int i = 3; i < element.length;i++)
                    {
                        element[i] = element[i].replaceAll("\\[", "").replaceAll("]", "").replaceAll("\\s", "");
                    }
                    ArrayList<String> acceptableCropTypes = new ArrayList<>(Arrays.asList(element).subList(3, element.length));
                    animals.add(new Animal(id,name,species,acceptableCropTypes));//Makes crop

                    Animal.nextCropID = id+1; // makes nextCrop the next id after last highest id saved.
                }
                nextLine = br.readLine(); // Läser nästa rad innan kolla igenom i nästa loop
            }
            br.close();//Stänger cropData filen så den inte ligger o kör i bakgrunden.
        }catch (IOException ignored){

        }
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

}
