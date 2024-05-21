import java.util.Stack;

public class Text {
    public String text;

    void cleanText() {
        //conflict!
        Infer.left(() -> normalizeWhitespace());
        removeComments();
        Infer.right(() -> removeDuplicateWords());
    }

    // Contribution 1
    void normalizeWhitespace() {
        // removes duplicate whitespaces
        text = text.trim().replaceAll(" +", " ");
    }

    void removeComments() {}

    // Contribution 2
    void removeDuplicateWords() {
        // removes duplicate words without normalizing whitespaces
        String[] words = Infer.right(text.split("\\s+"));
        Stack<String> st = Infer.right(new Stack<>());
        for (int i = Infer.right(0); i < Infer.right(Infer.right(words).length); Infer.right(i++)) {
            if (Infer.right(st).isEmpty() || Infer.right(st).peek() != Infer.right(words[Infer.right(i)])) {
                Infer.right(st).push(Infer.right(words[Infer.right(i)]));
            } else {
                Infer.right(st).push(Infer.right(" "));
            }
        }
        text = Infer.right(String.join(Infer.right(" "), Infer.right(st)));
    }
}
