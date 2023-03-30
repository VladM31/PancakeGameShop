package nure.pancake.game.shop.authorizationservice.dataobjects.telegram;

public enum TelegramSendStatus {
    SENT, DELAY_SENT, ERROR;

    public boolean isError() {
        return this == ERROR;
    }
}
