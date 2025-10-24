package ma.emsi.ketoun.tp2.tests;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.googleai.GoogleAiEmbeddingModel;
import dev.langchain4j.store.embedding.CosineSimilarity;
import dev.langchain4j.model.output.Response;
import java.time.Duration;

public class Test3 {
    public static void main(String[] args) {

        // Récupération de la clé API
        String apiKey = System.getenv("GEMINI_KEY");
        if (apiKey == null) {
            System.err.println("No Key!");
            return;
        }

        // creation du modele embedding a partir du builder
        EmbeddingModel modele = GoogleAiEmbeddingModel.builder()
                .apiKey(apiKey)
                .modelName("gemini-embedding-001")
                .taskType(GoogleAiEmbeddingModel.TaskType.SEMANTIC_SIMILARITY)
                .outputDimensionality(300)
                .timeout(Duration.ofSeconds(2))
                .build();

        //phrases a fair embeder
        String phrase1 = "I love drinking coffee";
        String phrase2 = "I like coffee beans";

        System.out.println("Testing>>>");

        System.out.println("Phrase 1 : " + phrase1);
        System.out.println("Phrase 2 : " + phrase2);

        // generation des embeddings
        Response<Embedding> reponse1 = modele.embed(phrase1);
        Response<Embedding> reponse2 = modele.embed(phrase2);

        // extraction du contenu depuis du embedding
        Embedding emb1 = reponse1.content();
        Embedding emb2 = reponse2.content();

        // calculer similarite avec le cos
        double similarite = CosineSimilarity.between(emb1, emb2);

        System.out.printf("Similarite cosinus : %.4f%n", similarite);
    }
}
