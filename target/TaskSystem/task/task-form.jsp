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
				<li><a href="<%=request.getContextPath()%>/list">Main Page</a></li>
				<li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
				</ul>
		</nav>
	</header>
	<div>
		<div>
			<div>
				<c:if test="${task != null}">
					<form action="update_task" method="post">
				</c:if>
				<c:if test="${task == null}">
					<form action="insert_task" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${task != null}">
            			Edit Task
            		</c:if>
						<c:if test="${task == null}">
            			Add New Task
            		</c:if>
					</h2>
				</caption>

				<c:if test="${task != null}">
					<input type="hidden" name="id" value="<c:out value='${task.id}' />" />
				</c:if>

				<fieldset>
					<label>Task Title</label> <input type="text"
						value="<c:out value='${task.title}' />"
						name="title" required="required" minlength="5">
				</fieldset>

				<fieldset>
					<label>Task Description</label> <input type="text"
						value="<c:out value='${task.description}' />"
						name="description" minlength="5">
				</fieldset>

				<fieldset>
					<label>Task Status</label> <select name="isDone">
						<option value="false">In Progress</option>
						<option value="true">Complete</option>
					</select>
				</fieldset>

				<fieldset>
					<label>Task Target Date</label> <input type="date"
						value="<c:out value='${task.targetDate}' />" name="targetDate" required="required">
				</fieldset>

				<button>Save</button>
				</form>
			</div>
		</div>
	</div>
	<div>
		<table>
			<thead>
			<tr>
				<th>Title</th>
				<th>Actions</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="goal" items="${listGoal}">
				<tr>
					<td><c:out value="${goal.title}" /></td>
					<td>
						<form action="bind?goal_title=<c:out value='${goal.title}' />" method="post">
							<button>Bind with current task</button></form>

				</tr>
			</c:forEach>
			</tbody>

		</table>
	</div>
</body>
</html>
