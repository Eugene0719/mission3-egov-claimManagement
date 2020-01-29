<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c"         uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form"      uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>
<%@ taglib prefix="spring"    uri="http://www.springframework.org/tags"%>
<%
  /**
  * @Class Name : egovSampleRegister.jsp
  * @Description : Sample Register 화면
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
    <c:set var="registerFlag" value="${0 eq sampleVO.id ? 'create' : 'modify'}"/>
    <title>Sample <c:if test="${registerFlag == 'create'}"><spring:message code="button.create" /></c:if>
                  <c:if test="${registerFlag == 'modify'}"><spring:message code="button.modify" /></c:if>
    </title>
    <link type="text/css" rel="stylesheet" href="<c:url value='/css/egovframework/sample.css'/>"/>
    
    <!--For Commons Validator Client Side-->
    <script type="text/javascript" src="<c:url value='/cmmn/validator.do'/>"></script>
    <validator:javascript formName="sampleVO" staticJavascript="false" xhtml="true" cdata="false"/>
    
    
    
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
    
    <script type="text/javaScript" language="javascript" defer="defer">
        <!--
        /* 글 목록 화면 function */
        function fn_egov_selectList() {
           	document.detailForm.action = "<c:url value='/egovSampleList.do'/>";
           	document.detailForm.submit();
        }
        
        /* 글 삭제 function */
        function fn_egov_delete() {
           	document.detailForm.action = "<c:url value='/deleteSample.do'/>";
           	document.detailForm.submit();
        }
        
        /* 글 등록 function */
        function fn_egov_save() {
        	frm = document.detailForm;
        	if(!validateSampleVO(frm)){
                return;
            }else{
            	frm.action = "<c:url value="${registerFlag == 'create' ? '/addSample.do' : '/updateSample.do'}"/>";
            	alert('글을 등록하시겠습니까?');
                frm.submit();
            }
        }
        
        /* 승인 처리 function */
        function fn_egov_approved() {
        	frm = document.detailForm;
        	document.detailForm.action = "<c:url value='/updateApproved.do'/>";
           	document.detailForm.submit();
        }
        
        //-->
        
         $(function() {
    $( "#testDatepicker" ).datepicker({
    	dateFormat: "yy-mm-dd"
            /* , startView: 1
            , minViewMode: 1 */
            , yearSuffix: ""
            , showButtonPanel: true
            , currentText: "이번달"
            , closeText: "선택"
            ,changeYear: true
            ,changeMonth: true
            , beforeShow : function(el,obj){
                $("#ui-datepicker-div").addClass("calendar-none");
            }
    });
});
        
    </script>
</head>
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
${sampleVO}
<form:form commandName="sampleVO" id="detailForm" name="detailForm" enctype="multipart/form-data" method="post">
<form:input type="hidden" path="id" maxlength="30" cssClass="txt"/>
    <div id="content_pop">
    	<!-- 타이틀 -->
    	<!-- <div style="display:flex;"></div> -->
    	<div id="title">
    		<ul>
    			<li><img src="<c:url value='/images/egovframework/example/title_dot.gif'/>" alt=""/>
                    <c:if test="${registerFlag == 'create'}"><spring:message code="button.create" /></c:if>
                    <c:if test="${registerFlag == 'modify'}"><spring:message code="button.modify" /></c:if>
                </li>
    		</ul>
    	</div>
    	
    	<div id="sysbtn" style="padding: 10px 10px 10px 10px;"> 
    		<ul>
    		<c:if test="${sampleVO.processCode != 'pc3' }">
    			<c:if test="${registerFlag == 'modify'}">
                    <li>
                        <span class="btn_blue_l">
                            <a href="javascript:fn_egov_approved();"><spring:message code="button.approved" /></a>
                            <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                        </span>
                    </li>
    			</c:if>
    			</c:if>
    			<c:if test="${sampleVO.processCode != 'pc3' }">
    			<li>
                    <span class="btn_blue_l">
                        <a href="javascript:fn_egov_save();">
                            <c:if test="${registerFlag == 'create'}"><spring:message code="button.create" /></c:if>
                            <c:if test="${registerFlag == 'modify'}"><spring:message code="button.submit" /></c:if>
                        </a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
                </c:if>
                <c:if test="${sampleVO.processCode == 'pc3' }">
    			
    			</c:if>
    			<c:if test="${sampleVO.processCode != 'pc3' }">
    			<c:if test="${registerFlag == 'modify'}">
                    <li>
                        <span class="btn_blue_l">
                            <a href="javascript:fn_egov_delete();"><spring:message code="button.delete" /></a>
                            <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                        </span>
                    </li>
    			</c:if>
    			</c:if>
    			<li>
                    <span class="btn_blue_l">
                        <a href="javascript:fn_egov_selectList();"><spring:message code="button.list" /></a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li>
    			<%-- <li>
                    <span class="btn_blue_l">
                        <a href="javascript:document.detailForm.reset();"><spring:message code="button.reset" /></a>
                        <img src="<c:url value='/images/egovframework/example/btn_bg_r.gif'/>" style="margin-left:6px;" alt=""/>
                    </span>
                </li> --%>
            </ul>
    	</div>
    	<!-- // 타이틀 -->
    	<div id="table">
    	<table width="50%" border="1" cellpadding="0" cellspacing="0" style="bordercolor:#D3E2EC; bordercolordark:#FFFFFF; BORDER-TOP:#C2D0DB 2px solid; BORDER-LEFT:#ffffff 1px solid; BORDER-RIGHT:#ffffff 1px solid; BORDER-BOTTOM:#C2D0DB 1px solid; border-collapse: collapse;">
    		<colgroup>
    			<col width="150"/>
    			<col width="?"/>
    		</colgroup>
    		<%-- <c:if test="${registerFlag == 'modify'}">
        		<tr>
        			<td class="tbtd_caption"><label for="usecode"><spring:message code="title.sample.useCode" /></label></td>
        			<td class="tbtd_content">
        				<form:input path="usecode" cssClass="essentiality" maxlength="10" readonly="true" />
        			</td>
        		</tr>
    		</c:if> --%>
    		
    		<h1 style="color:black;"><spring:message code="title.sample.claim" /></h1>
    		
    		<!-- 사용내역  !!!!!!!밸류값 입력!!!!!!! -->
    		<tr>
    			<td class="tbtd_caption"><label for="useCode"><spring:message code="title.sample.useCode" /></label></td>
    			<td class="tbtd_content">
    				<form:select path="useCode" cssClass="use">
    					<form:option value="uc1" label="식대(야근)" />
    					<form:option value="uc2" label="택시비(야근)" />
    					<form:option value="uc3" label="택시비(회식)" />
    					<form:option value="uc4" label="사무용품구매" />
    					<form:option value="uc5" label="교육비" />
    					<form:option value="uc6" label="접대비" />
    				</form:select>
    			</td>
    		</tr>
    		
    		<!-- 사용일 -->
    		<tr>
    			<td class="tbtd_caption"><label for="useDate"><spring:message code="title.sample.useDate" /></label></td>
    			<td class="tbtd_content">
    				<form:input path="useDate" maxlength="30" cssClass="txt" id="testDatepicker"/>
    				&nbsp;<form:errors path="useDate" />
    			</td>
    		</tr>
    		
    		<!-- 금액 -->
    		<tr>
    			<td class="tbtd_caption"><label for="money"><spring:message code="title.sample.money" /></label></td>
    			<td class="tbtd_content">
    			<%-- <input type="text" value="<c:out value='${sampleVO.money}'/>"/> --%>
    				<form:input path="money" maxlength="30" cssClass="txt"/>
    				&nbsp;<form:errors path="money" />
    			</td>
    			<%-- <td class="tbtd_content">
    				<form:textarea path="money" rows="5" cols="58" />&nbsp;<form:errors path="money" />
                </td> --%>
    		</tr>
    		
    		<!-- 영수증 -->
    		<tr>
    			<td class="tbtd_caption"><label for="fileName"><spring:message code="title.sample.fileName" /></label></td>
    			<td class="tbtd_content">
    				<c:if test="${registerFlag == 'modify'}">
    					<%-- <form:input path="fileName" maxlength="30" cssClass="txt"/> --%>
	    				<img src="/0116/upload/<c:out value='${sampleVO.fileName}'/>" width="30%" readonly="true"/>
	    				<c:out value="${sampleVO.fileOriName}" />
	    				<input type="file" name="files"/>
	    			</c:if>
    				<c:if test="${registerFlag != 'modify'}">
	    				<input type="file" name="files"/>
	    			</c:if>
                    <%-- <c:if test="${registerFlag == 'modify'}">
        				<form:input path="filename" maxlength="10" cssClass="essentiality" readonly="true" />
        				&nbsp;<form:errors path="filename" /></td>
                    </c:if>
                    <c:if test="${registerFlag != 'modify'}">
        				<form:input path="filename" maxlength="10" cssClass="txt"  />
        				&nbsp;<form:errors path="filename" /></td>
                    </c:if> --%>
    		</tr>
    		
    		
    	</table>
      </div>
      
      
      
      
      
      
      
      <c:if test="${registerFlag == 'modify'}">
      <!-- 처리내역 -->
      <div id="table">
    	<table width="100%" border="1" cellpadding="0" cellspacing="0" style="bordercolor:#D3E2EC; bordercolordark:#FFFFFF; BORDER-TOP:#C2D0DB 2px solid; BORDER-LEFT:#ffffff 1px solid; BORDER-RIGHT:#ffffff 1px solid; BORDER-BOTTOM:#C2D0DB 1px solid; border-collapse: collapse;">
    		<h1 style="color:black;"><spring:message code="title.sample.status" /></h1>
    		<colgroup>
    			<col width="150"/>
    			<col width="?"/>
    		</colgroup>
    		<%-- <c:if test="${registerFlag == 'modify'}">
        		<tr>
        			<td class="tbtd_caption"><label for="usecode"><spring:message code="title.sample.processCode" /></label></td>
        			<td class="tbtd_content">
        				<form:select path="usecode" cssClass="use">
	    					<form:option value="" label="접수" />
	    					<form:option value="" label="승인" />
	    					<form:option value="" label="지급완료" />
	    					<form:option value="" label="반려" />
	    				</form:select>
        			</td>
        		</tr>
    		</c:if> --%>
    		
    		
    		
    		<!-- 처리상태 -->
    		<tr>
    			<td class="tbtd_caption"><label for="processCode"><spring:message code="title.sample.processCode" /></label></td>
    			<td class="tbtd_content">
    				<form:select path="processCode" cssClass="use">
    					<form:option value="pc2" label="접수" />
    					<form:option value="pc3" label="승인" />
    					<form:option value="pc4" label="지급완료" />
    					<form:option value="pc5" label="반려" />
    				</form:select>
    			</td>
    		</tr>
    		
    		<!-- 처리일시 -->
    		<tr>
    			<td class="tbtd_caption"><label for="pDate"><spring:message code="title.sample.pDate" /></label></td>
    			<td class="tbtd_content">
    				<form:input path="pDate" maxlength="30" cssClass="essentiality" label="자동으로 입력됩니다" readonly="true"/>
    				&nbsp;<form:errors path="pDate" />
    			</td>
    		</tr>
    		
    		<!-- 승인금액 -->
    		<tr>
    			<td class="tbtd_caption"><label for="pMoney"><spring:message code="title.sample.pMoney" /></label></td>
    			<td class="tbtd_content">
    				<form:input path="pMoney" maxlength="30" cssClass="txt" />
    				&nbsp;<form:errors path="pMoney" />
    			</td>
    			<%-- <td class="tbtd_content">
    				<form:textarea path="money" rows="5" cols="58" />&nbsp;<form:errors path="money" />
                </td> --%>
    		</tr>
    		
    		<!-- 비고 -->
    		<tr>
    			<td class="tbtd_caption"><label for="contents"><spring:message code="title.sample.contents" /></label></td>
    			<td class="tbtd_content">
		    		<form:textarea path="contents" rows="5" cols="58" />&nbsp;<form:errors path="contents" />
		    	</td>
                    <%-- <c:if test="${registerFlag == 'modify'}">
        				<form:input path="filename" maxlength="10" cssClass="essentiality" readonly="true" />
        				&nbsp;<form:errors path="filename" /></td>
                    </c:if>
                    <c:if test="${registerFlag != 'modify'}">
        				<form:input path="filename" maxlength="10" cssClass="txt"  />
        				&nbsp;<form:errors path="filename" /></td>
                    </c:if> --%>
    		</tr>
    	</table>
      </div>
      </c:if>
    </div>
    <!-- 검색조건 유지 -->
    <input type="hidden" name="searchCondition" value="<c:out value='${searchVO.searchCondition}'/>"/>
    <input type="hidden" name="searchKeyword" value="<c:out value='${searchVO.searchKeyword}'/>"/>
    <input type="hidden" name="pageIndex" value="<c:out value='${searchVO.pageIndex}'/>"/>
</form:form>
</body>
</html>