package eshop.exceptions;

public class CheckId {

    public static void checkIdNull(Long id) {
        if (id != null)
            throw new IdNotNullExcpetion();
    }
    
    public static void checkIdNotNull(Long id) {
        if (id == null)
            throw new IdNullExcpetion();
    }
}