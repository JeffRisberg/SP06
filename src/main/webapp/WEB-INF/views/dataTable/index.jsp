<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script>
    jQuery.fn.dataTableExt.oApi.fnPagingInfo = function (oSettings) {
        return {
            "iStart": oSettings._iDisplayStart,
            "iEnd": oSettings.fnDisplayEnd(),
            "iLength": oSettings._iDisplayLength,
            "iTotal": oSettings.fnRecordsTotal(),
            "iFilteredTotal": oSettings.fnRecordsDisplay(),
            "iPage": oSettings._iDisplayLength === -1 ?
                    0 : Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength),
            "iTotalPages": oSettings._iDisplayLength === -1 ?
                    0 : Math.ceil(oSettings.fnRecordsDisplay() / oSettings._iDisplayLength)
        };
    };

    $(document).ready(function () {

        $("#example").dataTable({
            "bProcessing": true,
            "bServerSide": true,
            "sort": "position",
            //bStateSave variable you can use to save state on client cookies: set value "true"
            "bStateSave": false,
            //Default: Page display length
            "iDisplayLength": 10,
            //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
            "iDisplayStart": 0,
            "fnDrawCallback": function () {
                //Get page number on client. Please note: number start from 0 So
                //for the first page you will see 0 second page 1 third page 2...
                //Un-comment below alert to see page number
                //alert("Current page number: "+this.fnPagingInfo().iPage);
            },
            "sAjaxSource": "dataTable/getData",
            "aoColumns": [
                {"mData": "name"},
                {"mData": "position"},
                {"mData": "office"},
                {"mData": "phone"},
                {"mData": "start_date"},
                {"mData": "salary"}
            ]
        });
    });
</script>

<div style="margin-bottom: 10px">
    <form:form action="" method="GET">
        <h2>Spring MVC pagination using data tables<br><br></h2>
        <table width="70%" style="border: 3px;background: rgb(243, 244, 248);">
            <tr>
                <td>
                    <table id="example" class="display" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Position</th>
                            <th>Office</th>
                            <th>Phone</th>
                            <th>Start Date</th>
                            <th>Salary</th>
                        </tr>
                        </thead>
                    </table>
                </td>
            </tr>
        </table>
    </form:form>
</div>