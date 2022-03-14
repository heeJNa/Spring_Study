package com.sist.mapper;
// 메모리할당(x) => 구현을 요청

import java.util.List;
import org.apache.ibatis.annotations.Select;
import com.sist.vo.LocationVO;

public interface LocationMapper {
	@Select("SELECT title,address,msg FROM seoul_location")
	public List<LocationVO> locationListData();
}
