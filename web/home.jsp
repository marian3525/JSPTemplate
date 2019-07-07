<%@ page import="repository.DBRepo" %>
<%@ page import="model.SiteUser" %><%--
  Created by IntelliJ IDEA.
  User: marian
  Date: 6/26/19
  Time: 3:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <script src="jquery-3.4.0.min.js"></script>
    <script src="home.js"></script>
    <script src="bootstrap.js"></script>
    <link rel="stylesheet" type="text/css" href="bootstrap.css">
</head>
<body>

    <div id="data">data</div>
    <%
        String username = (String) session.getAttribute("username");
    %>
    Username:<%=username%><br>
    <%
        String password = ((SiteUser) new DBRepo().getUsers().stream().filter(user -> user.getUsername().equals(username)).toArray()[0]).getPassword();
    %>
    Pass:<%=password%>

    <!-- modal to update an entity -->
    <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">

                    <h5 class="modal-title">Update details</h5>

                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <div class="modal-body">

                    <div class="form-group">
                        <label for="header1-id">Header1-ID</label>
                        <input class="form-control" id="header1-id" type="text" placeholder="placeholder" readonly #header1-id>
                    </div>

                    <div class="form-group">
                        <label for="header2">Header2</label>
                        <input type="text" class="form-control" id="header2" value="name" #header2>
                    </div>

                    <div class="form-group">
                        <label for="header3">Header3</label>
                        <input type="text" class="form-control" id="header3" value="other_name" #header3>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" id="update-confirm-button" class="btn btn-primary" data-dismiss="modal">Save changes</button>
                </div>

            </div>
        </div>
    </div>

    <!-- modal to add an entity -->
    <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Add a new car</h5>

                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <div class="modal-body">
                    <div class="form-group">
                        <label for="header1_a">username</label>
                        <input class="form-control" id="header1_a" type="text" placeholder="Username" #header1>
                    </div>

                    <div class="form-group">
                        <label for="header2_a">password</label>
                        <input type="text" class="form-control" id="header2_a" placeholder="Password" #header2>
                    </div>

                </div>

                <div class="modal-footer">
                    <button type="button" id="add-entity-confirm-button" class="btn btn-primary" data-dismiss="modal" >Save</button>
                </div>

            </div>
        </div>
    </div>

    <div style="display: flex; flex-direction: column;">
        <button class="btn btn-primary" id="add-entity-button" data-toggle="modal" data-target="#addModal">Add a new entity</button>

        <!--
        <div id="filter-column">
            <div style="display: flex; flex-direction: column;">

                <div>
                    Fuel Type:<br>
                    <input type="checkbox" name="gas">Gas</checkbox><br>
                    <input type="checkbox" name="petrol">Petrol</checkbox><br>
                    <input type="checkbox" name="electric">Electric</checkbox><br>
                </div>

                <div style="display: flex; flex-direction: column;">
                    Price range:
                    <input type="search" id="price-min" placeholder="min. price">
                    <input type="search" id="price-max" placeholder="max. price">
                </div>

                <div>
                    Colour:<br>
                    <input type="checkbox" name="red" value="Red">Red<br>
                    <input type="checkbox" name="green" value="Red">Green<br>
                    <input type="checkbox" name="blue" value="Blue">Blue<br>

                </div>

                <div style="display: flex; flex-direction: column;">
                    Age range:<br>
                    <input type="search" id="age-min" placeholder="min. age">
                    <input type="search" id="age-max" placeholder="max. age">
                </div>

                <button class="btn btn-secondary" id="filter-button">Filter</button>
                <button class="btn btn-secondary" id="clear-button">Clear filters</button>

            </div>
          -->
    </div>

    <div id="entity-list"></div>

</body>
</html>
