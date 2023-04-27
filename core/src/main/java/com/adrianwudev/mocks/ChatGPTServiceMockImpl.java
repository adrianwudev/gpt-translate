package com.adrianwudev.mocks;

import com.adrianwudev.ChatGPTService;
import com.adrianwudev.HttpRequestSender;
import com.adrianwudev.config.ChatGPTConfig;
import com.adrianwudev.models.ChatCompletionResponse;
import com.adrianwudev.models.Choice;
import com.adrianwudev.models.MessageRequestModel;
import com.adrianwudev.qualifiers.MockChatGPTService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@MockChatGPTService
public class ChatGPTServiceMockImpl implements ChatGPTService {
    private final ChatGPTConfig config;

    public ChatGPTServiceMockImpl(ChatGPTConfig config) {
        this.config = config;
    }

    public ChatCompletionResponse getChatCompletionResponse(MessageRequestModel newMessage, String presetContent) {

        ChatCompletionResponse response = null;
        try {
            List<MessageRequestModel> messages = new ArrayList<>();
            MessageRequestModel backgroundMessage = new MessageRequestModel("system", presetContent);
            messages.add(backgroundMessage);
            messages.add(newMessage);

            JSONObject data = new JSONObject();
            data.put("model", config.getModel());
            data.put("messages", new JSONArray(messages));
            data.put("temperature", config.getTemperature());

            log.info("Mock Send data: {}", data);

            response = new ChatCompletionResponse();

            response.setChoices(
                    new Choice[]{
                            new Choice(new MessageRequestModel("test", "this is a mock response"))
                    });

            Gson gson = new GsonBuilder().serializeNulls().create();
            String responseJson = gson.toJson(response);
            log.info("Mock Response: {}", responseJson);

        } catch (Exception e) {
            log.error("An error occurred: {}", e.getMessage(), e);
        }

        return response;
    }
}
