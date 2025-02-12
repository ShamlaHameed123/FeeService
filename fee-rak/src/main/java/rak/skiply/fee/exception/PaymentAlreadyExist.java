package rak.skiply.fee.exception;

public class PaymentAlreadyExist extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;
	private String message;

    public PaymentAlreadyExist() {}

    public PaymentAlreadyExist(String msg) {
        super(msg);
        this.message = msg;
    }

}
