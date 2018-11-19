
package com.cipher;

import javafx.beans.property.SimpleStringProperty;

public class Item {
      SimpleStringProperty name;
      SimpleStringProperty surname;

    public Item(String name, String surname) {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty (surname);
    }

    public String getName() {
        return name.get();
    }
    public void ChangeString(String s ) {
        this.surname=new SimpleStringProperty(s);
    }

    public String getSurname() {
        return surname.get();
    }
}
