

package jp.co.scc_kk.kensyu.new_employee_training_framework;

import spark.ModelAndView;
import spark.Spark;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class Application {
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

        Spark.post("/login", (request, response) -> new ModelAndView(model, "login"), templateEngine);
        //// String password = request.queryParams("passwd");
        // if (dao.login(userid, password)) {
        // UserEntity uentity = new UserEntity();
        // uentity = dao.rolecheck(userid, password);
        // int role = Integer.parseInt(uentity.getRole());


        // }

        // });
        Spark.after("/login", (request, response) -> {
            String userid = request.queryParams("userid");
            String password = request.queryParams("passwd");
            if (dao.login(userid, password)) {
                UserEntity uentity = new UserEntity();
                uentity = dao.rolecheck(userid, password);
                int role = Integer.parseInt(uentity.getRole());
                if (role == 1) {
                    request.session().attribute("role", role);
                    request.session().attribute("username", uentity.getUsername());
                    response.redirect("/AdminMain");
                } else if (role == 2) {
                    request.session().attribute("role", role);

                    request.session().attribute("username", uentity.getUsername());
                    response.redirect("/main");
                } else {
                    response.redirect("/login");
                }
            }
        });

        Spark.before("/main", (request, response) -> {


            String user = request.session().attribute("username");

            model.put("user", user);
        });

        Spark.post("/main", (request, response) -> new ModelAndView(model, "main"), templateEngine);
        Spark.get("/main", (request, response) -> new ModelAndView(model, "main"), templateEngine);

        Spark.before("/emp", (request, response) -> {
            String syainno = request.queryParams("syainno");
            // System.out.println(syainno);

            if (syainno == null) {
                EmpDao edao = new EmpDao();
                employeeEntity entity = new employeeEntity();
                EmpArrayBean EABean = edao.outputEmp();

                model.put("EABean", EABean);
                ArrayList<employeeEntity> ea = EABean.getEmpArray();
                // for (employeeEntity eBean : ea) {
                model.put("ea", ea);
                // model.put("entity", eBean);
            } else {

                int no = Integer.parseInt(syainno);
                EmpDao edao = new EmpDao();
                EmpArrayBean EABean = edao.SerchEmp(no);
                model.put("EABean", EABean);
                ArrayList<employeeEntity> ea = EABean.getEmpArray();
                // for (employeeEntity eBean : ea) {
                model.put("ea", ea);

            }
            // } System.out.println("null");



        });

        Spark.get("/emp", (request, response) -> new ModelAndView(model, "emp"), templateEngine);

        Spark.post("/emp", (request, response) -> new ModelAndView(model, "emp"), templateEngine);
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

        Spark.before("/changeemp", (request, response) -> {
            String syainno = request.queryParams("syainno");
            String name = request.queryParams("name");
            String department = request.queryParams("department");
            String beforesyainno = syainno;
            model.put("beforesyainno", beforesyainno);
            model.put("syainno", syainno);
            model.put("name", name);
            model.put("department", department);
        });

        Spark.post("/changeemp", (request, response) -> new ModelAndView(model, "changeemp"), templateEngine);

        Spark.before("/changeempconf", (request, response) -> {
            String syainno = request.queryParams("syainno");
            String name = request.queryParams("name");
            String department = request.queryParams("department");
            String beforesyainno = request.queryParams("beforesyainno");
            model.put("syainno", syainno);
            model.put("name", name);
            model.put("department", department);
            model.put("beforesyainno", beforesyainno);
        });
        Spark.post("/changeempconf", (request, response) -> new ModelAndView(model, "changeempconf"), templateEngine);

        Spark.before("/completechange", (request, response) -> {
            String syainno = request.queryParams("syainno");
            String name = request.queryParams("name");
            String department = request.queryParams("department");
            String beforesyainno = request.queryParams("beforesyainno");
            EmpDao edao = new EmpDao();
            int no = Integer.parseInt(syainno);
            int beforeno = Integer.parseInt(beforesyainno);



            edao.Update(no, name, department, beforeno);
        });
        Spark.post("/completechange", (request, response) -> new ModelAndView(model, "completechange"), templateEngine);

        Spark.before("/deleteemp", (request, response) -> {
            String syainno = request.queryParams("syainno");
            String name = request.queryParams("name");
            String department = request.queryParams("department");


            model.put("syainno", syainno);
            model.put("name", name);
            model.put("department", department);
        });

        Spark.post("/deleteemp", (request, response) -> new ModelAndView(model, "deleteemp"), templateEngine);

        Spark.before("/completedelete", (request, response) -> {
            String syainno = request.queryParams("syainno");
            String name = request.queryParams("name");
            String department = request.queryParams("department");

            EmpDao edao = new EmpDao();
            int no = Integer.parseInt(syainno);



            edao.empdelete(no);
        });
        Spark.post("/completedelete", (request, response) -> new ModelAndView(model, "completedelete"), templateEngine);

        Spark.get("/addCSV", (request, response) -> new ModelAndView(model, "aaCSV"), templateEngine);



        Spark.post("/AdminMain", (request, response) -> new ModelAndView(model, "AdminMain"), templateEngine);
        Spark.get("/AdminMain", (request, response) -> new ModelAndView(model, "AdminMain"), templateEngine);

        Spark.before("/user", (request, response) -> {


            String userid = request.queryParams("userid");

            if (userid == null || userid == "") {

                UserDao2 udao = new UserDao2();
                UserEntity entity = new UserEntity();
                UserArrayBean UABean = udao.outputUser();

                model.put("UABean", UABean);
                ArrayList<UserEntity> ua = UABean.getUserArray();
                // for (employeeEntity eBean : ea) {
                model.put("ua", ua);
                // model.put("entity", eBean);
            } else {
                UserDao2 udao = new UserDao2();
                UserArrayBean UABean = udao.SerchUser(userid);
                model.put("UABean", UABean);
                ArrayList<UserEntity> ua = UABean.getUserArray();
                // for (employeeEntity eBean : ea) {
                model.put("ua", ua);


            }

            // }



        });
        Spark.get("/user", (request, response) -> new ModelAndView(model, "user"), templateEngine);

        Spark.post("/user", (request, response) -> new ModelAndView(model, "user"), templateEngine);

        Spark.before("/deleteuser", (request, response) -> {
            String userid = request.queryParams("userid");
            String name = request.queryParams("name");
            String role = request.queryParams("role");



            model.put("userid", userid);
            model.put("name", name);
            model.put("role", role);
        });

        Spark.post("/deleteuser", (request, response) -> new ModelAndView(model, "deleteuser"), templateEngine);

        Spark.before("/completeuserdelete", (request, response) -> {
            String userid = request.queryParams("userid");
            String name = request.queryParams("name");
            String role = request.queryParams("role");

            UserDao2 udao = new UserDao2();



            udao.userdelete(userid);
        });
        Spark.post("/completeuserdelete", (request, response) -> new ModelAndView(model, "completeuserdelete"),
                templateEngine);



        Spark.get("/adduser", (request, response) -> new ModelAndView(model, "adduser"), templateEngine);

        Spark.post("/adduserconf", (request, response) -> new ModelAndView(model, "adduserconf"), templateEngine);

        // new ModelAndView(model, "login"), templateEngine);
        Spark.before("/adduserconf", (request, response) ->

        {
            String userid = request.queryParams("userid");
            String password = request.queryParams("password");
            String password2 = request.queryParams("password2");
            String name = request.queryParams("name");
            String role = request.queryParams("yakuwari");

            model.put("userid", userid);
            model.put("name", name);
            model.put("role", role);
            model.put("password", password);

        });
        Spark.before("/completeuserregister", (request, response) -> {
            String userid = request.queryParams("userid");
            String password = request.queryParams("password");

            String name = request.queryParams("name");
            String role = request.queryParams("role");
            UserDao2 udao = new UserDao2();


            udao.UserRegister(role, name, userid, password);
        });
        Spark.post("/completeuserregister", (request, response) -> new ModelAndView(model, "completeuserregister"),
                templateEngine);



        Spark.before("/changeuser", (request, response) -> {
            String userid = request.queryParams("userid");
            String password = request.queryParams("password");

            String name = request.queryParams("name");
            String role = request.queryParams("role");
            String beforeuserid = userid;
            model.put("userid", userid);
            model.put("beforeuserid", beforeuserid);
            model.put("name", name);
            model.put("role", role);
            model.put("password", password);
        });

        Spark.post("/changeuser", (request, response) -> new ModelAndView(model, "changeuser"), templateEngine);

        Spark.before("/changeuserconf", (request, response) -> {
            String userid = request.queryParams("userid");
            String password = request.queryParams("password");

            String name = request.queryParams("name");
            String role = request.queryParams("role");
            String beforeuserid = request.queryParams("beforeuserid");
            model.put("userid", userid);
            model.put("beforeuserid", beforeuserid);
            model.put("name", name);
            model.put("role", role);
            model.put("password", password);
        });
        Spark.post("/changeuserconf", (request, response) -> new ModelAndView(model, "changeuserconf"), templateEngine);

        Spark.before("/completeuserchange", (request, response) -> {
            String userid = request.queryParams("userid");
            String password = request.queryParams("password");

            String name = request.queryParams("name");
            String role = request.queryParams("role");
            String beforeuserid = request.queryParams("beforeuserid");
            UserDao2 udao = new UserDao2();

            udao.UpdateUser(role, name, userid, password, beforeuserid);

        });
        Spark.post("/completeuserchange", (request, response) -> new ModelAndView(model, "completeuserchange"),
                templateEngine);


        Spark.before("/AdminMain", (request, response) -> {
            String user = request.session().attribute("username");

            model.put("user", user);
        });
    }


}


