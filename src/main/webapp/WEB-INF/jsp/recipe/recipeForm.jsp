<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:import url="../shared/header.jsp">
    <c:param name="pageName" value="recipeForm"></c:param>
</c:import>
<html>
    <script>

            var i = 0;
            var skl = 0;

            function myFunction() {
                i =document.getElementById("krk").childElementCount ;
                skl = document.getElementById("skl").childElementCount ;
            }

            function dodajKrok() {
                myFunction();
                $('#krk').append("<div id=\"krk"+i+"\">\n" +
                    "                                <div class=\"col-xs-1\">\n" +
                    "                                    <h3>"+ i +"</h3>\n" +
                    "                                </div>\n" +
                    "                                <div class=\"col-xs-11\">\n" +
                    "                                    <label for=\"name\">Opis:</label>\n" +
                    "                                    <input id=\"listOfSteps"+(i-1)+".content\" name=\"listOfSteps["+(i-1)+"].content\" class=\"form-control\" type=\"text\" value=\"\"required=\"required\"/>\n" +
                    "                                    \n" +
                    "                                </div>\n" +
                    "                            </div>");

            };

            function dodajSkladnik() {
                myFunction();
                $('#skl').append("<div id=\"skl" + skl + "\">" +
                    "                    <div class=\"col-xs-1\">" +
                "                           <h3>"+skl+"</h3>" +
                "                       </div>" +
                "                       <div class=\"col-xs-3\">" +
                "                           <label for=\"name\">Nazwa składnika:</label>" +
                "                           <input id=\"ingredientList"+(skl-1)+".name\" name=\"ingredientList["+(skl-1)+"].name\" class=\"form-control\" type=\"text\" value=\"\" required=\"required\"/>" +
                "                       </div>" +
                    "                   <div class=\"col-xs-4\"> "+
                "                           <label for=\"name\">Miara:</label>" +
                "                           <input id=\"ingredientList"+(skl-1)+".measure\" name=\"ingredientList["+(skl-1)+"].measure\" class=\"form-control\" type=\"text\" value=\"\"required=\"required\"/>" +
                "                       </div>" +

                "                       <div class=\"col-xs-4\">" +
                "                           <label for=\"name\">Ilość:</label>" +
                "                           <input id=\"ingredientList"+(skl-1)+".quantity\" name=\"ingredientList["+(skl-1)+"].quantity\" class=\"form-control\" type=\"text\" value=\"0\"required=\"required\"/>" +
                "                       </div> " +
                "                  </div>");


            };
            function usunSkladnik()
            {
                myFunction();
                var select = document.getElementById("skl"+(skl-1));
                select.parentNode.removeChild(select);
            }
            function usunKrok()
            {
                myFunction();
                var select = document.getElementById("krk"+(i-1));
                select.parentNode.removeChild(select);
            }


    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dodaj nowy przepis</title>
    </head>
    <body>
        <div id="main" class="container" style="background-color: #f1df8b">


            <form:form modelAttribute="recipe">
                <div class="form-group">
                    <label for="name">Tytuł przpisu:</label>
                    <form:input path="name" cssClass="form-control" cssErrorClass="form-control is-invalid" required="required"/>
                    <form:errors path="name" cssClass="error text-danger" element="div"/>
                </div>

                <div class="form-group">
                    <label for="category.id">Typ Przepisu:</label>
                    <form:select path="category.id" cssClass="form-control" cssErrorClass="form-control is-invalid" required="required">
                        <form:option value="0" >--wybierz typ przpisu--</form:option>
                        <form:options items="${categories}" itemLabel="name" itemValue="id"/>
                    </form:select>
                    <form:errors path="category.id" cssClass="error text-danger" element="div"/>
                </div>
                <div class="form-group">
                    <label for="levelOfDificulty.id">Typ Przepisu:</label>
                    <form:select path="levelOfDificulty.id" cssClass="form-control" cssErrorClass="form-control is-invalid" required="required">
                        <form:option value="0">--Trudność wykonania--</form:option>
                        <form:options items="${levelsOfDificulty}" itemLabel="name" itemValue="id"/>
                    </form:select>
                    <form:errors path="levelOfDificulty.id" cssClass="error text-danger" element="div"/>
                </div>

                <div class="form-group hidden" style="visibility: hidden">

                    <form:input path="punkty" cssClass="form-control" cssErrorClass="form-control is-invalid" required="required"/>

                </div>

                <div class="form-group" id="skl">
                        <label>Składniki</label>
                    <c:forEach items="${recipe.ingredientList}}" var="ingredient" varStatus="i">


                        <div id="skl${i.index+1}">
                            <div class="col-xs-1">
                                <h3>${i.index+1}</h3>
                            </div>
                            <div class="col-xs-3">
                                <label for="name">Nazwa składnika:</label>
                                <form:input path="ingredientList[${i.index}].name"  cssClass="form-control" cssErrorClass="form-control is-invalid" required="required"/>
                                <form:errors path="ingredientList[${i.index}].name" cssClass="error text-danger" element="div"/>
                            </div>
                            <div class="col-xs-4">
                                <label for="name">Miara:</label>
                                <form:input path="ingredientList[${i.index}].measure" cssClass="form-control" cssErrorClass="form-control is-invalid" required="required"/>
                                <form:errors path="ingredientList[${i.index}].measure" cssClass="error text-danger" element="div"/>
                            </div>
                            <div class="col-xs-4">
                                <label for="name">Ilość:</label>
                                <form:input path="ingredientList[${i.index}].quantity"  cssClass="form-control" cssErrorClass="form-control is-invalid" required="required"/>
                                <form:errors path="ingredientList[${i.index}].quantity" cssClass="error text-danger" element="div" />
                            </div>

                        </div>


                    </c:forEach>




                </div>

                <button type="button" class="btn-success" onclick="dodajSkladnik()">Dodaj składnik</button>
                <button type="button" class="btn-danger" onclick="usunSkladnik()">Usuń składnik</button>

                <br><br>
                <div class="form-group" id="krk">
                    <label>Lista etapów</label>
                    <c:forEach items="${recipe.listOfSteps}}"  varStatus="i">

                            <div id="krk${i.index+1}" >
                                <div class="col-xs-1">
                                    <h3>${i.index+1}</h3>
                                </div>
                                <div class="col-xs-11 ">
                                    <label for="name">Opis</label>
                                    <form:input path="listOfSteps[${i.index}].content" rows="50" cssClass="form-control" cssErrorClass="form-control is-invalid" required="required"/>
                                    <form:errors path="listOfSteps[${i.index}].content" cssClass="error text-danger" element="div"/>
                                </div>

                            </div>

                    </c:forEach>
                </div>
                <button type="button" class="btn-success" onclick="dodajKrok()">Dodaj krok</button>
                <button type="button" class="btn-danger" onclick="usunKrok()">Usuń krok</button>
                <br>

                <input type="file" name="plik" />


                <div class="form-group">
                    <button type="submit" class="btn btn-success" class="btn btn-success">Wyślij</button>
                </div>


            </form:form>



        </div>
    </body>
</html>
<c:import url="../shared/footer.jsp"/>