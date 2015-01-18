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
  <title>Show</title>
  <style>

    body{
      font-family: Clean, Arial, sans-serif;
      width: 100%;
      background-color: #B3E5FC;
      margin: auto;

    }



    #list{
      width: 60%;
      float:left;
      background-color: #B2DFDB;
      align-items: center;
      padding-top: 15px;
    }

    #add{
      background-color: #B3E5FC;
      width: 40%;
      float: right;
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

    .form{
      margin-left: 10px;
      color: #0277BD;
      display: table-caption;
    }

    .field{
      color: #004D40;
      margin-bottom: 5px;
      display: inline-flex;
    }


    .fieldlabel{
      color: #004D40;
      width:100px;
      margin: 5px;
      float:left;
    }

    .fieldA{
      color: #0277BD;
      margin-bottom: 5px;
      display: inline-flex;
    }


    .fieldlabelA{
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
    <div class="captionL">Dane klienta</div>
    <div id="form" class="form">
      <div class="field"><p class="fieldlabel">ID klienta: </p><c:out value="${customer.customerId}"/></div>
      <div class="field"><p class="fieldlabel">PIN: </p><c:out value="${customer.PIN}" /></div>
      <div class="field"><p class="fieldlabel">Imię: </p><c:out value="${customer.profil.surname}" /></div>
      <div class="field"><p class="fieldlabel">Nazwisko: </p><c:out value="${customer.profil.name}" /></div>
      <div class="field"><p class="fieldlabel">Email: </p><c:out value="${customer.profil.email}" /></div>
      <div class="field"><p class="fieldlabel">Telefon: </p><c:out value="${customer.profil.phoneNumber}" /></div>
      <div class="field"><p class="fieldlabel">Adres: </p><c:out value="${customer.profil.adress}" /></div>
    </div>

    <table class="table tables">
      <caption>Rachunki</caption>
      <thead>
      <tr class="tables">
        <th class="headertable">Numer konta</th>
        <th class="headertable">Typ konta</th>
        <th class="headertable">Saldo</th>
        <th class="headertable">Waluta</th>
        <th class="headertable">Karta</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${accounts}" var="account">
        <tr>
          <td class="tables tdborder tdfirst"><c:out value="${account.accountNumber}"/></td>
          <td class="tables tdborder"><c:out value="${account.accountType}"/></td>
          <td class="tables tdborder"><c:out value="${account.balance}"/></td>
          <td class="tables tdborder"><c:out value="${account.currency}"/></td>
          <td class="tables tdborder"><c:out value="${account.isCard}"/></td>
          <td class="optiontd">
            <c:set  var="urldelete" value="../deleteaccount/${customer.customerId}/${account.accountNumber}" />
            <form:form action="${urldelete}" method="post">
              <button id="delete" type="submit"  class="buttonL">U</button>
            </form:form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
    <table class="table tables">
      <caption>Transakcje</caption>
      <thead>
      <tr class="tables">
        <th class="headertable">Na konto</th>
        <th class="headertable">Typ</th>
        <th class="headertable">Od/Do</th>
        <th class="headertable">Data</th>
        <th class="headertable">Tytul</th>
        <th class="headertable">Adres</th>
        <th class="headertable">Kwota</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${transactions}" var="t">
        <tr>
          <td class="tables tdborder tdfirst"><c:out value="${t.bankAccount.accountNumber}"/></td>
          <td class="tables tdborder">
            <c:if test="${t.transactionType == 'INCOMING'}">
              IN
            </c:if>
            <c:if test="${t.transactionType == 'OUTBOUND'}">
              OUT
            </c:if>
          </td>
          <td class="tables tdborder"><c:out value="${t.surname}"/> <c:out value="${t.name}"/></td>
          <td class="tables tdborder"><fmt:formatDate value="${t.transactionDateTime}" pattern="hh:mm:ss dd.MM.yyyy"/></td>
          <td class="tables tdborder"><c:out value="${t.title}"/></td>
          <td class="tables tdborder"><c:out value="${t.adress}"/></td>
          <td class="tables tdborder"><c:out value="${t.amount}"/></td>
          <td class="optiontd">
            <c:set  var="urldelete" value="../deletetransaction/${customer.customerId}/${t.id}" />
            <form:form action="${urldelete}" method="post">
              <button id="delete" type="submit"  class="buttonL">U</button>
            </form:form>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>

  <div id="add">
    <div class="captionA">Dodaj konto</div>
    <div id="form2" class="form">
      <form:form action="../saveaccount/${customer.customerId}" modelAttribute="newaccount" method="post">
        <div class="fieldA"><p class="fieldlabelA">Typ konta: </p>
            <form:select path="accountType">
              <form:options items="${acctypes}" />
            </form:select>
        </div>
        <div class="fieldA"><p class="fieldlabelA">Waluta: </p>
          <form:select path="currency">
            <form:options items="${currencies}" />
          </form:select>
        </div>
        <div class="fieldA"><p class="fieldlabelA">Karta: </p>
          <form:radiobutton path="isCard" value="true" />Tak
          <form:radiobutton path="isCard" value="false" /> Nie
        </div>
        <button id="zapisz" type="submit" class="buttonA">Zapisz</button>
      </form:form>
    </div>
    <div class="captionA">Dodaj Transakcje</div>
    <div id="form3" class="form">
      <form:form action="../saveaccount/${customer.customerId}" modelAttribute="newt">
        <div class="fieldA"><p class="fieldlabelA">Na/z konta: </p>
          <form:select path="bankAccount.accountNumber">
            <form:options items="${accnums}" />
          </form:select>
        </div>
        <div class="fieldA"><p class="fieldlabelA">Typ: </p>
          <form:select path="transactionType">
            <form:options items="${ttypes}" />
          </form:select>
        </div>
        <div class="fieldA"><p class="fieldlabelA">Od/Do imie: </p><form:input  id="pin" path="surname" /></div>
        <div class="fieldA"><p class="fieldlabelA">Od/Do nazwisko: </p><form:input  id="pin" path="name" /></div>
        <div class="fieldA"><p class="fieldlabelA">Tytul: </p><form:input  id="pin" path="title" /></div>
        <div class="fieldA"><p class="fieldlabelA">Kwota: </p><form:input  id="pin" path="amount" /></div>
        <div class="fieldA"><p class="fieldlabelA">Numer konta: </p><form:input  id="pin" path="accountNumber" /></div>
        <button id="zapisz" type="submit" class="buttonA">Zapisz</button>
      </form:form>
    </div>
  </div>
</div>
</body>
</html>
