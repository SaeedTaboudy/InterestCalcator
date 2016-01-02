package deposits;

import java.math.BigDecimal;

/**
 * @author Maral Khojasteh
 */
public class ShortTerm extends DepositType {
    public static final BigDecimal INTESREST_RATE = BigDecimal.valueOf(10);

    @Override
    public BigDecimal getInterestRate() {
        return INTESREST_RATE;
    }
}
