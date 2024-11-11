package store.common.support;

import java.util.Arrays;
import java.util.Objects;
import store.common.exception.AnswerFormatException;

public enum Answer {
    Y,N;

    public static boolean isAgree(String answer) {
        return Objects.equals(answer, Answer.Y.name());
    }

    public static void validateFormat(String inputAnswer) {
        boolean correct = Arrays.stream(Answer.values())
                .anyMatch(answer -> Objects.equals(inputAnswer, answer.name()));

        if (!correct) {
            throw new AnswerFormatException();
        }
    }
}
