<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Task Manager</title>
</head>

<body>
<header>
    <nav style="background-color: #204eef">
        <ul>
            <li><a href="<%=request.getContextPath()%>/list">Main page</a></li>
            <li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
        </ul>
    </nav>
</header>
<div>
    <div>
        <div>
            <c:if test="${goal != null}">
            <form action="update_goal" method="post">
                </c:if>
                <c:if test="${goal == null}">
                <form action="insert_goal" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${goal != null}">
                                Edit Goal
                            </c:if>
                            <c:if test="${goal == null}">
                                Add New Goal
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${goal != null}">
                        <input type="hidden" name="id" value="<c:out value='${goal.id}' />" />
                    </c:if>

                    <fieldset>
                        <label>Goal Title</label> <input type="text" value="<c:out value='${goal.title}' />"
                        name="title" required="required" minlength="5">
                    </fieldset>


                    <button>Save</button>
                </form>
        </div>
    </div>
</div>

</body>
</html>