package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class RecipeFileHandler {
    private String filePath;

    public RecipeFileHandler() {
        filePath = "app/src/main/resources/recipes.txt";
    }

    public RecipeFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 設問1: 一覧表示機能
     * recipes.txtからレシピデータを読み込み、それをリスト形式で返します。 <br>
     * IOExceptionが発生したときは<i>Error reading file: 例外のメッセージ</i>とコンソールに表示します。
     *
     * @return レシピデータ
     */
    public ArrayList<String> readRecipes() {
        // 配列を作成後、そのなかにファイルの中身を読み込み、それをdisplayRecipesメソッドに渡す
        ArrayList<String> recipe = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                recipe.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file:" + e.getMessage());
        }
        return recipe;
    }

    /**
     * 設問2: 新規登録機能
     * 新しいレシピをrecipes.txtに追加します。<br>
     * レシピ名と材料はカンマ区切りで1行としてファイルに書き込まれます。
     *
     * @param recipeName  レシピ名
     * @param ingredients 材料名
     */
    //
    public void addRecipe(String recipeName, String ingredients) {

        // addNewRecipeメソッドから渡されたユーザーの入力した情報を","で区切ってファイル内に新しく追記する

        String writing = "";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writing = recipeName + "," + ingredients;
            writer.write(writing);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error readeing file:" + e.getMessage());
        }
        System.out.println("Recipe added successfully.");
    }
}
