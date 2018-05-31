package org.libmgmt.utility;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public class QueryBuilderUtility {

	public static <T> Path getPropertyPath(Root<T> root, String propertyPath) {
		Path path = root;
		String[] propertyPathArr = propertyPath.split("\\.");
		for (String property : propertyPathArr) {
			path = path.get(property);
		}
		return path;
	}

}
