package com.lankaice.project.bo;

import com.lankaice.project.bo.custom.impl.CustomerBOImpl;

public class BOFactoryImpl implements  BOFactory {
    private static BOFactoryImpl boFactory;

    private BOFactoryImpl() {}

    public static BOFactory getInstance(){
        if (boFactory==null){
            boFactory=new BOFactoryImpl();
        }
        return boFactory;
    }

    @SuppressWarnings("unchecked")
    public <T extends SuperBO> T getBO(BOType boType){
      return switch (boType){
          case CUSTOMER -> (T) new CustomerBOImpl();
      };
    }
}
