package com.pokedex;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//TODO 1 Faire des tests
public class App {
    public static void main(String[] args) throws IOException, ParseException, URISyntaxException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("src/main/jsonFiles/pokemonForm.json");
        Object obj = jsonParser.parse(reader);
        JSONArray pokemonArray = (JSONArray) obj;
        int targetPokedexId = 3;
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
                String safeFrenchName = frenchName.replaceAll("[^a-zA-Z0-9.-]", "_");
                ImageIO.write(spriteImage, "png", new File("src/main/pokemonSprites/" + safeFrenchName + ".png"));
                System.out.println("Sprite URL of pokemon: " + spriteUrl);
                System.out.println("Sprite image of pokemon: " + spriteImage);
                break;
            }
        }
    }
}
