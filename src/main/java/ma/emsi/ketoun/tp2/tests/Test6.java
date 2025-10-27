package ma.emsi.ketoun.tp2.tests;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;

public class Test6 {
    //Assistant meteo

    interface AssistantMeteo {
        // Prend un message de l'utilisateur et retourne une réponse du LLM.
        String chat(String userMessage);
    }

    public static void main(String[] args) {


        String llmKey = System.getenv("GEMINI_Key");
        if (llmKey == null) {
            System.err.println("No Key!");
            return;
        }

        ChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(llmKey)
                .modelName("gemini-2.5-flash")
                .logRequestsAndResponses(true)
                .build();

        AssistantMeteo assistant =
                AiServices.builder(AssistantMeteo.class)
                        .chatModel(model)
                        .tools(new MeteoTool())  // Ajout de l'outil
                        .build();

        String response = assistant.chat("je veux voyager a paris est ce que j'ai besoin de parapluie ?");
//          reponse : À Paris, il fait +12°C et le temps est partiellement nuageux. Il n'y a pas de pluie annoncée pour le moment, mais un parapluie est toujours utile en cas de changements imprévus, surtout à Paris !

//        String response = assistant.chat("je veux voyager a fjrbg est ce que j'ai besoin de parapluie ?");
//        reponse : Je n'arrive pas à trouver la météo pour cette ville. Es-tu sûr que le nom de la ville est bien orthographié ?

//        String response = assistant.chat("je veux manger ?");
//        reponse : Je ne peux pas vous aider avec ça.

        System.out.println(response);
    }
}
