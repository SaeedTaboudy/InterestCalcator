import deposits.Deposit;
import exceptions.*;

/**
 * @author Saeed Taboudy
 */
public class Runner {
    public static void main(String args[]) {
        DepositHandler depositHandler = new DepositHandler();

        try {
            Object[] resultList = depositHandler.readXMLFile();
            for (Object obj : resultList) {
                Deposit baseData = (Deposit) obj;
                System.out.println(baseData.getCustomerNumber() + " # " + baseData.getPayedInterest());
            }
        } catch (FileFormatException e) {
            System.out.println("Error: File Format Exception");
        } catch (LessDepositAmount e) {
            System.out.println("Error: Deposit amount is zero or less....");
        } catch (InternalErrorException internalError) {
            System.out.println("Error: Internal exception..... ");
        } catch (LessDayException e) {
            System.out.println("error: duration days are less than one day....");
        } catch (DepositTypeException e) {
            System.out.println("Error: Deposite type is not correct");
        }
    }
}
