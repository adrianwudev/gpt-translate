package com.adrianwudev;

import com.adrianwudev.config.ChatGPTConfig;
import com.adrianwudev.models.ChatCompletionResponse;
import com.adrianwudev.models.MessageRequestModel;
import com.adrianwudev.qualifiers.ChatGPTHttpRequestSender;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@com.adrianwudev.qualifiers.ChatGPTService
public class ChatGPTServiceImpl implements ChatGPTService {
    private final HttpRequestSender httpRequestSender;
    private final ChatGPTConfig config;

    public ChatGPTServiceImpl(@ChatGPTHttpRequestSender HttpRequestSender httpRequestSender, ChatGPTConfig config) {
        this.httpRequestSender = httpRequestSender;
        this.config = config;
    }

    public ChatCompletionResponse getChatCompletionResponse(MessageRequestModel newMessage, String presetContent) {

        ChatCompletionResponse response = null;
        try {

            List<MessageRequestModel> messages = new ArrayList<>();
            MessageRequestModel BackgroundMessage = new MessageRequestModel("system", presetContent);
            messages.add(BackgroundMessage);
            messages.add(newMessage);

            JSONObject data = new JSONObject();
            data.put("model", config.getModel());
            data.put("messages", new JSONArray(messages));
            data.put("temperature", config.getTemperature());

            response = httpRequestSender.send(data, ChatCompletionResponse.class);

        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage(), e);
        }
        return response;
    }
}
