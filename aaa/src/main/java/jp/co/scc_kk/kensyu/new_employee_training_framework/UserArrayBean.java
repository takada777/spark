package jp.co.scc_kk.kensyu.new_employee_training_framework;

import java.util.ArrayList;

public class UserArrayBean {
    private ArrayList<UserEntity> userArray;

    public UserArrayBean() {
        userArray = new ArrayList<UserEntity>();

        // TODO Auto-generated constructor stub
    }

    public void addEmpArray(UserEntity uEntity) {
        userArray.add(uEntity);
    }

    public int getArraysize() {
        return userArray.size();
    }

    public ArrayList<UserEntity> getUserArray() {
        return userArray;

    }

    public void setUserArray(ArrayList<UserEntity> userArray) {
        this.userArray = userArray;
    }
}


