package com.cherry.stack;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

/**
 * 不一定都是我们写的代码出现递归，下面是对象循环引用会导致stackOverflow
 */
public class JsonStackOverflowDemo {
    public static void main(String[] args) throws JsonProcessingException {
        Dept dept = new Dept();
        dept.setName("部门");

        Emp emp = new Emp();
        emp.setName("zhangsan");
        emp.setDept(dept);

        Emp emp1 = new Emp();
        emp1.setName("lisi");
        emp1.setDept(dept);

        dept.setEmps(Arrays.asList(emp,emp1));

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(dept));
    }
}


class Dept{
    private String name;
    private List<Emp> emps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Emp> getEmps() {
        return emps;
    }

    public void setEmps(List<Emp> emps) {
        this.emps = emps;
    }
}

class Emp{
    private String name;
    @JsonIgnore
    private Dept dept;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}