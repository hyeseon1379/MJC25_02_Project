package kr.ac.mjc.fitMate.global.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Trouble {
    @JsonProperty("love")
    LOVE("연애"),

    @JsonProperty("friends")
    FRIENDS("친구"),

    @JsonProperty("work")
    WORK("직장"),

    @JsonProperty("other")
    OTHER("기타");

    private final String displayName;

    Trouble(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    @Override
    public String toString() {
        return displayName;
    }

    public static Trouble fromValue(String value) {
        for (Trouble t : Trouble.values()) {
            if (t.displayName.equals(value)) {
                return t;
            }
        }
        throw new IllegalArgumentException("Invalid trouble value: " + value);
    }
}
