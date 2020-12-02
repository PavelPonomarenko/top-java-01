<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<%@ taglib prefix="dandelion" uri="http://github.com/dandelion" %>

<html>
<dandelion:bundle includes="topjavaDatatable"/>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><fmt:message key="users.title"/></h3>

            <c:set var="ajaxUrl" value="ajax/admin/users/"/>
            <div class="view-box">
                <a class="btn btn-sm btn-info" id="add">Add User</a>

                <datatables:table id="datatable" url="${ajaxUrl}" row="user" theme="bootstrap3"
                                  cssClass="table table-striped" pageable="false" info="false">

                <datatables:column title="Name" sortInitDirection="asc" property="name"/>
                <datatables:column title="Email" property="email" renderFunction="renderEmail"/>
                <datatables:column title="Roles" property="roles"/>
                <datatables:column title="Active" filterable="false" property="enabled" renderFunction="renderCheckbox"/>
                <datatables:column title="Registered" filterable="false" property="registered" renderFunction="renderDate"/>
                <datatables:column sortable="false" renderFunction="renderUpdateBtn"/>
                <datatables:column sortable="false" renderFunction="renderDeleteBtn"/>

                <datatables:callback type="init" function="makeEditable"/>


            <%--            <div class="view-box">--%>
<%--                <a class="btn btn-sm btn-info" id="add">Add User</a>--%>

<%--                <datatables:table id="datatable" data="${userList}" row="user" theme="bootstrap3"--%>
<%--                                  cssClass="table table-striped" pageable="false" info="false">--%>
<%--                    <datatables:column title="Name" property="name"/>--%>
<%--                    <datatables:column title="Email">--%>
<%--                        <a href="<c:url value="mailto:${user.email}"/>">${user.email}</a>--%>
<%--                    </datatables:column>--%>
<%--                    <datatables:column title="Roles" property="roles"/>--%>
<%--                    <datatables:column title="Active">--%>
<%--                        <input type="checkbox"--%>
<%--                               <c:if test="${user.enabled}">checked</c:if> id="${user.id}"--%>
<%--                               onclick=" enable($(this).attr('id'),$(this))"/>--%>
<%--                    </datatables:column>--%>
<%--                    <datatables:column title="Registered">--%>
<%--                        <fmt:formatDate value="${user.registered}" pattern="dd-MMMM-yyyy"/>--%>
<%--                    </datatables:column>--%>
<%--                    <datatables:column filterable="false" sortable="false">--%>
<%--                        <a class="btn btn-xs btn-primary update" id="${user.id}">Update</a>--%>
<%--                    </datatables:column>--%>

<%--                    <datatables:column filterable="false" sortable="false">--%>
<%--                        <a class="btn btn-xs btn-danger delete" id="${user.id}">Delete</a>--%>
<%--                    </datatables:column>--%>


                </datatables:table>
            </div>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title">User details:</h2>
            </div>

            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3">Name</label>
                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="Name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="email" class="control-label col-xs-3">Email</label>

                        <div class="col-xs-9">
                            <input type="email" class="form-control" id="email" name="email" placeholder="email">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="password" class="control-label col-xs-3">Password</label>

                        <div class="col-xs-9">
                            <input type="password" class="form-control" id="password" name="password" placeholder="">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var ajaxUrl = '${ajaxUrl}';

    function specialUpdate(){
        $(':checkbox').each(function () {
            if (!$(this).is(":checked")) {
                $(this).parent().parent().css("text-decoration", "line-through");
            }
        });
    }

    // var ajaxUrl = 'ajax/admin/users/';
    //
    // $(function () {
    //     makeEditable();
    // });
    //
    // function specialUpdate() {
    //     $(':checkbox').each(function () {
    //         if (!$(this).is(":checked")) {
    //             $(this).parent().parent().css("text-decoration", "line-through");
    //         }
    //     });
    // }
    //
    //
    // //        $(document).ready(function () {
    // // $(function () {
    // //     makeEditable();
    // // });
</script>
</html>