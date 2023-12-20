package com.example.user_authentification_firebase;

public class UserRestData {
    String Name,DataOfBirthDay;

    public UserRestData(String name, String dataOfBirthDay) {
        Name = name;
        DataOfBirthDay = dataOfBirthDay;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDataOfBirthDay() {
        return DataOfBirthDay;
    }

    public void setDataOfBirthDay(String dataOfBirthDay) {
        DataOfBirthDay = dataOfBirthDay;
    }
}
