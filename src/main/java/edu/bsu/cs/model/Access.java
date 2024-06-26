package edu.bsu.cs.model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Properties;

public class Access {

    public static String createClientInfo(){
        Properties properties = new Properties();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream("configuration.properties");
        } catch (FileNotFoundException e) {
            ErrorCatcher.configFileNotFoundError();
        }
        try {
            properties.load(inputStream);
        }
        catch (NullPointerException e){
            ErrorCatcher.nullPointerExceptionError();
        }
        catch (IOException e) {
            ErrorCatcher.printUnknownError();
        }
        String clientID = properties.getProperty("client_id");
        String clientSecret = properties.getProperty("client_secret");
        String auth_string = clientID + ":" + clientSecret;
        return encode(auth_string);
    }

    public static String encode(String string) {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String getAccessToken(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://accounts.spotify.com/api/token"))
                .header("Authorization", "Basic " + createClientInfo())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials"))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            ErrorCatcher.ioExceptionError();
        } catch (InterruptedException e) {
            ErrorCatcher.interruptedExceptionError();
        }

        if (Objects.requireNonNull(response).statusCode() == 200) {
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);
            return jsonResponse.get("access_token").getAsString();
        }

        else {
            ErrorCatcher errorcatch = new ErrorCatcher();
            String responseToString = response.request().toString();
            int responseToInt = Integer.parseInt(responseToString);
            errorcatch.statusError(responseToInt);
        }
        return null;
    }
}