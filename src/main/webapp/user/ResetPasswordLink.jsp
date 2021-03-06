<%@ include file="/taglibs.jsp" %>
<stripes:layout-render name="/layout/start.jsp" title="Reset password">

    <stripes:layout-component name="wholePage">
        <c:if test="${not empty actionBean.message}">
            <div id="feedback">
                <h5>${actionBean.message}</h5>
            </div>
        </c:if>
        <div id="changePw">
            <div id="changePw">
                <div class="panel panel-default">
                    <div class="panel panel-heading">
                        <h3 class="panel-title">Reset password</h3>
                    </div>
                    <div class="panel panel-body">
                        <stripes:form beanclass="${actionBean.class}">
                            <ul>
                                <cat:in name="username" label="Username"/>
                                <stripes:submit class="btn btn-success" name="generateNewPasswordLink" value="Send resend link to email"/>
                            </ul>
                        </stripes:form>
                    </div>
                </div>
            </div>
        </div>
        <style>

            #changePw .panel-default {
                width: 500px;
                float: none;
                margin: 0px auto;
                border: medium none;
            }

            #changePw .panel-heading {
                margin: 0px;
                border-radius: 0px;
                border: medium none;
            }

            #changePw .panel-body {
                border-radius: 0px;
                box-shadow: none;
                border: 1px solid rgb(222, 222, 222);
            }

            #changePw input {
                margin-bottom: 10px;
            }

            #changePw form ul {
                padding: 0px;
                margin: 0;
            }

            #changePw .input-group {
                width: 100%;
            }

            #changePw .btn {
                float: right;
            }

            .btn {
                margin: 10px;
            }
            #feedback h5{
                background: #17b980;
                padding: 10px;
                color: white;
                text-align: center;
                font-weight: bold;
            }
        </style>
    </stripes:layout-component>
</stripes:layout-render>
