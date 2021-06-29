package me.sample.uhaha.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import me.sample.uhaha.web.stock.MytableVo;

@Mapper
public interface TestMapper {

	List<MytableVo> selectAllMytable();
	int insertMytable(MytableVo mytableVo);

}
