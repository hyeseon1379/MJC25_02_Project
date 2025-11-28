package kr.ac.mjc.fitMate.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Gender {
    @JsonProperty("male")
    MALE("남성"),

    @JsonProperty("female")
    FEMALE("여성");

    private final String displayName;

    Gender(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    @Override
    public String toString() {
        return displayName;
    }
}
