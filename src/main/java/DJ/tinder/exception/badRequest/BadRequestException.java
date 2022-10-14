package DJ.tinder.exception.badRequest;

import DJ.tinder.exception.ErrorMessage;

public class BadRequestException extends RuntimeException {

    private ErrorMessage errorMessage;

    public BadRequestException(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

}
