package domain;

import lombok.Data;

@Data
public class Human {
    private String name;
    private PersonalityType personalityType;
    private int emotionalUplift;
    private int crowdApprovalLevel;

    public Human(String name, PersonalityType personalityType, int emotionalUplift, int crowdApprovalLevel) {
        this.name = name;
        this.personalityType = personalityType;
        if (emotionalUplift < 0 || emotionalUplift > 100) throw new IllegalArgumentException("Emotional uplift should be between 0 and 100");
        this.emotionalUplift = emotionalUplift;
        this.crowdApprovalLevel = crowdApprovalLevel;
    }

    public void addressMessageToCrowd(Crowd crowd, Message message) {
        if (crowd.getHumansCount() == 0) throw new IllegalArgumentException("Crowd is empty");
        if (message == Message.GOOD) {
            crowd.increaseEmotionalUplift(10);
        } else if (message == Message.BAD) {
            crowd.reduceEmotionalUplift(10);
        }
        crowd.cheer();
    }

    public void addressMessageToCrowdFromPodium(Crowd crowd, Message message) {
        if (crowd.getHumansCount() == 0) throw new IllegalArgumentException("Crowd is empty");
        if (message == Message.GOOD) {
            crowd.increaseEmotionalUplift(25);
        } else if (message == Message.BAD) {
            crowd.reduceEmotionalUplift(25);
        }
        crowd.cheer();
    }

}
