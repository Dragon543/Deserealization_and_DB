package MyPack;

import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by taras on 27.02.2016.
 */
public class Payments implements Serializable {
    int ab=1;
    private static final long serialVersionUID= -5676213486788032264L;
    public int customerNumber;

    @Override
    public String toString() {
        String checkNumber=UUID.randomUUID().toString();
        return "Payments{" +
                " checkNumber='" + checkNumber + '\'' +
                ", customerNumber=" + customerNumber +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                ", " +
                '}';
    }

    public Date paymentDate;
    public float amount;
    int a=5;
    public Payments(Date paymentDate, int customerNumber, float amount, String checkNumber) {
        this.paymentDate = paymentDate;
        this.customerNumber = customerNumber;
        this.amount = amount;
    }
}


