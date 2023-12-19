package src.UIComponent;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ChatGPT {
    private static final String OPENAI_API_KEY = "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";

    public static String chatGPT(String prompt, String model) {
        String url = "https://api.openai.com/v1/chat/completions";

        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + OPENAI_API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt
                    + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // calls the method to extract the message.
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content") + 11;

        int end = response.indexOf("\"", start);

        return response.substring(start, end);

    }

    public static CompletableFuture<BufferedImage> fetchDALLEResponse(String prompt, int sizeX, int sizeY)
            throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            String url = "https://api.openai.com/v1/images/generations";
            try {
                HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
                System.out.println("brunh");
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "application/json");
                con.setRequestProperty("Authorization", "Bearer " + OPENAI_API_KEY);
                String body = "{\"model\": \"dall-e-2\", \"prompt\": \"" + prompt + "\", \"n\": 1, \"size\": \"" + sizeX
                        + "x" + sizeY + "\"}";
                con.setDoOutput(true);
                System.out.println("why god why have u forsaken me");
                OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                writer.write(body);
                writer.flush();
                System.out.println(body);
                System.out.println(con);
                System.out.println(con.getContent());
                Scanner scan = new Scanner(con.getInputStream());
                String output = "";
                while (scan.hasNextLine()) {
                    output += scan.nextLine();
                    System.out.println(output);
                }
                output = output.substring(output.indexOf("url") + 7);
                output = output.substring(0, output.indexOf("\""));
                URL imgUrl = new URL(output);
                BufferedImage buff = ImageIO.read(imgUrl);
                System.out.println("LMAOOOOOOOO");
                return buff;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void main(String[] args) {
        try {
            System.out.println(fetchDALLEResponse("a cute cat", 1024, 1024));

            // BufferedImage dalleResponse = fetchDALLEResponse("a cute cat");
            // File outputfile = new File("dalle_response.png");
            // ImageIO.write(dalleResponse, "png", outputfile);
            // System.out.println("DALL-E response saved to dalle_response.png");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
