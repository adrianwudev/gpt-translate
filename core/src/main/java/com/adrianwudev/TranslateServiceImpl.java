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

    // Change the annotation from @MockChatGPTService to @ChatGPTService when use the real API.
    public TranslateServiceImpl(@MockChatGPTService ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @Override
    public String translate(String source, String lang) {
        if(source == null || lang == null){
            log.error("source and lang can not be null");
            throw new NullPointerException();
        }

        if (source.isBlank() || lang.isBlank()){
            String errorMessage = "please enter the valid source and language which you want to translate to";
            log.error(errorMessage);
            return errorMessage;
        }

        MessageRequestModel newMessage = new MessageRequestModel(USER, source);
        String presetContent = TransCommand
                .formatted(lang);
        ChatCompletionResponse response =
                chatGPTService.getChatCompletionResponse(newMessage, presetContent);


        return response.getChoices()[0].getMessage().getContent();
    }
}
