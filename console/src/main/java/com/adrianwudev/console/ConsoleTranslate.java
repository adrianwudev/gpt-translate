package com.adrianwudev.console;

import com.adrianwudev.TranslateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.adrianwudev.Constants.Spanish;

@Slf4j
@Component
public class ConsoleTranslate {
    private final TranslateService translateService;

    public ConsoleTranslate(TranslateService translateService) {
        this.translateService = translateService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void start(){
        log.info("start() --> Container ready for use.");
        System.out.println("Enter q/quit if you wanna quit.");

        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Enter the word/phrase you want to translate:");
            String source = scanner.nextLine();

            String trimSource = source.trim();
            boolean isQuit = trimSource.equalsIgnoreCase("q")
                             || trimSource.equalsIgnoreCase("quit");
            if(isQuit) break;

            String translation = translateService.translate(source, Spanish);
            System.out.println(translation);

        }

        log.info("Exiting application.");
        System.exit(-1);
    }
}
