package domain;

import lombok.Getter;

public class Podium {
    @Getter
    private Human speaker;

    public void setSpeaker(Human speaker) {
        if (speaker.getCrowdApprovalLevel() > this.speaker.getCrowdApprovalLevel())
            this.speaker = speaker;
        else
            throw new IllegalArgumentException("Speaker is not popular enough");
    }


}
