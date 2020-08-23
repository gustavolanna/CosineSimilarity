package domain.docs;

public class Document extends Text {

    private String text;

    public Document(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Document content is required");
        }
        this.text = text;
    }

    public String getText() {
        return text;
    }

}