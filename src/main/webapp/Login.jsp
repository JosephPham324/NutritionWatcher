<%-- 
    Document   : Login
    Created on : Oct 4, 2022, 10:11:09 PM
    Author     : Thinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login form</h1>
        <form action="login" method="post">
             <div class="page-header align-items-start min-vh-100" style="background-image: url('image/Food.jpg');" loading="lazy">
                 <span class="mask bg-gradient-dark opacity-6"></span>
                 <div class="container my-auto">
            <div class="row">
                    <div class="col-lg-4 col-md-8 col-12 mx-auto">
                        <div class="card z-index-0 fadeIn3 fadeInBottom">
                            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                                <div class="bg-gradient-primary shadow-primary border-radius-lg py-3 pe-1">
                                    <h4 class="text-black font-weight-bolder text-center mt-2 mb-0">Welcome to food-tracker</h4>
                                </div>
                            </div>
                            <div class="card-body">
                                <form action="login" method="POST" class="text-start">
                                    <div class="input-group input-group-outline my-3">
                                        <label class="form-label">Username</label>
                                        <input type="text" required name="username" class="form-control" required>
                                    </div>
                                    <div class="input-group input-group-outline mb-3">
                                        <label class="form-label">Password</label>
                                        <input type="password" required name="password" class="form-control"required>
                                    </div>
                                    <div class="text-center">
                                        <small class="font-weight-bold" style="color: red;">Error</small>
                                    </div>
                                    <div class="text-center">
                                        <input type="submit" name="action" value="login" class="btn bg-gradient-primary w-100 my-4 mb-2">
                                    </div>
                                    <p class="mt-4 text-sm text-center">
                                        Don't have an account?
                                        <a href="RegisterAccount.jsp" class="text-primary text-gradient font-weight-bold">Sign up</a>
                                    </p>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                 </div>
             </div>
        </form>
    </body>
</html>
