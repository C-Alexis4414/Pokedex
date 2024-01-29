package com.pokedex;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException, URISyntaxException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("src/main/jsonFiles/pokemonForm.json");
        Object obj = jsonParser.parse(reader);
        JSONArray pokemonArray = (JSONArray) obj;
        int targetPokedexId = 4;
        Scene scene = null;
        for (Object pokemonObj : pokemonArray) {
            JSONObject pokemon = (JSONObject) pokemonObj;
            long pokedexId = (long) pokemon.get("pokedexId");

            if (pokedexId == targetPokedexId) {
                JSONObject nameObject = (JSONObject) pokemon.get("name");
                String frenchName = (String) nameObject.get("fr");
                System.out.println("French name of pokemon: " + frenchName);
                JSONObject spriteObject = (JSONObject) pokemon.get("sprites");
                String spriteUrlString = (String) spriteObject.get("regular");
                URI spriteUrl = new URI(spriteUrlString);
                BufferedImage spriteImage = ImageIO.read(spriteUrl.toURL());

                // Convert BufferedImage to JavaFX Image
                Image image = SwingFXUtils.toFXImage(spriteImage, null);

                ImageView imageView = new ImageView(image);
                StackPane root = new StackPane(imageView);
                scene = new Scene(root, 500, 500);

                stage.setTitle("Sprite de " + frenchName);
                String safeFrenchName = frenchName.replaceAll("[^a-zA-Z0-9.-]", "_");
                ImageIO.write(spriteImage, "png", new File("src/main/pokemonSprites/" + safeFrenchName + ".png"));
                System.out.println("Sprite URL of pokemon: " + spriteUrl);
                break;
            }
        }
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}