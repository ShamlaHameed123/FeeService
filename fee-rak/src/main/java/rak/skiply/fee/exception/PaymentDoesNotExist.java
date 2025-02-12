package rak.skiply.fee.exception;

public class PaymentDoesNotExist extends RuntimeException {
	
    private static final long serialVersionUID = 1L;
	private String message;

    public PaymentDoesNotExist() {}

    public PaymentDoesNotExist(String msg) {
        super(msg);
        this.message = msg;
    }
}
