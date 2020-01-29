<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"      uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ui"     uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : egovSampleList.jsp
  * @Description : Sample List 화면
  * @Modification Information
  *
  *   수정일         수정자                   수정내용
  *  -------    --------    ---------------------------
  *  2009.02.01            최초 생성
  *
  * author 실행환경 개발팀
  * since 2009.02.01
  *
  * Copyright (C) 2009 by MOPAS  All right reserved.
  */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><spring:message code="title.money" /></title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
    
    <style>
    .ui-datepicker-calendar{ display: none; }
    </style>
    
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
	
    <script type="text/javaScript" language="javascript" defer="defer">
        <!--
        /* 글 수정 화면 function */
        function fn_egov_select(id) {
        	document.listForm.selectedId.value = id;
           	document.listForm.action = "<c:url value='/updateSampleView.do'/>";
           	document.listForm.submit();
        }
        
        /* 글 등록 화면 function */
        function fn_egov_addView() {
           	document.listForm.action = "<c:url value='/addSample1.do'/>";
           	document.listForm.submit();
        }
        
        /* 글 목록 화면 function */
        function fn_egov_selectList() {
        	document.listForm.action = "<c:url value='/egovSampleList.do'/>";
           	document.listForm.submit();
        }
        
        /* pagination 페이지 링크 function */
        function fn_egov_link_page(pageNo){
        	document.listForm.pageIndex.value = pageNo;
        	document.listForm.action = "<c:url value='/egovSampleList.do'/>";
           	document.listForm.submit();
        }
        
        /* 엑셀 다운로드 */
        function fn_egov_excelDown(){
        	document.listForm.action = "<c:url value='/excelDown.do'/>";
           	document.listForm.submit();
        }
        
        /*초기화 */
        function fn_egov_reset(){
        	document.listForm.reset();
        }
        
        /*검색*/
        function fn_egov_search(){
        	
        }
        
        //-->
        
        $(function() {
    $( "#testDatepicker" ).datepicker({
    	dateFormat: "yy-mm"
            , startView: 1
            , minViewMode: 1
            , yearSuffix: ""
            , showButtonPanel: true
            , currentText: "이번달"
            , closeText: "선택"
            ,changeYear: true
            ,changeMonth: true
            , beforeShow : function(el,obj){
                $("#ui-datepicker-div").addClass("calendar-none");
            }
            , onClose: function(dateText, inst){
                var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
                var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                $(this).datepicker("option", "defaultDate", new Date(year, month, 1));
                $(this).datepicker('setDate', new Date(year, month, 1));
                $("#ui-datepicker-div").removeClass("calendar-none");
            }
    });
});
    </script>
    <!-- <script>
			        $("#datepicker").datepicker();
			    </script> -->
</head>


<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">

            
    <form:form commandName="searchVO" id="listForm" name="listForm" method="post">
    <input type="hidden" name="id" />
        <input type="hidden" name="selectedId" />
        <div id="content_pop">
        	<!-- 타이틀 -->
        	<div id="title">
        		<ul>
        			<li><img src="<c:url value='/images/egovframework/example/title_dot.gif'/>" alt=""/><spring:message code="title.money" /></li>
        		</ul>
        	</div>
        	<!-- // 타이틀 -->
        	
        	<div id="search">
        		<div id="firstsearch" style="padding:0  0 0 50px;">
        		
        		등록연월<form:input path="searchRegDate" cssClass="txt" id="testDatepicker"/>
        		<!--  <input type="text" id="testDatepicker"> --><br><br>
        		
        		처리상태<form:select path="searchProcessCode" cssClass="use">
       					<form:option value="pc1" label="전체" />
       					<form:option value="pc2" label="접수" />
       					<form:option value="pc3" label="승인" />
       					<form:option value="pc4" label="지급완료" />
       					<form:option value="pc5" label="반려" />
       				 </form:select>
        		</div>
        		<ul>
        			<li>
        				<!-- <div id="t"> -->
        					<li style="color:black;">사용내역</li>
        				<!-- </div> -->
        			    <%-- <label for="searchCondition" style="visibility:hidden;"><spring:message code="search.choose" /></label> --%>
        				<form:select path="searchUseCode" cssClass="use">
        					<form:option value="uc0" label="전체" />
        					<form:option value="uc1" label="식대(야근)" />
        					<form:option value="uc2" label="택시비(야근)" />
        					<form:option value="uc3" label="택시비(회식)" />
        					<form:option value="uc4" label="사무용품구매" />
        					<form:option value="uc5" label="교육비" />
        					<form:option value="uc6" label="접대비" />
        				</form:select>
        			</li>
        			<%-- <li><label for="searchKeyword" style="visibility:hidden;display:none;"><spring:message code="search.keyword" /></label>
                        <form:input path="searchKeyword" cssClass="txt"/>
                    </li>
        			<li>
        	            <span class="btn_blue_l">
        	                <a href="javascript:fn_egov_selectList();"><spring:message code="button.search" /></a>
        	                <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
        	            </span>
        	        </li> --%>
                </ul>
                
        	</div>
        	<div style="width:680px; height:150px;">
	        	<div id="sysbtn" style="float:right;">
	        	  <ul>
	        	      <li>
	        	          <span class="btn_blue_l">
	        	              <a href="javascript:fn_egov_selectList();"><spring:message code="button.search" /></a>
	                          <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
	                      </span>
	                  </li>
	              </ul>
	        	</div>
	        	<div id="sysbtn">
	        	  <ul>
	        	      <li>
	        	          <span class="btn_blue_l">
	        	              <a href="javascript:document.listForm.reset();"><spring:message code="button.reset1" /></a>
	                          <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
	                      </span>
	                  </li>
	              </ul>
	        	</div>
        	</div>
        	
        	
        	
        	
        	<br>총 <c:out value="${totCnt}"/> 건
        	<!-- 등록, 엑셀 -->
        	<div id="sysbtn">
        	  <ul>
        	      <li>
        	          <span class="btn_blue_l">
        	              <a href="javascript:fn_egov_excelDown();"><spring:message code="button.excel" /></a>
                          <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                      </span>
                  </li>
              </ul>
        	</div>
        	<div id="sysbtn">
        	  <ul>
        	      <li>
        	          <span class="btn_blue_l">
        	              <a href="javascript:fn_egov_addView();"><spring:message code="button.create" /></a>
                          <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                      </span>
                  </li>
              </ul>
        	</div>
        	
        	
        	<!-- List -->
        	<div id="table">
        		<table width="100%" border="0" cellpadding="0" cellspacing="0" summary="카테고리ID, 케테고리명, 사용여부, Description, 등록자 표시하는 테이블">
        			<caption style="visibility:hidden">카테고리ID, 케테고리명, 사용여부, Description, 등록자 표시하는 테이블</caption>
        			<colgroup>
        				<col width="40"/>
        				<col width="110"/>
        				<col width="110"/>
        				<col width="80"/>
        				<col width="80"/>
        				<col width="100"/>
        			</colgroup>
        			<tr>
        				<th align="center">순번</th>
        				<th align="center"><spring:message code="title.sample.useDate" /></th>
        				<th align="center"><spring:message code="title.sample.useCode" /></th>
        				<th align="center"><spring:message code="title.sample.money" /></th>
        				<th align="center"><spring:message code="title.sample.pMoney" /></th>
        				<th align="center"><spring:message code="title.sample.processCode" /></th>
        				<th align="center"><spring:message code="title.sample.regDate" /></th>
        			</tr>
        			<c:forEach var="result" items="${resultList}" varStatus="status">
            			<tr>
            				<%-- <td align="center" class="listtd"><c:out value="${paginationInfo.totalRecordCount+1 - ((searchVO.pageIndex-1) * searchVO.pageSize + status.count)}"/></td> --%>
            				<td align="left" class="listtd"><c:out value="${result.rownum}"/>&nbsp;</td>
            				<td align="center" class="listtd"><c:out value="${result.useDate}"/></td>
            				<td align="left" class="listtd"><a href="javascript:fn_egov_select('<c:out value="${result.id}"/>')"><c:out value="${result.useCode}"/></a>&nbsp;</td>
            				<td align="center" class="listtd"><c:out value="${result.money}"/>&nbsp;</td>
            				<td align="center" class="listtd"><c:out value="${result.pMoney}"/>&nbsp;</td>
            				<td align="center" class="listtd"><c:out value="${result.processCode}"/>&nbsp;</td>
            				<td align="center" class="listtd"><c:out value="${result.regDate}"/>&nbsp;</td>
            			</tr>
        			</c:forEach>
        			<tr>
            				<td align="center" class="listtd"><spring:message code="title.sample.sum" /></td>
            				<td align="center" class="listtd"></td>
            				<td align="center" class="listtd"></td>
            				<td align="center" class="listtd">${mtotal}</td>
            				<td align="center" class="listtd">${ptotal}</td>
            				<td align="center" class="listtd"></td>
            				<td align="center" class="listtd"></td>
            			</tr>
        		</table>
        	</div>
        	<!-- /List -->
        	<%-- <div id="paging">
        		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_egov_link_page" />
        		<form:hidden path="pageIndex" />
        	</div> --%>
        	<%-- <div id="sysbtn">
        	  <ul>
        	      <li>
        	          <span class="btn_blue_l">
        	              <a href="javascript:fn_egov_addView();"><spring:message code="button.create" /></a>
                          <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                      </span>
                  </li>
              </ul>
        	</div> --%>
        </div>
    </form:form>
</body>
</html>
