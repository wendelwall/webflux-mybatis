package com.wendel.page;

import com.wendel.utils.Constants;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PagingModel<T> implements Serializable{

	private int pageIndex = Constants.DEFAULT_PAGE_INDEX;

	private int pageSize = Constants.DEFAULT_PAGE_SIZE;

	private long total;

	private List<T> data;

	public <R> PagingModel<R> convert(Function<? super T, ? extends R> mapper) {

		PagingModel<R> ret = new PagingModel<>();
		ret.setPageIndex(this.getPageIndex());
		ret.setPageSize(this.getPageSize());
		ret.setTotal(this.getTotal());
		if (!CollectionUtils.isEmpty(this.getData())) {
			ret.setData(this.getData().stream()
					.map(mapper)
					.filter(Objects::nonNull)
					.collect(Collectors.toList())
			);
		}
		return ret;
	}

	public List<T> getData() {
		if (data == null) {
			return Collections.emptyList();
		} else {
			return data;
		}
	}

}
