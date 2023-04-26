package com.adrianwudev.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Choice {
    private MessageRequestModel message;
    private String finish_reason;
    private int index;
    public Choice(MessageRequestModel message){
        this.message = message;
    }
}
