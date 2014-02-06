package com.bo.acredito.domain;

/**
 * Created by asejas on 2/1/14.
 */
public enum IdTypeEnum {
    ID,PASSPORT,MILITAR;

    @Override
    public String toString() {
        String label="";
        if(this.equals(ID)){
            label="Carnet de identidad";
        }
        else if(this.equals(PASSPORT)){
            label="Pasaporte";
        }
        else if(this.equals(MILITAR)){
            label="Libreta militar";
        }
        return label;
    }
}