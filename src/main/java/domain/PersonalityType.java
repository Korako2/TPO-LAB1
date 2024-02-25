package domain;

import lombok.Getter;

@Getter
public enum PersonalityType {
    Optimist(1.5), Pessimist(0.5), Realist(1);
    private final double emotionalUpliftCoefficient;

    PersonalityType(double emotionalUpliftCoefficient) {
        this.emotionalUpliftCoefficient = emotionalUpliftCoefficient;
    }

}
