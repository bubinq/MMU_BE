package team.yellow.docconnect.utils;

public enum TokenName {
    RESET("Reset_Token"),
    VERIFICATION("Verification_Token");

    private final String value;

    TokenName(String value) {
        this.value = value;
    }

    public String getName() {
        return value;
    }
}