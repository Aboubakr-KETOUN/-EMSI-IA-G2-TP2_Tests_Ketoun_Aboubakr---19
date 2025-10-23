package ma.emsi.ketoun.tp2.tests;

import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;

import java.util.Map;

public class Test2 {
    public static void main(String[] args) {

        //retrait de la cle du env local
        String apiKey = System.getenv("GEMINI_KEY");
        if (apiKey == null) {
            System.err.println("No Key!");
            return;
        }

        // Creation du Model avec le builder
        GoogleAiGeminiChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-2.5-flash")
                .temperature(0.7)
                .build();

        PromptTemplate template = PromptTemplate.from("Traduis le texte suivant en anglais : {{texte}}");

        //creation du propmt qui utilise l interface de prompt en envoyant la qst comme valeur de variable texte
        Prompt prompt = template.apply(Map.of(
                "texte", "J’aime apprendre l’intelligence artificielle et le développement logiciel."
        ));

        //envoi du prompt au model
        String response = model.chat(prompt.text());

        //affichage de resultat
        System.out.println("Prompt envoye : " + prompt.text());
        System.out.println("Reponse : " + response);

    }
}
