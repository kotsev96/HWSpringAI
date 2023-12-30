package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.prompt.PromptTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class PromptController {

    private final ChatClient chatClient;


    @GetMapping("/hello")
    public String sayHello() {
        String prompt= "Привет, как на китайском будет Hello World?";
        return chatClient.generate(prompt);
    }

    @GetMapping("/{prompt}")
    public String sendPrompt(@PathVariable String prompt) {
        return chatClient.generate(prompt);
    }

    @GetMapping("/learn/{message}")
    public String getMessage(@PathVariable("message") String message) {

        PromptTemplate promptTemplate = new PromptTemplate("""
        Пожалуйста, расскажи кратко и доступным языком
        что такое {message}.
        """);
        promptTemplate.add("message", message);
        return this.chatClient.generate(promptTemplate.create()).getGeneration().getContent();
    }
}
