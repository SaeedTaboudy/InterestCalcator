/**
 * @author Saeed Taboudy
 */

import deposits.Deposit;
import deposits.DepositType;
import exceptions.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DepositHandler {
    private ArrayList<Object> depositBaseData = new ArrayList<Object>();

    public Object[] readXMLFile() throws FileFormatException, LessDepositAmount, InternalErrorException, LessDayException, DepositTypeException {
        Map<BigDecimal, Object> sortMap = new HashMap<BigDecimal, Object>();

            /*
            Xml File Reading
             */
        File inputFile = new File("src/deposits.xml");
        DocumentBuilderFactory dbFactory
                = DocumentBuilderFactory.newInstance();
        Document doc;
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputFile);
        } catch (ParserConfigurationException | IOException | org.xml.sax.SAXException e) {
            throw new FileFormatException();
        }


        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("deposit");
        //Loading Xml File Tags and values
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            Element eElement = (Element) nNode;
            //Loading xml Elements
            String customerNum = eElement.getElementsByTagName("customerNumber").item(0).getTextContent();
            BigDecimal depositBalance = new BigDecimal(eElement.getElementsByTagName("depositBalance")
                    .item(0).getTextContent());
            if (depositBalance.longValue() < 0) throw new LessDepositAmount();
            int duration = Integer.parseInt(eElement.getElementsByTagName("durationInDays").item(0).getTextContent());
            if (duration <= 0) {
                throw new LessDayException();
            }
            String depType = eElement.getElementsByTagName("depositType").item(0).getTextContent();
                /*
                 prepare for reflection and method invoking...
                 */
            DepositType reflectObject = null;
            try {
                Class reflectClass = Class.forName("deposits." + depType);
                reflectObject = (DepositType) reflectClass.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException exception) {
                throw new DepositTypeException();
            }
            Deposit deposit = new Deposit();
            deposit.setCustomerNumber(customerNum);
            deposit.setDepositType(reflectObject);
            deposit.setDepositBalance(depositBalance);
            deposit.setDuration(duration);
            deposit.setPayedInterest(deposit.getDepositType().interestCalculator(deposit, depType));
            depositBaseData.add(deposit);
        }

        //Sort Array
        Object[] resultList = depositBaseData.toArray();
        Arrays.sort(resultList);
        return resultList;
    }


}

