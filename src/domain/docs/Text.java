package domain.docs;

public abstract class Text {

    private static final String NON_CHAR_REGX = "[^a-zA-Z0-9 \\t]";

    public abstract String getText();

    public String[] getWords() {
        return getText().toLowerCase()
                .replaceAll(NON_CHAR_REGX, "")
                .split("\\s");
    }

}