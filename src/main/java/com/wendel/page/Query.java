package com.wendel.page;

import java.util.List;

/**
 * @author ：wendel
 * @description ：
 * @copyright ：	Copyright 2019 riskeys Corporation. All rights reserved.
 * @create ：2019/11/6 19:43
 */
@FunctionalInterface
public interface Query<T> {

	public List<T> doQuery();
}
