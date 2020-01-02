<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
${authticationError }
	<form action="login" method="post">
    
        <table border="0" cellpadding="0" cellspacing="0" style="margin:320px 0px 0px 300px; font-weight:bold;">
        	<tr><td colspan="4" style=" color: red">${authticationError }</td></tr>
            <tr>
                <td style="padding:5px; ">用户名</td>
                <td colspan="2"><input name="username" type="text" size="20"></td>
                <td style="padding:5px; color: red"></td>
            </tr>
            <tr>
                <td style="padding:5px; ">密&nbsp;码</td>
                <td colspan="2"><input name="password" type="password" size="20"></td>
                <td style="padding:5px; color: red;font-size: 13">${error }</td>
            </tr>
            <tr>
                <td style="padding:5px; ">验证码</td>
                <td><input name="randomcode" type="text" size="10"></td>
                <td><img src="validatecode" width="55" height="20" border="0" id="codeImage"  onClick="chageCode()"></td>
                <td><input type="submit" value="登录"/></td>
            </tr>
        </table>
    </form>

</body>
</html>