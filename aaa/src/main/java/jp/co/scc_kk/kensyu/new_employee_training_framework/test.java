package jp.co.scc_kk.kensyu.new_employee_training_framework;

import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class test {
    public static void main(String[] args) {
        UserDao2 dao = new UserDao2();
        // public static void serchuser(String userid,String password) {

        // UserEntity uEntity=
        // }
        final ThymeleafTemplateEngine templateEngine = new ThymeleafTemplateEngine();
        Spark.staticFileLocation("/new-employee-training_framework/src/main/resources/templates");
        Map<String, Object> model = new HashMap<>();
        // model.put("msg", "Hello World");
        Spark.get("/login", (request, response) -> new ModelAndView(model, "login"), templateEngine);

        // Spark.after((request, response) -> {

        Spark.get("/login", (request, response) -> {
            String userid = request.queryParams("userid");
            String password = request.queryParams("passwd");
            if (dao.login(userid, password)) {
                UserEntity uentity = new UserEntity();
                uentity = dao.rolecheck(userid, password);
                int role = Integer.parseInt(uentity.getRole());
                /// response.redirect("/main");


            }
            return null;

        });
        //// String password = request.queryParams("passwd");
        // if (dao.login(userid, password)) {
        // UserEntity uentity = new UserEntity();
        // uentity = dao.rolecheck(userid, password);
        // int role = Integer.parseInt(uentity.getRole());


        // }

        // });

        Spark.post("/main", (request, response) -> new ModelAndView(model, "main"), templateEngine);


        Spark.before("/emp", (request, response) -> {
            EmpDao edao = new EmpDao();
            employeeEntity entity = new employeeEntity();
            EmpArrayBean EABean = edao.outputEmp();
            model.put("EABean", EABean);
            ArrayList<employeeEntity> ea = EABean.getEmpArray();
            model.put("ea", ea);
            model.put("entity", entity);



        });

        Spark.get("/emp", (request, response) -> new ModelAndView(model, "emp"), templateEngine);


        Spark.get("/addemp", (request, response) -> new ModelAndView(model, "addemp"), templateEngine);

        Spark.post("/addempconf", (request, response) -> new ModelAndView(model, "addempconf"), templateEngine);

        // new ModelAndView(model, "login"), templateEngine);
        Spark.before("/addempconf", (request, response) ->

        {
            String syainno = request.queryParams("syainno");
            String name = request.queryParams("name");
            String department = request.queryParams("department");

            model.put("syainno", syainno);
            model.put("name", name);
            model.put("department", department);

        });
        Spark.before("/completeregister", (request, response) -> {
            String syainno = request.queryParams("syainno");
            String name = request.queryParams("name");
            String department = request.queryParams("department");
            EmpDao edao = new EmpDao();
            int no = Integer.parseInt(syainno);

            edao.EmpRegister(no, name, department);
        });
        Spark.post("/completeregister", (request, response) -> new ModelAndView(model, "completeregister"),
                templateEngine);
    }

}
