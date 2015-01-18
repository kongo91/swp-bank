<%--
  Created by IntelliJ IDEA.
  User: Konrad
  Date: 2015-01-16
  Time: 21:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Customers</title>
    <style>

      body{
        font-family: Clean, Arial, sans-serif;
        width: 100%;

        margin: auto;

      }



      #list{
        width: 60%;
        height: 100%;
        float:left;
        background-color: #B2DFDB;
        align-items: center;
        padding-top: 15px;
      }

      #add{
        background-color: #B3E5FC;
        width: 40%;
        float: right;
        height: 100%;
        padding-top: 15px;
      }

      .tables {
        margin-left: 10px;
      }

      .tables {
         border-collapse: collapse;
         border: 0px solid  #004D40;
         text-align: center;
         color: #004D40;
       }
      .tdborder{
        border: 1px solid  #80CBC4;
        color: #004D40;
      }
      .tdfirst{
        background-color: #26A69A;
      }
      .headertable{
        background: #009688;
        color:#fff;
        border: 1px solid  #80CBC4;
        font-weight: normal;
      }
      .optiontd{
        border-width: 0px;
        align-content: center;
        text-align: center;
        margin: auto;
      }

      .buttonL{
        color: white;
        border-radius: 2px;
        border:0 none;
        text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
        background:#009688;
        cursor:pointer;
        text-align: center;
        font-size: medium;
      }

      .buttonA{
        color: white;
        border-radius: 2px;
        border:0 none;
        text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
        background:#03A9F4;
        cursor:pointer;
        text-align: center;
        font-size: medium;
      }

      .captionL{
        font-family: sans-serif;
        font-size: x-large;
        color: #009688;
        text-align: center;
        margin-bottom: 15px;
      }

      .captionA{
        font-family: sans-serif;
        font-size: x-large;
        color: #03A9F4;
        text-align: center;
        margin-bottom: 15px;
      }

      #form{
        margin-left: 10px;
        color: #0277BD;
        display: table-caption;
      }

      .field{
        color: #0277BD;
        margin-bottom: 5px;
        display: inline-flex;
      }


      .fieldlabel{
        color: #0277BD;
        width:100px;
        margin: 5px;
        float:left;
      }


    </style>
</head>
<body>
  <div id="main">
    <div id="list">
      <div class="captionL">Klienci banku</div>
      <table class="table tables">
        <thead>
          <tr class="tables">
            <th class="headertable">ID</th>
            <th class="headertable">PIN</th>
            <th class="headertable">Ostatnio zalogowany</th>
            <th class="headertable">Imię</th>
            <th class="headertable">Naziwsko</th>
            <th class="headertable">Email</th>
            <th class="headertable">Telefon</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach items="${customers}" var="customer">
            <tr>
              <td class="tables tdborder tdfirst"><c:out value="${customer.customerId}"/></td>
              <td class="tables tdborder"><c:out value="${customer.PIN}"/></td>
              <td class="tables tdborder"><fmt:formatDate value="${customer.lastLoginTime}" pattern="hh:mm:ss dd.MM.yyyy"/></td>
              <td class="tables tdborder"><c:out value="${customer.profil.surname}"/></td>
              <td class="tables tdborder"><c:out value="${customer.profil.name}"/></td>
              <td class="tables tdborder"><c:out value="${customer.profil.email}"/></td>
              <td class="tables tdborder"><c:out value="${customer.profil.phoneNumber}"/></td>
              <td class="optiontd">
                <c:set  var="urledit" value="edit/${customer.customerId}" />
                <form:form action="${urledit}">
                  <button id="edit" type="submit" class="buttonL">E</button>
                </form:form>
              </td>
              <td class="optiontd">
                <c:set  var="urldelete" value="delete/${customer.customerId}" />
                <form:form action="${urldelete}">
                  <button id="delete" type="submit"  class="buttonL">U</button>
                </form:form>
              </td>
              <td class="optiontd">
                <c:set  var="urlshow" value="show/${customer.customerId}" />
                <form:form action="${urlshow}" method="get">
                  <button id="show" type="submit"  class="buttonL">P</button>
                </form:form>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>

    <div id="add">
      <div class="captionA">Dodaj klienta</div>
      <div id="form">
        <form:form action="save" modelAttribute="newcustomer">
          <div class="field"><p class="fieldlabel">ID klienta: </p><form:input  id="id"  path="customerId" /></div>
          <div class="field"><p class="fieldlabel">PIN: </p><form:input  id="pin" path="PIN" /></div>
          <div class="field"><p class="fieldlabel">Imię: </p><form:input  id="pin" path="profil.surname" /></div>
          <div class="field"><p class="fieldlabel">Nazwisko: </p><form:input  id="pin" path="profil.name" /></div>
          <div class="field"><p class="fieldlabel">Email: </p><form:input  id="pin" path="profil.email" /></div>
          <div class="field"><p class="fieldlabel">Telefon: </p><form:input  id="pin" path="profil.phoneNumber" /></div>
          <button id="zapisz" type="submit" class="buttonA">Zapisz</button>
        </form:form>
      </div>
    </div>
  </div>
</body>
</html>
