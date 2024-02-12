/**
 * @description: dataflow conflict detected
 * @command: infer --pulse-only --pulse-taint-config ./.infertaintconfig -- javac ./Text.java 
*/

import java.util.Stack;

class Text {

  public String text;

  void cleanText() {
    //conflict!
    this.normalizeWhitespace();
    this.removeComments();
    this.removeDuplicateWords();
  }

  // Contribution 1
  void normalizeWhitespace() {
    // removes duplicate whitespaces
    this.text = this.text.trim().replaceAll(" +", " ");
  }

  void removeComments() {}

  // Contribution 2
  void removeDuplicateWords() {
    // removes duplicate words without normalizing whitespaces
    String[] words = this.text.split("\\s+");
    Stack<String> st = new Stack<>();
    for (int i = 0; i < words.length; i++) {
      if (st.isEmpty() || st.peek() != words[i]) {
        st.push(words[i]);
      } else {
        st.push(" ");
      }
    }
    this.text = String.join(" ", st);
  }
}

//infer --pulse-only --pulse-taint-config ./.infertaintconfig -- javac ./Text.java