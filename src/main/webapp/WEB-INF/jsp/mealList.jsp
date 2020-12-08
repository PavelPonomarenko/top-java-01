<%@ page import="com.gmail.ponomarenko.model.UserMeal" %>
<%@ page import="com.gmail.ponomarenko.util.TimeUtil" %>
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
            <h3><fmt:message key="meals.title"/></h3>

            <c:set var="ajaxUrl" value="ajax/profile/meals/"/>
            <div class="view-box">
                <a class="btn btn-sm btn-info" id="add">Add Meal</a>

                <datatables:table id="datatable" url="${ajaxUrl}" row="user" theme="bootstrap3"
                                  cssClass="table table-striped" pageable="false" info="false">

                    <datatables:column title="Date" filterable="false" sortInitDirection="desc" property="dateTime"/>
                    <datatables:column title="Description" property="description"/>
                    <datatables:column title="Calories" filterable="false" property="calories"/>
                    <datatables:column sortable="false" renderFunction="renderUpdateBtn"/>
                    <datatables:column sortable="false" renderFunction="renderDeleteBtn"/>

                    <datatables:callback type="init" function="makeEditable"/>
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
                <h2 class="modal-title">Meal details:</h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="datetime" class="control-label col-xs-3">Date</label>

                        <div class="col-xs-9">
                            <input type="datetime" class="form-control datetime-picker" id="dateTime" name="dateTime" placeholder="Date">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3">Description</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description" placeholder="Description">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="calories" class="control-label col-xs-3">Calories</label>

                        <div class="col-xs-9">
                            <input type="number" class="form-control" id="calories" name="calories"
                                   placeholder="2000">
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

    function init() {
        $('.date-picker').datetimepicker({
            timepicker: false,
            format: 'Y-m-d'
        });
        $('.time-picker').datetimepicker({
            datepicker: false,
            format: 'H:i'
        });
        $('.datetime-picker').datetimepicker({
            format: 'Y-m-d H:i'
        });
        coloredTable();
    }

    function updateTable() {
        $.get(ajaxUrl, updateByData);
        coloredTable();
    }

    function coloredTable() {
        // TODO implement;
    }
</script>
<%--<dandelion:bundle includes="topjavaDatatable"/>--%>
<%--<jsp:include page="fragments/headTag.jsp"/>--%>
<%--<body>--%>
<%--<jsp:include page="fragments/bodyHeader.jsp"/>--%>
<%--<div class="jumbotron">--%>
<%--    <div class="container">--%>
<%--        <div class="shadow">--%>
<%--            <h3><fmt:message key="meals.title"/></h3>--%>
<%--            <c:set var="ajaxUrl" value="/ajax/profile/meals"/>--%>

<%--            <div class="view-box">--%>
<%--                <a class="btn btn-sm btn-info" id="add">Add Meal</a>--%>

<%--                <datatables:table id="datatable" url="${ajaxUrl}" row="meal" theme="bootstrap3"--%>
<%--                                  cssClass="table table-striped" pageable="false" info="false">--%>
<%--                    <datatables:column title="Date" sortInitDirection="desc" property="dateTime"--%>
<%--                                       renderFunction="renderDate"/>--%>
<%--                    <datatables:column title="Description" property="description"/>--%>
<%--                    <datatables:column title="Calories" property="calories"/>--%>
<%--                    <datatables:column sortable="false" renderFunction="renderUpdateBtn"/>--%>
<%--                    <datatables:column sortable="false" renderFunction="renderDeleteBtn"/>--%>

<%--                    <datatables:callback type="init" function="makeEditable"/>--%>
<%--                </datatables:table>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--<jsp:include page="fragments/footer.jsp"/>--%>
<%--<div class="modal fade" id="editRow">--%>
<%--    <div class="modal-dialog">--%>
<%--        <div class="modal-content">--%>
<%--            <div class="modal-header">--%>
<%--                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
<%--                <h2 class="modal-title">Edit Meal</h2>--%>
<%--            </div>--%>
<%--            <div class="modal-body">--%>
<%--                <form class="form-horizontal" method="post" id="detailsForm">--%>
<%--                    <input hidden="hidden" id="id" name="id">--%>

<%--                    <div class="form-group">--%>
<%--                        <label for="datetime" class="control-label col-xs-3">Date</label>--%>

<%--                        <div class="col-xs-9">--%>
<%--                            <input type="datetime-local" class="form-control datetime-picker" id="datetime"--%>
<%--                                   name="datetime" placeholder="Date">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        <label for="description" class="control-label col-xs-3">Description</label>--%>

<%--                        <div class="col-xs-9">--%>
<%--                            <input type="text" class="form-control" id="description" name="description"--%>
<%--                                   placeholder="Description">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        <label for="calories" class="control-label col-xs-3">Calories</label>--%>

<%--                        <div class="col-xs-9">--%>
<%--                            <input type="number" class="form-control" id="calories" name="calories"--%>
<%--                                   placeholder="2000">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="form-group">--%>
<%--                        <div class="col-xs-offset-3 col-xs-9">--%>
<%--                            <button type="submit" class="btn btn-primary">Save</button>--%>
<%--                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </form>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--<script type="text/javascript">--%>
<%--    var ajaxUrl = 'ajax/profile/meals/';--%>
<%--       $(function () {--%>
<%--           makeEditable();--%>
<%--       });--%>
<%--</script>--%>
</html>