package Pojo;

import java.util.List;

public class CustomerDetails {

    private int maxResults;
    private int counter;


    private customerParameters customerParameters;

    public List<Pojo.dealerParameters> getDealerParameters() {
        return dealerParameters;
    }

    public void setDealerParameters(List<Pojo.dealerParameters> dealerParameters) {
        this.dealerParameters = dealerParameters;
    }

    private List<dealerParameters> dealerParameters;


    public Pojo.customerParameters getCustomerParameters() {
        return customerParameters;
    }

    public void setCustomerParameters(Pojo.customerParameters customerParameters) {
        this.customerParameters = customerParameters;
    }







    public int getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }






}
