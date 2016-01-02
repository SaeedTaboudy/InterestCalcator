package deposits;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author SaeedTaboudy
 */
public abstract class DepositType {
    public abstract BigDecimal getInterestRate();

    public BigDecimal interestCalculator(Deposit deposit, String depType) {
        BigDecimal interestRate = deposit.getDepositType().getInterestRate();
        BigDecimal piTemp = interestRate.multiply(BigDecimal.valueOf(deposit.getDuration()));
        BigDecimal db = deposit.getDepositBalance().multiply((piTemp));
        BigDecimal pi = db.divide(BigDecimal.valueOf(365000), 2, RoundingMode.HALF_DOWN);
        return pi;
    }
}
