package com.adrianwudev.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usage {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;
}
