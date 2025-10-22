package com.mycompany.eadproject.controller;

import com.mycompany.eadproject.view.HomePanel;

public class HomePanelController {

    HomePanel homePanel;
    public HomePanelController(HomePanel homePanel){
        this.homePanel = homePanel;
    }

    public void generateStockReport(){
        System.out.println("Generating Stock Report...");
    }
}