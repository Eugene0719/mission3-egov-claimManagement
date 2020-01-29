package egovframework.example.sample.service;

import java.sql.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TestVo {
	private int MNO;
	private String MNAME;
	private Date MCREATE;
}
