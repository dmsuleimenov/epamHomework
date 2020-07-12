<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>

</head>

<body>
<header>
	<nav style="background-color: #204eef">
		<ul>
			<li><a href="<%= request.getContextPath() %>/login" class="nav-link">Login</a></li>
			<li><a href="<%= request.getContextPath() %>/register" class="nav-link">Signup</a></li>
		</ul>
	</nav>
</header>
	<div>

	<h2>User Register Form</h2>
	<div>
			<div>
				<p>${NOTIFICATION}</p>
			</div>
			
				<form action="<%=request.getContextPath()%>/register" method="post">

					<div>
						<label for="uname">First Name:</label> <input type="text"
							 id="uname" placeholder="First Name"
							name="firstName" required>
					</div>

					<div class="form-group">
						<label for="uname">Last Name:</label> <input type="text"
							 id="uname" placeholder="last Name"
							name="lastName" required>
					</div>

					<div class="form-group">
						<label for="uname">User Name:</label> <input type="text"
							 id="username" placeholder="User Name"
							name="username" required>
					</div>

					<div class="form-group">
						<label for="uname">Password:</label> <input type="password"
							 id="password" placeholder="Password" name="password" required>
					</div>

					<button type="submit">Submit</button>

				</form>
			</div>
		</div>
</body>
</html>