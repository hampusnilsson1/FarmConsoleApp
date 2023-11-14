package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Farm {
    File dataFolder = new File("Data");

    CropManager cropManager = new CropManager(dataFolder);
    AnimalManager animalManager = new AnimalManager(dataFolder);



    public void mainMenu() {
        boolean menuActive = true;
        while (menuActive) {
            System.out.println("Select an action: ");
            System.out.println("1. Open crop-menu");
            System.out.println("2. Open animal-menu");
            System.out.println("3. Save");
            System.out.println("4. Close");

            try {
                int menuInput = Integer.parseInt(InputUtils.scanner.nextLine());
                switch (menuInput) {
                    case 1:
                        cropManager.cropMenu();
                        break;
                    case 2:
                        animalManager.animalMenu(cropManager.getCrops());
                        break;
                    case 3:
                        save(cropManager,cropManager.cropsData);
                        save(animalManager,animalManager.animalData);
                        break;
                    case 4:
                        menuActive = false;
                        break;
                    default:
                        System.out.println("That's not an option!");
                }
            } catch (Exception e) {
                System.out.println("Try Again.");
            }
        }

    }
    private void save(CropManager cropManager, File saveFile) { // Save the crop Data with same method
        try {
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            FileWriter fileWriter = new FileWriter(saveFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < cropManager.getCrops().size(); i++) {
                bufferedWriter.write(cropManager.getCrops().get(i).getCSV());
                if (i < cropManager.getCrops().size() - 1)
                    bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving crops! " + e);
        }
    }

    private void save(AnimalManager animalManager, File saveFile) { // Save the animal Data with same method
        try {
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

            FileWriter fileWriter = new FileWriter(saveFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < animalManager.getAnimals().size(); i++) {
                bufferedWriter.write(animalManager.getAnimals().get(i).getCSV());
                if (i < animalManager.getAnimals().size() - 1)
                    bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving animals! " + e);
        }
    }
}
