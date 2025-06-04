package pl.krzysztofskul.cadmdb.ai;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

@Service
public class AiService {
	
  private final OpenAiService client;
  
  public AiService(@Value("${openai.api-key}") String key) {
	  this.client = new OpenAiService(key, Duration.ofSeconds(60));
  }
  
  public String chatCompletion(String prompt) {
    ChatCompletionRequest req = ChatCompletionRequest.builder()
      .model("gpt-4o-mini")
      .messages(List.of(
         new ChatMessage("system",
           "You are a helpful assistant for medical facility planning."),
         new ChatMessage("user", prompt)
      ))
      .build();
    return client.createChatCompletion(req)
                 .getChoices().get(0)
                 .getMessage().getContent()
                 .trim();
  }
}
