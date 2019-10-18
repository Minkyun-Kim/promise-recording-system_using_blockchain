<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Blocks</title>
    <link href="/resources/style/showBlockChain.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <header>
        <nav> 
            <a class="headerDiv" id = "serviceName" href="index.html">Promise Service</a>
            <a class="headerDiv" id = "myPromise" href="mypromises.html">My Promises</a>
        </nav>
    </header>
    <div id="container">
    <section id="notYetParticipatedList">
        <fieldset id="notYetFieldset">
            <form action="#" >
            <h1 class="tableName">Blockchain</h1>
            <div class="showDiv">
                <input type="submit" value="Show Blocks" class="showPromiseButton" id = "showAvailables" onclick="">
                <table align="center" border=1px>
                <c:forEach items="${blockChainList}" var="blockChain" varStatus="status" >
	                <tr>
						<th colspan=2>Block #${fn:length(blockChainList) - status.index }</th>
	                </tr>
	                <tr>
	                	<th>Block Hash</th>
	                	<td>${blockChain.blockHeaderHash }</td>
	                </tr>
	                <tr>
	                	<th>PrevBlockHash</th>
	                	<td>${blockChain.blockHeader.prevBlockHash }</td>
	                </tr>
	                <tr>
	                	<th>MerkleRootHash</th>
	                	<td>${blockChain.blockHeader.merkleRoot }</td>
	                </tr>
	                <tr>
	                	<th>Height</th>
	                	<td>${blockChain.blockHeader.height}</td>
	                </tr>
	                <tr>
	                	<th>timestamp</th>
	                	<td>${blockChain.blockHeader.timeStamp}</td>
	                </tr>
	                <tr>
	                	<th>nonce</th>
	                	<td>${blockCHain.blockHeader.nonce}</td>
	                </tr>
	                <tr>
	                	<th colspan=2>Promises</th>
	                </tr>
	                <c:forEach items="${blockChain.block.transactions }" var="transaction" varStatus="txStatus" >
	                	<tr>
	                		<th colspan=2>Promise #${fn:length(blockChain.block.transactions) - txStatus.index }</th>
	                	</tr>
	                	<tr>
	                		<th>Date</th>
	                		<td>${transaction.date }</td>
	                	</tr>
	                	<tr>
	                		<th>Location</th>
	                		<td>${transaction.location }</td>
	                	</tr>
	                	<tr>
	                		<th>Fund</th>
	                		<td>${transaction.fund }</td>
	                	</tr>
	                	<tr>
	                		<th>Participants</th>
	                		<td>${transaction.participants }</td>
	                	</tr>
	                	<tr>
	                		<th>Content</th>
	                		<td>${transaction.content }</td>
	                	</tr>
	                </c:forEach>
                </c:forEach>
                </table>
            </div>
            </form>
        </fieldset>
    </section>
    <section id="notYetParticipatedList">
        <fieldset id="notYetFieldset">
            <form action="#" >
            <h1 class="tableName">Promise Queue</h1>
            <div class="showDiv">
                <input type="submit" value="Show Blocks" class="showPromiseButton" id = "showAvailables" onclick="">
                <table align="center" border=1px >
	            <c:forEach items="${txQueueList}" var="tx" varStatus="status">
					<tr>
						<th colspan=2>Promise #${status.count }</th>
					</tr>	
                	<tr>
                		<th>Date</th>
                		<td>${tx.date }</td>
                	</tr>
                	<tr>
                		<th>Location</th>
	               		<td>${tx.location }</td>
                	</tr>
                	<tr>
                		<th>Fund</th>
                		<td>${tx.fund }</td>
                	</tr>
                	<tr>
                		<th>Participants</th>
                		<td>${tx.participants }</td>
	               	</tr>
                	<tr>
                		<th>Content</th>
                		<td>${tx.content }</td>
                	</tr>
	            </c:forEach>
                </table>
            </div>
            </form>
        </fieldset>
    </section>
    </div>
</body>
</html>