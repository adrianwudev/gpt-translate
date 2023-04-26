package com.adrianwudev;

import com.adrianwudev.models.ChatCompletionResponse;
import com.adrianwudev.models.MessageRequestModel;
import com.adrianwudev.qualifiers.MockChatGPTService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.adrianwudev.Constants.TransCommand;
import static com.adrianwudev.Constants.USER;

@Slf4j
@Service
public class TranslateServiceImpl implements TranslateService {
    @Getter
    @Setter
    private String source;
    @Getter
    @Setter
    private String translation;
    private final ChatGPTService chatGPTService;

    public TranslateServiceImpl(@MockChatGPTService ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @Override
    public String translate(String source, String lang) {

        MessageRequestModel newMessage = new MessageRequestModel(USER, source);
        String presetContent = TransCommand
                .formatted(lang);
        ChatCompletionResponse response =
                chatGPTService.getChatCompletionResponse(newMessage, presetContent);


        return response.getChoices()[0].getMessage().getContent();
    }
}
