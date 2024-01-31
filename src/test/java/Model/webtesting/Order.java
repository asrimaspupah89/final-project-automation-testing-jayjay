package Model.webtesting;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<ItemProduct> dataOrdered = new ArrayList<ItemProduct>();
    private int totalPrice;

    public List<ItemProduct> getDataOrdered() {
        return dataOrdered;
    }

    public void setDataOrdered(List<ItemProduct> dataOrdered) {
        this.dataOrdered = dataOrdered;
    }

    public void addItemProductOrder (ItemProduct product){
        dataOrdered.add(product);
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void calculateTotalPrice(){
        totalPrice = 0;
        for(int idx=0; idx<dataOrdered.size(); idx++){
            totalPrice = totalPrice + dataOrdered.get(idx).getPrice();
        }
    }
}
