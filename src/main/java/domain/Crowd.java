package domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class Crowd {
    @Getter
    private Set<Human> humans;
    @Getter
    @Setter
    private double emotionalUplift;

    private final int MAX_EMOTIONAL_UPLIFT = 100;
    private final int MIN_EMOTIONAL_UPLIFT = 0;

    public Crowd(Set<Human> humans) {
        this.humans = humans;
        this.emotionalUplift = calcEmotionalUplift();
    }

    public void addHuman(Human human) {
        humans.add(human);
        emotionalUplift = calcEmotionalUplift();
    }

    public void kickHuman(Human human) {
        humans.remove(human);
        emotionalUplift = calcEmotionalUplift();
    }

    public void clearHuman() {
        humans.clear();
        emotionalUplift = calcEmotionalUplift();
    }

    public int getHumansCount() {
        return humans.size();
    }

    public void increaseEmotionalUplift(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount should be positive");
        if (amount > 100) throw new IllegalArgumentException("Amount should be less than 100");
        if (emotionalUplift == MAX_EMOTIONAL_UPLIFT)
            throw new IllegalArgumentException("Emotional uplift is the maximum possible");
        increaseHumansEmotionalUplift(amount);
        emotionalUplift = calcEmotionalUplift();
    }

    public void reduceEmotionalUplift(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount should be positive");
        if (amount > 100) throw new IllegalArgumentException("Amount should be less than 100");
        if (emotionalUplift == MIN_EMOTIONAL_UPLIFT)
            throw new IllegalArgumentException("Emotional uplift is the minimum possible");
        reduceHumansEmotionalUplift(amount);
        emotionalUplift = calcEmotionalUplift();
    }

    private double calcEmotionalUplift() {
        int amountEmotionalUplift = 0;
        for (Human human : humans) {
            amountEmotionalUplift += human.getEmotionalUplift();
        }
        return (double) amountEmotionalUplift / humans.size();
    }

    private void increaseHumansEmotionalUplift(int amount) {
        for (Human human : humans) {
            if (human.getPersonalityType().getEmotionalUpliftCoefficient() * amount + human.getEmotionalUplift() > MAX_EMOTIONAL_UPLIFT)
                human.setEmotionalUplift(MAX_EMOTIONAL_UPLIFT);
            else
                human.setEmotionalUplift((int) Math.round(human.getPersonalityType().getEmotionalUpliftCoefficient() * amount + human.getEmotionalUplift()));
        }
    }

    private void reduceHumansEmotionalUplift(int amount) {
        for (Human human : humans) {
            if (human.getEmotionalUplift() - 1 / human.getPersonalityType().getEmotionalUpliftCoefficient() * amount < MIN_EMOTIONAL_UPLIFT)
                human.setEmotionalUplift(MIN_EMOTIONAL_UPLIFT);
            else
                human.setEmotionalUplift((int) Math.round(human.getEmotionalUplift() - 1 / human.getPersonalityType().getEmotionalUpliftCoefficient() * amount));
        }
    }

    public void cheer() {
        if (emotionalUplift >= 80) {
            System.out.println("Толпа в полнейшем восторге! Раздаются ликующие крики!");
        } else if (emotionalUplift >= 50) {
            System.out.println("Толпа радостно кричит и аплодирует!");
        } else if (emotionalUplift >= 20) {
            System.out.println("Толпа волнуется и выражает свою поддержку!");
        } else {
            System.out.println("Толпа немного апатична. Нужно что-то, чтобы их взбодрить!");
        }
    }

}
