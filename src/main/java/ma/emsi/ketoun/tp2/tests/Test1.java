package ma.emsi.ketoun.tp2.tests;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

public class Test1 {

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

        // Question au model
        String question = "Quelle est la capitale du Maroc ?";
        String response = model.chat(question);

        // Affichage de la réponse
        System.out.println("Question : " + question);
        System.out.println("Réponse : " + response);

    }
}
