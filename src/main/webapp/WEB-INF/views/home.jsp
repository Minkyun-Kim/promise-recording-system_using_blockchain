<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<head>
    <meta charset = "utf-8">
    <title>promise management</title>
    <!--  link href="<c:url value="/resources/style/home.css"/>" rel="stylesheet" type="text/css" /-->
    <link href="/resources/style/home.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <header>
        <nav> 
            <a class="headerDiv" id = "serviceName" href="/showHome">Promise Service</a>
            <a class="headerDiv" id = "myPromise" href="/myPromises.html">My Promises</a>
        </nav>
    </header>
    <div id="container">
    <section id="sectionSentence">
        <div id="promiseexplanation">
            <div id="heading">Blockchain makes your promise safe</div>
            <div id="explan">Blockchain keeps your meeting information secure</div> 
        </div>
    </section>
    <section id="makepromise">
        <fieldset>
            <form action="/makePromise" method="post">
            <div id="tablename">Creat Promise</div>
            <ul>
                <div class="input-box">
                    <!--label for = "promise-date">Date</label-->
                    <input type="text" id="promise-date" name="date" placeholder="Date" >
                </div>
                <div class="input-box">
                    <!--label for = "promise-location">Location</label-->
                    <input type="text" id="promise-location" name="location" placeholder = "Location">
                </div>
                <div class="input-box">
                    <!--label for = "promise-fund">fund</label-->
                    <input type="text" id="promise-fund" name="fund" placeholder="Fund">
                </div>
                <div class="input-box">
                    <!--label for = "promise-participants">Participants</label-->
                    <input type="text" id="promise-participants" name="participants"placeholder="Participants">
                </div>
                <div class="input-box">
                    <!--label for = "promise-participants">Participants</label-->
                    <textarea type="text" id="promise-participants" name="content" cols="28" rows="10" placeholder="content"></textarea>
                </div>
            </ul>
            <div id="submitDiv">
                <input type="submit" value="Make promise" id="clickButton">
            </div>
            </form>
        </fieldset>
    </section>
    </div>
</body>
</html>