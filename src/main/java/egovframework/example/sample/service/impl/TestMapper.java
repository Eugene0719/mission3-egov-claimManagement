package egovframework.example.sample.service.impl;

import java.util.List;

import egovframework.example.sample.service.TestVo;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface TestMapper {
	List<TestVo> list() throws Exception;
}
