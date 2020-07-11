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
			<h3 >List of Goals</h3>
			<hr>
			<div>
				<a href="<%=request.getContextPath()%>/new_goal">Add Goal</a>
			</div>
			<br>
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
						<td><a href="edit_goal?id=<c:out value='${goal.id}' />">Edit</a>

							<a href="delete_goal?id=<c:out value='${goal.id}' />">Delete</a></td>

					</tr>
				</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
	<div>

		<div>
			<h3>List of Tasks</h3>
			<hr>
			<div>

				<a href="<%=request.getContextPath()%>/new_task">Add Task</a>
			</div>
			<br>
			<table>
				<thead>
					<tr>
						<th>Title</th>
						<th>Target Date</th>
						<th>Task Status</th>
						<th>Goal</th>
						<th>Actions</th>

					</tr>
				</thead>
				<tbody>
					<c:forEach var="task" items="${listTask}">

						<tr>
							<td><c:out value="${task.title}" /></td>
							<td><c:out value="${task.targetDate}" /></td>
							<td><c:out value="${task.status}" /></td>
							<td><c:out value="${task.goal_title}"/></td>

							<td><a href="edit_task?id=<c:out value='${task.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete_task?id=<c:out value='${task.id}' />">Delete</a></td>

						</tr>
					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
