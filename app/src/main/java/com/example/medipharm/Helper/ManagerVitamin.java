package com.example.medipharm.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.medipharm.Domain.VitaminsDomain;
import com.example.medipharm.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagerVitamin {
    private static Context context;
    private VitaminDB vitaminDB;
    private ArrayList<VitaminsDomain> listVit;

    public ManagerVitamin(Context context) {
        this.context = context;
        this.vitaminDB = new VitaminDB(context);
    }

    public static void insertVit(VitaminsDomain item) {
        ArrayList<VitaminsDomain> listVit = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listVit.size(); i++) {
            if (listVit.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if (existAlready) {
            listVit.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listVit.add(item);
        }
        VitaminDB.putListObject("CardList", listVit);
        Toast.makeText(context, "Added To Your Cart", Toast.LENGTH_SHORT).show();
    }

    public static ArrayList<VitaminsDomain> getListCart() {
        return VitaminDB.getListObject("CartList");
    }

    public void plusNumberDrug(ArrayList<VitaminsDomain> listDrug, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        listDrug.get(position).setNumberInCart(listDrug.get(position).getNumberInCart() + 1);
        VitaminDB.putListObject("Cartlist", listVit);
        changeNumberItemsListener.changed();
    }

    public void minusNumberDrug(ArrayList<VitaminsDomain> listdrug, int position, ChangeNumberItemsListener changeNumberItemsListener) {
        if (listdrug.get(position).getNumberInCart() == 1) {
            listdrug.remove(position);
        } else {
            listdrug.get(position).setNumberInCart(listdrug.get(position).getNumberInCart() - 1);
        }
        VitaminDB.putListObject("Cartlist", listVit);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee() {
        ArrayList<VitaminsDomain> listfood = getListCart();
        double fee = 0;
        for (int i = 0; i < listfood.size(); i++) {
            fee = fee + (listfood.get(i).getFee() * listfood.get(i).getNumberInCart());
        }
        return fee;
    }
}

