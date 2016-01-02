package deposits;

/**
 * @author Saeed Taboudy
 */

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Deposit implements Comparable {

    private String customerNumber;
    private int duration;
    private BigDecimal depositBalance;
    private BigDecimal payedInterest;
    private DepositType depositType;


    public Deposit() {
    }

    protected Deposit(String costumerNumber, BigDecimal depositBalance, int duration) {
        this.customerNumber = costumerNumber;
        this.depositBalance = depositBalance;
        this.duration = duration;
    }

    public static Collection<Object> sort(List<Object> objectList) {
        Map<BigDecimal, Object> map = new TreeMap<BigDecimal, Object>();
        for (Object obj : objectList) {
            Deposit baseData = (Deposit) obj;
            map.put(baseData.getPayedInterest(), obj);
        }
        return map.values();
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public BigDecimal getDepositBalance() {
        return depositBalance;
    }

    public void setDepositBalance(BigDecimal depositBalance) {
        this.depositBalance = depositBalance;
    }

    public BigDecimal getPayedInterest() {
        return payedInterest;
    }

    public void setPayedInterest(BigDecimal payedInterest) {
        this.payedInterest = payedInterest;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }



    @Override
    public int compareTo(Object obj) {
        Deposit baseData = (Deposit) obj;
        if (baseData.getPayedInterest().compareTo(this.getPayedInterest()) > 0) {
            return 1;
        } else return -1;
    }
}
