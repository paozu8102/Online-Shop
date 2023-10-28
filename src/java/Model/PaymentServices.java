/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import com.paypal.api.payments.Payer;
import java.util.*;
 
import com.paypal.api.payments.*;
import com.paypal.base.rest.*;
public class PaymentServices {
    private static final String CLIENT_ID = "AV7LmUldlsdqpSWgHyWSJHiW-fZFHJoEys_YXJqbmSefiuT4XL9Qb25FI-UokdBzLsRPXSD0f0FpOR7-";
    private static final String CLIENT_SECRET = "EK71O3QyLsxGj9KuhBlTv53ZRiWFUaOE6MPztEAdX5azQyeY5xE_Fun2fend9M1GDyW1c63KsRegEEw4";
    private static final String MODE = "sandbox";
 
    public String authorizePayment(ProOrder proorder)        
            throws PayPalRESTException {       
 
        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(proorder);
         
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");
 
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
 
        Payment approvedPayment = requestPayment.create(apiContext);
 
        return getApprovalLink(approvedPayment);
 
    }
     
    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("William")
                .setLastName("Peterson")
                .setEmail("william.peterson@company,com");
        payer.setPayerInfo(payerInfo);
        return payer;
    }
     
    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost/cart");
        redirectUrls.setReturnUrl("http://localhost/checkout");
        return redirectUrls;
    }
     
    private List<Transaction> getTransactionInformation(ProOrder proorder) {
        Details details = new Details();
        details.setShipping("0");
        details.setTax("0");
        
        Amount amount = new Amount();
        amount.setCurrency("USD");
        String totalMoney= Double.toString(proorder.getPrice());
        amount.setTotal(totalMoney);
        amount.setDetails(details);
        
        Transaction transaction = new Transaction();
        transaction.setDescription(proorder.getProductname());
        
        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<Item>();
        
        Item item = new Item();
        item.setCurrency("USD")
        .setName(proorder.getProductname())
                .setPrice(totalMoney)
        .setTax("0")
                .setQuantity("1");
        
        items.add(item);
        itemList.setItems(items);
        transaction.setItemList(itemList);
        List<Transaction> listTransaction = new ArrayList<Transaction>();
        listTransaction.add(transaction);
        return listTransaction;
        
    }
     
    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvedLink = null;
        for(Links link : links){
            if(link.getRel().equalsIgnoreCase("approval_url")) {
                approvedLink = link.getHref();
            }
        }
           return approvedLink;
    }

   
 
}
