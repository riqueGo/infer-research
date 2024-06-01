import java.util.Stack;

public class Text {
    public String text;
    public int spaces = 0;
    public int words = 0;

    void cleanText() {
        //conflict!
        Infer.left(() -> normalizeWhitespace());
        removeComments();
        Infer.right(() -> removeDuplicateWords());
    }

    // Contribution 1
    void normalizeWhitespace() {
        // removes duplicate whitespaces
        text = Infer.left(text.trim().replaceAll(" +", " "));
    }

    void removeComments() {}

    // Contribution 2
    void removeDuplicateWords() {
        // removes duplicate words without normalizing whitespaces
        String[] words = Infer.right(Infer.left(text.split("\\s+")));
        Stack<String> st = Infer.right(new Stack<>());
        for (int i = Infer.right(0); Infer.right(Infer.left(i < words.length)); Infer.right(i++)) {
            if (Infer.right(Infer.left(st.isEmpty() || st.peek() != words[i]))) {
                Infer.right(Infer.left(st.push(words[i])));
            } else {
                Infer.right(Infer.left(st.push(" ")));
            }
        }
        text = Infer.right(String.join(" ", st));
    }

    int countFixes() {
        Infer.left(()->countDupWhiteSpace());
        countComments();
        Infer.right(()->countDupWords());
        //conflict!
        return Infer.right(Infer.left(spaces + words));
    }

    // Contribution 1
    private void countDupWhiteSpace() {
        if(Infer.left(text.isEmpty())) {
            return;
        }

        for (int i = Infer.left(1); Infer.left(i < text.length()); Infer.left(i++)) {
            if (Infer.left(Character.isWhitespace(text.charAt(i)) && text.charAt(i-1) == text.charAt(i))) {
                Infer.left(spaces++); //assignment
            }
        }
    }

    private void countComments() {}

    // Contribution 2
    private void countDupWords() {
        if(Infer.right(text.isBlank())) {
            return;
        }

        String[] onlyWords = Infer.right(text.trim().split("\\s+"));

        for (int i = Infer.right(1); Infer.right(i < onlyWords.length); Infer.right(i++)) {
            if (Infer.right(onlyWords[i-1].equals(onlyWords[i]))) {
                Infer.right(words++); //assignment
            }
        }
    }
}
