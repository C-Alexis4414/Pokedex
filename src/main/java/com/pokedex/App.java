package com.pokedex;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
//TODO 1 Faire des tests
public class App {
    public static void main(String[] args) throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader reader = new FileReader("src/main/jsonFiles/pokemonForm.json");
        Object obj = jsonParser.parse(reader);
        JSONArray pokemonArray = (JSONArray) obj;
        int targetPokedexId = 1;
        for (Object pokemonObj : pokemonArray) {
            JSONObject pokemon = (JSONObject) pokemonObj;
            long pokedexId = (long) pokemon.get("pokedexId");

            if (pokedexId == targetPokedexId) {
                JSONObject nameObject = (JSONObject) pokemon.get("name");
                String frenchName = (String) nameObject.get("fr");
                System.out.println("French name of first pokemon: " + frenchName);
                break;
            }
        }
    }
}
