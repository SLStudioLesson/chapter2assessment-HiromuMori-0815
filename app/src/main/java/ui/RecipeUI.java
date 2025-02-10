package ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;

import data.RecipeFileHandler;

public class RecipeUI {
    private BufferedReader reader;
    private RecipeFileHandler fileHandler;

    public RecipeUI() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        fileHandler = new RecipeFileHandler();
    }

    public RecipeUI(BufferedReader reader, RecipeFileHandler fileHandler) {
        this.reader = reader;
        this.fileHandler = fileHandler;
    }

    public void displayMenu() {
        while (true) {
            try {
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // 設問1: 一覧表示機能
                        fileHandler.readRecipes();
                        displayRecipes();
                        break;
                    case "2":
                        // 設問2: 新規登録機能
                        addNewRecipe();
                        break;
                    case "3":
                        // 設問3: 検索機能
                        break;
                    case "4":
                        System.out.println("Exit the application.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    /**
     * 設問1: 一覧表示機能
     * RecipeFileHandlerから読み込んだレシピデータを整形してコンソールに表示します。
     */
    private void displayRecipes() {
        // 配列を作成後、まずファイルの中身が空かどうかを判断する。
        // その後、空であれば"No recipes available."を表示し、空でなければ料理名と具材を分けて、一覧を表示する
        ArrayList<String> recipe = fileHandler.readRecipes();
        if (recipe.isEmpty()) {
            System.out.println("No recipes available.");
            return;
        }

        System.out.println("Recipes:");
        for (String menu : recipe) {
            String[] recipeName = menu.split(",", 2);
            if (recipeName.length == 2) {
                System.out.println("-----------------------------------");
                System.out.println("Recipe Name: " + recipeName[0]);
                System.out.println("Main Ingredients: " + recipeName[1]);
            }
        }
    }

    /**
     * 設問2: 新規登録機能
     * ユーザーからレシピ名と主な材料を入力させ、RecipeFileHandlerを使用してrecipes.txtに新しいレシピを追加します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void addNewRecipe() throws IOException {

        // ユーザーに新しいレシピ名と主な具材を入力させる。
        // それをaddRecipeメソッドに渡す。

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter recipe name:");
        String recipeName = reader.readLine();

        System.out.println("Enter main ingredients (comma separated):");
        String ingredients = reader.readLine();

        fileHandler.addRecipe(recipeName, ingredients);

    }

    /**
     * 設問3: 検索機能
     * ユーザーから検索クエリを入力させ、そのクエリに基づいてレシピを検索し、一致するレシピをコンソールに表示します。
     *
     * @throws java.io.IOException 入出力が受け付けられない
     */
    private void searchRecipe() throws IOException {

    }

}
