package com.example.demo.logger;

public class PaymentService {

    private static final Logger log =
            LoggerFactory.getLogger(PaymentService.class);

    public void processPayment() {

        log.info("Processing payment");

        try {
            // process payment

        } catch (Exception e) {

            log.error(
                    "Payment processing failed",
                    e
            );
        }
    }
    
}
