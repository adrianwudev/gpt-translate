package com.adrianwudev;

import com.adrianwudev.models.ChatCompletionResponse;
import com.adrianwudev.models.MessageRequestModel;

public interface ChatGPTService {
    ChatCompletionResponse getChatCompletionResponse(MessageRequestModel newMessage, String presetContent);
}
