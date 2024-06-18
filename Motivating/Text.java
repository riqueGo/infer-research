import java.util.Stack;

public class Text {
    public String text;
    public int spaces = 0;
    public int words = 0;

    void cleanText() {
        //conflict!
        normalizeWhitespace(); //left change
        removeComments();
        removeDuplicateWords(); //right change
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
        for (int i = Infer.right(0); Infer.right(i < words.length); Infer.right(i++)) {
            if (Infer.right(st.isEmpty() || st.peek() != words[i])) {
                Infer.right(st.push(words[i]));
            } else {
                Infer.right(st.push(" "));
            }
        }
        text = Infer.right(String.join(" ", st));
    }

    int countFixes() {
        countDupWhiteSpace();
        countComments();
        countDupWords();
        //conflict!
        return spaces + words;
    }

    // Contribution 1
    private void countDupWhiteSpace() {
        if(text.isEmpty()) {
            return;
        }

        for (int i = 1; i < text.length(); i++) {
            if (Character.isWhitespace(text.charAt(i)) && text.charAt(i-1) == text.charAt(i)) {
                spaces++; //assignment
            }
        }
    }

    private void countComments() {}

    // Contribution 2
    private void countDupWords() {
        if(text.isBlank()) {
            return;
        }

        String[] onlyWords = text.trim().split("\\s+");

        for (int i = 1; i < onlyWords.length; i++) {
            if (onlyWords[i-1].equals(onlyWords[i])) {
                words++; //assignment
            }
        }
    }
}
