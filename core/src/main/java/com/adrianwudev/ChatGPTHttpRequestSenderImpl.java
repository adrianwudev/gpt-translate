package com.adrianwudev;

import com.adrianwudev.qualifiers.ChatGPTHttpRequestSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Service
@ChatGPTHttpRequestSender
public class ChatGPTHttpRequestSenderImpl implements HttpRequestSender {
    private static final String apiUrl = "https://api.openai.com/v1/chat/completions";
    private final String API_KEY;

    public ChatGPTHttpRequestSenderImpl(String API_KEY){
        this.API_KEY = API_KEY;
    }
    @Override
    public <T> T send(JSONObject data, Class<T> responseType) throws Exception {
        URL url = new URL(apiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "Bearer " + API_KEY);
        con.setDoOutput(true);


        log.info("Send Request: url: {},data: {}", url, data);
        OutputStream os = con.getOutputStream();
        os.write(data.toString().getBytes());
        os.flush();

        String responseJson = new BufferedReader(new InputStreamReader(con.getInputStream()))
                .lines().reduce((a, b) -> a + b).get();

        Gson gson = new GsonBuilder().serializeNulls().create();
        T response = gson.fromJson(responseJson, responseType);

        log.info("response: {}", gson.toJson(response));

        return response;
    }
}
