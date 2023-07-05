package com.example.medipharm.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.medipharm.Domain.DrugDomain;
import com.example.medipharm.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }
    public void insertDrug(DrugDomain item){
        ArrayList<DrugDomain> listDrug= getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i= 0; i < listDrug.size(); i++) {
            if(listDrug.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n=i;
                break;
            }
        }

        if(existAlready){
            listDrug.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listDrug.add(item);
        }
        tinyDB.putListObject("CardList",listDrug);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<DrugDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberDrug(ArrayList<DrugDomain>listDrug, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listDrug.get(position).setNumberInCart(listDrug.get(position).getNumberInCart()+1);
        tinyDB.putListObject("Cartlist",listDrug);
        changeNumberItemsListener.changed();
    }
    public void minusNumberDrug(ArrayList<DrugDomain>listdrug, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if(listdrug.get(position).getNumberInCart()==1){
            listdrug.remove(position);
        }else {
            listdrug.get(position).setNumberInCart(listdrug.get(position).getNumberInCart()-1);
        }
        tinyDB.putListObject("Cartlist",listdrug);
        changeNumberItemsListener.changed();
    }
    public Double getTotalFee(){
        ArrayList<DrugDomain> listfood = getListCart();
        double fee = 0;
        for (int i = 0; i < listfood.size(); i++) {
            fee = fee + (listfood.get(i).getFee()*listfood.get(i).getNumberInCart());
        }
        return fee;
    }
}
