package com.edmar.cadastro.application.config;

import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
//import com.google.auth.oauth2.GoogleCredentials;

public class PushNotification {

    // Endpoint atualizado
    /**private static final String FCM_URL = "https://fcm.googleapis.com/v1/projects/YOUR_PROJECT_ID/messages:send";

    public static void main(String[] args) {
        String deviceToken = "YOUR_DEVICE_TOKEN_HERE";
        String message = "Olá, você tem uma nova notificação!";
        sendPushNotification(deviceToken, message);
    }

    private static String getAccessToken() throws Exception {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new FileInputStream("caminho/para/service-account.json"))
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/firebase.messaging"));
        googleCredentials.refreshIfExpired();
        return googleCredentials.getAccessToken().getTokenValue();
    }

    public static void sendPushNotification(String deviceToken, String message) {
        try {
            // Obter o token de acesso OAuth 2.0
            String accessToken = getAccessToken();

            // Crie a URL para a requisição
            URL url = new URL(FCM_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Configurações de conexão
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + accessToken);
            connection.setRequestProperty("Content-Type", "application/json; UTF-8");
            connection.setDoOutput(true);

            // Corpo da requisição no formato da API HTTP v1
            String payload = "{"
                    + "\"message\": {"
                    + "\"token\": \"" + deviceToken + "\","
                    + "\"notification\": {"
                    + "\"title\": \"Notificação\","
                    + "\"body\": \"" + message + "\""
                    + "}"
                    + "}"
                    + "}";

            // Enviar o payload para o FCM
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = payload.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Ler a resposta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Notificação enviada com sucesso!");
            } else {
                System.out.println("Falha ao enviar notificação. Código de resposta: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }**/
}
