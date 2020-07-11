<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

<header>
	<nav class="navbar-nav navbar-collapse justify-content-end"
		 style="background-color: #204eef">
		<ul class="navbar-nav navbar-collapse justify-content-end">
			<li><a href="<%= request.getContextPath() %>/login" class="nav-link">Login</a></li>
			<li><a href="<%= request.getContextPath() %>/register" class="nav-link">Signup</a></li>
		</ul>
	</nav>
</header>
	<div>
		<h1>Login Form</h1>
		<form action="<%=request.getContextPath()%>/login" method="post">

			<div class="form-group">
				<label>User Name:</label> <input type="text" id="username" placeholder="Username"
					name="username" required>
			</div>

			<div class="form-group">
				<label>Password:</label> <input type="password" id="password" placeholder="Password"
					name="password" required>
			</div>


			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
</body>
</html>