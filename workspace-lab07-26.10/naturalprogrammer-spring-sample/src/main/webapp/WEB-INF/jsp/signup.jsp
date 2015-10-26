<%@include file="includes/header.jsp"%>

<div class="panel panel-default">

<div class="panel-heading">
<h3 class="panel-title">Please signup</h3> 
</div> 


<div class="panel-body">
<form:form modelAttribute="signupForm" role="form">
 
  <form:errors />
  
  <div class="form-group">
    <form:label path="email">Email address</form:label>
    <form:input path="email" type="email"  class="form-control"  placeholder="Email" />
    <form:errors path="email" cssClass="error" />
    <p class="helper-block">Enter a unique email address. It will be also your login id.</p>
  </div>

 <div class="form-group">
    <form:label path="name">Name</form:label>
    <form:input path="name" type="name"  class="form-control"  placeholder="Enter Name" />
    <form:errors path="name" cssClass="error" />
    <p class="helper-block">Enter your display name.</p> 
  </div>

 <div class="form-group">
    <form:label path="password">Password</form:label>
    <form:input path="password" type="password"  class="form-control"  placeholder="Password" />
    <form:errors path="password" cssClass="error" />
  </div>
  
  <button type="submit" class="btn btn-default">Submit</button>

</form:form>
</div>



</div>


<%@include file="includes/footer.jsp"%>


