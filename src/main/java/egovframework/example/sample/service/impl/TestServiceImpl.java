package egovframework.example.sample.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.example.sample.service.TestService;
import egovframework.example.sample.service.TestVo;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestServiceImpl extends EgovAbstractServiceImpl implements TestService {
	@Autowired
	TestMapper mapper;

	@Override
	public List<TestVo> list() throws Exception {
//		log.debug("{}", mapper.list());
		return mapper.list();
	}

}
