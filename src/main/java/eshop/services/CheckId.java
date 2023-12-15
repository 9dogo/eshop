package eshop.services;

import eshop.exceptions.IdNotNullExcpetion;
import eshop.exceptions.IdNullExcpetion;

public class CheckId extends RuntimeException {

	public void checkIdNull(Long id) {
		if (id != null)
			throw new IdNotNullExcpetion();
	}

	public void checkIdNotNull(Long id) {
		if (id == null)
			throw new IdNullExcpetion();
	}

}
