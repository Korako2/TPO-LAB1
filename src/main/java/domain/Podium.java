package domain;

import lombok.Getter;

public class Podium {
    @Getter
    private Human speaker;

    public void setSpeaker(Human newSpeaker) {
        if (speaker == null || newSpeaker.getCrowdApprovalLevel() > speaker.getCrowdApprovalLevel())
            speaker = newSpeaker;
        else
            throw new IllegalArgumentException("Speaker is not popular enough");
    }


}
