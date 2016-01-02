package deposits;

import java.math.BigDecimal;

/**
 * @author SaeedTaboudy
 */
public class Qarz extends DepositType {
    public  static final BigDecimal INTESREST_RATE = BigDecimal.ZERO;

    @Override
    public BigDecimal getInterestRate() {
        return INTESREST_RATE;
    }
}
