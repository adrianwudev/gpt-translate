package com.adrianwudev;

import com.adrianwudev.models.ChatCompletionResponse;
import com.adrianwudev.models.MessageRequestModel;
import org.json.JSONObject;

import java.util.List;

public interface HttpRequestSender {
    <T> T send(JSONObject data, Class<T> responseType) throws Exception;
}
