package app;

import interface_adapters.GenerateRecipeController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("What is the keyword? ");
        String q = in.nextLine();

        System.out.print("One of your diet preference? ");
        String[] diet = {in.nextLine()};

        System.out.print("One of your health preference? ");
        String[] health = {in.nextLine()};

        System.out.print("One of your cuisine type preference? ");
        String[] cuisineType = {in.nextLine()};

        System.out.print("One of your meal type preference? ");
        String[] mealType = {in.nextLine()};

        System.out.print("Your minimum calories needed? ");
        String minCalories = in.nextLine();

        System.out.print("Your maximum calories needed? ");
        String maxCalories = in.nextLine();

        System.out.print("Max preparation time?");
        String maxPreparationTime = in.nextLine();


        GenerateRecipeController generateRecipeController = GenerateRecipeFactory.createGenerateUseCase();
        generateRecipeController.execute(q, diet, health, cuisineType, mealType, minCalories, maxCalories, maxPreparationTime);

    }
}