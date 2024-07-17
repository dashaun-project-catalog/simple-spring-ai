package dev.dashaun.springai.simplechat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
class SimpleChatController {

    private final ChatClient chatClient;


    public SimpleChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping("/simpleChat")
    String simpleChat(@RequestParam(defaultValue = "Can you please tell me a joke?") String question) throws Exception {
        return chatClient.prompt()
                .user(userSpec -> userSpec
                        .text(question)
                )
                .call()
                .content();
    }

    @GetMapping("/jokeStructured")
    StructuredResponse jokeStructured(@RequestParam(defaultValue = "Can you please tell me a joke?") String question) throws Exception {
        return chatClient.prompt()
                .user(userSpec -> userSpec
                        .text(question)
                )
                .call()
                .entity(StructuredResponse.class);
    }
}
