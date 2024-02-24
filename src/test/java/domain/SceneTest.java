package domain;

import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SceneTest {
    @Nested
    public class CrowdTest {
        private Crowd crowd;
        @BeforeEach
        public void init() {
            Set<Human> humans = new HashSet<>(Arrays.asList(
                    new Human("Иван", PersonalityType.Optimist, 10, 0),
                    new Human("Валерий", PersonalityType.Pessimist, 0, 0),
                    new Human("Аркадий", PersonalityType.Realist, 5, 0)
                    ));
            crowd = new Crowd(humans);
        }

        @Test
        @DisplayName("Check the addition to the crowd")
        public void addNewHumanTest() {
            Human human = new Human("Анатолий", PersonalityType.Optimist, 50, 0);
            crowd.addHuman(human);
            assertEquals(4, crowd.getHumansCount());
        }

        @Test
        @DisplayName("Check the kick from the crowd")
        public void kickHumanTest() {
            Human human = new Human("Анатолий", PersonalityType.Optimist, 50, 0);
            crowd.addHuman(human);
            crowd.kickHuman(human);
            assertEquals(3, crowd.getHumansCount());
        }

        @Test
        @DisplayName("Crowd should be empty after clear")
        public void clearHumanTest() {
            crowd.clearHuman();
            assertTrue(crowd.getHumans().isEmpty());
        }

        @Test
        @DisplayName("Check starting emotionalUplift")
        public void checkStartingEmotionalUpliftTest() {
            assertEquals(5, crowd.getEmotionalUplift());
        }

        @Test
        @DisplayName("The emotional uplift has to change after adding a person")
        public void checkEmotionalUpliftAfterAddingTest() {
            Human human = new Human("Анатолий", PersonalityType.Optimist, 50, 0);
            crowd.addHuman(human);
            assertEquals(16.25, crowd.getEmotionalUplift());
        }

        @Test
        @DisplayName("The emotional uplift has to change after the kick of a person")
        public void checkEmotionalUpliftAfterKickTest() {
            Human human = new Human("Анатолий", PersonalityType.Optimist, 50, 0);
            crowd.addHuman(human);
            crowd.kickHuman(human);
            assertEquals(5, crowd.getEmotionalUplift());
        }

        @Test
        @DisplayName("The emotional uplift should change after the increase")
        public void checkEmotionalUpliftAfterIncreaseTest() {
            crowd.increaseEmotionalUplift(10);
            assertEquals(15, crowd.getEmotionalUplift());
        }

        @Test
        @DisplayName("The emotional uplift should change after the reduce")
        public void checkEmotionalUpliftAfterReduceTest() {
            crowd.reduceEmotionalUplift(3);
            assertEquals(3.33, crowd.getEmotionalUplift(), 0.01);
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when increase uplift and amount is negative")
        public void checkNegativeAmountWhenIncreaseTest() {
            assertThrows(IllegalArgumentException.class, () -> crowd.increaseEmotionalUplift(-1));
        }
        @Test
        @DisplayName("Should throw IllegalArgumentException when reduce uplift and amount is negative")
        public void checkNegativeAmountWhenReduceTest() {
            assertThrows(IllegalArgumentException.class, () -> crowd.reduceEmotionalUplift(-1));
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when increase uplift and amount is greater than 100")
        public void checkMaxAmountWhenIncreaseTest() {
            assertThrows(IllegalArgumentException.class, () -> crowd.increaseEmotionalUplift(101));
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when reduce uplift and amount is greater than 100")
        public void checkMaxAmountWhenReduceTest() {
            assertThrows(IllegalArgumentException.class, () -> crowd.reduceEmotionalUplift(101));
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when increase uplift and uplift is the maximum possible")
        public void checkMaxUpliftWhenIncreaseTest() {
            crowd.setEmotionalUplift(100);
            assertThrows(IllegalArgumentException.class, () -> crowd.increaseEmotionalUplift(1));
        }
        @Test
        @DisplayName("Should throw IllegalArgumentException when reduce uplift and uplift is the minimum possible")
        public void checkMinUpliftWhenReduceTest() {
            crowd.setEmotionalUplift(0);
            assertThrows(IllegalArgumentException.class, () -> crowd.reduceEmotionalUplift(1));
        }

        @Test
        @DisplayName("Emotional uplift should become 100 when an increase in uplift leads to an increase above 100")
        public void checkMaxUpliftAfterIncreaseTest() {
            crowd.clearHuman();
            Human human = new Human("Аркадий", PersonalityType.Realist, 95, 0);
            crowd.addHuman(human);
            crowd.increaseEmotionalUplift(50);
            assertEquals(100, crowd.getEmotionalUplift());
        }

        @Test
        @DisplayName("Emotional uplift should become 0 when a decrease in uplift leads to a decrease below 0")
        public void checkMinUpliftAfterReduceTest() {
            crowd.clearHuman();
            Human human = new Human("Аркадий", PersonalityType.Realist, 5, 0);
            crowd.addHuman(human);
            crowd.reduceEmotionalUplift(50);
            assertEquals(0, crowd.getEmotionalUplift());
        }
    }
    @Nested
    public class HumanTest {
        private Human human;
        private Crowd crowd;
        @BeforeEach
        public void init() {
            human = new Human("Мария", PersonalityType.Optimist, 10, 0);
            crowd = new Crowd(new HashSet<>(Arrays.asList(
                    new Human("Иван", PersonalityType.Optimist, 10, 0),
                    new Human("Валерий", PersonalityType.Pessimist, 0, 0),
                    new Human("Аркадий", PersonalityType.Realist, 5, 0)
            )));
        }

        @Test
        @DisplayName("Check the good message to the crowd")
        public void addressGoodMessageToCrowdTest() {
            human.addressMessageToCrowd(crowd, Message.GOOD);
            assertEquals(15, crowd.getEmotionalUplift());
        }
        @Test
        @DisplayName("Check the bad massage to the crowd")
        public void addressBadMessageToCrowdTest() {
            human.addressMessageToCrowd(crowd, Message.BAD);
            assertEquals(1, crowd.getEmotionalUplift());
        }

        @Test
        @DisplayName("Check the neutral message to the crowd")
        public void addressNeutralMessageToCrowdTest() {
            human.addressMessageToCrowd(crowd, Message.NEUTRAL);
            assertEquals(5, crowd.getEmotionalUplift());
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when the crowd is empty")
        public void checkAddressMessageToEmptyCrowdTest() {
            Crowd crowd = new Crowd(new HashSet<>());
            assertThrows(IllegalArgumentException.class, () -> human.addressMessageToCrowd(crowd, Message.GOOD));
        }

        @Test
        @DisplayName("Check the good message to the crowd from the podium")
        public void addressGoodMessageToCrowdFromPodiumTest() {
            human.addressMessageToCrowdFromPodium(crowd, Message.GOOD);
            assertEquals(30.33, crowd.getEmotionalUplift(), 0.01);
        }

        @Test
        @DisplayName("Check the bad massage to the crowd from the podium")
        public void addressBadMessageToCrowdFromPodiumTest() {
            human.addressMessageToCrowdFromPodium(crowd, Message.BAD);
            assertEquals(0, crowd.getEmotionalUplift(), 0.01);
        }
        @Test
        @DisplayName("Check the neutral message to the crowd from the podium")
        public void addressNeutralMessageToCrowdFromPodiumTest() {
            human.addressMessageToCrowdFromPodium(crowd, Message.NEUTRAL);
            assertEquals(5, crowd.getEmotionalUplift());
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when the crowd is empty and human on the podium")
        public void checkAddressMessageToEmptyCrowdFromPodiumTest() {
            Crowd crowd = new Crowd(new HashSet<>());
            assertThrows(IllegalArgumentException.class, () -> human.addressMessageToCrowd(crowd, Message.GOOD));
        }
    }

    @Nested
    public class PodiumTest {
        private Podium podium;

        @BeforeEach
        public void init() {
            podium = new Podium();
            Human human = new Human("Максим", PersonalityType.Optimist, 10, 50);
            podium.setSpeaker(human);
        }

        @Test
        @DisplayName("Check the speaker if crowd approval level more than the previous speaker")
        public void checkSpeakerTest() {
            Human human = new Human("Мария", PersonalityType.Optimist, 10, 60);
            podium.setSpeaker(human);
            assertEquals(human, podium.getSpeaker());
        }
        @Test
        @DisplayName("Should throw IllegalArgumentException when the speaker is not popular enough")
        public void checkSpeakerNotPopularTest() {
            Human human = new Human("Мария", PersonalityType.Optimist, 10, 30);
            assertThrows(IllegalArgumentException.class, () -> podium.setSpeaker(human));
        }
    }
}
