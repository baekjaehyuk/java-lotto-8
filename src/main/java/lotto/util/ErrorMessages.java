package lotto.util;

public enum ErrorMessages {

    DUPLICATE_LOTTO_NUMBER("중복된 로또 번호가 존재합니다."),
    INVALID_LOTTO_NUMBER_COUNT("로또 번호는 6개여야 합니다"),
    INVALID_NUMBER_INPUT("숫자를 입력해주세요."),
    EMPTY_LOTTO_TICKETS("로또 티켓은 비어 있을 수 없습니다"),
    INVALID_PURCHASE_LOTTO("금액은 1,000원 단위여야 합니다."),
    DUPLICATE_BONUS_NUMBER("보너스 번호는 당첨 번호와 중복될 수 없습니다."),
    INVALID_LOTTO_NUMBER_RANGE("로또 번호는 1 ~ 45 사이의 숫자여야 합니다.");

    private static final String ERROR_PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return ERROR_PREFIX + message;
    }
}
