package com.can.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @author: LCN
 * @date: 2018-05-25 16:43
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {

	/** 分页的页数 */
	private int pageNo;

	/** 页条数*/
	private int pageSize;

	/** 总条数 */
	private int totalCount;

	/** 分页结果 */
	protected List<T> result;

	{
		pageNo = 1;
		pageSize = 10;
		totalCount = 0;
	}

}
