package deposits;

import java.math.BigDecimal;

/**
 * @author Saeed Taboudy
 */
public class LongTerm extends DepositType {
    public static final BigDecimal INTESREST_RATE = BigDecimal.valueOf(20);

    @Override
    public BigDecimal getInterestRate() {
        return INTESREST_RATE;
    }
}
