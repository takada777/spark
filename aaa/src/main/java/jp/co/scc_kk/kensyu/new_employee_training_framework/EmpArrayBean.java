package jp.co.scc_kk.kensyu.new_employee_training_framework;

import java.util.ArrayList;


public class EmpArrayBean {
    private ArrayList<employeeEntity> empArray;

    public EmpArrayBean() {
        empArray = new ArrayList<employeeEntity>();

        // TODO Auto-generated constructor stub
    }

    public void addEmpArray(employeeEntity eEntity) {
        empArray.add(eEntity);
    }

    public int getArraysize() {
        return empArray.size();
    }

    public ArrayList<employeeEntity> getEmpArray() {
        return empArray;

    }

    public void setEmpArray(ArrayList<employeeEntity> empArray) {
        this.empArray = empArray;
    }
}

