/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.example.sample.service;

/**
 * @Class Name : SampleVO.java
 * @Description : SampleVO Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */
public class SampleVO extends SampleDefaultVO {

	private static final long serialVersionUID = 1L;

		private int rownum;
	
	    // 식별아이디 
	    private int id;

	    // 사용내역 uc1, uc2, uc3, uc4, uc5, uc6
	    private String useCode;

	    // 사용일 
	    private String useDate;

	    // 금액 
	    private String money;

	    // 영수증 
	    private String fileName;

	    // 처리상태 pc1, pc2, pc3, pc4, pc5
	    private String processCode;

	    // 처리일시 
	    private String pDate;

	    // 승인금액 
	    private String pMoney;

	    // 비고 
	    private String contents;
	    
	    //등록일
	    private String regDate;
	    
	    private String fileOriName;
	    
	    private String fileUrl;
	    
	    
	    
	    
	    

		public int getRownum() {
			return rownum;
		}

		public void setRownum(int rownum) {
			this.rownum = rownum;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUseCode() {
			return useCode;
		}

		public void setUseCode(String useCode) {
			this.useCode = useCode;
		}

		public String getUseDate() {
			return useDate;
		}

		public void setUseDate(String useDate) {
			this.useDate = useDate;
		}

		public String getMoney() {
			return money;
		}

		public void setMoney(String money) {
			this.money = money;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		public String getProcessCode() {
			return processCode;
		}

		public void setProcessCode(String processCode) {
			this.processCode = processCode;
		}

		public String getpDate() {
			return pDate;
		}

		public void setpDate(String pDate) {
			this.pDate = pDate;
		}

		public String getpMoney() {
			return pMoney;
		}

		public void setpMoney(String pMoney) {
			this.pMoney = pMoney;
		}

		public String getContents() {
			return contents;
		}

		public void setContents(String contents) {
			this.contents = contents;
		}

		public String getRegDate() {
			return regDate;
		}

		public void setRegDate(String regDate) {
			this.regDate = regDate;
		}

		public String getFileOriName() {
			return fileOriName;
		}

		public void setFileOriName(String fileOriName) {
			this.fileOriName = fileOriName;
		}

		public String getFileUrl() {
			return fileUrl;
		}

		public void setFileUrl(String fileUrl) {
			this.fileUrl = fileUrl;
		}
	    
	    
	    
	    
	    
	    
		
	    

	    
	}

