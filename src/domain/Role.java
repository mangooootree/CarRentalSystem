package domain;

public enum Role {
    GUEST("Гость"), CLIENT("Клиент"), ADMIN("Администратор");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return Long.valueOf(ordinal());
    }

    public String getName() {
        return name;
    }

}
