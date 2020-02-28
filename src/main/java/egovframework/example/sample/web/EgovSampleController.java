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
package egovframework.example.sample.web;

import java.io.File;
import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springmodules.validation.commons.DefaultBeanValidator;

import egovframework.example.sample.service.EgovSampleService;
import egovframework.example.sample.service.SampleDefaultVO;
import egovframework.example.sample.service.SampleVO;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
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

@Controller
public class EgovSampleController {

	/** EgovSampleService */
	@Resource(name = "sampleService")
	private EgovSampleService sampleService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** Validator */
	@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;
	
	@ResponseBody
	@RequestMapping(value = "/excelUploadAjax.do", method = RequestMethod.POST)
		public ModelAndView excelUploadAjax(MultipartFile testFile, MultipartHttpServletRequest request) throws  Exception{
		
		System.out.println("업로드 진행");
		
		MultipartFile excelFile = request.getFile("excelFile");
		
		if(excelFile == null || excelFile.isEmpty()) {
			throw new RuntimeException("엑셀파일을 선택해 주세요");
		}
		
		File destFile = new File("C:\\upload\\"+excelFile.getOriginalFilename());
		
		try {
			//내가 설정한 위치에 내가 올린 파일을 만들고 
			excelFile.transferTo(destFile);
		}catch(Exception e) {
			throw new RuntimeException(e.getMessage(),e);
		}
		
		//업로드를 진행하고 다시 지우기
		sampleService.excelUpload(destFile);
		
		destFile.delete();
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/egovSampleList.do");
		
		return view;
	}
	
	
	
	
	@RequestMapping(value = "/excelDown2.do")
	   public void excelDown2(@RequestParam("id") int id, HttpServletResponse response, @ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		System.out.println("엑셀다운2 실행");
		
	      List<SampleVO> list = sampleService.excelDown2(id);
	      
	      // 워크북 생성
	      Workbook wb = new HSSFWorkbook();
	      Sheet sheet = wb.createSheet("detail");
	      Row row = null;
	      Cell cell = null;
	      int rowNo = 0;

	      // 테이블 헤더용 스타일
	      CellStyle headStyle = wb.createCellStyle();
	      // 가는 경계선을 가집니다.
	      headStyle.setBorderTop(BorderStyle.THIN);
	      headStyle.setBorderBottom(BorderStyle.THIN);
	      headStyle.setBorderLeft(BorderStyle.THIN);
	      headStyle.setBorderRight(BorderStyle.THIN);

	      // 배경색은 노란색입니다.
	      headStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
	      headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	      // 데이터는 가운데 정렬합니다.
	      headStyle.setAlignment(HorizontalAlignment.CENTER);

	      // 데이터용 경계 스타일 테두리만 지정
	      CellStyle bodyStyle = wb.createCellStyle();
	      bodyStyle.setBorderTop(BorderStyle.THIN);
	      bodyStyle.setBorderBottom(BorderStyle.THIN);
	      bodyStyle.setBorderLeft(BorderStyle.THIN);
	      bodyStyle.setBorderRight(BorderStyle.THIN);

	      // 헤더 생성
	      row = sheet.createRow(rowNo++);
	      cell = row.createCell(0);
	      cell.setCellStyle(headStyle);
	      cell.setCellValue("사용내역");
	      cell = row.createCell(1);
	      cell.setCellStyle(headStyle);
	      cell.setCellValue("사용일");
	      cell = row.createCell(2);
	      cell.setCellStyle(headStyle);
	      cell.setCellValue("사용금액");
	      cell = row.createCell(3);
	      cell.setCellStyle(headStyle);
	      cell.setCellValue("영수증파일이름");
	      cell = row.createCell(4);
	      cell.setCellStyle(headStyle);
	      cell.setCellValue("처리상태");
	      cell = row.createCell(5);
	      cell.setCellStyle(headStyle);
	      cell.setCellValue("처리일시");
	      cell = row.createCell(6);
	      cell.setCellStyle(headStyle);
	      cell.setCellValue("비고");
	     

	      // 데이터 부분 생성
	      for(SampleVO vo : list) {  
	         row = sheet.createRow(rowNo++);
	         cell = row.createCell(0);
	         cell.setCellStyle(bodyStyle);
	         cell.setCellValue(vo.getUseCode());
	         cell = row.createCell(1);
	         cell.setCellStyle(bodyStyle);
	         cell.setCellValue(vo.getUseDate());
	         cell = row.createCell(2);
	         cell.setCellStyle(bodyStyle);
	         cell.setCellValue(vo.getMoney());
	         cell = row.createCell(3);
	         cell.setCellStyle(bodyStyle);
	         cell.setCellValue(vo.getFileOriName());
	         cell = row.createCell(4);
	         cell.setCellStyle(bodyStyle);
	         cell.setCellValue(vo.getProcessCode());
	         cell = row.createCell(5);
	         cell.setCellStyle(bodyStyle);
	         cell.setCellValue(vo.getpDate());
	         cell = row.createCell(6);
	         cell.setCellStyle(bodyStyle);
	         cell.setCellValue(vo.getContents());
	      }

	      // 컨텐츠 타입과 파일명 지정
	      response.setContentType("ms-vnd/excel");
	      response.setHeader("Content-Disposition", "attachment;filename=ListSample.xls");

	      // 엑셀 출력
	      wb.write(response.getOutputStream());
	      wb.close();

	   }
	
	
	
	@RequestMapping(value = "/excelDown.do")
	   public void excelDown(HttpServletResponse response, @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {

	      // 게시판 목록조회
	      /** EgovPropertyService.sample */
	    /*  searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
	      searchVO.setPageSize(propertiesService.getInt("pageSize"));

	      *//** pageing setting *//*
	      PaginationInfo paginationInfo = new PaginationInfo();
	      paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
	      paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
	      paginationInfo.setPageSize(searchVO.getPageSize());*/

	      /*searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	      searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
	      searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());*/
	      
	      //List<?> sampleList = sampleService.selectSampleList(searchVO);
	      List<SampleVO> list = sampleService.excelDown(searchVO);
	      
	      // 워크북 생성
	      Workbook wb = new HSSFWorkbook();
	      Sheet sheet = wb.createSheet("게시판");
	      Row row = null;
	      Cell cell = null;
	      int rowNo = 0;

	      // 테이블 헤더용 스타일
	      CellStyle headStyle = wb.createCellStyle();
	      // 가는 경계선을 가집니다.
	      headStyle.setBorderTop(BorderStyle.THIN);
	      headStyle.setBorderBottom(BorderStyle.THIN);
	      headStyle.setBorderLeft(BorderStyle.THIN);
	      headStyle.setBorderRight(BorderStyle.THIN);

	      // 배경색은 노란색입니다.
	      headStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
	      headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

	      // 데이터는 가운데 정렬합니다.
	      headStyle.setAlignment(HorizontalAlignment.CENTER);

	      // 데이터용 경계 스타일 테두리만 지정
	      CellStyle bodyStyle = wb.createCellStyle();
	      bodyStyle.setBorderTop(BorderStyle.THIN);
	      bodyStyle.setBorderBottom(BorderStyle.THIN);
	      bodyStyle.setBorderLeft(BorderStyle.THIN);
	      bodyStyle.setBorderRight(BorderStyle.THIN);

	      // 헤더 생성
	      row = sheet.createRow(rowNo++);
	      cell = row.createCell(0);
	      cell.setCellStyle(headStyle);
	      cell.setCellValue("순번");
	      cell = row.createCell(1);
	      cell.setCellStyle(headStyle);
	      cell.setCellValue("사용일");
	      cell = row.createCell(2);
	      cell.setCellStyle(headStyle);
	      cell.setCellValue("사용내역");
	      cell = row.createCell(3);
	      cell.setCellStyle(headStyle);
	      cell.setCellValue("사용금액");
	      cell = row.createCell(4);
	      cell.setCellStyle(headStyle);
	      cell.setCellValue("승인금액");
	      cell = row.createCell(5);
	      cell.setCellStyle(headStyle);
	      cell.setCellValue("처리상태");
	      cell = row.createCell(6);
	      cell.setCellStyle(headStyle);
	      cell.setCellValue("등록일");
	     

	      // 데이터 부분 생성
	      for(SampleVO vo : list) {  
	         row = sheet.createRow(rowNo++);
	         cell = row.createCell(0);
	         cell.setCellStyle(bodyStyle);
	         cell.setCellValue(vo.getRownum());
	         cell = row.createCell(1);
	         cell.setCellStyle(bodyStyle);
	         cell.setCellValue(vo.getUseDate());
	         cell = row.createCell(2);
	         cell.setCellStyle(bodyStyle);
	         cell.setCellValue(vo.getUseCode());
	         cell = row.createCell(3);
	         cell.setCellStyle(bodyStyle);
	         cell.setCellValue(vo.getMoney());
	         cell = row.createCell(4);
	         cell.setCellStyle(bodyStyle);
	         cell.setCellValue(vo.getpMoney());
	         cell = row.createCell(5);
	         cell.setCellStyle(bodyStyle);
	         cell.setCellValue(vo.getProcessCode());
	         cell = row.createCell(6);
	         cell.setCellStyle(bodyStyle);
	         cell.setCellValue(vo.getRegDate());
	      }

	      // 컨텐츠 타입과 파일명 지정
	      response.setContentType("ms-vnd/excel");
	      response.setHeader("Content-Disposition", "attachment;filename=ListSample.xls");

	      // 엑셀 출력
	      wb.write(response.getOutputStream());
	      wb.close();

	   }
	
	

	/**
	 * 글 목록을 조회한다. (pageing)
	 * @param searchVO - 조회할 정보가 담긴 SampleDefaultVO
	 * @param model
	 * @return "egovSampleList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/egovSampleList.do")
	public String selectSampleList(@ModelAttribute("searchVO") SampleDefaultVO searchVO, ModelMap model) throws Exception {

		/** EgovPropertyService.sample */
		/*searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));*/

		//** pageing setting *//*
		/*PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());*/
		/*paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());*/

		/*searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());*/

		List<SampleVO> sampleList = sampleService.selectSampleList(searchVO);
		
		int mtotal = 0;
		int ptotal = 0;
		String mo;
		String pm;
		String money = null;
		String pmoney = null;
		/*for(int i = 0; i<sampleList.size(); i++) {
			money = sampleList.get(i).getMoney();
			if(money != null) {
				mtotal += Integer.parseInt(money);
			}
			pmoney = sampleList.get(i).getpMoney();
			if(pmoney != null) {
				ptotal += Integer.parseInt(pmoney);
			}
		}*/
		
		DecimalFormat dc = new DecimalFormat("###,###,###,###");
		
		for(int i = 0; i < sampleList.size(); i++) {
			
	         String cheang = sampleList.get(i).getMoney();
	         if(cheang != null) {
	        	mo = cheang.replace(",","");
	        	mtotal += Integer.parseInt(mo);
	        	money = dc.format(mtotal);
	        	sampleList.get(i).setMoney(sampleList.get(i).getMoney());
	         }
	         
	         String cheang1 = sampleList.get(i).getpMoney();
	         if(cheang1 != null) {
	            pm = cheang1.replace(",","");
	            ptotal += Integer.parseInt(pm);
	            pmoney = dc.format(ptotal);
	            sampleList.get(i).setpMoney(sampleList.get(i).getpMoney());
	         }
	      }
		
		model.addAttribute("resultList", sampleList);
		model.addAttribute("mtotal", money);
		model.addAttribute("ptotal", pmoney);

		/*System.out.println("useCode 검색ㄱㄱㄱㄱㄱㄱ"+searchVO.getSearchUseCode());
		System.out.println("달력검색ㅇㅇㅇㅇㅇㅇ"+searchVO.getSearchRegDate());*/
		
		int totCnt = sampleService.selectSampleListTotCnt(searchVO);
		/*paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);*/
		model.addAttribute("totCnt", totCnt);

		return "sample/egovSampleList";
	}

	/**
	 * 글 등록 화면을 조회한다.
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addSample1.do", method = RequestMethod.POST)
	public String addSampleView(@ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		model.addAttribute("sampleVO", new SampleVO());
		return "sample/egovSampleRegister";
	}

	/**
	 * 글을 등록한다.
	 * @param sampleVO - 등록할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping(value = "/addSample.do", method = RequestMethod.POST)
	public String addSample(@ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO, BindingResult bindingResult, Model model, SessionStatus status, @RequestPart MultipartFile files)
			throws Exception {
		
		String sourceFileName = files.getOriginalFilename(); 
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
        File destinationFile;
        String destinationFileName;
        String fileUrl = "C:\\eGovFrameDev-3.8.0-64bit\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\0116\\upload\\";
        /*String fileUrl = "C:\\eGovFrameDev-3.8.0-64bit\\workspace\\sample\\src\\main\\webapp\\upload\\";*/ 
        
        if(!files.isEmpty()) {
        do { 
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension; 
            destinationFile = new File(destinationFileName); 
            /*System.out.println("file경로"+destinationFile);*/
            sampleVO.setFileUrl(fileUrl+destinationFile);
            sampleVO.setFileOriName(sourceFileName);
            sampleVO.setFileName(destinationFileName);
        } while (destinationFile.exists());
        
        destinationFile.getParentFile().mkdirs(); 
        files.transferTo(destinationFile);
        
		// Server-Side Validation
		beanValidator.validate(sampleVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sampleVO", sampleVO);
			return "sample/egovSampleRegister";
		}
        }
		sampleService.insertSample(sampleVO);
		status.setComplete();
		
		
		return "forward:/egovSampleList.do";
	}

	/**
	 * 글 수정화면을 조회한다.
	 * @param id - 수정할 글 id
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param model
	 * @return "egovSampleRegister"
	 * @exception Exception
	 */
	
	
	
	
	@RequestMapping("/updateSampleView.do")
	public String updateSampleView(@RequestParam("selectedId") int id, @ModelAttribute("searchVO") SampleDefaultVO searchVO, Model model) throws Exception {
		SampleVO sampleVO = new SampleVO();
		sampleVO.setId(id);
		// 변수명은 CoC 에 따라 sampleVO
		model.addAttribute(selectSample(sampleVO, searchVO));
	 
		List<SampleVO> sampleList = sampleService.selectSampleList(searchVO);
		model.addAttribute("resultList", sampleList);
		
		return "sample/egovSampleRegister";
	}

	/**
	 * 글을 조회한다.
	 * @param sampleVO - 조회할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return @ModelAttribute("sampleVO") - 조회한 정보
	 * @exception Exception
	 */
	public SampleVO selectSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO) throws Exception {
		return sampleService.selectSample(sampleVO);
	}

	/**
	 * 글을 수정한다.
	 * @param sampleVO - 수정할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/updateSample.do")
	public String updateSample( @ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO, BindingResult bindingResult, Model model, SessionStatus status, @RequestPart MultipartFile files)
			throws Exception {
		
		String sourceFileName = files.getOriginalFilename(); 
        String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase(); 
        File destinationFile;
        String destinationFileName;
        String fileUrl = "C:\\eGovFrameDev-3.8.0-64bit\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\0116\\upload\\";
        /*String fileUrl = "C:\\eGovFrameDev-3.8.0-64bit\\workspace\\sample\\src\\main\\webapp\\upload\\";*/ 
        
        if(!files.isEmpty()) {
        do { 
            destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension; 
            destinationFile = new File(fileUrl + destinationFileName); 
            System.out.println("file경로"+destinationFile);
            sampleVO.setFileUrl(fileUrl+destinationFile);
            sampleVO.setFileOriName(sourceFileName);
            sampleVO.setFileName(destinationFileName);
        } while (destinationFile.exists());
        
        destinationFile.getParentFile().mkdirs(); 
        files.transferTo(destinationFile);

        }
        
		beanValidator.validate(sampleVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sampleVO", sampleVO);
			return "sample/egovSampleRegister";
		}
			
		sampleService.updateSample(sampleVO);
		
		status.setComplete();
		System.out.println("수정할때 아이디값 "+sampleVO.getId());
		return "forward:/egovSampleList.do";
	}
	
		
		@RequestMapping("/updateApproved.do")
	public String updateApproved( @ModelAttribute("searchVO") SampleDefaultVO searchVO, SampleVO sampleVO, BindingResult bindingResult, Model model, SessionStatus status)
			throws Exception {

		beanValidator.validate(sampleVO, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("sampleVO", sampleVO);
			return "sample/egovSampleRegister";
		}
			
		sampleService.updateApproved(sampleVO);
		
		status.setComplete();
		return "forward:/egovSampleList.do";
	}

	/**
	 * 글을 삭제한다.
	 * @param sampleVO - 삭제할 정보가 담긴 VO
	 * @param searchVO - 목록 조회조건 정보가 담긴 VO
	 * @param status
	 * @return "forward:/egovSampleList.do"
	 * @exception Exception
	 */
	@RequestMapping("/deleteSample.do")
	public String deleteSample(SampleVO sampleVO, @ModelAttribute("searchVO") SampleDefaultVO searchVO, SessionStatus status) throws Exception {
		sampleService.deleteSample(sampleVO);
		status.setComplete();
		return "forward:/egovSampleList.do";
	}

}
